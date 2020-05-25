package com.github.charapex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MozambiqueHere {
    @GET("/gamedata?type=legends&auth=G1AOeXgrh3Na2TYrFZKu")
    Call<List<Legends>> getLegendsList();
}
