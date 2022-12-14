package Models;

import Models.User;

public class Task {
    static int taskCounter = 0;

    private int id;
    String description;
    User assignedUser = null;

    public Task(String description) {
        id = ++taskCounter;
        this.description = description;
    }

    public String toString() {
        return String.format(
            "id.: %d | %s | %s",
            this.id,
            this.description,
            this.assignedUser == null ? "Não atribuída" : "Atribuída a " + this.assignedUser.getName()
        );
    }

    public int getId() {
        return id;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void assignUser(User user) {
        this.assignedUser = user;
    }

    public void removeUser() {
        this.assignedUser = null;
    }

    public void delete() {
        this.assignedUser.removeTask(this);
        this.assignedUser = null;
    }
}
