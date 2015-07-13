package DAO;


import BO.UsuarioBO;
import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import static LaveRoupasAppMySQL.LaveRoupasAppMySQL.executaQueryConsulta;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO extends LaveRoupasAppMySQL{
    
    public PessoaDAO() throws SQLException {
        
    }

    public UsuarioBO getDadosDoUsuarioByMatriculaDoUsuarioLogin(int codigoDoUsuario) throws SQLException {
        String sql = "";
        UsuarioBO usuarioBO = null;
        
        sql = "SELECT * FROM T_PESSOA WHERE CODIGO = '" +codigoDoUsuario+ "' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (rs.next()) {
            usuarioBO = new UsuarioBO();
            usuarioBO.setCodigo(rs.getInt("CODIGO"));
            usuarioBO.setCpf(rs.getString("CPF"));
            usuarioBO.setNome(rs.getString("NOME"));
            
            return usuarioBO;
        }else    
            return usuarioBO;
    }
    
}
