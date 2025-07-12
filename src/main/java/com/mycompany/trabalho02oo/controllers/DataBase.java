package com.mycompany.trabalho02oo.controllers;

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
        sistemaAcademico.cadastrarAluno("João", "202365172");
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
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT101", "Matemática I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("MAT102", "Matemática II", 60);
        sistemaAcademico.getDisciplinas().get(1).addPreRequisito(sistemaAcademico.getDisciplinas().get(0));
        sistemaAcademico.cadastrarDisciplinaObrigatoria("POR101", "Português I", 60);
        sistemaAcademico.cadastrarDisciplinaObrigatoria("HIS101", "História I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("FIS201", "Física I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("BIO201", "Biologia I", 60);
        sistemaAcademico.cadastrarDisciplinaEletiva("GEO201", "Geografia I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("QUI301", "Química I", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("ART301", "Artes Visuais", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("MUS301", "Música", 60);
        sistemaAcademico.cadastrarDisciplinaOptativa("EDU301", "Educação Física", 60);
    }

    public void inicializaTurmas() {
        sistemaAcademico.cadastrarTurma("TURMA1", sistemaAcademico.getDisciplinas().get(0), "Prof. Silva", 30, "Segunda-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("TURMA2", sistemaAcademico.getDisciplinas().get(1), "Prof. Souza", 25, "Terça-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("TURMA3", sistemaAcademico.getDisciplinas().get(2), "Prof. Lima", 20, "Quarta-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("TURMA4", sistemaAcademico.getDisciplinas().get(3), "Prof. Andrade", 30, "Quinta-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("TURMA5", sistemaAcademico.getDisciplinas().get(4), "Prof. Ribeiro", 25, "Sexta-feira, 16h - 18h");
        sistemaAcademico.cadastrarTurma("TURMA6", sistemaAcademico.getDisciplinas().get(5), "Prof. Almeida", 30, "Segunda-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("TURMA7", sistemaAcademico.getDisciplinas().get(6), "Prof. Teixeira", 20, "Terça-feira, 8h - 10h");
        sistemaAcademico.cadastrarTurma("TURMA8", sistemaAcademico.getDisciplinas().get(7), "Prof. Castro", 25, "Quarta-feira, 14h - 16h");
        sistemaAcademico.cadastrarTurma("TURMA9", sistemaAcademico.getDisciplinas().get(8), "Prof. Nogueira", 30, "Quinta-feira, 10h - 12h");
        sistemaAcademico.cadastrarTurma("TURMA10", sistemaAcademico.getDisciplinas().get(9), "Prof. Barbosa", 20, "Sexta-feira, 8h - 10h");
    }

    public SistemaAcademico getSistemaAcademico() { return sistemaAcademico; }
}
