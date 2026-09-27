package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class PerguntaActivity extends AppCompatActivity {

    private ImageView imgBandeira;
    private RadioGroup rgOpcoes;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnResponder;

    private String nomeUsuario;
    private int indicePergunta;
    private int pontuacao;

    private List<Pergunta> listaPerguntas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        imgBandeira = findViewById(R.id.imgBandeira);
        rgOpcoes = findViewById(R.id.rgOpcoes);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnResponder = findViewById(R.id.btnResponder);

        Intent intent = getIntent();
        nomeUsuario = intent.getStringExtra("NOME_USUARIO");
        indicePergunta = intent.getIntExtra("INDICE_PERGUNTA", 0);
        pontuacao = intent.getIntExtra("PONTUACAO", 0);

        carregarPerguntas();
        exibirPergunta();

        rgOpcoes.setOnCheckedChangeListener((group, checkedId) -> btnResponder.setEnabled(true));

        btnResponder.setOnClickListener(v -> {
            int respostaSelecionada = -1;
            int selectedId = rgOpcoes.getCheckedRadioButtonId();

            if (selectedId == R.id.rb1) respostaSelecionada = 0;
            else if (selectedId == R.id.rb2) respostaSelecionada = 1;
            else if (selectedId == R.id.rb3) respostaSelecionada = 2;
            else if (selectedId == R.id.rb4) respostaSelecionada = 3;

            if (respostaSelecionada == listaPerguntas.get(indicePergunta).getRespostaCorreta()) {
                pontuacao++;
            }

            if (indicePergunta + 1 < listaPerguntas.size()) {
                Intent proxIntent = new Intent(PerguntaActivity.this, PerguntaActivity.class);
                proxIntent.putExtra("NOME_USUARIO", nomeUsuario);
                proxIntent.putExtra("INDICE_PERGUNTA", indicePergunta + 1);
                proxIntent.putExtra("PONTUACAO", pontuacao);
                startActivity(proxIntent);
            } else {
                Intent rankIntent = new Intent(PerguntaActivity.this, RankingActivity.class);
                rankIntent.putExtra("NOME_USUARIO", nomeUsuario);
                rankIntent.putExtra("PONTUACAO", pontuacao);
                startActivity(rankIntent);
            }
            finish();
        });
    }

    private void carregarPerguntas() {
        listaPerguntas.add(new Pergunta(R.drawable.brasil, new String[]{"Brasil", "Granada", "Guiana", "Colômbia"}, 0));
        listaPerguntas.add(new Pergunta(R.drawable.japao, new String[]{"China", "Japão", "Coréia do Sul", "Vietnã"}, 1));
        listaPerguntas.add(new Pergunta(R.drawable.alemanha, new String[]{"Bélgica", "Espanha", "Alemanha", "Itália"}, 2));
        listaPerguntas.add(new Pergunta(R.drawable.franca, new String[]{"França", "Holanda", "Rússia", "Inglaterra"}, 0));
        listaPerguntas.add(new Pergunta(R.drawable.argentina, new String[]{"Uruguai", "Chile", "Argentina", "Paraguai"}, 2));
        listaPerguntas.add(new Pergunta(R.drawable.canada, new String[]{"EUA", "Canadá", "México", "Peru"}, 1));
        listaPerguntas.add(new Pergunta(R.drawable.italia, new String[]{"México", "Itália", "Irlanda", "Hungria"}, 1));
        listaPerguntas.add(new Pergunta(R.drawable.espanha, new String[]{"Espanha", "Portugal", "Andorra", "Colômbia"}, 0));
        listaPerguntas.add(new Pergunta(R.drawable.australia, new String[]{"Nova Zelândia", "Austrália", "Reino Unido", "Fiji"}, 1));
        listaPerguntas.add(new Pergunta(R.drawable.sul_africa, new String[]{"Nigéria", "Gana", "África do Sul", "Camarões"}, 2));
    }

    private void exibirPergunta() {
        Pergunta p = listaPerguntas.get(indicePergunta);
        imgBandeira.setImageResource(p.getImagemResId());
        String[] ops = p.getOpcoes();
        rb1.setText(ops[0]);
        rb2.setText(ops[1]);
        rb3.setText(ops[2]);
        rb4.setText(ops[3]);
        rgOpcoes.clearCheck();
        btnResponder.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}