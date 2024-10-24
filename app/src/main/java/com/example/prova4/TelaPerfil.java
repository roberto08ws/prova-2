package com.example.prova4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prova4.databinding.ActivityTelaPerfilBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class TelaPerfil extends AppCompatActivity {

    private ActivityTelaPerfilBinding view;

    private interface Atualiza {

        @PUT("/alunos/{id}")
        Call<ResponseCadastro> cadastra(@Path("id") int id, @Body BodyAtualiza bodyAtualiza);

    }

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();

        Atualiza atualiza = retrofit.create(Atualiza.class);

        SharedPreferences cache = getSharedPreferences("cadastro", MODE_PRIVATE);

        int id = cache.getInt("id", 0);
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

        view.viewFundo.setOnClickListener(e -> {

            view.imgMenu.setEnabled(true);
            view.viewFundo.setEnabled(false);

            Animation animation = new AlphaAnimation(1, 0);
            animation.setDuration(400);

            view.viewFundo.setAnimation(animation);
            view.viewFundo.setVisibility(View.GONE);

            Animation animation1 = AnimationUtils.loadAnimation(TelaPerfil.this, R.anim.anim_out);
            animation1.setDuration(400);

            view.viewMenu.setAnimation(animation1);
            view.viewMenu.setVisibility(View.GONE);

        });

        view.viewMenu.setOnClickListener(e -> {

        });

        view.btnAtualizar.setOnClickListener(e -> {

            String novoNome = view.edtNome.getText().toString().trim();
            String novaCidade = view.edtCidade.getText().toString().trim();

            if (novoNome.equals(nome) && novaCidade.equals(cidade)) {

                Toast.makeText(this, "Nenhuma alteração feita!", Toast.LENGTH_SHORT).show();

            } else {

                atualiza.cadastra(id, new BodyAtualiza(novoNome, novaCidade)).enqueue(new Callback<ResponseCadastro>() {
                    @Override
                    public void onResponse(Call<ResponseCadastro> call, Response<ResponseCadastro> response) {

                        if (response.isSuccessful()) {

                            SharedPreferences.Editor editor = getSharedPreferences("cadastro", MODE_PRIVATE).edit();

                            editor.putString("nome", novoNome);
                            editor.putString("cidade", novaCidade);
                            editor.apply();

                            Toast.makeText(TelaPerfil.this, "Informações atualizadas com sucesso!", Toast.LENGTH_SHORT).show();

                        } else {

                            Log.e("ERROR", "Error: " + response.code());

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseCadastro> call, Throwable throwable) {

                        Log.e("ERROR", "Error: "  + throwable.getMessage());

                    }
                });

            }

        });

        view.btnLogout.setOnClickListener(e -> {

            startActivity(new Intent(TelaPerfil.this, MainActivity.class));
            finish();

        });

    }
}