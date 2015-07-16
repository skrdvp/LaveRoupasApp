package BO;

import DAO.LoginDAO;
import VO.UsuarioVO;
import java.sql.SQLException;


public class LoginBO {
  
    public boolean verificaCampoCodigoVazio(int codUsuario){
        return codUsuario != -1;
    }
    
    public boolean verificaCampoSenhaVazio(String senhaUsuario){
        return senhaUsuario != null;
    }
    
    public UsuarioVO ChecaSeCodigoESenhaEstaoNoBanco(int codigo, String senha) throws SQLException {
        LoginDAO loginDAO = new LoginDAO();
        return loginDAO.ChecaMatriculaESenhaDoUsuarioNoBanco(codigo, senha);
    }
    
}
