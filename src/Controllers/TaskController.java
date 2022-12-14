package Controllers;

import Models.Activity;
import Models.Project;
import Models.Task;
import Models.User;

import java.util.Optional;
import java.util.Scanner;

public class TaskController {
    public static void listTasksByActivity(int projectId, int activityId) {
        Optional<Project> project = Project.getProject(projectId);

        project.ifPresent(p -> {
            Optional<Activity> activity = p.getActivity(activityId);
            activity.ifPresent(a -> {
                System.out.println("Tarefas da atividade: ");
                a.getTasks().forEach(task -> System.out.println(task));
            });
        });
    }

    public static void addTaskToActivity(int projectId, int activityId) {
        Optional<Project> project = Project.getProject(projectId);

        project.ifPresent(p -> {
            Optional<Activity> activity = p.getActivity(activityId);

            activity.ifPresent(a -> {
                Scanner input = new Scanner(System.in);

                System.out.println("Digite a descrição da tarefa: ");
                String description = input.nextLine();

                Task task = new Task(description);
                a.addTask(task);
                System.out.println("Tarefa adicionada com sucesso");
            });
        });
    }

    public static void removeTaskById(int projectId, int activityId) {
        Optional<Project> project = Project.getProject(projectId);

        project.ifPresent(p -> {
            Optional<Activity> activity = p.getActivity(activityId);

            activity.ifPresent(a -> {
                Scanner input = new Scanner(System.in);

                System.out.println("Digite o ID da tarefa a ser removida: ");
                int taskId = input.nextInt();

                Optional<Task> task = a.getTasks().stream().filter(t -> t.getId() == taskId).findFirst();

                if(task.isPresent()) {
                    activity.get().removeTask(task.get());
                    System.out.println("Tarefa removida.");
                } else {
                    System.out.println("Tarefa não encontrada.");
                }
            });
        });
    }

    public static void associateUserToTask(int projectId, int activityId) {
        Optional<Project> project = Project.getProject(projectId);

        project.ifPresent(p -> {
            Optional<Activity> activity = p.getActivity(activityId);
            activity.ifPresent(a -> {
                Scanner input = new Scanner(System.in);

                System.out.println("Digite o ID da tarefa: ");
                int taskId = input.nextInt();

                Optional<Task> task = a.getTask(taskId);
                if(task.isPresent()) {
                    System.out.println("Digite o ID do usuário: ");
                    int userId = input.nextInt();

                    Optional<User> user = User.getUser(userId);
                    if(user.isPresent()) {
                        task.get().assignUser(user.get());
                        activity.get().addProfessional(user.get());
                        user.get().addTask(task.get());
                        System.out.println("Tarefa associada ao usuário");
                    } else {
                        System.out.println("Usuário não encontrado");
                    }
                } else {
                    System.out.println("Tarefa não encontrada");
                }
            });
        });
    }

    public static void remoUserFromTask(int projectId, int activityId) {
        Optional<Project> project = Project.getProject(projectId);
        Optional<Activity> activity = project.get().getActivity(activityId);

        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID da tarefa: ");
        int taskId = input.nextInt();

        Optional<Task> task = activity.get().getTask(taskId);

        if(!task.isPresent()) {
            System.out.println("Tarefa não encontrada");
        }

        task.get().removeUser();
        System.out.println("Usuário removido da tarefa.");
    }
}
