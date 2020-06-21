package com.github.charapex.data;

import com.github.charapex.presentation.model.Legends;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MozambiqueHere {
    @GET("/b/5ecb8dd9e91d1e45d110fa37")
    Call<List<Legends>> getLegendsList();
}
