package LaveRoupasAppVO;

import java.io.IOException;
import java.sql.SQLException;



public class LaveRoupasAppVO {
    
    public static void limpaSaida() {
        for (int i = 0; i < 90; i++) {
            System.out.println();
        }
    
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        int opcao = -1; 
        int matricula = -1;
        
        while (opcao != 4) {            
                    
            switch (opcao){
                case -1:
                    LoginVO loginVO = new LoginVO();
                    opcao = loginVO.EfutuarLogin();
                    limpaSaida();
                    System.out.println((opcao != -1) ? "Acessando o Sistema..." : "Usuário ou Senha invalidos!!!");
                    matricula = loginVO.matriculaUsuarioLogin;
                    break;
                case 1:
                    MenuFuncionarioVO funcionarioVO = new MenuFuncionarioVO();
                    opcao = funcionarioVO.menuPrincipal(matricula);
                    limpaSaida();
                    break;
                case 9:
                    System.out.println("Pedidos");
                    break;
                case 6:
                    System.out.println("Gerente");
                    
                    break;
            }
        }
    }  
}
