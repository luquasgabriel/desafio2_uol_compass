package sistemaAbrigo;


import java.util.List;
import java.util.Scanner;

public class AbrigoMenu {

    private AbrigoService abrigoService = new AbrigoService();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("=== Menu de Abrigos ===");
            System.out.println("1. Cadastrar Abrigo");
            System.out.println("2. Ver Abrigos");
            System.out.println("3. Editar Abrigo");
            System.out.println("4. Excluir Abrigo");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            //switch para iniciar as funcoes do menu
            switch (opcao) {
                case 1:
                    cadastrarAbrigo();
                    break;
                case 2:
                    listarAbrigos();
                    break;
                case 3:
                    editarAbrigo();
                    break;
                case 4:
                    excluirAbrigo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }
    }

    private void cadastrarAbrigo() {
        Abrigo abrigo = new Abrigo();
        // os dados sao solicitados um a um, passando pela validacao de entrada
        abrigo.setNome(validarEntrada("Nome: ", ".+"));
        abrigo.setEndereco(validarEntrada("Endereco: ", ".+"));
        abrigo.setResponsavel(validarEntrada("Responsavel: ", "^[a-zA-Z\\s]+$"));
        abrigo.setTelefone(validarEntrada("Telefone: ", "^\\d{8,11}$"));
        abrigo.setEmail(validarEntrada("Email: ", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
        abrigo.setCapacidade(validarNumero("Capacidade: "));
        abrigo.setOcupacao(validarNumero("Ocupacao: "));

        abrigoService.criarAbrigo(abrigo);
        System.out.println("Abrigo cadastrado com sucesso!");
    }

    private void listarAbrigos() {
        List<Abrigo> abrigos = abrigoService.listarAbrigos();
        if (abrigos.isEmpty()) {
            System.out.println("Nenhum abrigo cadastrado.");
            return;
        }

        for (int i = 0; i < abrigos.size(); i++) {
            System.out.println((i + 1) + ". " + abrigos.get(i).getNome());
        }

        System.out.print("Escolha um abrigo para ver os detalhes ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= abrigos.size()) {
            verDetalhesAbrigo(abrigos.get(opcao - 1));
        }
    }

    private void verDetalhesAbrigo(Abrigo abrigo) {
        System.out.println("=== Detalhes do Abrigo ===");
        System.out.println("Nome: " + abrigo.getNome());
        System.out.println("Endereco: " + abrigo.getEndereco());
        System.out.println("Responsavel: " + abrigo.getResponsavel());
        System.out.println("Telefone: " + abrigo.getTelefone());
        System.out.println("Email: " + abrigo.getEmail());
        System.out.println("Capacidade: " + abrigo.getCapacidade());
        System.out.println("Ocupacao: " + abrigo.getOcupacao());
        System.out.print("Pressione Enter para voltar ao menu anterior...");
        scanner.nextLine();
    }

    private void editarAbrigo() {
        List<Abrigo> abrigos = abrigoService.listarAbrigos();
        if (abrigos.isEmpty()) {
            System.out.println("Nenhum abrigo cadastrado para edicao.");
            return;
        }

        System.out.println("=== Editar Abrigo ===");
        for (int i = 0; i < abrigos.size(); i++) {
            System.out.println((i + 1) + ". " + abrigos.get(i).getNome());
        }

        System.out.print("Escolha um abrigo para editar ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        if (opcao > 0 && opcao <= abrigos.size()) {
            Abrigo abrigo = abrigos.get(opcao - 1);
            editarDetalhesAbrigo(abrigo);
        }
    }

    private void editarDetalhesAbrigo(Abrigo abrigo) {
        System.out.println("=== Editando Abrigo: " + abrigo.getNome() + " ===");
        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome atual: " + abrigo.getNome());
        String novoNome = validarEntrada("Novo Nome: ", ".*");

        System.out.println("Endereco atual: " + abrigo.getEndereco());
        String novoEndereco = validarEntrada("Novo Endereco: ", ".*");

        System.out.println("Responsavel atual: " + abrigo.getResponsavel());
        String novoResponsavel = validarEntrada("Novo Responsavel: ", "^[a-zA-Z\\s]+$");

        System.out.println("Telefone atual: " + abrigo.getTelefone());
        String novoTelefone = validarEntrada("Novo Telefone: ", "^\\d{8,11}$");
        if (novoTelefone == null) {
            novoTelefone = abrigo.getTelefone(); 
        }

        System.out.println("Email atual: " + abrigo.getEmail());
        String novoEmail = validarEntrada("Novo Email: ", "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        if (novoEmail == null) {
            novoEmail = abrigo.getEmail();
        }

        System.out.println("Capacidade atual: " + abrigo.getCapacidade());
        int novaCapacidade = validarNumeroOpcional("Nova Capacidade: ");

        System.out.println("Ocupacao atual: " + abrigo.getOcupacao());
        int novaOcupacao = validarNumeroOpcional("Nova Ocupacao: ");

        // verificar se a entrada é null antes de atribuir ao abrigo
        if (novoNome != null && !novoNome.isEmpty()) {
            abrigo.setNome(novoNome);
        }
        if (novoEndereco != null && !novoEndereco.isEmpty()) {
            abrigo.setEndereco(novoEndereco);
        }
        if (novoResponsavel != null && !novoResponsavel.isEmpty()) {
            abrigo.setResponsavel(novoResponsavel);
        }
        if (novoTelefone != null && !novoTelefone.isEmpty()) {
            abrigo.setTelefone(novoTelefone);
        }
        if (novoEmail != null && !novoEmail.isEmpty()) {
            abrigo.setEmail(novoEmail);
        }
        if (novaCapacidade != -1) {
            abrigo.setCapacidade(novaCapacidade);
        }
        if (novaOcupacao != -1) {
            abrigo.setOcupacao(novaOcupacao);
        }

        abrigoService.atualizarAbrigo(abrigo);
        System.out.println("Abrigo atualizado com sucesso!");
    }

    private void excluirAbrigo() {
        List<Abrigo> abrigos = abrigoService.listarAbrigos();
        if (abrigos.isEmpty()) {
            System.out.println("Nenhum abrigo cadastrado para exclusao.");
            return;
        }

        System.out.println("=== Excluir Abrigo ===");
        for (int i = 0; i < abrigos.size(); i++) {
            System.out.println((i + 1) + ". " + abrigos.get(i).getNome());
        }

        System.out.print("Escolha um abrigo para excluir ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= abrigos.size()) {
            Abrigo abrigoSelecionado = abrigos.get(opcao - 1);
            confirmarExclusao(abrigoSelecionado);
        }
    }

    private void confirmarExclusao(Abrigo abrigo) {
        System.out.print("Tem certeza que deseja excluir o abrigo " + abrigo.getNome() + "? (S/N): ");
        while (true) {
            String confirmacao = scanner.nextLine().toUpperCase();
            if (confirmacao.equals("S")) {
                abrigoService.deletarAbrigo(abrigo.getId());
                System.out.println("Abrigo excluido com sucesso!");
                break;
            } else if (confirmacao.equals("N")) {
                System.out.println("Exclusao cancelada.");
                break;
            } else {
                System.out.print("Entrada invalida. Por favor, digite S para SIM ou N para NAO: ");
            }
        }
    }

    private String validarEntrada(String mensagem, String regex) {
        String entrada;
        while (true) {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                return null;
            }

            if (entrada.matches(regex)) {
                break;
            } else {
                System.out.println("Entrada invalida. Tente novamente.");
            }
        }
        return entrada;
    }


    private int validarNumero(String mensagem) {
        int numero;
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Entrada invalida. Digite um numero.");
                scanner.next();
            }
        }
        return numero;
    }

    private int validarNumeroOpcional(String mensagem) {
        int numero = -1;
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        if (!entrada.isEmpty()) {
            try {
                numero = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero.");
            }
        }
        return numero;
    }
}