//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <type_traits>

namespace jni {
    // Métafonctions
    /**
     * Test if O is or is derived of jobject
     * @tparam O type to test
     */
    template<class O> struct is_jobject : std::is_base_of<_jobject,typename std::remove_pointer<O>::type> {};
}