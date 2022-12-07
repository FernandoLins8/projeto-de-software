package Views;

import Controllers.ProjectController;
import Controllers.UserController;
import Models.Project;

import java.util.Scanner;

public class ProjectUsersMenu {
    public static void render(Project project) {
        Scanner input = new Scanner(System.in);
        int optionChosen;

        while(true) {
            System.out.println("#########################");
            System.out.println("Gerenciamento de Projetos");
            System.out.println("#########################");
            System.out.println(String.format(
                    "Início -> Projetos -> #%d -> Associados",
                    project.getId())
            );
            System.out.println("#########################");
            System.out.println("0 - Voltar");
            System.out.println("1 - Listar Usuários");
            System.out.println("2 - Listar Usuários no Projeto");
            System.out.println("3 - Associar Usuário ao Projeto");
            System.out.println("4 - Remover Usuários do Projeto");
            System.out.println("Digite o número de uma opção acima:");
            optionChosen = input.nextInt();

            switch(optionChosen) {
                case 0:
                    return;
                case 1:
                    UserController.listAllUsers();
                    break;
                case 2:
                    ProjectController.listUsersByProject(project);
                    break;
                case 3:
                    ProjectController.addUserToProject(project);
                    break;
                case 4:
                    ProjectController.removeUserFromProject(project);
                    break;
                default:
                    System.out.println("Opção inválida");
            }

            System.out.println("Pressione enter para continuar...");
            input.nextLine();
            input.nextLine();
        }

    }
}
