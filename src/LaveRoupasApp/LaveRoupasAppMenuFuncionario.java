package LaveRoupasApp;

import BO.PedidoBO;
import BO.PessoaBO;
import BO.UsuarioBO;
import VO.ClienteVO;
import VO.PedidoVO;
import VO.PessoaVO;
import VO.ServicoVO;
import VO.UsuarioVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


class LaveRoupasAppMenuFuncionario extends LaveRoupasApp{
    
    UsuarioVO funcionario = null;
    UsuarioBO usuarioBO = null;
    
    public int menuPrincipal(UsuarioVO  usuarioVO) throws SQLException {
        
        funcionario = usuarioVO;
        
        Scanner in = new Scanner(System.in);
              
        int opcao = funcionario.getTipoDeAcessoDoUsuario();
        
        while (opcao != 5 && opcao != -1) {     
            switch (opcao){
                case 1:
                    opcao = exibiMenuDoUsuarioFuncionario();
                    opcao = (opcao == 3) ? -1 : opcao;
                break;
                case 2:
                    opcao = exibiMenuDoUsuarioFuncionario();
                    opcao = (opcao == 3) ? -1 : opcao;
                break;
            }
        }
        
        return opcao;
    }

    private void exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario() {
        System.out.println("                                 | ————————— | Lave Roupas App | ————————— |");
        String[] aux = funcionario.getNome().split(" ");
        System.out.println("                                         Olá, " +aux[0]+ " " +aux[aux.length - 1] );
        System.out.println("                                         Último acesso: " +UsuarioVO.getDataUltimoAcesso());
        System.out.println("                                 |————————————————————————————————|");
        System.out.println();
    }
    
    private int exibiMenuDoUsuarioFuncionario() throws SQLException{
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        int quantidadeDePedidos = 0;
        Scanner in = new Scanner(System.in);
        while (opcao != 5 && opcao != -1) {
            exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario();
            quantidadeDePedidos = listaTodosOsPedidosEmAberto() + 1;
            System.out.println("     1 - Cadastrar Cliente     2 - Cadastrar Pedido     3 - Alterar Pedido     4 - Finalizar Pedido     5 - Logout     6 - Exit System ");     
         
            opcao = in.nextInt();
           
            opcao = (opcao == 1) ? 1 : opcao;
            opcao = (opcao == 2) ? 2 : opcao;
            opcao = (opcao == 3) ? 3 : opcao;
            opcao = (opcao == 4) ? 4 : opcao;
            opcao = (opcao == 5) ? -1 : opcao;
            opcao = (opcao == 6) ? -1 : opcao;

            switch (opcao){
                case 1:
                    boolean cadastro = cadastrarCliente();
                    if(cadastro == true){
                        System.out.println("Cadastro Realizado com Sucesso");
                        input.nextLine();
                    }else{
                        System.out.println("DADOS INVALIDOS!!!");
                        System.out.println("Falha ao Realizar o cadastro!!!");
                        input.nextLine();
                    }
                    break;
                case 2:
                    cadastrarPedido(quantidadeDePedidos);
                    input.nextLine();
                    break;
                case 3:
                    alterarPedido();
                    input.nextLine();
                    break;
                case 4:
                    finalizarPedido();
                    break;
                case 5:
                    break;
            }
            limpaSaida();
        }
        return opcao;   
    }

