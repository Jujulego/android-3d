//
// Created by julien on 06/07/2019.
//
#include "outils.h"

using namespace jni;

// Outils
jint jni::javaThrow(JNIEnv* env, std::string const& cls, std::string const& msg) {
    localref<jclass> jcls = findClass(env, cls);
    return env->ThrowNew(jcls, msg.data());
}