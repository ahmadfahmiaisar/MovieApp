package com.santridev.newmovieapp.base.abstraction

interface UseCase<Params, out T> {
    suspend operator fun invoke(params: Params): T

    object None
}
