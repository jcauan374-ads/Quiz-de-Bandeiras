# 🌎 Quiz das Bandeiras

> Um quiz educativo para testar seus conhecimentos sobre bandeiras e países, desenvolvido no **Android Studio** como projeto acadêmico.

<div align="center">

![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-9.5-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Status](https://img.shields.io/badge/status-projeto%20acadêmico-2563EB?style=for-the-badge)

**Aprender • Jogar • Melhorar sua pontuação**

</div>

---

## 📱 Sobre o projeto

O **Quiz das Bandeiras** é um aplicativo Android nativo no qual o jogador identifica o país correspondente a cada bandeira apresentada. O projeto foi desenvolvido para praticar fundamentos de desenvolvimento mobile, construção de telas, navegação entre Activities e persistência de dados local.

A experiência foi organizada em três etapas simples: identificação do jogador, resolução das perguntas e visualização do resultado com ranking.

## ✨ Funcionalidades

- Cadastro do nome do jogador antes do início da partida;
- Quiz com **10 perguntas** sobre países e bandeiras;
- Quatro alternativas por pergunta;
- Validação da resposta selecionada;
- Contagem automática de acertos;
- Resultado final personalizado;
- Ranking local ordenado pela maior pontuação;
- Histórico persistente no aparelho usando `SharedPreferences`;
- Opção para jogar novamente;
- Opção para retornar à tela principal;
- Opção para limpar o histórico de pontuações;
- Interface responsiva baseada em layouts XML e componentes Material/AndroidX.

## 🧭 Fluxo do aplicativo

```text
Tela inicial
    ↓
Jogador informa o nome
    ↓
10 perguntas com alternativas
    ↓
Resultado final
    ↓
Ranking local de pontuações
```

## 🛠️ Tecnologias utilizadas

| Tecnologia | Uso no projeto |
|---|---|
| **Java** | Regras de negócio, navegação e controle das telas |
| **Android SDK** | Plataforma e componentes nativos |
| **XML** | Layouts, temas, cores e drawables |
| **AndroidX AppCompat** | Compatibilidade e Activities |
| **Material Components** | Componentes e identidade visual |
| **ConstraintLayout** | Organização de interfaces |
| **SharedPreferences** | Persistência local do ranking |
| **Gradle Kotlin DSL** | Configuração e build do projeto |

## 🗂️ Estrutura principal

```text
app/src/main/
├── java/com/example/quizbandeiras/
│   ├── MainActivity.java          # Tela inicial e identificação do jogador
│   ├── PerguntaActivity.java      # Perguntas, alternativas e pontuação
│   ├── Pergunta.java              # Modelo de uma pergunta
│   ├── RankingActivity.java       # Resultado e ranking
│   ├── RankingManager.java        # Persistência e ordenação do ranking
│   └── ResultadoRanking.java      # Modelo de resultado
│
└── res/
    ├── drawable/                  # Bandeiras, cards, botões e backgrounds
    ├── layout/                    # Telas XML do aplicativo
    ├── mipmap/                    # Ícones do aplicativo
    └── values/                    # Cores, temas e textos
```

## 🚀 Como executar

### Pré-requisitos

- Android Studio atualizado;
- Android SDK com API 37;
- JDK 11 ou compatível;
- Emulador Android ou dispositivo físico com Android 7.0 (API 24) ou superior.

### Passo a passo

1. Clone o repositório:

   ```bash
   git clone https://github.com/jcauan374-ads/Quiz-de-Bandeiras.git
   ```

2. Abra a pasta no Android Studio;
3. Aguarde a sincronização do Gradle;
4. Selecione um emulador ou conecte um dispositivo Android;
5. Execute o módulo `app` pelo botão **Run**.

> O arquivo `local.properties` não é versionado. O Android Studio cria essa configuração automaticamente para cada ambiente local.

## 🌐 Versão web para visualização

Também está disponível uma versão web responsiva para apresentar o projeto diretamente no navegador, sem instalação:

**[Abrir o Quiz das Bandeiras no GitHub Pages ↗](https://jcauan374-ads.github.io/Quiz-de-Bandeiras/)**

Ela mantém a proposta do aplicativo original e inclui perguntas interativas, resultado final, ranking salvo no `localStorage` e layout adaptado para celular e desktop.

## 📦 APK para testes

Uma versão de demonstração está disponível na seção **Releases** do repositório. No Android, permita a instalação de aplicativos de fontes desconhecidas apenas quando necessário e instale o APK por sua conta e risco.

## 👥 Colaboradores

- **José Cauan Ferreira da Silva**
- **Kauã Bitencourt Da Paixão**
- **Pedro Fidrlis Mandoti**
- **Rafael Aparecido Zimbaldi Gomes**
- **Leonardo Soares Rocha**
- **Vinicius Custódio Pavan de Fatima**

## 🔭 Possíveis evoluções

- Adicionar categorias e níveis de dificuldade;
- Randomizar a ordem das perguntas e alternativas;
- Exibir explicações após cada resposta;
- Criar ranking online com autenticação;
- Adicionar testes automatizados para as regras de pontuação;
- Publicar uma versão assinada para distribuição na Google Play.

## 👤 Autor

**José Cauan Ferreira da Silva**  
Estudante de Análise e Desenvolvimento de Sistemas — UNICID

- GitHub: [@jcauan374-ads](https://github.com/jcauan374-ads)
- LinkedIn: [José Cauan](https://www.linkedin.com/in/jose-cauan-8247922b0)
- E-mail: [jcauan374@gmail.com](mailto:jcauan374@gmail.com)

---

<div align="center">

Feito com Java, Android Studio e dedicação aos estudos. 🚀

</div>
