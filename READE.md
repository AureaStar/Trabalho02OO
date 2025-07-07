model/
Entidades e lógica de domínio:

Aluno.java

Disciplina.java (abstrata)

DisciplinaObrigatoria.java, DisciplinaEletiva.java, DisciplinaOptativa.java

Turma.java

Historico.java (se desejar separar)

Planejamento.java (lista de turmas desejadas)

controller/
Coordenador da simulação:

SistemaAcademico.java ou ServicoMatricula.java

Lê dados de entrada (mockados ou fixos)

Aplica regras de negócio

Coordena validações e atualizações no modelo

view/
Responsável por exibir os resultados:

RelatorioSimulacao.java

Mostra disciplinas aceitas/rejeitadas

Mostra motivos de rejeição (exceções)

Pode gerar saída em texto, console, JSON, etc.

exception/
Hierarquia de exceções:

MatriculaException.java (base)

ValidacaoMatriculaException.java

PreRequisitoNaoCumpridoException.java

CoRequisitoNaoAtendidoException.java

CargaHorariaExcedidaException.java

ConflitoDeHorarioException.java

GerenciamentoVagasException.java

TurmaCheiaException.java

validator/
Validadores de pré-requisitos (interface + implementações):

ValidadorPreRequisito.java (interface)

ValidadorSimples.java

ValidadorLogicoAND.java

ValidadorLogicoOR.java

ValidadorCreditosMinimos.java

test/
Testes JUnit separados por camada:

Testes de unidade para:

Matrícula com sucesso

Todas as exceções

Lógica de precedência

Geração de relatório

