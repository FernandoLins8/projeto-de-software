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

        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        UserTypes.showUserTypes();
        System.out.println("Escolha o tipo de usuário (digite o número da opção acima): ");
        int userTypeOption = input.nextInt() - 1;

        new User(
            name,
            email,
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

    public static void deleteUser() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o ID do usuário: ");
        int userId = input.nextInt();
        Optional<User> user = User.getUser(userId);

        if(!user.isPresent()) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("O objeto e todas as suas tarefas serão removidas. Tem certeza?");
        System.out.println("Digite 1 para confirmar ou 0 para voltar atrás:");

        if(input.nextInt() == 1) {
            user.get().delete();
            System.out.println("Usuário deletado com sucesso.");
        } else {
            System.out.println("Usuário não deletado");
        }
    }
}
