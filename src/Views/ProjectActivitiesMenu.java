package Views;

import Controllers.ActivityController;
import Controllers.ProjectController;
import Models.Activity;
import Models.Project;

import java.util.Optional;
import java.util.Scanner;

public class ProjectActivitiesMenu {
    public static void render(Project project) {
        Scanner input = new Scanner(System.in);
        int optionChosen = -1;

        while(true) {
            System.out.println("#########################");
            System.out.println("Gerenciamento de Projetos");
            System.out.println("#########################");
            System.out.println(String.format("Início -> Projetos -> #%d -> [Atividades]", project.getId()));
            System.out.println("#########################");
            System.out.println("0 - Voltar");
            System.out.println("1 - Listar Atividades do Projeto");
            System.out.println("2 - Adicionar Nova");
            System.out.println("3 - Selecionar Atividade");
            System.out.println("Digite o número de uma opção acima:");
            optionChosen = input.nextInt();

            if(optionChosen == 0) {
                break;
            }
            else if(optionChosen == 1) {
                ActivityController.listActivitiesByProject(project);
            }
            else if(optionChosen == 2) {
                ActivityController.createActivity(project);
            }
            else if(optionChosen == 3) {
                System.out.println("Digite o ID da atividade selecionada: ");
                int activityId = input.nextInt();

                if(project.hasActivity(activityId)) {
                    SelectedActivityMenu.render(project.getId(), activityId);
                } else {
                    System.out.println("Atividade não encontrada");
                }
            }
            else {
                System.out.println("Opção inválida");
            }

            System.out.println("Pressione enter para continuar...");
            input.nextLine();
            input.nextLine();
        }
    }
}
