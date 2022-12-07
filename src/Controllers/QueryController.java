package Controllers;

import Models.Activity;
import Models.Project;
import Models.User;

import java.util.Optional;
import java.util.Scanner;

public class QueryController {
    public static void queryProject() {
        ProjectController.listProjects();

        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID do projeto: ");
        int projectId = input.nextInt();

        Optional<Project> project = Project.getProjectList().stream().filter(e -> e.getId() == projectId).findFirst();
        if (!project.isPresent()) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        project.get().showDetailedInfo();
    }

    public static void queryActivity() {
        ActivityController.listAllActivities();

        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID da atividade desejada: ");
        int activityId = input.nextInt();

        Optional<Project> project = Project.getProjectList().stream().filter(p -> p.hasActivity(activityId)).findFirst();
        Optional<Activity> activity = project.get().getActivity(activityId);

        if(!activity.isPresent()) {
            System.out.println("Atividade não encontrada.");
            return;
        }

        System.out.println();
        System.out.println(String.format("Atividade %d: ", activityId));
        activity.get().showDetailedInfo();
    }

    public static void queryUser() {
        UserController.listAllUsers();

        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID da usuário desejado: ");
        int userId = input.nextInt();

        Optional<User> user = User.getUser(userId);

        if(!user.isPresent()) {
            System.out.println("Usuário não encontrado");
            return;
        }

        user.get().showDetailedInfo();
    }
}
