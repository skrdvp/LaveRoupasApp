package LaveRoupasApp;

import BO.UsuarioBO;
import VO.PedidoVO;
import VO.UsuarioVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


class LaveRoupasAppMenuFuncionario extends LaveRoupasApp{
    
    UsuarioVO funcionario = null;
    
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
        
        Scanner in = new Scanner(System.in);
        while (opcao != 5 && opcao != -1) {
            exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario();
            listaTodosOsPedidosEmAberto();
            System.out.println("  1 - Cadastrar Pedido     2 - Alterar Pedido      3 - Finalizar Pedido      4 - Logout     5 - Exit System ");     
         
            opcao = in.nextInt();
            
            opcao = (opcao == 4) ? -1 : opcao; 
            opcao = (opcao == 1) ? 5 : opcao;
            opcao = (opcao == 2) ? 6 : opcao;
            opcao = (opcao == 3) ? 7 : opcao;

            switch (opcao){
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break; 
            }
            limpaSaida();
        }
        return opcao;   
    }

    private void listaTodosOsPedidosEmAberto() throws SQLException {
        
        UsuarioBO usuarioBO = new UsuarioBO();
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
    }
    
}