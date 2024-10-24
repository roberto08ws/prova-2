package com.example.prova4;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prova4.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding view;
    private String[] cursos;
    private List<Cursos> list = new ArrayList<>();

    private interface Lista {

        @GET("/cursos")
        Call<List<Cursos>> lista();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        view = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();

        Lista lista = retrofit.create(Lista.class);

        lista.lista().enqueue(new Callback<List<Cursos>>() {
            @Override
            public void onResponse(Call<List<Cursos>> call, Response<List<Cursos>> response) {

                list = response.body();
                cursos = new String[list.size()];

            }

            @Override
            public void onFailure(Call<List<Cursos>> call, Throwable throwable) {



            }
        });

    }
}