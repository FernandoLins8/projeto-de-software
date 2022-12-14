package Views;

import Controllers.ProjectController;
import Controllers.UserController;

import java.util.Scanner;

public class UserMenu extends MenuTemplate {
    int renderMenuBody() {
        System.out.println("Início -> [Usuários]");
        System.out.println("#########################");
        System.out.println("0 - Voltar");
        System.out.println("1 - Listar");
        System.out.println("2 - Adicionar Novo");
        System.out.println("3 - Deletar Usuário");
        System.out.println("Digite o número de uma opção acima:");
        optionChosen = input.nextInt();

        switch(optionChosen) {
            case 0:
                return -1;
            case 1:
                UserController.listAllUsers();
                break;
            case 2:
                UserController.createUser();
                break;
            case 3:
                UserController.deleteUser();
                break;
            default:
                System.out.println("Opção inválida");
        }
        return 0;
    }
}

