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

    public Task(String description, User assignedUser) {
        id = taskCounter++;
        this.description = description;
        this.assignedUser = assignedUser;
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
        user.addTask(this);
    }

    public User removeUser() {
        User removedUser = this.assignedUser;
        this.assignedUser = null;

        return removedUser;
    }
}
