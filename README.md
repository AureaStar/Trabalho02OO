academico-planner/
│
├── pom.xml                      # Configuração do projeto Maven (dependências, JDK 21, etc.)
├── README.md                    # Este arquivo com informações do projeto
└── src/
    ├── main/
    │   ├── java/
    │   │   └── br/
    │   │       └── ufjf/
    │   │           └── academico/
    │   │               ├── model/           # MODELO: entidades e regras de negócio
    │   │               │   ├── Aluno.java
    │   │               │   ├── Disciplina.java
    │   │               │   ├── DisciplinaObrigatoria.java
    │   │               │   ├── DisciplinaEletiva.java
    │   │               │   ├── DisciplinaOptativa.java
    │   │               │   ├── Turma.java
    │   │               │   └── ...
    │   │               │
    │   │               ├── controller/      # CONTROLADOR: coordenação da matrícula
    │   │               │   └── SistemaAcademico.java
    │   │               │
    │   │               ├── view/            # VISUALIZAÇÃO: geração de relatórios (sem interface gráfica)
    │   │               │   └── RelatorioSimulacao.java
    │   │               │
    │   │               ├── validator/       # VALIDADORES: regras de pré-requisito/co-requisito
    │   │               │   ├── ValidadorPreRequisito.java
    │   │               │   ├── ValidadorSimples.java
    │   │               │   ├── ValidadorLogicoAND.java
    │   │               │   ├── ValidadorLogicoOR.java
    │   │               │   └── ValidadorCreditosMinimos.java
    │   │               │
    │   │               └── exception/       # EXCEÇÕES: falhas e validações específicas
    │   │                   ├── MatriculaException.java
    │   │                   ├── ValidacaoMatriculaException.java
    │   │                   ├── PreRequisitoNaoCumpridoException.java
    │   │                   ├── CoRequisitoNaoAtendidoException.java
    │   │                   ├── CargaHorariaExcedidaException.java
    │   │                   ├── ConflitoDeHorarioException.java
    │   │                   ├── GerenciamentoVagasException.java
    │   │                   └── TurmaCheiaException.java
    │   │
    │   └── resources/                       # (Opcional) Arquivos de configuração ou dados de entrada simulada
    │       ├── alunos.json
    │       ├── disciplinas.csv
    │       └── ...
    │
    └── test/
        └── java/
            └── br/
                └── ufjf/
                    └── academico/
                        ├── model/          # Testes de entidades e lógica de negócios
                        ├── controller/     # Testes do sistema de matrícula
                        ├── validator/      # Testes dos validadores de pré-requisito
                        ├── exception/      # Testes das exceções
                        └── ...