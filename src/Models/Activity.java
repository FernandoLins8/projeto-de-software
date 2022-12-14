package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class Activity {
    static int activityCounter = 0;

    private int id;
    private String description;
    private Period activityDuration;

    private User responsible = null;

    private ArrayList<User> professionals = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    private Project projectIncluded;

    public Activity(String description, Calendar startingDate, Calendar endingDate, Project projectIncluded) {
        this.id = ++activityCounter;
        this.description = description;
        this.activityDuration = new Period(startingDate, endingDate);

        this.projectIncluded = projectIncluded;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return String.format(
            "Id.: %d | Description.: %s | %s | Responsável.: %s",
            this.id,
            this.description,
            this.activityDuration.toString(),
            this.responsible == null ? "-" : this.responsible.getName()
        );
    }

    public void showDetailedInfo() {
        System.out.println(this);
        System.out.println();
        System.out.println("Tarefas: ");
        this.getTasks().forEach(task -> System.out.println(task));
    }

    public void listProfessionals() {
        System.out.println("Profissionais envolvidos: ");
        professionals.forEach(professional -> System.out.println(professional));
    }

    public void addProfessional(User professional) {
        if(!this.professionals.contains(professional)) {
            this.professionals.add(professional);
            professional.addActivity(this);
        }
    }

    public void removeProfessional(User professional) {
        this.professionals.remove(professional);

        if(professional == this.responsible) {
            this.responsible = null;
        }
    }

    public void addOrUpdateResponsible(User responsible) {
        if(!professionals.contains(responsible)) {
            professionals.add(responsible);
        }
        this.responsible = responsible;
    }

    public ArrayList<User> getProfessionals() {
        return professionals;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Optional<Task> getTask(int taskId) {
        Optional<Task> task = this.tasks.stream().filter(t -> t.getId() == taskId).findFirst();
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.delete();
    }

    public void deleteActivity() {
        this.projectIncluded.removeActivity(this);
        this.removeAssociations();
    }

    public void removeAssociations() {
        this.tasks.forEach(task -> {
            task.removeUser();
            if(task.assignedUser != null) {
                task.assignedUser.removeTask(task);
            }
        });
        this.tasks = null;

        this.professionals.forEach(user -> {
            user.removeActivity(this);
        });
        this.professionals = null;
    }
}
