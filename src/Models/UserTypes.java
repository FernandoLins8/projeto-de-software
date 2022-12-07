package Models;

public enum UserTypes {
    Aluno_Graduacao("Aluno de Graduação"),
    Aluno_Mestrado("Aluno de Mestrado"),
    Aluno_Doutorado("Aluno de Doutorado"),
    Professor("Professor"),
    Pesquisador("Pesquisador"),
    Tecnico("Técnico"),
    Profissional_Desenvolvedor("Profissional Desenvolvedor"),
    Profissional_Analista("Profissional Analista"),
    Profissional_Testador("Profissional Testador");

    private String description;

    UserTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    static public void showUserTypes() {
        System.out.println("Tipos de usuários:");
        for(UserTypes type : UserTypes.values()) {
            System.out.println(String.format("%d - %s", UserTypes.valueOf(String.valueOf(type)).ordinal()+1, type));
        }
    }
}
