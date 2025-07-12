package com.mycompany.trabalho02oo.controllers;

import com.mycompany.trabalho02oo.validators.*;
import java.util.Arrays;

public class DataBase {
    private SistemaAcademico sistemaAcademico;

    public DataBase() {
        this.sistemaAcademico = new SistemaAcademico();
    }

    public void inicializaSistema() {
        inicializaAlunos();
        inicializaDisciplinas();
        inicializaTurmas();
    }

    public void inicializaAlunos() {
        sistemaAcademico.cadastrarAluno("Joao", "202365172");
        sistemaAcademico.cadastrarAluno("Maria", "202365174");
        sistemaAcademico.cadastrarAluno("Carlos", "202365175");
        sistemaAcademico.cadastrarAluno("Ana", "202365176");
        sistemaAcademico.cadastrarAluno("Lucas", "202365177");
        sistemaAcademico.cadastrarAluno("Fernanda", "202365178");
        sistemaAcademico.cadastrarAluno("Rafael", "202365179");
        sistemaAcademico.cadastrarAluno("Beatriz", "202365180");
        sistemaAcademico.cadastrarAluno("Paulo", "202365181");
        sistemaAcademico.cadastrarAluno("Camila", "202365182");
    }

    public void inicializaDisciplinas() {
        // Disciplinas basicas
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.buscarDisciplinaPorCodigo("MAT102").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"));
        sistemaAcademico.cadastrarDisciplinaObrigatoria("POR101", "Portugues I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("HIS101", "Historia I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("FIS201", "Fisica I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("BIO201", "Biologia I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("GEO201", "Geografia I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("QUI301", "Quimica I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("ART301", "Artes Visuais", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("MUS301", "Musica", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("EDU301", "Educacao Fisica", 60);
        
        // Disciplinas com pre-requisitos complexos
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT201", "Calculo I", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("MAT201").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"));
        sistemaAcademico.buscarDisciplinaPorCodigo("MAT201").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"));
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT202", "Calculo II", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("MAT202").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"));
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("FIS301", "Fisica II", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("FIS301").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("FIS201"));
        sistemaAcademico.buscarDisciplinaPorCodigo("FIS301").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("EST401", "Estatistica", 60);
        sistemaAcademico.buscarDisciplinaPorCodigo("EST401").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("ALG401", "Algebra Linear", 60);
        sistemaAcademico.buscarDisciplinaPorCodigo("ALG401").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"));
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("ENG501", "Engenharia de Software", 100);
        sistemaAcademico.buscarDisciplinaPorCodigo("ENG501").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("EST401"));
        sistemaAcademico.buscarDisciplinaPorCodigo("ENG501").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("BD501", "Banco de Dados", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("BD501").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("LAB501", "Laboratorio de Programacao", 60);
        sistemaAcademico.buscarDisciplinaPorCodigo("LAB501").addCoRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("IA601", "Inteligencia Artificial", 100);
        sistemaAcademico.buscarDisciplinaPorCodigo("IA601").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("EST401"));
        sistemaAcademico.buscarDisciplinaPorCodigo("IA601").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"));
        sistemaAcademico.buscarDisciplinaPorCodigo("IA601").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
        
        sistemaAcademico.cadastrarDisciplinaOptativa("RDC601", "Redes de Computadores", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("RDC601").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("SEC701", "Seguranca da Informacao", 60);
        sistemaAcademico.buscarDisciplinaPorCodigo("SEC701").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("RDC601"));
        sistemaAcademico.buscarDisciplinaPorCodigo("SEC701").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("QUI301"));
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("TCC801", "Trabalho de Conclusao de Curso", 120);
        sistemaAcademico.buscarDisciplinaPorCodigo("TCC801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
        sistemaAcademico.buscarDisciplinaPorCodigo("TCC801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("BD501"));
        sistemaAcademico.buscarDisciplinaPorCodigo("TCC801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("IA601"));
        
        sistemaAcademico.cadastrarDisciplinaEletiva("MOB801", "Desenvolvimento Mobile", 80);
        sistemaAcademico.buscarDisciplinaPorCodigo("MOB801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("BD501"));
        sistemaAcademico.buscarDisciplinaPorCodigo("MOB801").addCoRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("LAB501"));
        
        sistemaAcademico.cadastrarDisciplinaOptativa("WEB801", "Desenvolvimento Web Avancado", 100);
        sistemaAcademico.buscarDisciplinaPorCodigo("WEB801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("BD501"));
        sistemaAcademico.buscarDisciplinaPorCodigo("WEB801").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("LAB501"));
        
        adicionarValidadoresComplexos();
    }
    
    private void adicionarValidadoresComplexos() {
        // Adicionar validadores AND e OR para disciplinas especificas
        
        // Disciplina que requer (Matematica I OU Matematica II) E (Fisica I OU Biologia I)
        sistemaAcademico.cadastrarDisciplinaEletiva("CMP901", "Computacao Cientifica", 120);
        
        ValidadorSimples validadorMat1 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"));
        ValidadorSimples validadorMat2 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"));
        ValidadorLogicoOR validadorMatOR = new ValidadorLogicoOR(Arrays.asList(validadorMat1, validadorMat2));
        
        ValidadorSimples validadorFis = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("FIS201"));
        ValidadorSimples validadorBio = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("BIO201"));
        ValidadorLogicoOR validadorCienciaOR = new ValidadorLogicoOR(Arrays.asList(validadorFis, validadorBio));
        
        ValidadorLogicoAND validadorCompleto = new ValidadorLogicoAND(validadorMatOR, validadorCienciaOR);
        sistemaAcademico.buscarDisciplinaPorCodigo("CMP901").addValidadorPreRequisito(validadorCompleto);
        
        // Disciplina que requer (Calculo I E Calculo II) OU (Estatistica E Algebra Linear)
        sistemaAcademico.cadastrarDisciplinaEletiva("OPT901", "Otimizacao", 80);
        
        ValidadorSimples validadorCalc1 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"));
        ValidadorSimples validadorCalc2 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT202"));
        ValidadorLogicoAND validadorCalculoAND = new ValidadorLogicoAND(validadorCalc1, validadorCalc2);
        
        ValidadorSimples validadorEst = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("EST401"));
        ValidadorSimples validadorAlg = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"));
        ValidadorLogicoAND validadorEstatisticaAND = new ValidadorLogicoAND(validadorEst, validadorAlg);
        
