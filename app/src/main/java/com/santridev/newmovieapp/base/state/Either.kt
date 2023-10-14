package com.santridev.newmovieapp.base.state

sealed class Either<A, B> {
    class Success<A, B>(val data: B) : Either<A, B>()
    class Failure<A, B>(val cause: A) : Either<A, B>()
}