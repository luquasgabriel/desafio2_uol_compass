package sistemaAbrigo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = validarNumero(scanner, "Escolha uma opcao: ");
            
            //switch que inicia as funcionalidades
            switch (opcao) {
                case 1:
                    AbrigoMenu abrigoMenu = new AbrigoMenu();
                    abrigoMenu.exibirMenu();
                    break;
                case 2:
                    DoacoesMenu doacoesMenu = new DoacoesMenu();
                    doacoesMenu.exibirMenu();
                    break;
                case 3:
                    System.out.println("opcao 3");
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    JPAUtil.close();
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Abrigos");
        System.out.println("2. Doacoes");
        System.out.println("3. Centros de Distribuicao");
        System.out.println("4. Sair");
    }
    
    //valida o valor inserido
    private static int validarNumero(Scanner scanner, String mensagem) {
        int numero;
        while (true) {
            try {
                System.out.print(mensagem);
                numero = Integer.parseInt(scanner.nextLine().trim());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Digite uma opção valida");
            }
        }
    }
}
