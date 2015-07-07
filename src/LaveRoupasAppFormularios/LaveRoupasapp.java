package LaveRoupasAppFormularios;

import BO.loginBO;
import java.util.Scanner;
import LaveRoupasAppMySQL.Conexao;



public class LaveRoupasapp {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
      
        Conexao.getConexao();
        
        int cod;
        String senha;
        
        System.out.println("Digite o codigo do usuario: ");
        cod = input.nextInt();
        System.out.println("Digite a senha ");
        senha = input.next();
            
        login Logar = new login(cod,senha);
        
        loginBO loginbo = new loginBO();
        if (loginbo.VerificaCampoCodigoVazio(cod)){
            
               System.out.println("Deu Certo");
        
        }else{
            System.out.println("Deu errado");
        }
    }  
}
