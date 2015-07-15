package LaveRoupasApp;

import LaveRoupasAppMySQL.Conexao;
import VO.UsuarioVO;
import java.io.IOException;
import java.sql.SQLException;

public class LaveRoupasApp {
    

    
    public static void limpaSaida() {
        for (int i = 0; i < 90; i++) {
            System.out.println();
        }
    
    }
   
    
    public static void main(String[] args) throws SQLException, IOException {
        
        int opcao = -1; 
        UsuarioVO usuarioVO = null;
        LaveRoupasAppLogin login= null;
        
        while (opcao != 5) {            
                    
            switch (opcao){
                case -1:
                    login = (login == null) ? new LaveRoupasAppLogin() : login;
                    
                    usuarioVO = login.EfutuarLogin();
                    
                    opcao = (usuarioVO != null) ? usuarioVO.getTipoDeAcessoDoUsuario() : -1;
                    
                    usuarioVO = (opcao == -1) ? null : usuarioVO;
                    login = (opcao == -1) ? null : login;

                    limpaSaida();
                    System.out.println((opcao != -1) ? "Acessando o Sistema..." : "Usuário ou Senha invalidos!!!");
                    
                    break;
                case 1:
                    LaveRoupasAppMenuFuncionario menuFuncionario = new LaveRoupasAppMenuFuncionario();
                    opcao = menuFuncionario.menuPrincipal(usuarioVO);
                    limpaSaida();
                    
                    break;
            }
        }
        Conexao.fecharConexao();
    }  
}
