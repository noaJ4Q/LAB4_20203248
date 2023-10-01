package com.example.contactosysensores.Services;

import com.example.contactosysensores.Objetos.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactoService {
    @GET("/api")
    Call<Root> getRoot();
}
