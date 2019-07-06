//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>

#include <string>

#include "field.h"
#include "localref.h"
#include "method.h"

namespace jni {
    // Outils
    // - classe
    inline localref<jclass> findClass(JNIEnv* env, jobject jobj) {
        return localref(env, env->GetObjectClass(jobj));
    }

    inline localref<jclass> findClass(JNIEnv* env, std::string const& nom) {
        return localref(env, env->FindClass(nom.data()));
    }

    // - field
    inline jfieldID findField(JNIEnv* env, jclass jcls, std::string const& nom, std::string const& type) {
        return env->GetFieldID(jcls, nom.data(), type.data());
    }

    template<class R>
    inline field<R> findField(JNIEnv* env, jobject jthis, std::string const& nom, std::string const& type) {
        localref<jclass> jcls = findClass(env, jthis);
        return field<R>(env, findField(env, jcls, nom, type), jthis);
    }

    // - method
    inline jmethodID findMethod(JNIEnv* env, jclass jcls, std::string const& nom, std::string const& sig) {
        return env->GetMethodID(jcls, nom.data(), sig.data());
    }

    template<class R> inline method<R> findMethod(JNIEnv* env, jobject jthis, std::string const& nom, std::string const& sig) {
        localref<jclass> jcls = findClass(env, jthis);
        return method<R>(env, findMethod(env, jcls, nom, sig), jthis);
    }

    // - contructeur
    template<class... Args> inline localref<jobject> construct(JNIEnv* env, jclass jcls, std::string const& sig, Args const& ... args) {
        jmethodID constructor = findMethod(env, jcls, "<init>", sig);
        return localref<jobject>(env, env->NewObject(jcls, constructor, args...));
    }

    template<class... Args> inline localref<jobject> construct(JNIEnv* env, std::string const& cls, std::string const& sig, Args const& ... args) {
        localref<jclass> jcls = findClass(env, cls);
        return localref<jobject>(env, construct(env, jcls, sig, args...));
    }

    // - throw
    jint javaThrow(JNIEnv* env, std::string const& cls, std::string const& msg);
}