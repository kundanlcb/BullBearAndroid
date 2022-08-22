package com.bootninza.bullbear.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public Retrofit getRestClient() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.225.209:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
