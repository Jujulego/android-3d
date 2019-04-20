//
// Created by julie on 20/04/2019.
//

#include "class.h"

#include <atomic>
#include <jni.h>
#include <map>
#include <memory>

#include "field.h"
#include "outils.h"

// Namespace
using namespace jni;

// Stockage des pointeurs
std::atomic<unsigned long> next_handle = 0UL;
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
JNIClass::~JNIClass() {
    pointers.erase(handle);
}

void JNIClass::register_jni(bool acq) {
    handle = ::register_jni(weak_from_this());

    if (acq) {
        acquire();
    }
}

void JNIClass::acquire() {
    assert(handle != 0);
    assert(::acquire(handle));
}

void JNIClass::dispose() {
    if (handle) {
        ::dispose(handle);
        handle = 0UL;
    }
}

jlong JNIClass::get_jhandle() {
    return static_cast<jlong>(handle);
}

// Outils
unsigned long jni::getHandle(JNIEnv* env, jobject jobj) {
    field<jlong> jfld = findField<jlong>(env, jobj, "nativeHandle", "J");
    return static_cast<unsigned long>(jfld.get());
}

std::shared_ptr<JNIClass> jni::_fromHandle(jlong handle) {
    std::map<unsigned long,std::shared_ptr<JNIClass>>::iterator it;

    it = used_pointers.find(static_cast<unsigned long>(handle));
    if (it == used_pointers.end()) return std::shared_ptr<JNIClass>();

    return it->second;
}

// Méthodes natives
extern "C" JNIEXPORT
jboolean JNICALL Java_net_capellari_julien_threed_jni_JNIClass_acquire(JNIEnv *env, jobject jthis) {
    return jboolean(acquire(getHandle(env, jthis)));
}

extern "C" JNIEXPORT
void JNICALL Java_net_capellari_julien_threed_jni_JNIClass_dispose(JNIEnv *env, jobject jthis) {
    dispose(getHandle(env, jthis));
}