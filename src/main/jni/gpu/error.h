//
// Created by julien on 01/09/2019.
//
#pragma once

#include <string>

#include "jnitools.h"

namespace gpu {
    // Class
    class GPUError: public std::exception {
    private:
        // Attributes
        std::string m_error;

    protected:
        // Methods
        virtual std::string javaName() const;

    public:
        // Constructor
        GPUError(std::string const& error);

        // Methods
        void javaThrow(JNIEnv* env) const;
        const char* what() const noexcept override;
    };
}