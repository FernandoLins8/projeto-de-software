package Views;

import Controllers.ProjectController;

import java.util.Scanner;

public class ProjectMenu extends MenuTemplate {
    int renderMenuBody() {
        System.out.println("Início -> [Projetos]");
        System.out.println("#########################");
        System.out.println("0 - Voltar");
        System.out.println("1 - Listar");
        System.out.println("2 - Adicionar Novo");
        System.out.println("3 - Selecionar Projeto");
        System.out.println("Digite o número de uma opção acima:");
        optionChosen = input.nextInt();

        switch(optionChosen) {
            case 0:
                return -1;
            case 1:
                ProjectController.listProjects();
                break;
            case 2:
                ProjectController.createProject();
                break;
            case 3:
                ProjectController.selectProject();
                break;
            default:
                System.out.println("Opção inválida");
        }
        return 0;
    }
}
