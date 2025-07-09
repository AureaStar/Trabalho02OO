## 🗂 Estrutura Completa de Diretórios (Padrão Maven + MVC)

Abaixo está a estrutura completa do projeto utilizando **Maven** e o padrão **MVC**, organizada em pacotes por responsabilidade (modelo, controlador, visualização, validação, exceções e testes automatizados):

```plaintext
academico-planner/
│
├── pom.xml                          # Configuração do Maven (JDK 21, dependências, plugins)
├── README.md                        # Documentação do projeto
└── src/
    ├── main/
    │   ├── java/
    │   │   └── br/
    │   │       └── ufjf/
    │   │           └── academico/
    │   │               ├── model/                           # MODELO: entidades do domínio
    │   │               │   ├── Aluno.java
    │   │               │   ├── Disciplina.java              # (abstract)
    │   │               │   ├── DisciplinaObrigatoria.java
    │   │               │   ├── DisciplinaEletiva.java
    │   │               │   ├── DisciplinaOptativa.java
    │   │               │   ├── Turma.java
    │   │               │   ├── Historico.java               # (opcional)
    │   │               │   └── Planejamento.java
    │   │               │
    │   │               ├── controller/                      # CONTROLADOR: lógica de matrícula
    │   │               │   └── SistemaAcademico.java
    │   │               │
    │   │               ├── view/                            # VIEW: geração de relatórios
    │   │               │   └── RelatorioSimulacao.java
    │   │               │       ├── Login
    │   │               │       ├── Dashboard
    │   │               │       ├── Tabela de Alunos
    │   │               │       ├── CRUD - Modais
    │   │               │       ├── Tabela de Disciplinas
    │   │               │       ├── CRUD - Modais
    │   │               │       ├── 
    │   │               │       └── Planejamento.java
    │   │               │
    │   │               ├── validator/                       # VALIDADORES: pré/co-requisitos
    │   │               │   ├── ValidadorPreRequisito.java   # (interface)
    │   │               │   ├── ValidadorSimples.java
    │   │               │   ├── ValidadorLogicoAND.java
    │   │               │   ├── ValidadorLogicoOR.java
    │   │               │   └── ValidadorCreditosMinimos.java
    │   │               │
    │   │               └── exception/                       # EXCEÇÕES: hierarquia de falhas
    │   │                   ├── MatriculaException.java              # (abstract)
    │   │                   ├── ValidacaoMatriculaException.java    # (extends MatriculaException)
    │   │                   ├── PreRequisitoNaoCumpridoException.java
    │   │                   ├── CoRequisitoNaoAtendidoException.java
    │   │                   ├── CargaHorariaExcedidaException.java
    │   │                   ├── ConflitoDeHorarioException.java
    │   │                   ├── GerenciamentoVagasException.java    # (extends MatriculaException)
    │   │                   └── TurmaCheiaException.java
    │   │
    │   └── resources/                                       # Dados simulados (opcional)
    │       ├── alunos.json
    │       ├── disciplinas.json
    │       └── turmas.json
    │
    └── test/
        └── java/
            └── br/
                └── ufjf/
                    └── academico/
                        ├── model/
                        │   ├── AlunoTest.java
                        │   ├── DisciplinaObrigatoriaTest.java
                        │   ├── DisciplinaEletivaTest.java
                        │   ├── DisciplinaOptativaTest.java
                        │   ├── TurmaTest.java
                        │   └── PlanejamentoTest.java
                        │
                        ├── controller/
                        │   └── SistemaAcademicoTest.java
                        │
                        ├── view/
                        │   └── RelatorioSimulacaoTest.java
                        │
                        ├── validator/
                        │   ├── ValidadorSimplesTest.java
                        │   ├── ValidadorLogicoANDTest.java
                        │   ├── ValidadorLogicoORTest.java
                        │   └── ValidadorCreditosMinimosTest.java
                        │
                        └── exception/
                            ├── PreRequisitoNaoCumpridoExceptionTest.java
                            ├── CoRequisitoNaoAtendidoExceptionTest.java
                            ├── CargaHorariaExcedidaExceptionTest.java
                            ├── ConflitoDeHorarioExceptionTest.java
                            └── TurmaCheiaExceptionTest.java
```
