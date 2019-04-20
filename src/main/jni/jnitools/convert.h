//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>

#include "localref.h"

namespace jni {
    // Outils
    template<class R,class J=jobject> R fromJava(JNIEnv* env, J jobj);
    template<class R,class J=jobject> localref<J> toJava(JNIEnv* env, R const& obj);
}