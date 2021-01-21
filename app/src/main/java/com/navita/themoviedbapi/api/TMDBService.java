package com.navita.themoviedbapi.api;

import com.navita.themoviedbapi.model.TMDB;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDBService {
   @GET("movie/popular?api_key=adf5445d278a1cd9a188ce9f52141837&language=en-US&page=1")
  Call<List<TMDB>> recuperarLista();
}
