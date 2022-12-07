package Views;

import Controllers.QueryController;

import java.util.Scanner;

public class QueryMenu extends MenuTemplate {
    int renderMenuBody() {
        System.out.println("Início -> [Consultas]");
        System.out.println("#########################");
        System.out.println("0 - Voltar");
        System.out.println("1 - Consultar Projeto");
        System.out.println("2 - Consultar Usuário");
        System.out.println("3 - Consultar Atividade");
        System.out.println("Digite o número de uma opção acima:");
        optionChosen = input.nextInt();

        switch(optionChosen) {
            case 0:
                return -1;
            case 1:
                QueryController.queryProject();
                break;
            case 2:
                QueryController.queryUser();
                break;
            case 3:
                QueryController.queryActivity();
                break;
            default:
                System.out.println("Opção inválida");
        }
        return 0;
    }
}
