package laveroupasapp.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    
    public static Connection conexao = null;

    public static Connection getConexao() {
      try{
          if(conexao == null){
          String banco = "laveroupasapp";    
          conexao = DriverManager.getConnection("jdbc:mysql://localhost/"+banco+"","root","");
          System.out.println("Conexao a "+banco+" Realizada com sucesso.");
          }
          return conexao;
      }
      catch(SQLException e){
          throw new RuntimeException("Erro na conexão: "+e.getMessage());
      }
    }
}
