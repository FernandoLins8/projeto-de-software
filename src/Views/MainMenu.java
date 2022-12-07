package Views;

import java.util.Scanner;

public class MainMenu extends MenuTemplate {
    private UserMenu userMenu = new UserMenu();
    private ProjectMenu projectMenu = new ProjectMenu();
    private QueryMenu queryMenu = new QueryMenu();

     int renderMenuBody() {
        System.out.println("-> [Início]");
        System.out.println("#########################");
        System.out.println("0 - Sair");
        System.out.println("1 - Projetos");
        System.out.println("2 - Usuários");
        System.out.println("3 - Consultas");
        System.out.println("Digite o número de uma opção acima:");
        optionChosen = input.nextInt();

        switch(optionChosen) {
            case 0:
                return -1;
            case 1:
                projectMenu.render();
                break;
            case 2:
                userMenu.render();
                break;
            case 3:
                queryMenu.render();
                break;
            default:
                System.out.println("Opção inválida");
        }
        return 0;
    }
}
