package com.mycompany.trabalho02oo.views;

import com.mycompany.trabalho02oo.models.*;
import com.mycompany.trabalho02oo.validators.ValidadorPreRequisito;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por gerar relatórios completos e detalhados 
 * da simulação de matrícula, oferecendo transparência sobre as decisões tomadas pelo modelo.
 */
public class RelatorioSimulacao {
    
    public RelatorioSimulacao() {
    }
    
    /**
     * Exibe relatório completo do aluno com todas as informações acadêmicas
     */
    public void exibirRelatorioAluno(Aluno aluno) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIO ACADÊMICO COMPLETO");
        System.out.println("=".repeat(60));
        
        // Informações básicas
        System.out.println("📋 DADOS PESSOAIS:");
        System.out.println("   Nome: " + aluno.getNome());
        System.out.println("   Matrícula: " + aluno.getMatricula());
        System.out.println("   Carga Horária Máxima: " + aluno.getCargaHorariaMaxima() + "h");
        
        // Histórico acadêmico
        exibirHistoricoAcademico(aluno);
        
        // Planejamento atual
        exibirPlanejamentoAtual(aluno);
        
        // Estatísticas
        exibirEstatisticas(aluno);
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe o histórico de disciplinas cursadas
     */
    private void exibirHistoricoAcademico(Aluno aluno) {
        System.out.println("\n📚 HISTÓRICO ACADÊMICO:");
        
        Map<Disciplina, Double> disciplinasCursadas = aluno.getDisciplinasCursadas();
        
        if (disciplinasCursadas.isEmpty()) {
            System.out.println("   ⚠️  Nenhuma disciplina cursada ainda.");
            return;
        }
        
        System.out.println("   Disciplinas Cursadas:");
        for (Map.Entry<Disciplina, Double> entry : disciplinasCursadas.entrySet()) {
            Disciplina disciplina = entry.getKey();
            Double nota = entry.getValue();
            String status = nota >= 60.0 ? "✅ APROVADO" : "❌ REPROVADO";
            String tipo = getTipoDisciplina(disciplina);
            
            System.out.printf("   • %s (%s) - %s - Nota: %.1f - %s%n",
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
        System.out.println("\n🎯 PLANEJAMENTO ATUAL:");
        
        List<Turma> planejamento = aluno.getPlanejamentoFuturo();
        
        if (planejamento.isEmpty()) {
            System.out.println("   ⚠️  Nenhuma turma no planejamento.");
            return;
        }
        
        System.out.println("   Turmas Matriculadas:");
        int cargaTotal = 0;
        
        for (Turma turma : planejamento) {
            Disciplina disciplina = turma.getDisciplina();
            String tipo = getTipoDisciplina(disciplina);
            cargaTotal += disciplina.getCargaHoraria();
            
            System.out.printf("   • %s (%s) - %s%n",
                    disciplina.getNome(),
                    turma.getId(),
                    tipo);
            System.out.printf("     Horário: %s | Carga: %dh | Professor: %s%n",
                    turma.getHorario(),
                    disciplina.getCargaHoraria(),
                    turma.getProfessor());
            System.out.printf("     Vagas: %d/%d%n",
                    turma.getVagasOcupadas(),
                    turma.getVagas());
        }
        
        System.out.printf("\n   📊 Carga Horária Total do Planejamento: %d/%dh%n",
                cargaTotal, aluno.getCargaHorariaMaxima());
    }
    
    /**
     * Exibe estatísticas acadêmicas do aluno
     */
    private void exibirEstatisticas(Aluno aluno) {
        System.out.println("\n📈 ESTATÍSTICAS:");
        
        // Carga horária cursada (apenas aprovados)
        int cargaCursada = aluno.calcularCargaHorariaCursada();
        System.out.println("   Carga Horária Cursada (Aprovado): " + cargaCursada + "h");
        
        // Carga horária planejada
        int cargaPlanejada = aluno.calcularCargaHorariaPlanejamento();
        System.out.println("   Carga Horária Planejada: " + cargaPlanejada + "h");
        
        // Utilização da carga máxima
        double utilizacao = (double) cargaPlanejada / aluno.getCargaHorariaMaxima() * 100;
        System.out.printf("   Utilização da Carga Máxima: %.1f%%%n", utilizacao);
        
        // Disciplinas aprovadas
        List<Disciplina> aprovadas = aluno.getDisciplinasAprovadas();
        System.out.println("   Disciplinas Aprovadas: " + aprovadas.size());
        
        // Total de disciplinas cursadas
        int totalCursadas = aluno.getDisciplinasCursadas().size();
        System.out.println("   Total de Disciplinas Cursadas: " + totalCursadas);
        
        if (totalCursadas > 0) {
            double taxaAprovacao = (double) aprovadas.size() / totalCursadas * 100;
            System.out.printf("   Taxa de Aprovação: %.1f%%%n", taxaAprovacao);
        }
    }
    
    /**
     * Exibe relatório de disciplinas disponíveis
     */
    public void exibirRelatorioDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIO DE DISCIPLINAS DISPONÍVEIS");
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
                        System.out.println("\n📘 DISCIPLINAS OBRIGATÓRIAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        disciplinas.stream()
                .filter(d -> d instanceof DisciplinaEletiva)
                .forEach(d -> {
                    if (disciplinas.stream().filter(disc -> disc instanceof DisciplinaEletiva).findFirst().equals(d)) {
                        System.out.println("\n📗 DISCIPLINAS ELETIVAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        disciplinas.stream()
                .filter(d -> d instanceof DisciplinaOptativa)
                .forEach(d -> {
                    if (disciplinas.stream().filter(disc -> disc instanceof DisciplinaOptativa).findFirst().equals(d)) {
                        System.out.println("\n📙 DISCIPLINAS OPTATIVAS:");
                    }
                    exibirDetalheDisciplina(d);
                });
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe detalhes de uma disciplina específica
     */
    private void exibirDetalheDisciplina(Disciplina disciplina) {
        System.out.printf("   • %s (%s) - %dh%n",
                disciplina.getNome(),
                disciplina.getCodigo(),
                disciplina.getCargaHoraria());
        
        // Pré-requisitos
        if (!disciplina.getValidadoresPreRequisito().isEmpty()) {
            System.out.print("     Pré-requisitos: ");
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
     * Exibe relatório de turmas disponíveis
     */
    public void exibirRelatorioTurmas(List<Turma> turmas) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RELATÓRIO DE TURMAS DISPONÍVEIS");
        System.out.println("=".repeat(60));
        
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        
        for (Turma turma : turmas) {
            System.out.printf("\n🏫 Turma %s - %s%n",
                    turma.getId(),
                    turma.getDisciplina().getNome());
            System.out.printf("   Disciplina: %s (%s)%n",
                    turma.getDisciplina().getNome(),
                    turma.getDisciplina().getCodigo());
            System.out.printf("   Tipo: %s%n", getTipoDisciplina(turma.getDisciplina()));
            System.out.printf("   Professor: %s%n", turma.getProfessor());
            System.out.printf("   Horário: %s%n", turma.getHorario());
            System.out.printf("   Vagas: %d/%d (%.1f%% ocupação)%n",
                    turma.getVagasOcupadas(),
                    turma.getVagas(),
                    (double) turma.getVagasOcupadas() / turma.getVagas() * 100);
            System.out.printf("   Carga Horária: %dh%n", turma.getDisciplina().getCargaHoraria());
        }
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Exibe relatório de simulação de matrícula
     */
    public void exibirSimulacaoMatricula(Aluno aluno, List<Turma> turmasDisponiveis) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SIMULAÇÃO DE MATRÍCULA");
        System.out.println("=".repeat(60));
        
        System.out.println("Aluno: " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
        System.out.println("Carga Horária Disponível: " +
                (aluno.getCargaHorariaMaxima() - aluno.calcularCargaHorariaPlanejamento()) + "h");
        
        System.out.println("\n📋 TURMAS DISPONÍVEIS PARA MATRÍCULA:");
        
        for (Turma turma : turmasDisponiveis) {
            System.out.printf("\n   🏫 %s - %s%n", turma.getId(), turma.getDisciplina().getNome());
            
            // Verificar possibilidade de matrícula
            String status = verificarPossibilidadeMatricula(aluno, turma);
            System.out.println("      Status: " + status);
        }
        
        System.out.println("=".repeat(60));
    }
    
    /**
     * Verifica se é possível matricular o aluno na turma
     */
    private String verificarPossibilidadeMatricula(Aluno aluno, Turma turma) {
        // Verificar vagas
        if (!turma.temVagasDisponiveis()) {
            return "❌ Turma cheia";
        }
        
        // Verificar carga horária
        if (!aluno.podeAdicionarCargaHoraria(turma.getDisciplina().getCargaHoraria())) {
            return "❌ Carga horária excedida";
        }
        
        // Verificar pré-requisitos
        for (ValidadorPreRequisito validador : turma.getDisciplina().getValidadoresPreRequisito()) {
            if (!validador.validar(aluno, turma.getDisciplina())) {
                return "❌ Pré-requisito não cumprido";
            }
        }
        
        return "✅ Disponível para matrícula";
    }
    
    /**
     * Retorna o tipo da disciplina como string
     */
    private String getTipoDisciplina(Disciplina disciplina) {
        if (disciplina instanceof DisciplinaObrigatoria) {
            return "Obrigatória";
        } else if (disciplina instanceof DisciplinaEletiva) {
            return "Eletiva";
        } else if (disciplina instanceof DisciplinaOptativa) {
            return "Optativa";
        }
        return "Desconhecida";
    }
}
