package com.santridev.newmovieapp.base.abstraction

import com.santridev.newmovieapp.data.dto.MovieItemDTO

interface Mapper<in I, out O> {
    fun map(input: I): O
}
