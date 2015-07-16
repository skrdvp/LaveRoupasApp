package LaveRoupasApp;

import BO.UsuarioBO;
import VO.ClienteVO;
import VO.PedidoVO;
import VO.ServicoVO;
import VO.UsuarioVO;
import java.sql.SQLException;
import java.util.ArrayList;
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
        System.out.println("                                  ************** Lave Roupas App **************");
        String[] aux = funcionario.getNome().split(" ");
        System.out.println("                                         Olá, " +aux[0]+ " " +aux[aux.length - 1] );
        System.out.println("                                         Último acesso: " +UsuarioVO.getDataUltimoAcesso());
        System.out.println("                                  *********************************************");
        System.out.println();
    }
    
    private int exibiMenuDoUsuarioFuncionario() throws SQLException{
        int opcao = 0;
        int quantidadeDePedidos = 0;
        Scanner in = new Scanner(System.in);
        while (opcao != 5 && opcao != -1) {
            exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario();
            quantidadeDePedidos = listaTodosOsPedidosEmAberto() + 1;
            System.out.println("  1 - Cadastrar Pedido     2 - Alterar Pedido      3 - Finalizar Pedido      4 - Logout     5 - Exit System ");     
         
            opcao = in.nextInt();
            
            opcao = (opcao == 4) ? -1 : opcao; 
            opcao = (opcao == 1) ? 5 : opcao;
            opcao = (opcao == 2) ? 6 : opcao;
            opcao = (opcao == 3) ? 7 : opcao;

            switch (opcao){
                case 5:
                    cadastrarPedido(quantidadeDePedidos);
                    break;
                case 6:
                    
                    break;
                case 7:
                    finalizarPedido();
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

        System.out.println("——————————————————————————————————————————————————————————————————————");
        System.out.println("| FUNCINARIO  |  CÓDIGO DO PEDIDO  |  DATA DE ENTRADA  |  DATA DE SAÍDA  |  STATUS PAGAMENTO  |  SOLICITANTE |");
        System.out.println("——————————————————————————————————————————————————————————————————————");
        
        if (pedidosVO.size() > 0) {
            String[] nomeFuncionario, nomeCliente;
            for (int i = 0; i < pedidosVO.size(); i++) {
                nomeFuncionario = usuarioBO.getNomeDoFuncinarioOuClientePeloCidogoNoPedido(pedidosVO.get(i).getCodigoDoFuncionario()).split(" ");
                nomeCliente = usuarioBO.getNomeDoFuncinarioOuClientePeloCidogoNoPedido(pedidosVO.get(i).getCodigoDoCliente()).split(" ");
                
                System.out.println("| " +nomeFuncionario[0]+ " " +nomeFuncionario[nomeFuncionario.length - 1]+ "  |  " +pedidosVO.get(i).getCodigoDoPedido()+ 
                                   "   |  " +pedidosVO.get(i).getDataDeEntrada()+ "  |  " +pedidosVO.get(i).getDataDeSaida()+ "  |  " 
                                   +pedidosVO.get(i).getStatusDoPedido()+ "  |  " +nomeCliente[0]+" "+nomeCliente[ nomeCliente.length - 1]+ " |");
            }
        }else
            System.out.println("                                          NÃO HÁ PEDIDOS CADASTRADOS!!!                                           ");
        
        System.out.println("——————————————————————————————————————————————————————————————————————");
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
        if(listarClientes()){
            int codigoDoCliente;
            
            System.out.println("Informe o codigo do cliente:");
            codigoDoCliente = in.nextInt();
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
        exibiMenuDoUsuarioFuncionario();
    }

    private boolean listarClientes() throws SQLException {
        ArrayList <ClienteVO> clientesVO = null;
        clientesVO = usuarioBO.getTodosOsClientes();
        if (clientesVO.size() > 0) {
            System.out.println("——————————————————————————————————————————————————————————————————————");
            System.out.println("| CODIGO  |                   NOME                   |         CPF          |");
            System.out.println("——————————————————————————————————————————————————————————————————————");
            String[] nomeCliente = null;
            for (ClienteVO clienteVO : clientesVO) {
                nomeCliente = clienteVO.getNome().split(" ");
                System.out.println(clienteVO.getCodigo() + "        |" + nomeCliente[0] + " " + nomeCliente[nomeCliente.length -1] + "        |" + clienteVO.getCpf());
                System.out.println("——————————————————————————————————————————————————————————————————————");
            }
            return true;
        }else
            System.out.println("                                          NÃO HÁ CLIENTES CADASTRADOS!!!                                           ");
        
        return false;
    }
    
        private boolean listarServicos() throws SQLException {
        ArrayList <ServicoVO> servicosVO = null;
        servicosVO = usuarioBO.getTodosOsServicos();
        if (servicosVO.size() > 0) {
            System.out.println("—————————————————————————————SERVICOS————————————————————————————————————");
            System.out.println("——————————————————————————————————————————————————————————————————————");
            System.out.println("| CODIGO  |                   DESCRIÇÃO                   |         VALOR          |");
            System.out.println("——————————————————————————————————————————————————————————————————————");
            for (ServicoVO servicoVO : servicosVO) {
                System.out.println(servicoVO.getCodigoDoServico() + "        |" + servicoVO.getDescricaoDoPedido() + "        |" + servicoVO.getValor());
            }
            return true;
        }else
            System.out.println("                                          NÃO HÁ CLIENTES CADASTRADOS!!!                                           ");
        
        System.out.println("——————————————————————————————————————————————————————————————————————");
        return false;
    }
}