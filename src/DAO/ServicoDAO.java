package DAO;

import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import VO.ServicoVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServicoDAO extends LaveRoupasAppMySQL{

    public ServicoDAO() throws SQLException {
    }

    public ArrayList<ServicoVO> getTodosOsServicos() throws SQLException {
        String sql = "";
        ServicoVO servico = null;
 
        sql = "SELECT * FROM T_SERVICO";

        
        ResultSet rs = executaQueryConsulta(sql);
        ArrayList <ServicoVO> servicosVO = new ArrayList<ServicoVO>();
        while (rs.next()) {
            
            servico = new ServicoVO();
            
            servico.setCodigoDoServico(rs.getInt("COD_SERVICO"));
            servico.setDescricaoDoPedido(rs.getString("DESCRICAO"));
            servico.setValor(rs.getDouble("VALOR"));
            servicosVO.add(servico);
        }
    
        return servicosVO;    
    }
    
    
}
