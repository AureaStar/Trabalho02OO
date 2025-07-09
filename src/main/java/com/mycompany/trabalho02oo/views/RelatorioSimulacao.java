package com.mycompany.trabalho02oo.views;

import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.validators.ValidadorPreRequisito;
import java.util.List;
import java.util.Map;

/**
 * Classe responsavel por gerar relatorios completos e detalhados 
 * da simulacao de matricula, oferecendo transparencia sobre as decisoes tomadas pelo modelo.
 */
public class RelatorioSimulacao {
    
    public RelatorioSimulacao() {
    }
    
    /**
     * Exibe relatorio completo do aluno com todas as informacoes academicas
     */
    public void exibirRelatorioAluno(Aluno aluno) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATORIO ACADEMICO COMPLETO");
        System.out.println("=".repeat(60));
        
        // Informacoes basicas
        System.out.println("[] DADOS PESSOAIS:");
        System.out.println("   Nome: " + aluno.getNome());
        System.out.println("   Matricula: " + aluno.getMatricula());
        System.out.println("   Carga Horaria Maxima: " + aluno.getCargaHorariaMaxima() + "h");
        
        // Historico academico
        exibirHistoricoAcademico(aluno);
        
        // Planejamento atual
        exibirPlanejamentoAtual(aluno);
        
        // Estatisticas
        exibirEstatisticas(aluno);
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe o historico de disciplinas cursadas
     */
    private void exibirHistoricoAcademico(Aluno aluno) {
        System.out.println("\n[] HISTORICO ACADEMICO:");
        
        Map<Disciplina, Double> disciplinasCursadas = aluno.getDisciplinasCursadas();
        
        if (disciplinasCursadas.isEmpty()) {
            System.out.println("   !  Nenhuma disciplina cursada ainda.");
            return;
        }
        
        System.out.println("   Disciplinas Cursadas:");
        for (Map.Entry<Disciplina, Double> entry : disciplinasCursadas.entrySet()) {
            Disciplina disciplina = entry.getKey();
            Double nota = entry.getValue();
            String status = nota >= 60.0 ? "APROVADO" : "REPROVADO";
            String tipo = getTipoDisciplina(disciplina);
            
            System.out.printf("     %s (%s) - %s - Nota: %.1f - %s%n",
                    disciplina.getNome(),
                    disciplina.getCodigo(),
                    tipo,
                    nota,
                    status);
        }
    }
    
    /**
     * Exibe o planejamento atual do aluno
     */
    private void exibirPlanejamentoAtual(Aluno aluno) {
        System.out.println("\n[] PLANEJAMENTO ATUAL:");
        
        List<Turma> planejamento = aluno.getPlanejamentoFuturo();
        
        if (planejamento.isEmpty()) {
            System.out.println("   !  Nenhuma turma no planejamento.");
            return;
        }
        
        System.out.println("   Turmas Matriculadas:");
        int cargaTotal = 0;
        
        for (Turma turma : planejamento) {
            Disciplina disciplina = turma.getDisciplina();
            String tipo = getTipoDisciplina(disciplina);
            cargaTotal += disciplina.getCargaHoraria();
            
            System.out.printf("     %s (%s) - %s%n",
                    disciplina.getNome(),
                    turma.getId(),
                    tipo);
            System.out.printf("     Horario: %s | Carga: %dh | Professor: %s%n",
                    turma.getHorario(),
                    disciplina.getCargaHoraria(),
                    turma.getProfessor());
            System.out.printf("     Vagas: %d/%d%n",
                    turma.getVagasOcupadas(),
                    turma.getVagas());
        }
        
        System.out.printf("\n   [] Carga Horaria Total do Planejamento: %d/%dh%n",
                cargaTotal, aluno.getCargaHorariaMaxima());
    }
    
    /**
     * Exibe estatisticas academicas do aluno
     */
    private void exibirEstatisticas(Aluno aluno) {
        System.out.println("\n[] ESTATISTICAS:");
        
        // Carga horaria cursada (apenas aprovados)
        int cargaCursada = aluno.calcularCargaHorariaCursada();
        System.out.println("   Carga Horaria Cursada (Aprovado): " + cargaCursada + "h");
        
        // Carga horaria planejada
        int cargaPlanejada = aluno.calcularCargaHorariaPlanejamento();
        System.out.println("   Carga Horaria Planejada: " + cargaPlanejada + "h");
        
        // Utilizacao da carga maxima
        double utilizacao = (double) cargaPlanejada / aluno.getCargaHorariaMaxima() * 100;
        System.out.printf("   Utilizacao da Carga Maxima: %.1f%%%n", utilizacao);
        
        // Disciplinas aprovadas
        List<Disciplina> aprovadas = aluno.getDisciplinasAprovadas();
        System.out.println("   Disciplinas Aprovadas: " + aprovadas.size());
        
        // Total de disciplinas cursadas
        int totalCursadas = aluno.getDisciplinasCursadas().size();
        System.out.println("   Total de Disciplinas Cursadas: " + totalCursadas);
        
        if (totalCursadas > 0) {
            double taxaAprovacao = (double) aprovadas.size() / totalCursadas * 100;
            System.out.printf("   Taxa de Aprovacao: %.1f%%%n", taxaAprovacao);
        }
    }
    
