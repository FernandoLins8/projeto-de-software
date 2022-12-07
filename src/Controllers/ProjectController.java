package Controllers;

import Models.Project;
import Models.User;
import Views.SelectedProjectMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProjectController {
    static public void createProject() {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        System.out.println("Digite a descrição do projeto: ");
        String description = input.nextLine();

        Calendar startingDate = Calendar.getInstance();
        System.out.println("Digite a data de início do projeto (no formato dd/mm/aaaa)");
        String informedStartingDate = input.nextLine();
        try {
            startingDate.setTime(sdf.parse(informedStartingDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar endingDate = Calendar.getInstance();
        System.out.println("Digite a data de término do projeto (no formato dd/mm/aaaa)");
        String informedEndingDate = input.nextLine();
        try {
            endingDate.setTime(sdf.parse(informedEndingDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        new Project(
            description,
            startingDate,
            endingDate
        );
    }

    static public void listProjects() {
        System.out.println("Projetos cadastrados: ");
         Project.getProjectList().forEach(project -> {
            project.showProjectInfo();
        });
    }

    static public void selectProject() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o ID do projeto: ");
        int projectId = input.nextInt();

        Optional<Project> project = Project.getProjectList().stream().filter(e -> e.getId() == projectId).findFirst();
        if (!project.isPresent()) {
            System.out.println("Projeto não encontrado.");
        }

        SelectedProjectMenu.render(project.get());
    }

    static public void listUsersByProject(Project project) {
        project.getAssociatedUsers().forEach(user -> System.out.println(user));
    }

    static public void addUserToProject() {
        Scanner input = new Scanner(System.in);

        ProjectController.listProjects();
        System.out.println("Escolha o projeto (digite o id da opção):");
        int projectId = input.nextInt();

        UserController.listAllUsers();
        System.out.println("Escolha o usuário (digite o id do usuário escolhido):");
        int userId = input.nextInt();

        Optional<Project> project = ProjectController.getProject(projectId);
        Optional<User> user = UserController.getUser(userId);

        if(!project.isPresent() || !user.isPresent()) {
            System.out.println("Projeto ou usuário inválidos.");
        }

        project.ifPresent(projectElement -> {
            user.ifPresent(userElement -> projectElement.associateUser(userElement));
        });
    }

    static public void addUserToProject(Project project) {
        Scanner input = new Scanner(System.in);

        System.out.println("Escolha o usuário (digite o id do usuário escolhido):");
        int userId = input.nextInt();

        Optional<User> user = UserController.getUser(userId);

        if(!user.isPresent()) {
            System.out.println("Projeto ou usuário inválidos.");
            return;
        }

        project.associateUser(user.get());
        System.out.println("Usuário adicionado");
    }

    static public void removeUserFromProject(Project project) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o ID do usuário: ");
        int userId = input.nextInt();

        Optional<User> user = project.getAssociatedUser(userId);

        if(!user.isPresent()) {
            System.out.println("Usuário não encontrado");
            return;
        }

        project.removeUserById(userId);
        user.get().removeProject(project);

        System.out.println("Usuário removido.");
    }

    static public Optional<Project> getProject(int projectId) {
        Optional<Project> project = Project.getProjectList().stream().filter(e -> e.getId() == projectId).findFirst();
        return project;
    }
}
