package Views;

import java.util.Scanner;

abstract class MenuTemplate {
    protected Scanner input = new Scanner(System.in);
    int optionChosen;
    int exitCode;
    public void render() {
        while(true) {
            renderHeader();
            exitCode = renderMenuBody();
            if(exitCode == -1) {
                break;
            }
            renderContinue();
        }
    }

     abstract int renderMenuBody();

    public static void renderHeader() {
        System.out.println("#########################");
        System.out.println("Gerenciamento de Projetos");
        System.out.println("#########################");
    }

    public void renderContinue() {
        System.out.println("Pressione enter para continuar...");
        input.nextLine();
        input.nextLine();
    }
}
