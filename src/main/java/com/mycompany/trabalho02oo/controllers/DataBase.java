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
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matematica I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matematica II", 60);
        sistemaAcademico.addPreRequisito("MAT102", "MAT101");
        sistemaAcademico.cadastrarDisciplinaObrigatoria("POR101", "Portugues I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("HIS101", "Historia I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("FIS201", "Fisica I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("BIO201", "Biologia I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("GEO201", "Geografia I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("QUI301", "Quimica I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("ART301", "Artes Visuais", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("MUS301", "Musica", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("EDU301", "Educacao Fisica", 60);
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT201", "Calculo I", 80);
        sistemaAcademico.addPreRequisito("MAT201", "MAT101");
        sistemaAcademico.addPreRequisito("MAT201", "MAT102");
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT202", "Calculo II", 80);
        sistemaAcademico.addPreRequisito("MAT202", "MAT201");
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("FIS301", "Fisica II", 80);
        sistemaAcademico.addPreRequisito("FIS301", "FIS201");
        sistemaAcademico.addPreRequisito("FIS301", "MAT201");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("EST401", "Estatistica", 60);
        sistemaAcademico.addPreRequisito("EST401", "MAT102");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("ALG401", "Algebra Linear", 60);
        sistemaAcademico.addPreRequisito("ALG401", "MAT101");
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("ENG501", "Engenharia de Software", 100);
        sistemaAcademico.addPreRequisito("ENG501", "EST401");
        sistemaAcademico.addPreRequisito("ENG501", "ALG401");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("BD501", "Banco de Dados", 80);
        sistemaAcademico.addPreRequisito("BD501", "ENG501");
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("LAB501", "Laboratorio de Programacao", 60);
        sistemaAcademico.addCoRequisito("LAB501", "ENG501");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("IA601", "Inteligencia Artificial", 100);
        sistemaAcademico.addPreRequisito("IA601", "EST401");
        sistemaAcademico.addPreRequisito("IA601", "ALG401");
        sistemaAcademico.addPreRequisito("IA601", "ENG501");
        
        sistemaAcademico.cadastrarDisciplinaOptativa("RDC601", "Redes de Computadores", 80);
        sistemaAcademico.addPreRequisito("RDC601", "ENG501");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("SEC701", "Seguranca da Informacao", 60);
        sistemaAcademico.addPreRequisito("SEC701", "RDC601");
        sistemaAcademico.addPreRequisito("SEC701", "QUI301");
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("TCC801", "Trabalho de Conclusao de Curso", 120);
        sistemaAcademico.addPreRequisito("TCC801", "ENG501");
        sistemaAcademico.addPreRequisito("TCC801", "BD501");
        sistemaAcademico.addPreRequisito("TCC801", "IA601");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("MOB801", "Desenvolvimento Mobile", 80);
        sistemaAcademico.addPreRequisito("MOB801", "BD501");
        sistemaAcademico.addCoRequisito("MOB801", "LAB501");
        
        sistemaAcademico.cadastrarDisciplinaOptativa("WEB801", "Desenvolvimento Web Avancado", 100);
        sistemaAcademico.addPreRequisito("WEB801", "BD501");
        sistemaAcademico.addPreRequisito("WEB801", "LAB501");
        
        sistemaAcademico.addCoRequisito("FIS301", "MAT202");
        
        sistemaAcademico.addCoRequisito("BD501", "LAB501");
        
        sistemaAcademico.addCoRequisito("IA601", "BD501");
        
        sistemaAcademico.addCoRequisito("SEC701", "RDC601");
        
        sistemaAcademico.addCoRequisito("WEB801", "MOB801");
        
        sistemaAcademico.cadastrarDisciplinaEletiva("TEST901", "Teste de Software", 80);
        sistemaAcademico.addPreRequisito("TEST901", "ENG501");
        sistemaAcademico.addCoRequisito("TEST901", "LAB501");
        sistemaAcademico.addCoRequisito("TEST901", "BD501");
        
        sistemaAcademico.cadastrarDisciplinaOptativa("PROJ901", "Projeto Final", 120);
        sistemaAcademico.addPreRequisito("PROJ901", "ENG501");
        sistemaAcademico.addCoRequisito("PROJ901", "TCC801");
        sistemaAcademico.addCoRequisito("PROJ901", "IA601");
        
        adicionarValidadoresComplexos();
    }
    
    private void adicionarValidadoresComplexos() {
        
        sistemaAcademico.cadastrarDisciplinaEletiva("CMP901", "Computacao Cientifica", 120);
        
        ValidadorSimples validadorMat1 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"));
        ValidadorSimples validadorMat2 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"));
        ValidadorLogicoOR validadorMatOR = new ValidadorLogicoOR(Arrays.asList(validadorMat1, validadorMat2));
        
        ValidadorSimples validadorFis = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("FIS201"));
        ValidadorSimples validadorBio = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("BIO201"));
        ValidadorLogicoOR validadorCienciaOR = new ValidadorLogicoOR(Arrays.asList(validadorFis, validadorBio));
        
        ValidadorLogicoAND validadorCompleto = new ValidadorLogicoAND(validadorMatOR, validadorCienciaOR);
        sistemaAcademico.buscarDisciplinaPorCodigo("CMP901").addValidadorPreRequisito(validadorCompleto);
        
        sistemaAcademico.cadastrarDisciplinaEletiva("OPT901", "Otimizacao", 80);
        
        ValidadorSimples validadorCalc1 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"));
        ValidadorSimples validadorCalc2 = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("MAT202"));
        ValidadorLogicoAND validadorCalculoAND = new ValidadorLogicoAND(validadorCalc1, validadorCalc2);
        
        ValidadorSimples validadorEst = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("EST401"));
        ValidadorSimples validadorAlg = new ValidadorSimples(sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"));
        ValidadorLogicoAND validadorEstatisticaAND = new ValidadorLogicoAND(validadorEst, validadorAlg);
        
        ValidadorLogicoOR validadorFinalOR = new ValidadorLogicoOR(Arrays.asList(validadorCalculoAND, validadorEstatisticaAND));
        sistemaAcademico.buscarDisciplinaPorCodigo("OPT901").addValidadorPreRequisito(validadorFinalOR);
        
        sistemaAcademico.cadastrarDisciplinaObrigatoria("TEO901", "Teoria da Computacao", 100);
        sistemaAcademico.addCoRequisito("TEO901", "LAB501");
        sistemaAcademico.addPreRequisito("TEO901", "ALG401");
        sistemaAcademico.addPreRequisito("TEO901", "ENG501");
    }

    public void inicializaTurmas() {
        sistemaAcademico.cadastrarTurma("QUI301A", sistemaAcademico.buscarDisciplinaPorCodigo("QUI301"), "Prof. Castro", 25, "Segunda-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("ART301A", sistemaAcademico.buscarDisciplinaPorCodigo("ART301"), "Prof. Nogueira", 30, "Segunda-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("MUS301A", sistemaAcademico.buscarDisciplinaPorCodigo("MUS301"), "Prof. Barbosa", 20, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("HIS101A", sistemaAcademico.buscarDisciplinaPorCodigo("HIS101"), "Prof. Andrade", 30, "Sexta-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("EDU301A", sistemaAcademico.buscarDisciplinaPorCodigo("EDU301"), "Prof. Esporte", 25, "Segunda-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("RDC601A", sistemaAcademico.buscarDisciplinaPorCodigo("RDC601"), "Prof. Network", 20, "Terca-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("MAT101A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT101"), "Prof. Silva", 30, "Sexta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("MAT102A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT102"), "Prof. Souza", 25, "Sexta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("POR101A", sistemaAcademico.buscarDisciplinaPorCodigo("POR101"), "Prof. Lima", 20, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("WEB801A", sistemaAcademico.buscarDisciplinaPorCodigo("WEB801"), "Prof. Web", 18, "Terca-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("PROJ901A", sistemaAcademico.buscarDisciplinaPorCodigo("PROJ901"), "Prof. Project", 15, "Terca-feira, 14h - 16h");
        
        sistemaAcademico.cadastrarTurma("FIS201A", sistemaAcademico.buscarDisciplinaPorCodigo("FIS201"), "Prof. Ribeiro", 25, "Terca-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("BIO201A", sistemaAcademico.buscarDisciplinaPorCodigo("BIO201"), "Prof. Almeida", 30, "Terca-feira, 18h - 20h");
        sistemaAcademico.cadastrarTurma("GEO201A", sistemaAcademico.buscarDisciplinaPorCodigo("GEO201"), "Prof. Teixeira", 20, "Quarta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("EST401A", sistemaAcademico.buscarDisciplinaPorCodigo("EST401"), "Prof. Bayes", 25, "Quarta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("ALG401A", sistemaAcademico.buscarDisciplinaPorCodigo("ALG401"), "Prof. Matriz", 25, "Quarta-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("BD501A", sistemaAcademico.buscarDisciplinaPorCodigo("BD501"), "Prof. Database", 18, "Quarta-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("IA601A", sistemaAcademico.buscarDisciplinaPorCodigo("IA601"), "Prof. Robot", 12, "Quarta-feira, 18h - 20h");
        sistemaAcademico.cadastrarTurma("SEC701A", sistemaAcademico.buscarDisciplinaPorCodigo("SEC701"), "Prof. Secure", 15, "Quinta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("MOB801A", sistemaAcademico.buscarDisciplinaPorCodigo("MOB801"), "Prof. Mobile", 15, "Quinta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("CMP901A", sistemaAcademico.buscarDisciplinaPorCodigo("CMP901"), "Prof. Science", 10, "Quinta-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("OPT901A", sistemaAcademico.buscarDisciplinaPorCodigo("OPT901"), "Prof. Optimize", 12, "Quinta-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("TEST901A", sistemaAcademico.buscarDisciplinaPorCodigo("TEST901"), "Prof. Tester", 20, "Quinta-feira, 18h - 20h");
        
        sistemaAcademico.cadastrarTurma("MAT201A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT201"), "Prof. Calculus", 20, "Sexta-feira, 18h - 20h");
        sistemaAcademico.cadastrarTurma("MAT202A", sistemaAcademico.buscarDisciplinaPorCodigo("MAT202"), "Prof. Derivada", 20, "Segunda-feira, 18h - 20h");
        sistemaAcademico.cadastrarTurma("FIS301A", sistemaAcademico.buscarDisciplinaPorCodigo("FIS301"), "Prof. Newton", 15, "Segunda-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("ENG501A", sistemaAcademico.buscarDisciplinaPorCodigo("ENG501"), "Prof. Software", 20, "Terca-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("LAB501A", sistemaAcademico.buscarDisciplinaPorCodigo("LAB501"), "Prof. Code", 15, "Quarta-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("TCC801A", sistemaAcademico.buscarDisciplinaPorCodigo("TCC801"), "Prof. Thesis", 10, "Quinta-feira, 12h - 14h");
        sistemaAcademico.cadastrarTurma("TEO901A", sistemaAcademico.buscarDisciplinaPorCodigo("TEO901"), "Prof. Theory", 15, "Sexta-feira, 12h - 14h");
    }

    public SistemaAcademico getSistemaAcademico() { return sistemaAcademico; }
}
