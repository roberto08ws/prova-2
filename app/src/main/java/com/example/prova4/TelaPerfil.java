package com.example.prova4;

import android.app.Activity;
import android.content.SharedPreferences;
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

import com.example.prova4.databinding.ActivityTelaPerfilBinding;

public class TelaPerfil extends AppCompatActivity {

    private ActivityTelaPerfilBinding view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        view = ActivityTelaPerfilBinding.inflate(getLayoutInflater());
        setContentView(view.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Animation animation2 = new AlphaAnimation(1, 0);
        animation2.setDuration(4000);

        view.viewHarmonico.setAnimation(animation2);
        view.viewHarmonico.setVisibility(View.GONE);

        SharedPreferences cache = getSharedPreferences("cadastro", MODE_PRIVATE);

        String nome = cache.getString("nome", null);
        String cidade = cache.getString("cidade", null);
        String curso = cache.getString("curso", null);

        view.edtNome.setText(nome);
        view.edtCidade.setText(cidade);
        view.edtCurso.setText(curso);

        view.edtCurso.setEnabled(false);

        view.imgMenu.setOnClickListener(e -> {

            view.imgMenu.setEnabled(false);
            view.viewFundo.setEnabled(true);

            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(400);

            view.viewFundo.setAnimation(animation);
            view.viewFundo.setVisibility(View.VISIBLE);

            Animation animation1 = AnimationUtils.loadAnimation(TelaPerfil.this, R.anim.anim_in);
            animation1.setDuration(400);

            view.viewMenu.setAnimation(animation1);
            view.viewMenu.setVisibility(View.VISIBLE);


        });

        view.viewMenu.setOnClickListener(e -> {

        });

    }
}