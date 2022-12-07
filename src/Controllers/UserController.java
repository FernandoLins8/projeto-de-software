package Controllers;

import Models.User;
import Models.UserTypes;

import java.util.*;

public class UserController {
    static ArrayList<User> users = new ArrayList<User>();

    public static void createUser() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuário: ");
        String name = input.nextLine();

        UserTypes.showUserTypes();
        System.out.println("Escolha o tipo de usuário (digite o número da opção acima): ");
        int userTypeOption = input.nextInt() - 1;

        new User(
            name,
            UserTypes.values()[userTypeOption]
        );
    }

    public static void listAllUsers() {
        System.out.println("Usuários cadastrados: ");
        User.getUserList().forEach(user -> {
            System.out.println(user);
        });
    }

    public static Optional<User> getUser(int userId) {
        Optional<User> user = User.getUserList().stream().filter(e -> e.getId() == userId).findFirst();
        return user;
    }
}
