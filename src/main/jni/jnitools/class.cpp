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
    field<jlong> jfld = findField<jlong>(env, jthis, "nativeHandle", "J");
    auto handle = static_cast<unsigned long>(jfld.get());

    return jboolean(acquire(handle));
}

extern "C" JNIEXPORT
void JNICALL Java_net_capellari_julien_threed_jni_JNIClass_dispose(JNIEnv *env, jobject jthis) {
    field<jlong> jfld = findField<jlong>(env, jthis, "nativeHandle", "J");
    auto handle = static_cast<unsigned long>(jfld.get());

    dispose(handle);
}