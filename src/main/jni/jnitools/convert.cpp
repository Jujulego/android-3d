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
template<> localref<jstring> jni::toJava(JNIEnv *env, const std::string& obj) {
    return localref(env, env->NewStringUTF(obj.data()));
}