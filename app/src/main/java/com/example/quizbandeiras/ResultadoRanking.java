package com.example.quizbandeiras;

public class ResultadoRanking {
    private String nome;
    private int pontuacao;

    public ResultadoRanking(String nome, int pontuacao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public String getNome() { return nome; }
    public int getPontuacao() { return pontuacao; }
}
