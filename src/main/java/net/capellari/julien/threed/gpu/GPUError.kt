package net.capellari.julien.threed.gpu

sealed class GPUError(message: String) : Exception(message)

class ShaderError(message: String) : GPUError(message)
class ProgramError(message: String) : GPUError(message)