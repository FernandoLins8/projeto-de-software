package Views;

import Controllers.ProjectController;
import Models.Project;

import java.util.Scanner;

public class SelectedProjectMenu {
    public static void render(Project project) {
        Scanner input = new Scanner(System.in);
        int optionChosen;

        while(true) {
            System.out.println("#########################");
            System.out.println("Gerenciamento de Projetos");
            System.out.println("#########################");
            System.out.println(String.format("Início -> Projetos -> [#%d]", project.getId()));
            System.out.println("#########################");
            System.out.println("0 - Voltar");
            System.out.println("1 - Editar");
            System.out.println("2 - Atividades");
            System.out.println("3 - Associados ao Projeto");
            System.out.println("4 - Deletar este Projeto");
            System.out.println("Digite o número de uma opção acima:");
            optionChosen = input.nextInt();

            switch(optionChosen) {
                case 0:
                    return;
                case 1:
                    System.out.println("Editar");
                    break;
                case 2:
                    ProjectActivitiesMenu.render(project);
                    break;
                case 3:
                    ProjectUsersMenu.render(project);
                    break;
                case 4:
                    ProjectController.deleteProject(project);
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println("Pressione enter para continuar...");
            input.nextLine();
            input.nextLine();
        }
    }
}
