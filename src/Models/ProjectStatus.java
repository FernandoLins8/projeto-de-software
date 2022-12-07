package Models;

public enum ProjectStatus {
    Em_Processo_de_Criacao("Em processo de criação"),
    Iniciado("Iniciado"),
    Em_Andamento("Em andamento"),
    Concluido("Concluído");

    private String description;

    ProjectStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
