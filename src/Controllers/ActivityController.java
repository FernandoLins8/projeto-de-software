package Controllers;

import Models.Activity;
import Models.Project;
import Models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ActivityController {
    public static void createActivity(Project project) {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        System.out.println("Digite a descrição da atividade: ");
        String description = input.nextLine();

        Calendar startingDate = Calendar.getInstance();
        System.out.println("Digite a data de início da atividade (no formato dd/mm/aaaa)");
        String informedStartingDate = input.nextLine();
        try {
            startingDate.setTime(sdf.parse(informedStartingDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar endingDate = Calendar.getInstance();
        System.out.println("Digite a data de término da atividade (no formato dd/mm/aaaa)");
        String informedEndingDate = input.nextLine();
        try {
            endingDate.setTime(sdf.parse(informedEndingDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Activity newActivity = new Activity(
            description,
            startingDate,
            endingDate,
            project
        );

        project.addActivity(newActivity);
    }

    public static void listAllActivities() {
        System.out.println("Atividades cadastradas: ");

        Project.getProjectList().forEach(project -> {
            ArrayList<Activity> activities = project.getActivities();
            activities.forEach(activity -> System.out.println(activity));
            System.out.println();
        });
    }

    public static void listActivitiesByProject(Project project) {
        System.out.println("Atividades do projeto: ");
        project.listActivities();
    }

//    public static void listActivitiesByProject(int projectId) {
//        Optional<Project> project = ProjectController.getProject(projectId);
//
//        if(project.isPresent()) {
//            System.out.println("Atividades do projeto: ");
//            project.get().listActivities();
//        }
//    }

    public static void deleteActivity(Activity activity) {
        Scanner input = new Scanner(System.in);
        System.out.println("O objeto e todas as suas tarefas serão removidas. Tem certeza?");
        System.out.println("Digite 1 para confirmar ou 0 para voltar atrás:");

        if(input.nextInt() == 1) {
            activity.deleteActivity();
            System.out.println("Atividade removida com sucesso.");
            return;
        }

        System.out.println("Atividade não deletada.");
    }
}
