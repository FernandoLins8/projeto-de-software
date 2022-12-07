package Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class Activity {
    static int activityCounter = 0;

    private int id;
    private String description;
    private Calendar startingDate;
    private Calendar endingDate;

    private User responsible = null;

    private ArrayList<User> professionals = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Activity(String description, Calendar startingDate, Calendar endingDate) {
        this.id = ++activityCounter;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public int getId() {
        return this.id;
    }

    public String getFormatedStartingDate() {
        return String.format(
                "%s/%s/%s",
                this.startingDate.get(Calendar.DATE),
                this.startingDate.get(Calendar.MONTH),
                this.startingDate.get(Calendar.YEAR)
        );
    }

    public String getFormatedEndingDate() {
        return String.format(
                "%s/%s/%s",
                this.endingDate.get(Calendar.DATE),
                this.endingDate.get(Calendar.MONTH),
                this.endingDate.get(Calendar.YEAR)
        );
    }

    public String toString() {
        return String.format(
            "Id.: %d | Description.: %s | Início.: %s | Término.: %s | Responsável.: %s",
            this.id,
            this.description,
            this.getFormatedStartingDate(),
            this.getFormatedEndingDate(),
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

    public void removeProfessionalById(int professionalId) {
        Optional<User> professional = this.professionals.stream().filter(p -> p.getId() == professionalId).findFirst();

        if(!professional.isPresent()) {
            System.out.println("Profissional não cadastrado na atividade ou não existe");
            return;
        }

        this.professionals.remove(professional);
    }

    public void addOrUpdateResponsible(User responsible) {
        if(!professionals.contains(responsible)) {
            professionals.add(responsible);
        }
        this.responsible = responsible;
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

    public void removeTaskById(int taskId) {
        Optional<Task> task = this.tasks.stream().filter(e -> e.getId() == taskId).findFirst();

        if(!task.isPresent()) {
            System.out.println("Tarefa não encontrada");
            return;
        }

        this.tasks.remove(task);
    }
}