    private int listaTodosOsPedidosEmAberto() throws SQLException {
        
        usuarioBO = new UsuarioBO();
        ArrayList <PedidoVO> pedidosVO = null;
        
        pedidosVO = usuarioBO.getTodosOsPedidos("ABERTO");

        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");
        System.out.println("|     FUNCIONARIO      |    CÓDIGO DO PEDIDO    |    DATA DE ENTRADA    |    DATA DE SAÍDA    |    STATUS PAGAMENTO    |       SOLICITANTE      |");
        System.out.println("—————————————————————————————————————————————————————————————————————————————————————");
        
        if (pedidosVO.size() > 0) {
            String[] nomeFuncionario, nomeCliente;
            for (int i = 0; i < pedidosVO.size(); i++) {
                nomeFuncionario = usuarioBO.getNomeDoFuncinarioOuClientePeloCidogoNoPedido(pedidosVO.get(i).getCodigoDoFuncionario()).split(" ");
                nomeCliente = usuarioBO.getNomeDoFuncinarioOuClientePeloCidogoNoPedido(pedidosVO.get(i).getCodigoDoCliente()).split(" ");
                
                System.out.println("|      " +nomeFuncionario[0]+ " " +nomeFuncionario[nomeFuncionario.length - 1]+ "       |           " +pedidosVO.get(i).getCodigoDoPedido()+ 
                                   "           |   " +pedidosVO.get(i).getDataDeEntrada()+ "  |   " +pedidosVO.get(i).getDataDeSaida()+ "  |        " 
                                   +pedidosVO.get(i).getStatusDoPedido()+ "        |    " +nomeCliente[0]+" "+nomeCliente[ nomeCliente.length - 1]+ "   |");
            }
        }else
            System.out.println("                                            NÃO HÁ PEDIDOS CADASTRADOS!!!                                           ");
        
        System.out.println("—————————————————————————————————————————————————————————————————————————————");
        System.out.println();
        
        return pedidosVO.size();
    }

    private void finalizarPedido() throws SQLException {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite o Codigo do Pedido");
        usuarioBO.finalizarPedidoPeloCidogo(in.nextInt());
        System.out.println("Pedido Finalizado!!!");
        exibiMenuDoUsuarioFuncionario();
    }

    private void cadastrarPedido(int codigoDoPedido) throws SQLException {
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        if(listarClientes()){
            try{
            int codigoDoCliente = 0;           
            System.out.println("Informe o codigo do cliente:");
            codigoDoCliente = in.nextInt();
            PessoaBO pessoaBO = new PessoaBO();
            boolean verificacao = pessoaBO.verificaCodDigitadoConsulta(codigoDoCliente);
            if(verificacao==true){
            
            if (listarServicos()) {
                int codigoDoServico;
                int quantidadeDoServico;
                System.out.println("Informe o codigo do Serviço:");
                codigoDoServico = in.nextInt();
                System.out.println("Informe a quantidade :");
                quantidadeDoServico = in.nextInt();
                if(usuarioBO.cadastrarPedido(codigoDoCliente, codigoDoServico, quantidadeDoServico, funcionario.getCodigo(), codigoDoPedido)){
                    System.out.println("Pedido Cadastrado!!!");
                }
            }        
        }
            else{
                System.out.println("CODIGO DE CLIENTE INVALIDO");
                input.nextLine();
                limpaSaida();
            }
    
            }catch(InputMismatchException ex){
                System.out.println("CODIGO INVALIDO!");
                System.out.println("DIGITE UM VALOR NUMERICO!");
                input.nextLine();
                limpaSaida();
            }
        }
    exibiMenuDoUsuarioFuncionario();
}

    private boolean listarClientes() throws SQLException {
        Scanner input = new Scanner(System.in);
        
        ArrayList <ClienteVO> clientesVO = null;
        clientesVO = usuarioBO.getTodosOsClientes();
        if (clientesVO.size() > 0) {
            limpaSaida();
            System.out.println("——————————————————  RELATORIO DE CLIENTES ———————————————");
            System.out.println();
            System.out.println("———————————————————————————————————————————————");
            System.out.println("|   CODIGO    |                   NOME                   |         CPF          |");
            System.out.println("———————————————————————————————————————————————");
            String[] nomeCliente = null;
            for (ClienteVO clienteVO : clientesVO) {
                nomeCliente = clienteVO.getNome().split(" ");
                System.out.println("|      "+clienteVO.getCodigo() + "      |              " + nomeCliente[0] + " " + nomeCliente[nomeCliente.length -1] + "             |     " + clienteVO.getCpf()+"       | ");
                System.out.println("———————————————————————————————————————————————");
            }
            return true;
        }else
            System.out.println("                                          NÃO HÁ CLIENTES CADASTRADOS!!!                                           ");
            input.nextLine();
            limpaSaida();
        return false;
    }
    
