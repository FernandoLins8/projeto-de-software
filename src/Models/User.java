package Models;

import java.util.ArrayList;
import java.util.Optional;

public class User {
    static int userCounter = 0;
    private static ArrayList<User> userList = new ArrayList<User>();

    private int id;
    private String name;
    private String email = "";
    private String password = "";
    private int securityCode;
    private UserTypes userType;

    private ArrayList<Project> projectsAssociated = new ArrayList<Project>();
    private ArrayList<Activity> activitiesAssociated = new ArrayList<Activity>();
    private ArrayList<Task> tasksAssociated = new ArrayList<Task>();

    public User(String name, String email, UserTypes userType) {
        this(name, email, "", userType);
    }

    public User(String name, String email, String password, UserTypes userType) {
        this.id = ++userCounter;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public ArrayList<Project> getProjectsAssociated() {
        return projectsAssociated;
    }

    public ArrayList<Task> getTasksAssociated() {
        return tasksAssociated;
    }

    public ArrayList<Activity> getActivitiesAssociated() {
        return activitiesAssociated;
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
        this.tasksAssociated.add(task);
        task.assignUser(this);
    }

    public void removeTask(Task task) {
        this.tasksAssociated.remove(task);
        if(task.assignedUser == this) {
            task.removeUser();
        }
    }

    public void delete() {
        userList.remove(this);

        this.projectsAssociated.forEach(project -> project.removeUser(this));
        this.projectsAssociated = null;

        this.activitiesAssociated.forEach(activity -> activity.removeProfessional(this));
        this.activitiesAssociated = null;

        this.tasksAssociated.forEach(task -> task.removeUser());
        this.tasksAssociated = null;
    }

    public static Optional<User> getUserByEmail(String email) {
        Optional<User> user = User.userList.stream().filter(u -> u.email == email).findFirst();
        return user;
    }

    public boolean hasPassword() {
        return this.password.length() > 0;
    }

    public boolean isPasswordCorrect(String tried) {
        return this.password == tried;
    }

    public void setFirstPassword(String password) {
        this.password = password;
    }

    public void setSecurityCode(int code) {
        this.securityCode = code;
    }

    public String getEmail() {
        return this.email;
    }
}