    /**
     * Exibe relatorio de disciplinas disponiveis
     */
    public void exibirRelatorioDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATORIO DE DISCIPLINAS DISPONIVEIS");
        System.out.println("=".repeat(60));
        
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        
        // Agrupar por tipo
        disciplinas.stream()
                .filter(d -> d instanceof DisciplinaObrigatoria)
                .forEach(d -> {
                    if (disciplinas.stream().filter(disc -> disc instanceof DisciplinaObrigatoria).findFirst().equals(d)) {
                        System.out.println("\n[] DISCIPLINAS OBRIGATORIAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        disciplinas.stream()
                .filter(d -> d instanceof DisciplinaEletiva)
                .forEach(d -> {
                    if (disciplinas.stream().filter(disc -> disc instanceof DisciplinaEletiva).findFirst().equals(d)) {
                        System.out.println("\n  DISCIPLINAS ELETIVAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        disciplinas.stream()
                .filter(d -> d instanceof DisciplinaOptativa)
                .forEach(d -> {
                    if (disciplinas.stream().filter(disc -> disc instanceof DisciplinaOptativa).findFirst().equals(d)) {
                        System.out.println("\n  DISCIPLINAS OPTATIVAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe detalhes de uma disciplina especifica
     */
    private void exibirDetalheDisciplina(Disciplina disciplina) {
        System.out.printf("     %s (%s) - %dh%n",
                disciplina.getNome(),
                disciplina.getCodigo(),
                disciplina.getCargaHoraria());
        
        // Pre-requisitos
        if (!disciplina.getValidadoresPreRequisito().isEmpty()) {
            System.out.print("     Pre-requisitos: ");
            for (int i = 0; i < disciplina.getValidadoresPreRequisito().size(); i++) {
                if (i > 0) System.out.print(", ");
                System.out.print("[Validador " + (i + 1) + "]");
            }
            System.out.println();
        }
        
        // Co-requisitos
        if (!disciplina.getCoRequisitos().isEmpty()) {
            System.out.print("     Co-requisitos: ");
            for (int i = 0; i < disciplina.getCoRequisitos().size(); i++) {
                if (i > 0) System.out.print(", ");
                Disciplina coReq = disciplina.getCoRequisitos().get(i);
                System.out.print(coReq.getNome() + " (" + coReq.getCodigo() + ")");
            }
            System.out.println();
        }
    }
    
    /**
     * Exibe relatorio de turmas disponiveis
     */
    public void exibirRelatorioTurmas(List<Turma> turmas) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATORIO DE TURMAS DISPONIVEIS");
        System.out.println("=".repeat(60));
        
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        
        for (Turma turma : turmas) {
            System.out.printf("\n  Turma %s - %s%n",
                    turma.getId(),
                    turma.getDisciplina().getNome());
            System.out.printf("   Disciplina: %s (%s)%n",
                    turma.getDisciplina().getNome(),
                    turma.getDisciplina().getCodigo());
            System.out.printf("   Tipo: %s%n", getTipoDisciplina(turma.getDisciplina()));
            System.out.printf("   Professor: %s%n", turma.getProfessor());
            System.out.printf("   Horario: %s%n", turma.getHorario());
            System.out.printf("   Vagas: %d/%d (%.1f%% ocupacao)%n",
                    turma.getVagasOcupadas(),
                    turma.getVagas(),
                    (double) turma.getVagasOcupadas() / turma.getVagas() * 100);
            System.out.printf("   Carga Horaria: %dh%n", turma.getDisciplina().getCargaHoraria());
        }
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe relatorio de simulacao de matricula
     */
    public void exibirSimulacaoMatricula(Aluno aluno, List<Turma> turmasDisponiveis) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULACAO DE MATRICULA");
        System.out.println("=".repeat(60));
        
        System.out.println("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
        System.out.println("Carga Horaria Disponivel: " +
                (aluno.getCargaHorariaMaxima() - aluno.calcularCargaHorariaPlanejamento()) + "h");
        
        System.out.println("\n[] TURMAS DISPONIVEIS PARA MATRICULA:");
        
        for (Turma turma : turmasDisponiveis) {
            System.out.printf("\n   🏫 %s - %s%n", turma.getId(), turma.getDisciplina().getNome());
            
            // Verificar possibilidade de matricula
            String status = verificarPossibilidadeMatricula(aluno, turma);
            System.out.println("      Status: " + status);
        }
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Verifica se e possivel matricular o aluno na turma
     */
    private String verificarPossibilidadeMatricula(Aluno aluno, Turma turma) {
        // Verificar vagas
        if (!turma.temVagasDisponiveis()) {
            return "  Turma cheia";
        }
        
        // Verificar carga horaria
        if (!aluno.podeAdicionarCargaHoraria(turma.getDisciplina().getCargaHoraria())) {
            return "  Carga horaria excedida";
        }
        
        // Verificar pre-requisitos
        for (ValidadorPreRequisito validador : turma.getDisciplina().getValidadoresPreRequisito()) {
            if (!validador.validar(aluno, turma.getDisciplina())) {
                return "  Pre-requisito nao cumprido";
            }
        }
        
        return "  Disponivel para matricula";
    }
    
    /**
     * Retorna o tipo da disciplina como string
     */
    private String getTipoDisciplina(Disciplina disciplina) {
        if (disciplina instanceof DisciplinaObrigatoria) {
            return "Obrigatoria";
        } else if (disciplina instanceof DisciplinaEletiva) {
            return "Eletiva";
        } else if (disciplina instanceof DisciplinaOptativa) {
            return "Optativa";
        }
        return "Desconhecida";
    }
}
