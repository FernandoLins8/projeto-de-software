import Views.MainMenu;

public class Main {
    public static void main(String[] args) {
        boolean loggedIn = false;
        while(!loggedIn) {
            loggedIn = Login.login();
        }

        MainMenu mainMenu = new MainMenu();
        mainMenu.render();
    }
}