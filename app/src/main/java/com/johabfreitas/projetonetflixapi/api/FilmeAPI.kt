package com.johabfreitas.projetonetflixapi.api

import com.johabfreitas.projetonetflixapi.model.FilmeRecente
import com.johabfreitas.projetonetflixapi.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET

interface FilmeAPI {

    @GET("movie/latest?api_key=${RetrofitService.API_KEY}")
    suspend fun recuperarFilmeRecente() : Response<FilmeRecente>

    @GET("movie/popular?api_key=${RetrofitService.API_KEY}")
    suspend fun recuperarFilmesPopulares() : Response<FilmeResposta>



}