package com.example.prova4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding view;
    private String[] cursos;
    private List<Cursos> list = new ArrayList<>();
    private String curso = "";

    private interface Lista {

        @GET("/cursos")
        Call<List<Cursos>> lista();

    }

    private interface Cadastro {

        @POST("/alunos")
        Call<ResponseCadastro> cadastra(@Body Usuarios usuarios);

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
        Cadastro cadastro = retrofit.create(Cadastro.class);

        lista.lista().enqueue(new Callback<List<Cursos>>() {
            @Override
            public void onResponse(Call<List<Cursos>> call, Response<List<Cursos>> response) {

                list = response.body();
                cursos = new String[list.size()];

                for (int i = 0; i < list.size(); i++) {

                    cursos[i] = list.get(i).getNome_curso();

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, cursos);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                view.comboBox.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Cursos>> call, Throwable throwable) {



            }
        });

        view.btnCadastrar.setOnClickListener(e -> {

            String nome = view.edtNome.getText().toString().trim();
            String cidade = view.edtCidade.getText().toString().trim();
            String email = view.edtEmail.getText().toString().trim();
            String senha = view.edtSenha.getText().toString().trim();
            String confirmar = view.edtConfirmar.getText().toString().trim();

            if (nome.isEmpty() || cidade.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmar.isEmpty()) {

                String faltantes = "";

                if (nome.isEmpty()) {

                    faltantes = faltantes + "nome completo,";

                }

                if (cidade.isEmpty()) {

                    faltantes = faltantes + "cidade,";

                }

                if (email.isEmpty()) {

                    faltantes = faltantes + "e-mail,";

                }

                if (senha.isEmpty()) {

                    faltantes = faltantes + "senha,";

                }

                if (confirmar.isEmpty()) {

                    faltantes = faltantes + "confirmar senha.";

                }

                Toast.makeText(this, "Campos faltantes: " + faltantes, Toast.LENGTH_SHORT).show();

            } else {

                cadastro.cadastra(new Usuarios(0, nome, cidade, curso, email, senha)).enqueue(new Callback<ResponseCadastro>() {
                    @Override
                    public void onResponse(Call<ResponseCadastro> call, Response<ResponseCadastro> response) {

                        if (response.isSuccessful()) {

                            Usuarios usuario = response.body().getAluno();

                            SharedPreferences.Editor editor = getSharedPreferences("cadastro", MODE_PRIVATE).edit();

                            editor.putInt("id", usuario.getId());
                            editor.putString("nome", usuario.getNome_completo());
                            editor.putString("cidade", usuario.getCidade());
                            editor.putString("curso", usuario.getCurso());
                            editor.apply();

                            Toast.makeText(MainActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(MainActivity.this, TelaPerfil.class));
                            finish();

                        } else {

                            Log.e("ERROR", "Error: " + response.code());

                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseCadastro> call, Throwable throwable) {

                        Log.e("ERROR", "Error: " + throwable.getMessage());

                    }
                });

            }

        });

        view.comboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                curso = cursos[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

    }
}