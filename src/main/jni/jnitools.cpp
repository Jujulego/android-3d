//
// Created by julie on 17/04/2019.
//

#include "jnitools.h"

#include <atomic>
#include <jni.h>
#include <map>
#include <memory>
#include <string>

// Namespace
using namespace jni;

// Stockage des pointeurs
std::atomic<unsigned long> next_handle = 1UL;
std::map<unsigned long, std::weak_ptr<JNIClass>> pointers;
std::map<unsigned long, std::shared_ptr<JNIClass>> used_pointers;

// Fonctions
unsigned long register_jni(std::weak_ptr<JNIClass> const& wpt) {
    unsigned long handle = ++next_handle;
    pointers[handle] = wpt;

    return handle;
}

bool acquire(unsigned long handle) {
    std::map<unsigned long,std::weak_ptr<JNIClass>>::iterator it;

    it = pointers.find(handle);

    if (it == pointers.end()) return false;
    if (it->second.expired()) return false;

    used_pointers[handle] = it->second.lock();

    return true;
}

void dispose(unsigned long handle) {
    used_pointers.erase(handle);
}

// Classe
JNIClass::JNIClass() {
    handle = register_jni(weak_from_this());
}

JNIClass::~JNIClass() {
    pointers.erase(handle);
}

unsigned long JNIClass::get_handle() {
    return handle;
}

// Méthodes natives
extern "C" JNIEXPORT
jboolean JNICALL Java_net_capellari_julien_threed_jni_JNIClass_acquire(JNIEnv *env, jobject jthis) {
    field<long> jfld = findField<long>(env, jthis, "nativeHandle", "J");
    unsigned long handle = reinterpret_cast<unsigned long>(jfld.get());

    return jboolean(acquire(handle));
}

extern "C" JNIEXPORT
void JNICALL Java_net_capellari_julien_threed_jni_JNIClass_dispose(JNIEnv *env, jobject jthis) {
    field<long> jfld = findField<long>(env, jthis, "nativeHandle", "J");
    unsigned long handle = reinterpret_cast<unsigned long>(jfld.get());

    dispose(handle);
}

// Outils
template<> std::string jni::fromJava<std::string>(JNIEnv* env, jobject jobj) {
    if (!jobj) return "";

    char const* str = env->GetStringUTFChars((jstring) jobj, nullptr);
    std::string ret(str);

    env->ReleaseStringUTFChars((jstring) jobj, str);

    return ret;
}
template<> localref<jobject> jni::toJava(JNIEnv *env, const std::string &obj) {
    return localref(env, env->NewStringUTF(obj.data()));
}

localref<jclass> jni::findClass(JNIEnv *env, jobject jobj) {
    return localref(env, env->GetObjectClass(jobj));
}
localref<jclass> jni::findClass(JNIEnv *env, std::string const& nom) {
    return localref(env, env->FindClass(nom.data()));
}

jfieldID jni::findField(JNIEnv* env, jclass jcls, std::string const &nom, std::string const &type) {
    return env->GetFieldID(jcls, nom.data(), type.data());
}

jmethodID jni::findMethod(JNIEnv* env, jclass jcls, std::string const &nom, std::string const &sig) {
    return env->GetMethodID(jcls, nom.data(), sig.data());
}