//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <string>
#include <type_traits>

#include "localref.h"

namespace jni {
    // Metafunction
    template<class T> struct is_string: std::is_base_of<std::string,T> {};
    template<class T> struct java_type: std::conditional<is_string<T>::value,jstring,jobject> {};

    // Outils
    template<class R,class J=jobject> R fromJava(JNIEnv* env, J jobj);
    template<class R,class J=typename java_type<R>::type> J toJava(JNIEnv* env, R const& obj);
}