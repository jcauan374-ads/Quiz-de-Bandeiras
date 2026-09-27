package com.example.quizbandeiras;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Guarda e le a lista de pontuacoes do quiz usando SharedPreferences.
 * Cada resultado fica salvo no formato "nome|pontuacao", separado por ";".
 * Nao usa banco de dados nem bibliotecas externas, so o que o Android ja
 * oferece nativamente.
 */
public class RankingManager {

    private static final String PREFS_NAME = "ranking_prefs";
    private static final String KEY_LISTA = "lista_ranking";

    // Salva um novo resultado no final da lista (nao apaga os anteriores)
    public static void salvarResultado(Context context, String nome, int pontuacao) {
        if (nome == null || nome.trim().isEmpty()) {
            nome = "Jogador sem nome";
        }
        // Impede que o "|" ou ";" digitados pelo usuario quebrem o formato de salvamento
        nome = nome.trim().replace("|", "").replace(";", "");

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String listaAtual = prefs.getString(KEY_LISTA, "");
        String novoRegistro = nome + "|" + pontuacao;
        String listaAtualizada = listaAtual.isEmpty() ? novoRegistro : listaAtual + ";" + novoRegistro;
        prefs.edit().putString(KEY_LISTA, listaAtualizada).apply();
    }

    // Le todos os resultados salvos, do mais pra o menos pontuado
    public static List<ResultadoRanking> carregarRanking(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String listaSalva = prefs.getString(KEY_LISTA, "");

        List<ResultadoRanking> resultados = new ArrayList<>();
        if (!listaSalva.isEmpty()) {
            String[] registros = listaSalva.split(";");
            for (String registro : registros) {
                String[] partes = registro.split("\\|");
                if (partes.length == 2) {
                    try {
                        resultados.add(new ResultadoRanking(partes[0], Integer.parseInt(partes[1])));
                    } catch (NumberFormatException ignored) {
                        // Ignora registro corrompido, nao trava o app
                    }
                }
            }
        }

        // Ordena do maior acerto pro menor
        Collections.sort(resultados, new Comparator<ResultadoRanking>() {
            @Override
            public int compare(ResultadoRanking a, ResultadoRanking b) {
                return b.getPontuacao() - a.getPontuacao();
            }
        });

        return resultados;
    }

    // Apaga todo o historico de pontuacoes salvas
    public static void limparRanking(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_LISTA).apply();
    }
}
