package LaveRoupasAppFormularios;


import java.sql.Connection;
import java.sql.Statement;


public class login {
   
   int codigo = -1;
   String senha = null;
   static Connection conexao;
    
   public login(int ncod,String nsenha){
       this.codigo = ncod;
       this.senha = nsenha;
   }        
}

