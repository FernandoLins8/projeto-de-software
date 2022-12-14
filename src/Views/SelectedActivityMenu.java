package Views;

import Controllers.ActivityController;
import Controllers.TaskController;
import Models.Activity;
import Models.Project;

import java.util.Optional;
import java.util.Scanner;

public class SelectedActivityMenu {
    public static void render(int projectId, int activityId) {
        Scanner input = new Scanner(System.in);
        int optionChosen = -1;

        Project project = Project.getProject(projectId).get();
        Activity activity = project.getActivity(activityId).get();

        while(true) {
            System.out.println("#########################");
            System.out.println("Gerenciamento de Projetos");
            System.out.println("#########################");
            System.out.println(String.format(
                    "Início -> Projetos -> #%d -> Atividades -> [#%d]",
                    projectId, activityId)
            );
            System.out.println("#########################");
            System.out.println("0 - Voltar");
            System.out.println("1 - Listar Tarefas");
            System.out.println("2 - Adicionar Nova Tarefa");
            System.out.println("3 - Remover Tarefa");
            System.out.println("4 - Associar Usuário à Tarefa");
            System.out.println("5 - Remover Usuário de Tarefa");
            System.out.println("6 - Deletar esta atividade");
            System.out.println("Digite o número de uma opção acima:");
            optionChosen = input.nextInt();

            if(optionChosen == 0) {
                break;
            }
            else if(optionChosen == 1) {
                TaskController.listTasksByActivity(projectId, activityId);
            }
            else if(optionChosen == 2) {
                TaskController.addTaskToActivity(projectId, activityId);
            }
            else if(optionChosen == 3) {
                TaskController.removeTaskById(projectId, activityId);
            }
            else if(optionChosen == 4) {
                TaskController.associateUserToTask(projectId, activityId);
            }
            else if(optionChosen == 5) {
                TaskController.remoUserFromTask(projectId, activityId);
            }
            else if(optionChosen == 6) {
                ActivityController.deleteActivity(activity);
                break;
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