        private boolean listarServicos() throws SQLException {
        ArrayList <ServicoVO> servicosVO = null;
        servicosVO = usuarioBO.getTodosOsServicos();
        if (servicosVO.size() > 0) {
            System.out.println("———————————————————— SERVICOS ————————————————————");
            System.out.println();
            System.out.println("—————————————————————————————————————————————————");
            System.out.println("| CODIGO  |                   DESCRIÇÃO                   |         VALOR          |");
            System.out.println("—————————————————————————————————————————————————");
            for (ServicoVO servicoVO : servicosVO) {
                System.out.println("|    "+servicoVO.getCodigoDoServico() + "    |                " + servicoVO.getDescricaoDoPedido() + "        |          " + servicoVO.getValor()+ "          |");
            }
            return true;
        }else
            System.out.println("                                          NÃO HÁ CLIENTES CADASTRADOS!!!                                           ");
        
        System.out.println("——————————————————————————————————————————————————————————————————————");
        return false;
    }
        
    private boolean cadastrarCliente() throws SQLException{
        Scanner in = new Scanner(System.in);   
        
        PessoaVO pessoaVO = new PessoaVO();
     
        System.out.println("Informe o nome do Cliente: ");
        String nome = in.nextLine();
     
        System.out.println("Informe o CPF do cliente: ");
        String cpf = in.nextLine();
        //Cliente representado no banco de dados com o valor 0;
        int tipo = 0;
        //populando o objeto pessoaVO;
        pessoaVO.setNome(nome);
        pessoaVO.setCpf(cpf);
        pessoaVO.setTipo(tipo);
        //Criando o Objeto pessoaBO.
        PessoaBO pessoaBO = new PessoaBO();
        boolean teste = pessoaBO.validaDadosCliente(pessoaVO);
        
        if(teste== true){
            return true;
        }else{
            return false;
        }
    }

    private void alterarPedido() throws SQLException {
        Scanner input = new Scanner(System.in);
        
        ArrayList<PedidoVO> pedidoVO = new ArrayList<PedidoVO>();
        PedidoVO alteraVO = new PedidoVO();
       
        try{
        System.out.println("Selecione o codigo do Pedido a ser Alterado: ");
        int codigo = input.nextInt();
        
        PedidoBO pedidoBO = new PedidoBO();
        
        boolean verifica = pedidoBO.verificaCodDigitado(codigo);
        
        if(verifica==true){
            
        pedidoVO = pedidoBO.verificaPedidoPeloCod(codigo);
        
        System.out.println(" ————————————————————————————————— DETALHES DO PEDIDO ———————————————————————————— ");
        System.out.println();
        System.out.println("|    CÓDIGO DO PEDIDO    |    DATA DE ENTRADA    |    DESCRICAO       |   QUANTIDADE   |    VALOR    |    STATUS PAGAMENTO    |");
        System.out.println("——————————————————————————————————————————————————————————————————————————");
        
        for(int i = 0 ;i<pedidoVO.size();i++){
        System.out.println("|            "+pedidoVO.get(i).getCodigoDoPedido()+"           |    "+pedidoVO.get(i).getDataDeEntrada()+"   |     "+pedidoVO.get(i).getDescricao()+"     |      "+pedidoVO.get(i).getQuantidade()+"      |      "+pedidoVO.get(i).getValor()+"      |      "+pedidoVO.get(i).getStatusDoPedido()+"     |");
        }  
        System.out.println("——————————————————————————————————————————————————————————————————————————");
        System.out.println();
        System.out.println("Informe o que deseja alterar");
        System.out.println();
        System.out.println(" 1 - Alterar Tipo de Serviço    2 - Alterar Quantidade");
        int opcao = input.nextInt();
        switch(opcao){
            case 1:listarServicos();
                   int op = input.nextInt();
                   pedidoBO.alteraDadosPedido(pedidoVO.get(0).getCodigoDoPedido(),op);
                break;
                    
            case 2: System.out.println("Digite a quantidade Desejada: ");
                    int ope = input.nextInt();
                    pedidoBO.alteraQuantidadePedido(pedidoVO.get(0).getCodigoDoPedido(),ope);
                    
                break;
            default:
                System.out.println("OPCAO INVALIDA");
        }
        
        }else{
            System.out.println("CODIGO INVALIDO");
            input.nextLine();
        }
        }catch(InputMismatchException ex){
                System.out.println("CODIGO INVALIDO DIGITE UM VALOR NUMERICO");
                input.nextLine();
        }
    }
}