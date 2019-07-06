//
// Created by julien on 20/04/2019.
//

#include "convert.h"

#include <jni.h>
#include <string>

using namespace jni;

// Outils
template<> std::string jni::fromJava<std::string>(JNIEnv* env, jstring jstr) {
    if (!jstr) return "";

    char const* str = env->GetStringUTFChars(jstr, nullptr);
    std::string ret(str);

    env->ReleaseStringUTFChars(jstr, str);

    return ret;
}
template<> jstring jni::toJava<std::string,jstring>(JNIEnv* env, std::string const& obj) {
    return env->NewStringUTF(obj.data());
}