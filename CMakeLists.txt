#
# Options générales
#
cmake_minimum_required(VERSION 3.4)
project(Android3d)

set(CMAKE_VERBOSE_MAKEFILE 1) # Use 1 for debugging, 0 for release
set(CMAKE_CXX17_EXTENSION_COMPILE_OPTION "-std=c++17")

# Options de compilation
set(CMAKE_C_STANDARD 11)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -g3")

# Dépendances
# - Assimp
#add_library(assimp SHARED IMPORTED)
#set_target_properties(assimp PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libassimpd.so)

# - NDK
find_library(log-lib log)
find_library(gles-lib GLESv3)

# JNI
include_directories(src/main/jni)
#include_directories(libs/include)

add_library(android3d SHARED
        # - jnitools
        src/main/jni/jnitools/class.cpp
        src/main/jni/jnitools/convert.cpp
        src/main/jni/jnitools/outils.cpp

        # - math
        src/main/jni/math/array.cpp
        src/main/jni/math/matrix.cpp
        src/main/jni/math/outils.cpp
        src/main/jni/math/vector.cpp

        # - gpu
        src/main/jni/gpu/attribute.cpp
        src/main/jni/gpu/buffer.cpp
        src/main/jni/gpu/error.cpp
        src/main/jni/gpu/program.cpp
        src/main/jni/gpu/shader.cpp
)

target_link_libraries(android3d ${log-lib} ${gles-lib}) # assimp)