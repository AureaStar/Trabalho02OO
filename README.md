## 🗂 Estrutura de Diretórios (Maven + MVC)

Este projeto utiliza o padrão **MVC** e está estruturado com **Maven**, com foco em design orientado a objetos e testes automatizados. Abaixo está a organização completa dos pacotes e arquivos:

academico-planner/
│
├── pom.xml # Configuração Maven (JDK 21, dependências, build)
├── README.md # Documentação do projeto
└── src/
├── main/
│ ├── java/
│ │ └── br/
│ │ └── ufjf/
│ │ └── academico/
│ │ ├── model/ # MODELO: entidades do domínio
│ │ │ ├── Aluno.java
│ │ │ ├── Disciplina.java
│ │ │ ├── DisciplinaObrigatoria.java
│ │ │ ├── DisciplinaEletiva.java
│ │ │ ├── DisciplinaOptativa.java
│ │ │ ├── Turma.java
│ │ │ └── Planejamento.java
│ │ │
│ │ ├── controller/ # CONTROLADOR: orquestra regras e simulação
│ │ │ └── SistemaAcademico.java
│ │ │
│ │ ├── view/ # VIEW: geração de relatórios (sem interface gráfica)
│ │ │ └── RelatorioSimulacao.java
│ │ │
│ │ ├── validator/ # VALIDADORES: lógicas polimórficas de pré-requisitos
│ │ │ ├── ValidadorPreRequisito.java
│ │ │ ├── ValidadorSimples.java
│ │ │ ├── ValidadorLogicoAND.java
│ │ │ ├── ValidadorLogicoOR.java
│ │ │ └── ValidadorCreditosMinimos.java
│ │ │
│ │ └── exception/ # EXCEÇÕES: hierarquia de erros de matrícula
│ │ ├── MatriculaException.java
│ │ ├── ValidacaoMatriculaException.java
│ │ ├── PreRequisitoNaoCumpridoException.java
│ │ ├── CoRequisitoNaoAtendidoException.java
│ │ ├── CargaHorariaExcedidaException.java
│ │ ├── ConflitoDeHorarioException.java
│ │ ├── GerenciamentoVagasException.java
│ │ └── TurmaCheiaException.java
│ │
│ └── resources/ # Arquivos de entrada (mock) ou config
│ ├── alunos.json
│ ├── disciplinas.csv
│ └── turmas.csv
│
└── test/
└── java/
└── br/
└── ufjf/
└── academico/
├── model/
│ ├── AlunoTest.java
│ └── DisciplinaTest.java
│
├── controller/
│ └── SistemaAcademicoTest.java
│
├── validator/
│ ├── ValidadorSimplesTest.java
│ ├── ValidadorLogicoANDTest.java
│ └── ValidadorLogicoORTest.java
│
├── exception/
│ └── ExcecoesTest.java
│
└── view/
└── RelatorioSimulacaoTest.java