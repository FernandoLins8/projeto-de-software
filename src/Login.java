import Models.User;
import Models.UserTypes;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
public class Login {
    public static boolean login() {
        if(User.getUserList().isEmpty()) {
            createFirstAccount();
            return false;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Digite seu email: ");
        String email = input.nextLine();

        Optional<User> user = User.getUserByEmail(email);

        if(!user.isPresent()) {
            System.out.println("Email não encontrado. Tente novamente.");
            System.out.println(email);
            System.out.println(user.get());
            return false;
        }

        String password;
        if(!user.get().hasPassword()) {
            System.out.println("Como é seu primeiro cadastro será preciso criar uma senha: ");
            password = input.nextLine();
            user.get().setFirstPassword(password);

            int securityCode = createSecurityCode();
            user.get().setSecurityCode(securityCode);
            System.out.println(String.format("Guarde seu código de segurança: %d", securityCode));

            return true;
        }

        System.out.println("Digite sua senha: ");
        password = input.nextLine();

        if(user.get().isPasswordCorrect(password)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        System.out.println("Login feito com sucesso.");
        return true;
    }

    public static void createFirstAccount() {
        Scanner input = new Scanner(System.in);

        System.out.println("Iremos criar a conta de primeiro usuário.");
        System.out.println("Este usuário será o usuário que irá cadastrar os demais.");
        System.out.println("Digite seu nome: ");
        String name = input.nextLine();

        System.out.println("Digite seu email: ");
        String email = input.nextLine();

        System.out.println("Digite sua senha: ");
        String password = input.nextLine();

        new User(
            name,
            email,
            password,
            UserTypes.values()[3]
        );

        System.out.println("Conta criada com sucesso.");
    }

    public static int createSecurityCode() {
        Random random = new Random();
        // random.nextInt(max - min + 1) + min
        return random.nextInt(1000000 - 1 + 1) + 1;
    }
}
