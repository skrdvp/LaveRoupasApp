package LaveRoupasApp;

import BO.LoginBO;
import VO.UsuarioVO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class LaveRoupasAppLogin {
    
    static int matriculaUsuarioLogin = -1;
    static String senha = null;
    
    public static void cabecalhoTelaLogin() {
        Date hoje = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        System.out.println("************** Lave Roupas App **************");
        System.out.println("******************* LOGIN *******************");
        
    }
    
    public UsuarioVO EfutuarLogin() throws SQLException{
        Scanner in =  new Scanner (System.in);
        
        cabecalhoTelaLogin();
        System.out.println("Digite o codigo do usuario: ");
        matriculaUsuarioLogin = in.nextInt();
        System.out.println("Digite a senha ");
        senha = in.next();
            
       LoginBO loginBO = new LoginBO();
        if(!loginBO.verificaCampoCodigoVazio(matriculaUsuarioLogin)){
            System.out.println("Informe o Código do Usuário!!");
        }else{
            if (!loginBO.verificaCampoSenhaVazio(senha)) {
                System.out.println("Informe a Senha!!");
            }else{
                UsuarioVO usuarioVO = null;
                usuarioVO = loginBO.ChecaSeCodigoESenhaEstaoNoBanco(matriculaUsuarioLogin, senha);
                return usuarioVO;
            }
        }
        return null;
    }   
}
