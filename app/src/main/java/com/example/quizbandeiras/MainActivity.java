package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNomeJogador;
    private View btnIniciar, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNomeJogador = findViewById(R.id.edtNomeJogador);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnSair = findViewById(R.id.btnSair);

        // Começa desabilitado (visualmente "apagado") até o jogador digitar o nome
        habilitarBotaoIniciar(false);

        edtNomeJogador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                habilitarBotaoIniciar(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnIniciar.setOnClickListener(v -> {
            String nome = edtNomeJogador.getText().toString().trim();
            if (nome.isEmpty()) return;

            Intent intent = new Intent(MainActivity.this, PerguntaActivity.class);
            intent.putExtra("NOME_USUARIO", nome);
            intent.putExtra("INDICE_PERGUNTA", 0);
            intent.putExtra("PONTUACAO", 0);
            startActivity(intent);
        });

        btnSair.setOnClickListener(v -> finishAffinity());
    }

    // Como o botão agora é um TextView estilizado, controlamos "habilitado"
    // manualmente: trava o clique e deixa mais transparente quando desabilitado
    private void habilitarBotaoIniciar(boolean habilitado) {
        btnIniciar.setEnabled(habilitado);
        btnIniciar.setAlpha(habilitado ? 1f : 0.4f);
    }
}
