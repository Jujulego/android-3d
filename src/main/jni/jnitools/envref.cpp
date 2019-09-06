//
// Created by julien on 06/09/2019.
//
#include <android/log.h>

#include "envref.h"

#define LOG_ERROR(...) __android_log_print(ANDROID_LOG_ERROR, "jni::envref", __VA_ARGS__)

using namespace jni;

// Constructor
envref::envref(JNIEnv* env): m_version(env->GetVersion()) {
    env->GetJavaVM(&m_vm);
}

// Operators
envref::operator JNIEnv* () const {
    return jenv();
}

JNIEnv& envref::operator * () const {
    return *jenv();
}

JNIEnv* envref::operator -> () const {
    return jenv();
}

// Methods
JNIEnv* envref::jenv() const {
    JNIEnv* env;
    jint res = m_vm->GetEnv((void**) &env, m_version);

    if (res == JNI_EDETACHED) {
        LOG_ERROR("detached environment");
    } else if (res == JNI_EVERSION) {
        LOG_ERROR("invalid version");
    }

    return env;
}