package LaveRoupasAppMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection conexao = null;

    public static Connection getConexao() {
        
        String banco = "laveroupasapp";
        String user = "laveroupas";
        String password = "laveroupas123";
        
      try{
          if(conexao == null){
              
          conexao = DriverManager.getConnection("jdbc:mysql://localhost/" +banco, user, password );
          System.out.println("Conexao a " +banco+ " Realizada com sucesso.");
          }
          return conexao;
      }
      catch(SQLException e){
          throw new RuntimeException("Erro na conexão: " +e.getMessage());
      }
    }
    public static void fecharConexao() throws SQLException{
        
        conexao.close();
        System.out.println("Conxeao Fechada Com Sucesso!!!");
    }
}
