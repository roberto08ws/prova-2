package com.example.prova4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prova4.databinding.ActivityTelaRankingBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class TelaRanking extends AppCompatActivity {

    private ActivityTelaRankingBinding view;

    private List<Ranking> list = new ArrayList<>();
    private int id = 0;

    private interface Lista {

        @GET("/ranking-top10/{id}")
        Call<List<Ranking>> lista(@Path("id") int id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        view = ActivityTelaRankingBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        view.recyclerView.setLayoutManager(new LinearLayoutManager(TelaRanking.this));
        view.recyclerView.setHasFixedSize(true);

        Animation animation2 = new AlphaAnimation(1, 0);
        animation2.setDuration(4000);

        view.viewHarmonico.setAnimation(animation2);
        view.viewHarmonico.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();

        Lista lista = retrofit.create(Lista.class);

        SharedPreferences cache = getSharedPreferences("cadastro", MODE_PRIVATE);

        id = cache.getInt("id", 0);

        lista.lista(id).enqueue(new Callback<List<Ranking>>() {
            @Override
            public void onResponse(Call<List<Ranking>> call, Response<List<Ranking>> response) {

                if (response.isSuccessful()) {

                    list = response.body();


                }

            }

            @Override
            public void onFailure(Call<List<Ranking>> call, Throwable throwable) {

                Log.e("ERROR", "Error: " + throwable.getMessage());

            }
        });

        view.imgMenu.setOnClickListener(e -> {

            view.imgMenu.setEnabled(false);
            view.viewFundo.setEnabled(true);

            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(400);

            view.viewFundo.setAnimation(animation);
            view.viewFundo.setVisibility(View.VISIBLE);

            Animation animation1 = AnimationUtils.loadAnimation(TelaRanking.this, R.anim.anim_in);
            animation1.setDuration(400);

            view.viewMenu.setAnimation(animation1);
            view.viewMenu.setVisibility(View.VISIBLE);


        });

        view.viewFundo.setOnClickListener(e -> {

            view.imgMenu.setEnabled(true);
            view.viewFundo.setEnabled(false);

            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(400);

            view.viewFundo.setAnimation(animation);
            view.viewFundo.setVisibility(View.GONE);

            Animation animation1 = AnimationUtils.loadAnimation(TelaRanking.this, R.anim.anim_out);
            animation1.setDuration(400);

            view.viewMenu.setAnimation(animation1);
            view.viewMenu.setVisibility(View.GONE);

        });

        view.viewMenu.setOnClickListener(e -> {

        });

        view.btnLogout.setOnClickListener(e -> {

            startActivity(new Intent(TelaRanking.this, MainActivity.class));
            finish();

        });

    }
}