//import Views.MainMenu;
//import Views.ProjectMenu;
//
//import java.util.Scanner;
//
//public class Teste {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
//        int optionChosen = -1;
//        while(true) {
//            MainMenu.render();
//            optionChosen = input.nextInt();
//
//            if (optionChosen == 0) {
//                break;
//            }
//            // PROJETOS
//            else if (optionChosen == 1) {
//                optionChosen = 0;
//                while (true) {
//                    ProjectMenu.render();
//                    optionChosen = input.nextInt();
//
//                    if (optionChosen == 0) {
//                        break;
//                    }
//                    if (optionChosen == 1) {
//                        Controllers.ProjectController.listProjects();
//                        System.out.println("Pressione enter para continuar...");
//                        input.nextLine();
//                        input.nextLine();
//                    }
//                    if (optionChosen == 2) {
//                        Controllers.ProjectController.createProject();
//                        System.out.println("Pressione enter para continuar...");
//                        input.nextLine();
//                        input.nextLine();
//                    }
//                    if(optionChosen == 3) {
//                        Controllers.ProjectController.selectProject();
//                        System.out.println("Pressione enter para continuar...");
//                        input.nextLine();
//                        input.nextLine();
//                    }
//                }
//            }
//            // USUARIOS
//            else if (optionChosen == 2) {
//                optionChosen = 0;
//                while (true) {
//                    System.out.println("#########################");
//                    System.out.println("Gerenciamento de Projetos");
//                    System.out.println("#########################");
//                    System.out.println("Início -> [Usuários]");
//                    System.out.println("#########################");
//                    System.out.println("0 - Voltar");
//                    System.out.println("1 - Listar");
//                    System.out.println("2 - Adicionar Novo");
//                    optionChosen = input.nextInt();
//
//                    if (optionChosen == 0) {
//                        break;
//                    }
//                    if (optionChosen == 1) {
//                        Controllers.UserController.listUsers();
//                        System.out.println("Pressione enter para continuar...");
//                        input.nextLine();
//                        input.nextLine();
//                    }
//                    if (optionChosen == 2) {
//                        Controllers.UserController.createUser();
//                        System.out.println("Pressione enter para continuar...");
//                        input.nextLine();
//                        input.nextLine();
//                    }
//                }
//            }
//        }
//    }
//}