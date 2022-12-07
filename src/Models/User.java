package Models;

import java.util.ArrayList;
import java.util.Optional;

public class User {
    static int userCounter = 0;
    private static ArrayList<User> userList = new ArrayList<User>();

    private int id;
    private String name;
    private UserTypes userType;

    private ArrayList<Project> projectsAssociated = new ArrayList<Project>();
    private ArrayList<Activity> activitiesAssociated = new ArrayList<Activity>();

    public User(String name, UserTypes userType) {
        this.id = ++userCounter;
        this.name = name;
        this.userType = userType;

        User.userList.add(this);
    }

    public static ArrayList<User> getUserList() {
        return User.userList;
    }

    public static Optional<User> getUser(int userId) {
        Optional<User> user = User.userList.stream().filter(u -> u.id == userId).findFirst();
        return user;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return String.format(
            "Id.: %d | Nome.: %s | Tipo.: %s",
            this.id,
            this.name,
            this.userType.getDescription()
        );
    }

    public void showDetailedInfo() {
        System.out.println(this);
        System.out.println();
        System.out.println("Projetos Associados: ");
        this.projectsAssociated.forEach(project -> System.out.println(project));
        System.out.println("Atividades Associadas: ");
        this.activitiesAssociated.forEach(activity -> {
            System.out.println(String.format("Atividade %d", activity.getId()));
            System.out.println(activity);
            if(!activity.getTasks().isEmpty()) {
                System.out.println("Tarefas: ");
                activity.getTasks().forEach(task -> {
                    if(task.getAssignedUser() == this) {
                        System.out.println(task);
                    }
                });
            }
        });
    }

    public void addProject(Project project) {
        if(!this.projectsAssociated.contains(project)) {
            this.projectsAssociated.add(project);
        }
    }

    public void removeProject(Project project) {
        this.projectsAssociated.remove(project);
    }

    public void addActivity(Activity activity) {
        this.activitiesAssociated.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.activitiesAssociated.remove(activity);
    }

    public void addTask(Task task) {

    }

    public void removeTask(Task task) {

    }
}
