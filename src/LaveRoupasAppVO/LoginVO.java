package LaveRoupasAppVO;


import BO.LoginBO;
import BO.UsuarioBO;
import DAO.LoginDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class LoginVO{

    int matriculaUsuarioLogin = -1;
    String senha = null;
    
    public void cabecalhoTelaLogin() {
        Date hoje = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        System.out.println("************** Lave Roupas App **************");
        System.out.println("******************* LOGIN *******************");
        
    }
   
  
    public int EfutuarLogin() throws SQLException{
        Scanner in =  new Scanner (System.in);
        
        cabecalhoTelaLogin();
        System.out.println("Digite o codigo do usuario: ");
        matriculaUsuarioLogin = in.nextInt();
        System.out.println("Digite a senha ");
        senha = in.next();
            
       
        LoginBO loginBO = new LoginBO();
        if(loginBO.VerificaCampoCodigoVazio(matriculaUsuarioLogin)){
            loginBO.setMatriculaDoUsuario(matriculaUsuarioLogin);
            loginBO.setSenha(senha);
            LoginDAO loginDAO = new LoginDAO();
            loginDAO.ChecaMatriculaESenhaDoUsuarioNoBanco(loginBO);
            return loginBO.getTipoDeAcessoDoUsuario();
        }
        return -1;
    }
}

