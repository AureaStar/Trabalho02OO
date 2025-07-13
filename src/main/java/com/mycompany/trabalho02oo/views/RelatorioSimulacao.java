package com.mycompany.trabalho02oo.views;

import com.mycompany.trabalho02oo.models.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RelatorioSimulacao {
    private Aluno aluno;
    private List<TurmaAceita> turmasAceitas;
    private List<TurmaRejeitada> turmasRejeitadas;
    private int cargaHorariaTotal;
    private int cargaHorariaAceita;
    
    public RelatorioSimulacao(Aluno aluno) {
        this.aluno = aluno;
        this.turmasAceitas = new ArrayList<>();
        this.turmasRejeitadas = new ArrayList<>();
        this.cargaHorariaTotal = 0;
        this.cargaHorariaAceita = 0;
    }
    
    public void adicionarTurmaAceita(Turma turma, String motivo) {
        turmasAceitas.add(new TurmaAceita(turma, motivo));
        cargaHorariaAceita += turma.getDisciplina().getCargaHoraria();
        cargaHorariaTotal += turma.getDisciplina().getCargaHoraria();
    }
    
    public void adicionarTurmaRejeitada(Turma turma, String motivo) {
        turmasRejeitadas.add(new TurmaRejeitada(turma, motivo));
        cargaHorariaTotal += turma.getDisciplina().getCargaHoraria();
    }
    
    public String gerarRelatorioCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATORIO DE SIMULACAO DE MATRICULA ===\n");
        sb.append("Aluno: ").append(aluno.getNome()).append(" (").append(aluno.getMatricula()).append(")\n");
        sb.append("Limite de Carga Horaria: ").append(aluno.getHorasMaximas()).append(" horas\n");
        sb.append("Carga Horaria Aceita: ").append(cargaHorariaAceita).append(" horas\n\n");
        
        sb.append("DISCIPLINAS ACEITAS (").append(turmasAceitas.size()).append("):\n");
        if (turmasAceitas.isEmpty()) {
            sb.append("  Nenhuma disciplina aceita.\n");
        } else {
            for (TurmaAceita aceita : turmasAceitas) {
                sb.append("  [ACEITA] ").append(aceita.getTurma().getDisciplina().getNome())
                  .append(" (").append(aceita.getTurma().getCodigo()).append(")")
                  .append(" - ").append(aceita.getTurma().getDisciplina().getCargaHoraria()).append("h")
                  .append(" - ").append(getTipoDisciplina(aceita.getTurma().getDisciplina()))
                  .append(" - ").append(aceita.getMotivo()).append("\n");
            }
        }
        
        sb.append("\nDISCIPLINAS REJEITADAS (").append(turmasRejeitadas.size()).append("):\n");
        if (turmasRejeitadas.isEmpty()) {
            sb.append("  Nenhuma disciplina rejeitada.\n");
        } else {
            for (TurmaRejeitada rejeitada : turmasRejeitadas) {
                sb.append("  [REJEITADA] ").append(rejeitada.getTurma().getDisciplina().getNome())
                  .append(" (").append(rejeitada.getTurma().getCodigo()).append(")")
                  .append(" - ").append(rejeitada.getTurma().getDisciplina().getCargaHoraria()).append("h")
                  .append(" - ").append(getTipoDisciplina(rejeitada.getTurma().getDisciplina()))
                  .append(" - ").append(rejeitada.getMotivo()).append("\n");
            }
        }
        
        sb.append("\n=== RESUMO ===\n");
        sb.append("Total de disciplinas processadas: ").append(turmasAceitas.size() + turmasRejeitadas.size()).append("\n");
        sb.append("Disciplinas aceitas: ").append(turmasAceitas.size()).append("\n");
        sb.append("Disciplinas rejeitadas: ").append(turmasRejeitadas.size()).append("\n");
        sb.append("Taxa de sucesso: ").append(calcularTaxaSucesso()).append("%\n");
        sb.append("Utilizacao da carga horaria: ").append(cargaHorariaAceita).append("/").append(aluno.getHorasMaximas()).append(" horas\n");
        
        return sb.toString();
    }
    
    public String gerarRelatorioResumido() {
        StringBuilder sb = new StringBuilder();
        sb.append("Relatorio de ").append(aluno.getNome()).append(": ");
        sb.append(turmasAceitas.size()).append(" aceitas, ");
        sb.append(turmasRejeitadas.size()).append(" rejeitadas. ");
        sb.append("Carga horaria: ").append(cargaHorariaAceita).append("/").append(aluno.getHorasMaximas()).append("h");
        return sb.toString();
    }
    
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
    
    private double calcularTaxaSucesso() {
        int total = turmasAceitas.size() + turmasRejeitadas.size();
        if (total == 0) return 0.0;
        return (double) turmasAceitas.size() / total * 100;
    }
    
    public boolean temTurmasAceitas() {
        return !turmasAceitas.isEmpty();
    }
    
    public boolean temTurmasRejeitadas() {
        return !turmasRejeitadas.isEmpty();
    }
    
    public int getQuantidadeTurmasAceitas() {
        return turmasAceitas.size();
    }
    
    public int getQuantidadeTurmasRejeitadas() {
        return turmasRejeitadas.size();
    }
    
    public List<Turma> getTurmasAceitasLista() {
        return turmasAceitas.stream()
                .map(TurmaAceita::getTurma)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    public List<Turma> getTurmasRejeitadasLista() {
        return turmasRejeitadas.stream()
                .map(TurmaRejeitada::getTurma)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    // Getters
    public Aluno getAluno() {
        return aluno;
    }
    
    public List<TurmaAceita> getTurmasAceitas() {
        return new ArrayList<>(turmasAceitas);
    }
    
    public List<TurmaRejeitada> getTurmasRejeitadas() {
        return new ArrayList<>(turmasRejeitadas);
    }
    
    public int getCargaHorariaTotal() {
        return cargaHorariaTotal;
    }
    
    public int getCargaHorariaAceita() {
        return cargaHorariaAceita;
    }
    
    public void exportarCSV(String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("Tipo,Codigo,Nome,CargaHoraria,Horario,Motivo");
            
            for (TurmaAceita aceita : turmasAceitas) {
                writer.printf("Aceita,%s,%s,%d,%s,%s%n",
                    aceita.getTurma().getDisciplina().getCodigo(),
                    aceita.getTurma().getDisciplina().getNome(),
                    aceita.getTurma().getDisciplina().getCargaHoraria(),
                    aceita.getTurma().getHorario(),
                    aceita.getMotivo());
            }
            
            for (TurmaRejeitada rejeitada : turmasRejeitadas) {
                writer.printf("Rejeitada,%s,%s,%d,%s,%s%n",
                    rejeitada.getTurma().getDisciplina().getCodigo(),
                    rejeitada.getTurma().getDisciplina().getNome(),
                    rejeitada.getTurma().getDisciplina().getCargaHoraria(),
                    rejeitada.getTurma().getHorario(),
                    rejeitada.getMotivo());
            }
        }
    }
    
    public void exportarJSON(String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
            writer.println("{");
            writer.println("  \"relatorio_simulacao\": {");
            writer.println("    \"aluno\": \"" + aluno.getNome() + "\",");
            writer.println("    \"turmas_aceitas\": [");
            
            for (int i = 0; i < turmasAceitas.size(); i++) {
                TurmaAceita aceita = turmasAceitas.get(i);
                writer.println("      {");
                writer.println("        \"codigo\": \"" + aceita.getTurma().getDisciplina().getCodigo() + "\",");
                writer.println("        \"nome\": \"" + aceita.getTurma().getDisciplina().getNome() + "\",");
                writer.println("        \"carga_horaria\": " + aceita.getTurma().getDisciplina().getCargaHoraria() + ",");
                writer.println("        \"horario\": \"" + aceita.getTurma().getHorario() + "\",");
                writer.println("        \"motivo\": \"" + aceita.getMotivo() + "\"");
                writer.print("      }");
                if (i < turmasAceitas.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            
            writer.println("    ],");
            writer.println("    \"turmas_rejeitadas\": [");
            
            for (int i = 0; i < turmasRejeitadas.size(); i++) {
                TurmaRejeitada rejeitada = turmasRejeitadas.get(i);
                writer.println("      {");
                writer.println("        \"codigo\": \"" + rejeitada.getTurma().getDisciplina().getCodigo() + "\",");
                writer.println("        \"nome\": \"" + rejeitada.getTurma().getDisciplina().getNome() + "\",");
                writer.println("        \"carga_horaria\": " + rejeitada.getTurma().getDisciplina().getCargaHoraria() + ",");
                writer.println("        \"horario\": \"" + rejeitada.getTurma().getHorario() + "\",");
                writer.println("        \"motivo\": \"" + rejeitada.getMotivo() + "\"");
                writer.print("      }");
                if (i < turmasRejeitadas.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }
            
            writer.println("    ]");
            writer.println("  }");
            writer.println("}");
        }
    }
    
    // Classes internas para representar turmas aceitas e rejeitadas
    public static class TurmaAceita {
        private Turma turma;
        private String motivo;
        
        public TurmaAceita(Turma turma, String motivo) {
            this.turma = turma;
            this.motivo = motivo;
        }
        
        public Turma getTurma() {
            return turma;
        }
        
        public String getMotivo() {
            return motivo;
        }
    }
    
    public static class TurmaRejeitada {
        private Turma turma;
        private String motivo;
        
        public TurmaRejeitada(Turma turma, String motivo) {
            this.turma = turma;
            this.motivo = motivo;
        }
        
        public Turma getTurma() {
            return turma;
        }
        
        public String getMotivo() {
            return motivo;
        }
    }
}