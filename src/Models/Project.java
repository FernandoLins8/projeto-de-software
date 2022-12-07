package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class Project {
    static int projectCounter = 0;
    private static ArrayList<Project> projectList = new ArrayList<Project>();

    private int id;
    private String description;
    private Period projectDuration;

    private ProjectStatus status = ProjectStatus.Em_Processo_de_Criacao;

    private User coordinator = null;

    private ArrayList<User> associatedUsers = new ArrayList<User>();

    private ArrayList<Activity> activities = new ArrayList<Activity>();

    //(viii) Valor da bolsa para cada profissional;
    //(ix) Período de vigência da bolsa.

    public Project(String description, Calendar startingDate, Calendar endingDate) {
        this.id = ++projectCounter;
        this.description = description;
        this.projectDuration = new Period(startingDate, endingDate);
        status = ProjectStatus.Em_Processo_de_Criacao;

        projectList.add(this);
    }

    public static ArrayList<Project> getProjectList() {
        return projectList;
    }

    public static Optional<Project> getProject(int projectId) {
        Optional<Project> project = Project.getProjectList().stream().filter(p -> p.id == projectId).findFirst();
        return project;
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    public Optional<Activity> getActivity(int activityId) {
        Optional<Activity> activity = this.activities.stream().filter(a -> a.getId() == activityId).findFirst();
        return activity;
    }

    public Optional<User> getAssociatedUser(int userId) {
        Optional<User> user = this.associatedUsers.stream().filter(u -> u.getId() == userId).findFirst();
        return user;
    }

    public void showProjectInfo() {
        System.out.println(String.format(
            "Id.: %d | Descrição.: %s | Status.: %s | %s",
            this.id,
            this.description,
            this.status.getDescription(),
            this.projectDuration.toString()
        ));
    }

    public void showDetailedInfo() {
        System.out.println(this);
        System.out.println();

        System.out.println("Usuários Associados: ");
        this.associatedUsers.forEach(System.out::println);

        System.out.println("Atividades Associadas: ");
        this.activities.forEach(activity -> {
            System.out.println(String.format("Atividade %d", activity.getId()));
            System.out.println(activity);

            if(!activity.getTasks().isEmpty()) {
                System.out.println("Tarefas: ");
                activity.getTasks().forEach(System.out::println);
            }
        });
    }

    public String toString() {
        return String.format(
        "Id.: %d | Descrição.: %s | Status.: %s | %s",
            this.id,
            this.description,
            this.status.getDescription(),
            this.projectDuration.toString()
        );
    }

    public void updateStatusToInitialized() {
        if(this.status.equals(ProjectStatus.Em_Processo_de_Criacao)) {
            this.status = ProjectStatus.Iniciado;
        }
    }

    public void updateStatusToInProgress() {
        Calendar today = Calendar.getInstance();
        if(this.status.equals(ProjectStatus.Iniciado) && this.projectDuration.getStartingDate().compareTo(today) >= 0) {
            this.status = ProjectStatus.Em_Andamento;
        }
    }

    public void updateStatusToFinished() {
        Calendar today = Calendar.getInstance();
        if(this.status.equals(ProjectStatus.Em_Andamento)) {
            this.status = ProjectStatus.Concluido;
            this.projectDuration.setEndingDate(Calendar.getInstance());
        }
    }

    public void addOrUpdateCoordinator(User user) {
        this.coordinator = user;
    }

    public ArrayList<User> getAssociatedUsers() {
        return this.associatedUsers;
    }

    public void associateUser(User user) {
        if(!this.associatedUsers.contains(user)) {
            this.associatedUsers.add(user);
            user.addProject(this);
        }
    }

    public void removeUserById(int userId) {
        Optional<User> user = this.associatedUsers.stream().filter(u -> u.getId() == userId).findFirst();

        if(!user.isPresent()) {
            System.out.println("Usuário não encontrado");
            return;
        }

        this.associatedUsers.remove(user);
        System.out.println("Usuário removido");
    }

    public void listActivities() {
        this.activities.forEach(activity -> System.out.println(activity));
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void removeActivityById(int activityId) {
        Optional<Activity> activity = this.activities.stream().filter(e -> e.getId() == activityId).findFirst();

        if(!activity.isPresent()) {
            System.out.println("Atividade não encontrada");
            return;
        }

        this.activities.remove(activity);
        System.out.println("Atividade removida");
    }

    public boolean hasActivity(int activityId) {
        Optional<Activity> activity =  this.activities.stream()
                .filter(a -> a.getId() == activityId).findFirst();

        if(activity.isPresent()) {
            return true;
        }

        return false;
    }
}
