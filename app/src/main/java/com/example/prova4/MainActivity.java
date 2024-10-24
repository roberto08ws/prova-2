package com.example.prova4;

import android.app.Activity;
import android.os.Bundle;
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



            }

        });

        view.comboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                System.out.println("CURSOS: " + cursos[i]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });

    }
}