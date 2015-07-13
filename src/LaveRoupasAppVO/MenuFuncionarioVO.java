package LaveRoupasAppVO;

import BO.LoginBO;
import BO.UsuarioBO;
import DAO.PessoaDAO;
import java.sql.SQLException;
import java.util.Scanner;


class MenuFuncionarioVO {
    
    UsuarioBO funcionario = null;
    
    public int menuPrincipal(int matriculaFuncionario) throws SQLException {
        PessoaDAO pessoaDAO = new PessoaDAO();
        funcionario = pessoaDAO.getDadosDoUsuarioByMatriculaDoUsuarioLogin(matriculaFuncionario);
        Scanner in = new Scanner(System.in);

        exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario();
        
        int opcao = 1;
        
        while (opcao != 4 && opcao != -1) {     
            switch (opcao){
                case 1:
                    System.out.println("3 - Logout");
                    System.out.println("4 - Exit System");
                    opcao = in.nextInt();
                    opcao = (opcao == 3) ? -1 : opcao;
                break;
                case 2:
                    System.out.println("OutraCois");
                break;
            }
        }
        
        return opcao;
    }

    private void exibiCabecalhoComNomeEDataDoUltimoAcessoDoUsuario() {
        System.out.println("************** Lave Roupas App **************");
        String[] aux = funcionario.getNome().split(" ");
        System.out.println("Olá, " +aux[0]+ " " +aux[aux.length - 1] );
        System.out.println("Último acesso: " +LoginBO.getDataUltimoAcesso());
        System.out.println("*********************************************");
    }
    
}
