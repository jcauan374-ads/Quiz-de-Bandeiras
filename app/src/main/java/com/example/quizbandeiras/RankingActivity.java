package com.example.quizbandeiras;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private TextView txtNome, txtAcertos;
    private View btnNovamente, btnTelaPrincipal, btnLimparRanking;
    private LinearLayout llListaRanking;

    private String nomeJogadorAtual;
    private int pontuacaoAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        txtNome = findViewById(R.id.txtNome);
        txtAcertos = findViewById(R.id.txtAcertos);
        btnNovamente = findViewById(R.id.btnNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);
        btnLimparRanking = findViewById(R.id.btnLimparRanking);
        llListaRanking = findViewById(R.id.llListaRanking);

        Intent intent = getIntent();
        nomeJogadorAtual = intent.getStringExtra("NOME_USUARIO");
        pontuacaoAtual = intent.getIntExtra("PONTUACAO", 0);

        txtNome.setText(nomeJogadorAtual == null || nomeJogadorAtual.trim().isEmpty()
                ? "Jogador sem nome" : nomeJogadorAtual);
        txtAcertos.setText("Acertos: " + pontuacaoAtual + " / 10");

        // Salva esse resultado no histórico e recarrega a lista completa
        RankingManager.salvarResultado(this, nomeJogadorAtual, pontuacaoAtual);
        montarListaRanking();

        btnNovamente.setOnClickListener(v -> {
            Intent pIntent = new Intent(RankingActivity.this, PerguntaActivity.class);
            pIntent.putExtra("NOME_USUARIO", nomeJogadorAtual);
            pIntent.putExtra("INDICE_PERGUNTA", 0);
            pIntent.putExtra("PONTUACAO", 0);
            startActivity(pIntent);
            finish();
        });

        btnTelaPrincipal.setOnClickListener(v -> {
            Intent mIntent = new Intent(RankingActivity.this, MainActivity.class);
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mIntent);
            finish();
        });

        btnLimparRanking.setOnClickListener(v -> {
            RankingManager.limparRanking(this);
            montarListaRanking();
        });
    }

    // Monta a lista de jogadores na tela, um card por resultado salvo
    private void montarListaRanking() {
        llListaRanking.removeAllViews();

        List<ResultadoRanking> lista = RankingManager.carregarRanking(this);

        if (lista.isEmpty()) {
            TextView vazio = new TextView(this);
            vazio.setText("Nenhum resultado salvo ainda.");
            vazio.setTextColor(0xFF9E9E9E);
            vazio.setPadding(0, 16, 0, 16);
            llListaRanking.addView(vazio);
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            ResultadoRanking r = lista.get(i);
            llListaRanking.addView(criarLinhaRanking(i + 1, r));
        }
    }

    private View criarLinhaRanking(int posicao, ResultadoRanking r) {
        LinearLayout linha = new LinearLayout(this);
        linha.setOrientation(LinearLayout.HORIZONTAL);
        linha.setGravity(Gravity.CENTER_VERTICAL);
        linha.setPadding(20, 18, 20, 18);

        LinearLayout.LayoutParams paramsLinha = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsLinha.setMargins(0, 0, 0, 10);
        linha.setLayoutParams(paramsLinha);

        // Destaca a linha se for o resultado que acabou de ser jogado agora
        boolean ehResultadoAtual = posicao == 1 && r.getPontuacao() == pontuacaoAtual
                && r.getNome().equals(nomeJogadorAtual == null ? "" : nomeJogadorAtual.trim());

        linha.setBackgroundResource(ehResultadoAtual
                ? R.drawable.bg_linha_ranking_destaque
                : R.drawable.bg_linha_ranking);

        // Posição / medalha
        TextView txtPosicao = new TextView(this);
        String medalha = posicao == 1 ? "🥇" : posicao == 2 ? "🥈" : posicao == 3 ? "🥉" : String.valueOf(posicao) + "º";
        txtPosicao.setText(medalha);
        txtPosicao.setTextSize(16);
        txtPosicao.setMinWidth(60);

        // Nome
        TextView txtNomeLinha = new TextView(this);
        txtNomeLinha.setText(r.getNome());
        txtNomeLinha.setTextSize(15);
        txtNomeLinha.setTypeface(null, Typeface.BOLD);
        txtNomeLinha.setTextColor(0xFF1B1B1B);
        LinearLayout.LayoutParams paramsNome = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        txtNomeLinha.setLayoutParams(paramsNome);

        // Pontuação
        TextView txtPontos = new TextView(this);
        txtPontos.setText(r.getPontuacao() + " / 10");
        txtPontos.setTextSize(15);
        txtPontos.setTypeface(null, Typeface.BOLD);
        txtPontos.setTextColor(0xFF2E7D32);

        linha.addView(txtPosicao);
        linha.addView(txtNomeLinha);
        linha.addView(txtPontos);

        return linha;
    }
}