        ValidadorLogicoOR validadorFinalOR = new ValidadorLogicoOR(Arrays.asList(validadorCalculoAND, validadorEstatisticaAND));
        sistemaAcademico.buscarDisciplinaPorCodigo("OPT901").addValidadorPreRequisito(validadorFinalOR);
        
        // Disciplina com co-requisito complexo: deve cursar junto com Laboratorio
        sistemaAcademico.cadastrarDisciplinaObrigatoria("TEO901", "Teoria da Computacao", 100);
        sistemaAcademico.buscarDisciplinaPorCodigo("TEO901").addCoRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("LAB501"));
        sistemaAcademico.buscarDisciplinaPorCodigo("TEO901").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"));
        sistemaAcademico.buscarDisciplinaPorCodigo("TEO901").addPreRequisito(sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"));
    }

    public void inicializaTurmas() {
        // Turmas para disciplinas basicas
        sistemaAcademico.cadastrarTurma("FIS201A", sistemaAcademico.buscarDisciplinaPorCodigo("FIS201"), "Prof. Ribeiro", 25, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("MAT101A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"), "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("MAT102A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"), "Prof. Souza", 25, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("POR101A", sistemaAcademico.buscarDisciplinaPorCodigo("POR101"), "Prof. Lima", 20, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("HIS101A", sistemaAcademico.buscarDisciplinaPorCodigo("HIS101"), "Prof. Andrade", 30, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("BIO201A", sistemaAcademico.buscarDisciplinaPorCodigo("BIO201"), "Prof. Almeida", 30, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("GEO201A", sistemaAcademico.buscarDisciplinaPorCodigo("GEO201"), "Prof. Teixeira", 20, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("QUI301A", sistemaAcademico.buscarDisciplinaPorCodigo("QUI301"), "Prof. Castro", 25, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("ART301A", sistemaAcademico.buscarDisciplinaPorCodigo("ART301"), "Prof. Nogueira", 30, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("MUS301A", sistemaAcademico.buscarDisciplinaPorCodigo("MUS301"), "Prof. Barbosa", 20, "Segunda-feira, 8h - 10h");
        
        // Turmas para disciplinas avancadas
        sistemaAcademico.cadastrarTurma("MAT201A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"), "Prof. Calculus", 20, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("MAT202A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT202"), "Prof. Derivada", 20, "Terca-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("FIS301A", sistemaAcademico.buscarDisciplinaPorCodigo("FIS301"), "Prof. Newton", 15, "Quarta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("EST401A", sistemaAcademico.buscarDisciplinaPorCodigo("EST401"), "Prof. Bayes", 25, "Quinta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("ALG401A", sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"), "Prof. Matriz", 25, "Sexta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("ENG501A", sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"), "Prof. Software", 20, "Segunda-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("BD501A", sistemaAcademico.buscarDisciplinaPorCodigo("BD501"), "Prof. Database", 18, "Terca-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("LAB501A", sistemaAcademico.buscarDisciplinaPorCodigo("LAB501"), "Prof. Code", 15, "Quarta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("IA601A", sistemaAcademico.buscarDisciplinaPorCodigo("IA601"), "Prof. Robot", 12, "Quinta-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("RDC601A", sistemaAcademico.buscarDisciplinaPorCodigo("RDC601"), "Prof. Network", 20, "Sexta-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("SEC701A", sistemaAcademico.buscarDisciplinaPorCodigo("SEC701"), "Prof. Secure", 15, "Segunda-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("TCC801A", sistemaAcademico.buscarDisciplinaPorCodigo("TCC801"), "Prof. Thesis", 10, "Terca-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("MOB801A", sistemaAcademico.buscarDisciplinaPorCodigo("MOB801"), "Prof. Mobile", 15, "Quarta-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("WEB801A", sistemaAcademico.buscarDisciplinaPorCodigo("WEB801"), "Prof. Web", 18, "Quinta-feira, 12h - 14h");
        
        // Turmas para disciplinas com validadores complexos
        sistemaAcademico.cadastrarTurma("CMP901A", sistemaAcademico.buscarDisciplinaPorCodigo("CMP901"), "Prof. Science", 10, "Sexta-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("OPT901A", sistemaAcademico.buscarDisciplinaPorCodigo("OPT901"), "Prof. Optimize", 12, "Segunda-feira, 18h - 20h");
        sistemaAcademico.cadastrarTurma("TEO901A", sistemaAcademico.buscarDisciplinaPorCodigo("TEO901"), "Prof. Theory", 15, "Terca-feira, 18h - 20h");
    }

    public SistemaAcademico getSistemaAcademico() { return sistemaAcademico; }
}
