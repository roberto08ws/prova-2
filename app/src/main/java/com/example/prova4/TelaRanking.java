package com.example.prova4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class TelaRanking extends AppCompatActivity {

    private ActivityTelaRankingBinding view;

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