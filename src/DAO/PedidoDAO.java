package DAO;


import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import VO.PagamentoPedidoVO;
import VO.PedidoVO;
import VO.ServicoVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PedidoDAO extends LaveRoupasAppMySQL{

    public PedidoDAO() throws SQLException {
    }

    public ArrayList<PedidoVO> getTodosOsPedidosPorStatus(String status) throws SQLException {
        
        String sql = "";
        PedidoVO pedido = null;
        if (status.equalsIgnoreCase("TODOS")) {
            sql = "SELECT * FROM T_PEDIDO";
        }else
            sql = "SELECT * FROM T_PEDIDO WHERE STATUS = '"+status+"'";
        
        ResultSet rs = executaQueryConsulta(sql);
        ArrayList <PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
        while (rs.next()) {
            
            pedido = new PedidoVO();
            
            pedido.setCodigoDoPedido(rs.getInt("COD_PEDIDO"));
            pedido.setCodigoDoCliente(rs.getInt("COD_CLIENTE"));
            pedido.setCodigoDoServico(rs.getInt("COD_SERVICO"));
            pedido.setCodigoDoFuncionario(rs.getInt("COD_FUNCIONARIO"));
            pedido.setQuantidade(rs.getInt("QUANTIDADE"));
            pedido.setCodigoDoPagamento(rs.getInt("COD_PAGAMENTO"));
            pedido.setDataDeEntrada(FormataDataParaExibir(rs.getDate("DATA_ENTRADA")));
            pedido.setDataDeSaida(FormataDataParaExibir(rs.getDate("DATA_SAIDA")));
            pedido.setStatusDoPedido(rs.getString("STATUS"));
            pedidosVO.add(pedido);
        }
    
        return pedidosVO;        
    }
    
    public PagamentoPedidoVO getDadosDoPagamentoPorCodigoDoPagamentoDoPedido(int codigoPagamento) throws SQLException {
        
        String sql = "";
        PagamentoPedidoVO pagamentoPedidoVO = null;

        sql = "SELECT * FROM T_PAGAMENTO WHERE COD_PAGAMENTO = '"+codigoPagamento+"' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);

        
        if (rs.next()) {
            
            pagamentoPedidoVO = new PagamentoPedidoVO();
            
            pagamentoPedidoVO.setCodigoDoPagamento(rs.getInt("COD_PAGAMENTO"));
            pagamentoPedidoVO.setCodigoDeSegunrança(rs.getString("CODIGO_SEGURANCA"));
            pagamentoPedidoVO.setNumeroDoCartao(rs.getString("NUMERO_CARTAO"));
        }
    
        return pagamentoPedidoVO;        
    }
    
    public ServicoVO getDadosDoPagamentoPorCodigoDoServicoDoPedido(int codigoDoServico) throws SQLException {
        
        String sql = "";
        ServicoVO servicoVO = null;

        sql = "SELECT * FROM T_SERVICO WHERE COD_SERVICO = '"+codigoDoServico+"' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (rs.next()) {
            servicoVO = new ServicoVO();
            
            servicoVO.setCodigoDoServico(rs.getInt("COD_SERVICO"));
            servicoVO.setDescricaoDoPedido(rs.getString("DESCRICAO"));
            servicoVO.setValor(rs.getDouble("VALOR"));
        }
    
        return servicoVO;        
    }
}