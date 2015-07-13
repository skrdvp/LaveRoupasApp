package DAO;

import BO.LoginBO;
import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cti
 */
public class LoginDAO extends LaveRoupasAppMySQL{
    
    LoginBO loginBO = null;
    
    public LoginDAO() throws SQLException {
    }

    public static String FormataDataParaCadastroNoBanco(Date data) {
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatoData.format(data);
    }
    
    public String FormataDataParaExibir(Date data) {
        SimpleDateFormat formatoDataComHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        return formatoDataComHora.format(data);
    }
    
    public void atualizaDataAtualComoDataDoUltimoAcessoNoBanco(int matricula ){
        
        String sql = "";
        Date hoje = new Date(System.currentTimeMillis());
        String [] campos = {"DATA_ULTIMO_ACESSO"};
        String [] valores = {FormataDataParaCadastroNoBanco(hoje)};
        
        String [] camposComValores = uniOsCamposComOsRespectivosValores(campos, valores );
 
        update("T_USUARIOS", camposComValores, "MATRICULA" , Integer.toString(matricula));
        
        loginBO.setDataUltimoAcesso(FormataDataParaExibir(hoje));
    }

    public boolean ChecaMatriculaESenhaDoUsuarioNoBanco(LoginBO loginBO) throws SQLException{
        
        String sql = "";
        sql = " SELECT * FROM T_USUARIOS WHERE MATRICULA = "+loginBO.getMatriculaDoUsuario()+"  and SENHA = md5 ( "+loginBO.getSenha()+" ) limit 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (this.loginBO == null) {
            this.loginBO = loginBO;
        }
        
        if (rs.next()) {
            this.loginBO.setTipoDeAcessoDoUsuario(rs.getInt("TIPO_ACESSO"));
            this.loginBO.setMatriculaDoUsuario(rs.getInt("MATRICULA"));
            atualizaDataAtualComoDataDoUltimoAcessoNoBanco(rs.getInt("MATRICULA"));
            LoginBO.setDataUltimoAcesso(FormataDataParaExibir(rs.getDate("DATA_ULTIMO_ACESSO")));

            return true;
        }else
            return false;

    }
        
}
