package DAO;

import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import VO.UsuarioVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginDAO extends LaveRoupasAppMySQL{
    
    UsuarioVO usuarioVO = null;
    
    public LoginDAO() throws SQLException {
    }
   
    public UsuarioVO getDadosDoUsuarioByMatriculaDoUsuarioLogin(int codigoDoUsuario) throws SQLException {
        String sql = "";
        UsuarioVO usuarioVO = null;
        
        sql = "SELECT * FROM T_PESSOA WHERE CODIGO = '" +codigoDoUsuario+ "' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (rs.next()) {
            usuarioVO = new UsuarioVO();
            usuarioVO.setCodigo(rs.getInt("CODIGO"));
            usuarioVO.setCpf(rs.getString("CPF"));
            usuarioVO.setNome(rs.getString("NOME"));
            
            return usuarioVO;
        }else    
            return usuarioVO;
    }
    
    public void atualizaDataAtualComoDataDoUltimoAcessoNoBanco(int matricula ){
        
        String sql = "";
        Date hoje = new Date(System.currentTimeMillis());
        String [] campos = {"DATA_ULTIMO_ACESSO"};
        String [] valores = {FormataDataParaCadastroNoBanco(hoje)};
        
        String [] camposComValores = uniOsCamposComOsRespectivosValores(campos, valores );
 
        update("T_USUARIOS", camposComValores, "MATRICULA" , Integer.toString(matricula));
        
        UsuarioVO.setDataUltimoAcesso(FormataDataParaExibir(hoje));
    }

    public UsuarioVO ChecaMatriculaESenhaDoUsuarioNoBanco(int codigo, String senha) throws SQLException{
        
        String sql = "";
        sql = " SELECT * FROM T_USUARIOS WHERE MATRICULA = "+codigo+"  and SENHA = md5 ( "+senha+" ) LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (rs.next()) {
            if (usuarioVO == null) 
                usuarioVO = getDadosDoUsuarioByMatriculaDoUsuarioLogin(rs.getInt("MATRICULA"));
            
            usuarioVO.setTipoDeAcessoDoUsuario(rs.getInt("TIPO_ACESSO"));
            atualizaDataAtualComoDataDoUltimoAcessoNoBanco(rs.getInt("MATRICULA"));
            UsuarioVO.setDataUltimoAcesso(FormataDataParaExibir(rs.getTimestamp("DATA_ULTIMO_ACESSO")));

            return usuarioVO;
        }else
            return usuarioVO;

    }
        
}
