package sistemaAbrigo;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class DoacoesMenu {

    private DoacaoService doacaoService = new DoacaoService();
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        while (true) {
            System.out.println("=== Menu de Doações ===");
            System.out.println("1. Cadastrar Doação");
            System.out.println("2. Ver Doações");
            System.out.println("3. Editar Doação");
            System.out.println("4. Excluir Doação");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarDoacao();
                    break;
                case 2:
                    listarDoacoes();
                    break;
                case 3:
                    editarDoacao();
                    break;
                case 4:
                    excluirDoacao();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    private void cadastrarDoacao() {
        System.out.println("=== Cadastrar Doação ===");
        System.out.println("1. Roupa");
        System.out.println("2. Produto de Higiene");
        System.out.println("3. Alimento");
        System.out.println("4. Cadastrar por CSV");
        System.out.print("Escolha o tipo de doação: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        switch (tipo) {
            case 1:
                cadastrarRoupa();
                break;
            case 2:
                cadastrarProdutoHigiene();
                break;
            case 3:
                cadastrarAlimento();
                break;
            case 4:
                cadastrarDoacoesPorCSV();
                break;
            default:
                System.out.println("Tipo de doação inválido.");
        }
    }
    

    private void cadastrarRoupa() {
        Roupa roupa = new Roupa();

        roupa.setDescricao(validarEntrada("Descrição: ", ".+"));
        roupa.setGenero(validarEntrada("Gênero (M/F): ", "[MF]"));
        roupa.setTamanho(validarEntrada("Tamanho (Infantil/PP/P/M/G/GG): ", "(Infantil|PP|P|M|G|GG)"));

        doacaoService.criarRoupa(roupa);
        System.out.println("Roupa cadastrada com sucesso!");
    }

    private void cadastrarProdutoHigiene() {
        ProdutoHigiene produtoHigiene = new ProdutoHigiene();

        produtoHigiene.setDescricao(validarEntrada("Descrição: ", ".+"));
        produtoHigiene.setQuantidade(validarNumero("Quantidade: "));

        doacaoService.criarProdutoHigiene(produtoHigiene);
        System.out.println("Produto de higiene cadastrado com sucesso!");
    }

    private void cadastrarAlimento() {
        Alimento alimento = new Alimento();

        alimento.setDescricao(validarEntrada("Descrição: ", ".+"));
        alimento.setQuantidade(validarNumero("Quantidade: "));
        alimento.setUnidadeMedida(validarEntrada("Unidade de Medida: ", ".+"));
        alimento.setValidade(validarData("Validade (yyyy-MM-dd): "));

        doacaoService.criarAlimento(alimento);
        System.out.println("Alimento cadastrado com sucesso!");
    }

    private void listarDoacoes() {
        System.out.println("=== Ver Doações ===");
        System.out.println("1. Roupas");
        System.out.println("2. Produtos de Higiene");
        System.out.println("3. Alimentos");
        System.out.print("Escolha o tipo de doação para listar: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        switch (tipo) {
            case 1:
                listarRoupas();
                break;
            case 2:
                listarProdutosHigiene();
                break;
            case 3:
                listarAlimentos();
                break;
            default:
                System.out.println("Tipo de doação inválido.");
        }
    }

    private void listarRoupas() {
        List<Roupa> roupas = doacaoService.listarRoupas();
        if (roupas.isEmpty()) {
            System.out.println("Nenhuma roupa cadastrada.");
            return;
        }

        for (int i = 0; i < roupas.size(); i++) {
            System.out.println((i + 1) + ". " + roupas.get(i).getDescricao());
        }

        System.out.print("Escolha uma roupa para ver os detalhes ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (opcao > 0 && opcao <= roupas.size()) {
            verDetalhesRoupa(roupas.get(opcao - 1));
        }
    }

    private void verDetalhesRoupa(Roupa roupa) {
        System.out.println("=== Detalhes da Roupa ===");
        System.out.println("Descrição: " + roupa.getDescricao());
        System.out.println("Gênero: " + roupa.getGenero());
        System.out.println("Tamanho: " + roupa.getTamanho());
        System.out.print("Pressione Enter para voltar ao menu anterior...");
        scanner.nextLine();
    }

    private void listarProdutosHigiene() {
        List<ProdutoHigiene> produtosHigiene = doacaoService.listarProdutosHigiene();
        if (produtosHigiene.isEmpty()) {
            System.out.println("Nenhum produto de higiene cadastrado.");
            return;
        }

        for (int i = 0; i < produtosHigiene.size(); i++) {
            System.out.println((i + 1) + ". " + produtosHigiene.get(i).getDescricao());
        }

        System.out.print("Escolha um produto de higiene para ver os detalhes ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (opcao > 0 && opcao <= produtosHigiene.size()) {
            verDetalhesProdutoHigiene(produtosHigiene.get(opcao - 1));
        }
    }

    private void verDetalhesProdutoHigiene(ProdutoHigiene produtoHigiene) {
        System.out.println("=== Detalhes do Produto de Higiene ===");
        System.out.println("Descrição: " + produtoHigiene.getDescricao());
        System.out.println("Quantidade: " + produtoHigiene.getQuantidade());
        System.out.print("Pressione Enter para voltar ao menu anterior...");
        scanner.nextLine();
    }

    private void listarAlimentos() {
        List<Alimento> alimentos = doacaoService.listarAlimentos();
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado.");
            return;
        }

        for (int i = 0; i < alimentos.size(); i++) {
            System.out.println((i + 1) + ". " + alimentos.get(i).getDescricao());
        }

        System.out.print("Escolha um alimento para ver os detalhes ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        if (opcao > 0 && opcao <= alimentos.size()) {
            verDetalhesAlimento(alimentos.get(opcao - 1));
        }
    }

    private void verDetalhesAlimento(Alimento alimento) {
        System.out.println("=== Detalhes do Alimento ===");
        System.out.println("Descrição: " + alimento.getDescricao());
        System.out.println("Quantidade: " + alimento.getQuantidade());
        System.out.println("Unidade de Medida: " + alimento.getUnidadeMedida());
        System.out.println("Validade: " + alimento.getValidade());
        System.out.print("Pressione Enter para voltar ao menu anterior...");
        scanner.nextLine();
    }

    private void editarDoacao() {
        System.out.println("=== Editar Doação ===");
        System.out.println("1. Roupa");
        System.out.println("2. Produto de Higiene");
        System.out.println("3. Alimento");
        System.out.print("Escolha o tipo de doação para editar: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha

        switch (tipo) {
            case 1:
                editarRoupa();
                break;
            case 2:
                editarProdutoHigiene();
                break;
            case 3:
                editarAlimento();
                break;
            default:
                System.out.println("Tipo de doação inválido.");
        }
    }

    private void editarRoupa() {
        // pega a lista de roupas do serviço de doação
        List<Roupa> roupas = doacaoService.listarRoupas();
        
        // verifica se a lista de roupas está vazia
        if (roupas.isEmpty()) {

            System.out.println("Nenhuma roupa cadastrada para edição.");
            return;
        }

        // itera sobre a lista de roupas e imprime a descrição de cada uma com um indice
        for (int i = 0; i < roupas.size(); i++) {
            System.out.println((i + 1) + ". " + roupas.get(i).getDescricao());
        }

        System.out.print("Escolha uma roupa para editar ou 0 para voltar: ");
        int opcao = scanner.nextInt(); // Lê a escolha do usuário
        scanner.nextLine(); // Consome a nova linha deixada pelo nextInt()

        // verifica se a escolha do usuário e válida (entre 1 e o tamanho da lista de roupas)
        if (opcao > 0 && opcao <= roupas.size()) {
            Roupa roupa = roupas.get(opcao - 1);
            
            editarDetalhesRoupa(roupa);
        }
    }

    private void editarDetalhesRoupa(Roupa roupa) {
        System.out.println("=== Editando Roupa: " + roupa.getDescricao() + " ===");

        // mostrar descriçao atual e solicitar nova descrição
        System.out.println("Descrição atual: " + roupa.getDescricao());
        String novaDescricao = validarEntrada("Nova Descrição: ", ".+");
        roupa.setDescricao(novaDescricao);

        // mostrar genero atual e solicitar novo gênero
        System.out.println("Gênero atual: " + roupa.getGenero());
        String novoGenero = validarEntrada("Novo Gênero (M/F): ", "[MF]");
        roupa.setGenero(novoGenero);

        // mostrar tamanho atual e solicitar novo tamanho
        System.out.println("Tamanho atual: " + roupa.getTamanho());
        String novoTamanho = validarEntrada("Novo Tamanho (Infantil/PP/P/M/G/GG): ", "(Infantil|PP|P|M|G|GG)");
        roupa.setTamanho(novoTamanho);

        // atualizar a roupa com os novos dados
        doacaoService.atualizarRoupa(roupa);
        System.out.println("Roupa atualizada com sucesso!");
    }



    private void editarProdutoHigiene() {
        //pega a lista de produtos de higiene do serviço de doaçao
        List<ProdutoHigiene> produtosHigiene = doacaoService.listarProdutosHigiene();
        
        //Verifica se a lista de produtos de higiene esta vazia
        if (produtosHigiene.isEmpty()) {
            System.out.println("Nenhum produto de higiene cadastrado para edição.");
            return;
        }

        //itera sobre a lista de produtos de higiene e imprime a descrição de cada um
        for (int i = 0; i < produtosHigiene.size(); i++) {
            System.out.println((i + 1) + ". " + produtosHigiene.get(i).getDescricao());
        }

        //solicita ao usuário que escolha um produto de higiene para editar ou digite 0 para voltar
        System.out.print("Escolha um produto de higiene para editar ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        //verifica se a escolha do usuário é válida (entre 1 e o tamanho da lista de produtos de higiene)
        if (opcao > 0 && opcao <= produtosHigiene.size()) {
            ProdutoHigiene produtoHigiene = produtosHigiene.get(opcao - 1);
            
            editarDetalhesProdutoHigiene(produtoHigiene);
        }
    }


    private void editarDetalhesProdutoHigiene(ProdutoHigiene produtoHigiene) {
        // exibe a descrição do produto de higiene que está sendo editado
        System.out.println("=== Editando Produto de Higiene: " + produtoHigiene.getDescricao() + " ===");

        // mostra a descrição atual do produto
        System.out.println("Descrição atual: " + produtoHigiene.getDescricao());
        // solicita a nova descrição do produto e atualiza o objeto
        String novaDescricao = validarEntrada("Nova Descrição: ", ".+");
        produtoHigiene.setDescricao(novaDescricao);

        // mostra a quantidade atual do produto
        System.out.println("Quantidade atual: " + produtoHigiene.getQuantidade());
        // solicita a nova quantidade do produto e atualiza o objeto
        int novaQuantidade = validarNumero("Nova Quantidade: ");
        produtoHigiene.setQuantidade(novaQuantidade);

        doacaoService.atualizarProdutoHigiene(produtoHigiene);
        System.out.println("Produto de higiene atualizado com sucesso!");
    }

    
    private void editarAlimento() {
        List<Alimento> alimentos = doacaoService.listarAlimentos();
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado para edição.");
            return;
        }

        for (int i = 0; i < alimentos.size(); i++) {
            System.out.println((i + 1) + ". " + alimentos.get(i).getDescricao());
        }

        System.out.print("Escolha um alimento para editar ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= alimentos.size()) {
            Alimento alimento = alimentos.get(opcao - 1);
            editarDetalhesAlimento(alimento);
        }
    }

    private void editarDetalhesAlimento(Alimento alimento) {
        System.out.println("=== Editando Alimento: " + alimento.getDescricao() + " ===");

        System.out.println("Descrição atual: " + alimento.getDescricao());
        String novaDescricao = validarEntrada("Nova Descrição: ", ".+");
        alimento.setDescricao(novaDescricao);

        System.out.println("Nova Quantidade: ");
        int novaQuantidade = validarNumero("Nova Quantidade: ");
        alimento.setQuantidade(novaQuantidade);

        System.out.println("Nova Unidade de Medida: ");
        String novaUnidadeMedida = validarEntrada("Nova Unidade de Medida: ", ".+");
        alimento.setUnidadeMedida(novaUnidadeMedida);

        System.out.println("Nova Validade (yyyy-MM-dd): ");
        Date novaValidade = validarData("Nova Validade (yyyy-MM-dd): ");
        alimento.setValidade(novaValidade);

        doacaoService.atualizarAlimento(alimento);
        System.out.println("Alimento atualizado com sucesso!");
    }

    private void excluirDoacao() {
        System.out.println("=== Excluir Doação ===");
        System.out.println("1. Roupa");
        System.out.println("2. Produto de Higiene");
        System.out.println("3. Alimento");
        System.out.print("Escolha o tipo de doação para excluir: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        switch (tipo) {
            case 1:
                excluirRoupa();
                break;
            case 2:
                excluirProdutoHigiene();
                break;
            case 3:
                excluirAlimento();
                break;
            default:
                System.out.println("Tipo de doação inválido.");
        }
    }

    private void excluirRoupa() {
        List<Roupa> roupas = doacaoService.listarRoupas();
        if (roupas.isEmpty()) {
            System.out.println("Nenhuma roupa cadastrada para exclusão.");
            return;
        }

        for (int i = 0; i < roupas.size(); i++) {
            System.out.println((i + 1) + ". " + roupas.get(i).getDescricao());
        }

        System.out.print("Escolha uma roupa para excluir ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= roupas.size()) {
            doacaoService.deletarRoupa(roupas.get(opcao - 1).getId());
            System.out.println("Roupa excluída com sucesso!");
        }
    }

    private void excluirProdutoHigiene() {
        List<ProdutoHigiene> produtosHigiene = doacaoService.listarProdutosHigiene();
        if (produtosHigiene.isEmpty()) {
            System.out.println("Nenhum produto de higiene cadastrado para exclusão.");
            return;
        }

        for (int i = 0; i < produtosHigiene.size(); i++) {
            System.out.println((i + 1) + ". " + produtosHigiene.get(i).getDescricao());
        }

        System.out.print("Escolha um produto de higiene para excluir ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= produtosHigiene.size()) {
            doacaoService.deletarProdutoHigiene(produtosHigiene.get(opcao - 1).getId());
            System.out.println("Produto de higiene excluído com sucesso!");
        }
    }

    private void excluirAlimento() {
        List<Alimento> alimentos = doacaoService.listarAlimentos();
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado para exclusão.");
            return;
        }

        for (int i = 0; i < alimentos.size(); i++) {
            System.out.println((i + 1) + ". " + alimentos.get(i).getDescricao());
        }

        System.out.print("Escolha um alimento para excluir ou 0 para voltar: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao > 0 && opcao <= alimentos.size()) {
            doacaoService.deletarAlimento(alimentos.get(opcao - 1).getId());
            System.out.println("Alimento excluído com sucesso!");
        }
    }

    private String validarEntrada(String mensagem, String regex) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        while (!entrada.matches(regex)) {
            System.out.println("Entrada inválida. Tente novamente.");
            System.out.print(mensagem);
            entrada = scanner.nextLine();
        }
        return entrada;
    }

    private int validarNumero(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            System.out.print(mensagem);
            scanner.next();
        }
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
    
    //verifica se a data e valida
    private Date validarData(String mensagem) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            
            java.util.Date utilDate = dateFormat.parse(entrada);
            
            Date sqlDate = new Date(utilDate.getTime());
            
            return sqlDate;
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return validarData(mensagem);
        }
    }
    
    private void cadastrarDoacoesPorCSV() {
        System.out.print("Digite o caminho do arquivo CSV: ");
        String caminhoCSV = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;  // ignora a primeira linha (cabeçalho)
                }

                String[] campos = linha.split(";");
                String tipo = campos[0].trim(); // o tipo é definido pelo primeiro valor da linha
                String descricao = campos[1].trim(); // a descrição é definida pelo segundo valor da linha
              //aqui sao separados os dados inseridos e cada um é salvo no banco
              //é verificado o numero de campos inseridos em comparacao aos campos requeridos para o tipo
                switch (tipo) {
                    case "Roupa":
                        if (campos.length < 4) {
                            System.out.println("Dados insuficientes para Roupa: " + linha);
                            continue;
                        }
                        Roupa roupa = new Roupa();
                        roupa.setDescricao(descricao);
                        roupa.setGenero(campos[2].trim()); // gênero é o terceiro valor da linha
                        roupa.setTamanho(campos[3].trim()); // tamanho é o quarto valor da linha
                        doacaoService.criarRoupa(roupa);
                        break;

                    case "Produto de Higiene":
                        if (campos.length < 3) {
                            System.out.println("Dados insuficientes para Produto de Higiene: " + linha);
                            continue;
                        }
                        ProdutoHigiene produtoHigiene = new ProdutoHigiene();
                        produtoHigiene.setDescricao(descricao);
                        if (!campos[4].trim().isEmpty()) {
                            try {
                                produtoHigiene.setQuantidade(Integer.parseInt(campos[4].trim()));
                                doacaoService.criarProdutoHigiene(produtoHigiene);
                            } catch (NumberFormatException e) {
                                System.out.println("Erro ao converter quantidade para Produto de Higiene: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Quantidade não especificada para Produto de Higiene: " + linha);
                        }
                        break;

                    case "Alimento":
                        if (campos.length < 6) {
                            System.out.println("Dados insuficientes para Alimento: " + linha);
                            continue;
                        }
                        Alimento alimento = new Alimento();
                        alimento.setDescricao(descricao);
                        if (!campos[4].trim().isEmpty()) {
                            try {
                                alimento.setQuantidade(Integer.parseInt(campos[4].trim())); // quantidade é o quinto valor da linha
                                alimento.setUnidadeMedida(campos[5].trim()); // unidade de medida é o sexto valor da linha
                                alimento.setValidade(parseData(campos[6].trim())); // validade é o sétimo valor da linha
                                doacaoService.criarAlimento(alimento);
                            } catch (NumberFormatException e) {
                                System.out.println("Erro ao converter quantidade para Alimento: " + e.getMessage());
                            } catch (ParseException e) {
                                System.out.println("Erro ao analisar a data para Alimento: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Quantidade não especificada para Alimento: " + linha);
                        }
                        break;

                    default:
                        System.out.println("Tipo desconhecido: " + tipo);
                }
            }

            System.out.println("Doações cadastradas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

    private Date parseData(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        return dateFormat.parse(data);
    }




}
