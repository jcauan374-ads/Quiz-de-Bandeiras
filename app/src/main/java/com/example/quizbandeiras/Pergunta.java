package com.example.quizbandeiras;

public class Pergunta {
    private int imagemResId;
    private String[] opcoes;
    private int respostaCorreta;

    public Pergunta(int imagemResId, String[] opcoes, int respostaCorreta) {
        this.imagemResId = imagemResId;
        this.opcoes = opcoes;
        this.respostaCorreta = respostaCorreta;
    }

    public int getImagemResId() { return imagemResId; }
    public String[] getOpcoes() { return opcoes; }
    public int getRespostaCorreta() { return respostaCorreta; }
}