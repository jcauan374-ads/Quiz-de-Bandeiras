const questions = [
  { image: 'brasil.png', country: 'Brasil', options: ['Brasil', 'Granada', 'Guiana', 'Colômbia'], answer: 0 },
  { image: 'japao.png', country: 'Japão', options: ['China', 'Japão', 'Coreia do Sul', 'Vietnã'], answer: 1 },
  { image: 'alemanha.png', country: 'Alemanha', options: ['Bélgica', 'Espanha', 'Alemanha', 'Itália'], answer: 2 },
  { image: 'franca.png', country: 'França', options: ['França', 'Holanda', 'Rússia', 'Inglaterra'], answer: 0 },
  { image: 'argentina.png', country: 'Argentina', options: ['Uruguai', 'Chile', 'Argentina', 'Paraguai'], answer: 2 },
  { image: 'canada.png', country: 'Canadá', options: ['EUA', 'Canadá', 'México', 'Peru'], answer: 1 },
  { image: 'italia.png', country: 'Itália', options: ['México', 'Itália', 'Irlanda', 'Hungria'], answer: 1 },
  { image: 'espanha.png', country: 'Espanha', options: ['Espanha', 'Portugal', 'Andorra', 'Colômbia'], answer: 0 },
  { image: 'australia.png', country: 'Austrália', options: ['Nova Zelândia', 'Austrália', 'Reino Unido', 'Fiji'], answer: 1 },
  { image: 'sul_africa.png', country: 'África do Sul', options: ['Nigéria', 'Gana', 'África do Sul', 'Camarões'], answer: 2 },
];

const state = { player: '', current: 0, score: 0, selected: null };
const $ = (id) => document.getElementById(id);
const screens = { start: $('start-screen'), question: $('question-screen'), result: $('result-screen') };

function showScreen(name) {
  Object.values(screens).forEach((screen) => { screen.hidden = true; screen.classList.remove('active'); });
  screens[name].hidden = false;
  screens[name].classList.add('active');
}

function updateProgress() {
  const count = state.current + (screens.question.hidden ? 0 : 1);
  $('progress-count').textContent = `${Math.min(count, questions.length)} / ${questions.length}`;
  $('progress-label').textContent = screens.start.hidden ? 'PROGRESSO DO DESAFIO' : 'PRONTO PARA JOGAR';
  $('progress-bar').style.width = `${(Math.min(state.current, questions.length) / questions.length) * 100}%`;
}

function renderQuestion() {
  const question = questions[state.current];
  state.selected = null;
  $('question-number').textContent = String(state.current + 1).padStart(2, '0');
  $('flag-image').src = `assets/${question.image}`;
  $('flag-image').alt = `Bandeira para a pergunta ${state.current + 1}`;
  const answers = $('answers');
  answers.innerHTML = '';
  question.options.forEach((option, index) => {
    const label = document.createElement('label');
    label.className = 'answer-option';
    label.innerHTML = `<input type="radio" name="answer" value="${index}" /> <span>${option}</span>`;
    label.addEventListener('click', () => {
      state.selected = index;
      document.querySelectorAll('.answer-option').forEach((item) => item.classList.remove('selected'));
      label.classList.add('selected');
      $('answer-button').disabled = false;
    });
    answers.appendChild(label);
  });
  $('answer-button').disabled = true;
  updateProgress();
}

function startGame(event) {
  event.preventDefault();
  state.player = $('player-name').value.trim();
  if (!state.player) return;
  state.current = 0;
  state.score = 0;
  showScreen('question');
  renderQuestion();
  $('game').scrollIntoView({ behavior: 'smooth', block: 'start' });
}

function answerQuestion() {
  if (state.selected === null) return;
  if (state.selected === questions[state.current].answer) state.score += 1;
  state.current += 1;
  if (state.current < questions.length) renderQuestion();
  else finishGame();
}

function finishGame() {
  const score = state.score;
  const message = score === 10 ? 'Perfeito! Você conhece muito bem as bandeiras do mundo.' : score >= 7 ? 'Excelente resultado. Seu mapa mental está afiado.' : score >= 4 ? 'Bom trabalho! Continue praticando para subir no ranking.' : 'Cada partida é uma chance de aprender algo novo.';
  $('score-value').textContent = score;
  $('result-title').textContent = `Parabéns, ${state.player}!`;
  $('result-message').textContent = message;
  const ranking = JSON.parse(localStorage.getItem('quizBandeirasRanking') || '[]');
  ranking.push({ name: state.player, score, date: Date.now() });
  ranking.sort((a, b) => b.score - a.score || b.date - a.date);
  localStorage.setItem('quizBandeirasRanking', JSON.stringify(ranking.slice(0, 20)));
  showScreen('result');
  $('progress-label').textContent = 'DESAFIO CONCLUÍDO';
  $('progress-count').textContent = '10 / 10';
  $('progress-bar').style.width = '100%';
  renderRanking();
}

function renderRanking() {
  const list = $('ranking-list');
  const ranking = JSON.parse(localStorage.getItem('quizBandeirasRanking') || '[]');
  if (!ranking.length) { list.innerHTML = '<div class="empty-ranking">Ainda não há resultados. Seja o primeiro a jogar.</div>'; return; }
  list.innerHTML = ranking.slice(0, 10).map((item, index) => `<div class="ranking-row"><span class="ranking-position">${String(index + 1).padStart(2, '0')}</span><span class="ranking-name">${escapeHtml(item.name)}</span><span class="ranking-score">${item.score} / 10</span></div>`).join('');
}

function escapeHtml(value) { return value.replace(/[&<>'"]/g, (char) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', "'": '&#039;', '"': '&quot;' }[char])); }
function goHome() { state.player = ''; showScreen('start'); $('player-name').value = ''; updateProgress(); window.scrollTo({ top: 0, behavior: 'smooth' }); }
function restart() { state.current = 0; state.score = 0; showScreen('question'); renderQuestion(); }

$('start-form').addEventListener('submit', startGame);
$('answer-button').addEventListener('click', answerQuestion);
$('restart-button').addEventListener('click', restart);
$('home-button').addEventListener('click', goHome);
$('clear-ranking').addEventListener('click', () => { localStorage.removeItem('quizBandeirasRanking'); renderRanking(); });
renderRanking();
updateProgress();
