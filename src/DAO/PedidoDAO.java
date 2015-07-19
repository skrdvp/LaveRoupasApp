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
            pedido.setDataDeEntrada(FormataDataParaExibir(rs.getTimestamp("DATA_ENTRADA")));
            pedido.setDataDeSaida(FormataDataParaExibir(rs.getTimestamp("DATA_SAIDA")));
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

    public boolean alterarPedidosPorCodigo(int codigo,int op){
        
        String sql = "";
        
        sql = "UPDATE T_PEDIDO SET COD_SERVICO = "+op+" WHERE COD_PEDIDO = "+codigo+"";
        
        boolean rs = executaQuery(sql);
        
        if(rs==true){
        return true;
        }else{
            return false;
        }
    }
    
    public boolean alterarQuantPedidoPorCodigo(int codigo, int op){
    
       String sql = "";
        
        sql = "UPDATE T_PEDIDO SET QUANTIDADE = "+op+" WHERE COD_PEDIDO = "+codigo+"";
        
        boolean rs = executaQuery(sql);
        
        if(rs==true){
        return true;
        }else{
            return false;
        } 
    
    }
    
    public ArrayList verificaCodExisteTabela() throws SQLException{
    
        PedidoVO pedidoVO = null;
        ArrayList <PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
        String sql = "";
        
        sql = "SELECT COD_PEDIDO FROM T_PEDIDO";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        while(rs.next()){
           pedidoVO = new PedidoVO();
           pedidoVO.setCodigoDoPedido(rs.getInt("COD_PEDIDO"));
           pedidosVO.add(pedidoVO);
        }
        return pedidosVO;
    }
    
    public ArrayList<PedidoVO> getPedidoBanco(int cod) throws SQLException{
   
        PedidoVO pedidoVO = null;
        ArrayList<PedidoVO> pedidosVO = new ArrayList<PedidoVO>();
        String sql = "";
        
        sql = "SELECT COD_PEDIDO,COD_CLIENTE,COD_FUNCIONARIO,QUANTIDADE,COD_PAGAMENTO,DATA_ENTRADA,DATA_SAIDA,DESCRICAO,VALOR,STATUS FROM T_PEDIDO,T_SERVICO WHERE T_PEDIDO.COD_PEDIDO = '"+cod+"' AND T_SERVICO.COD_SERVICO = (SELECT COD_SERVICO FROM T_PEDIDO WHERE COD_PEDIDO = '"+cod+"')";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        while(rs.next()){
            pedidoVO = new PedidoVO();
            pedidoVO.setCodigoDoPedido(rs.getInt("COD_PEDIDO"));
            pedidoVO.setCodigoDoCliente(rs.getInt("COD_CLIENTE"));
            pedidoVO.setCodigoDoFuncionario(rs.getInt("COD_FUNCIONARIO"));
            pedidoVO.setQuantidade(rs.getInt("QUANTIDADE"));
            pedidoVO.setCodigoDoPagamento(rs.getInt("COD_PAGAMENTO"));
            pedidoVO.setDataDeEntrada(FormataDataParaExibir(rs.getTimestamp("DATA_ENTRADA")));
            pedidoVO.setDataDeSaida(FormataDataParaExibir(rs.getTimestamp("DATA_SAIDA")));
            pedidoVO.setDescricao(rs.getString("DESCRICAO"));
            pedidoVO.setValor(rs.getDouble("VALOR"));
            pedidoVO.setStatusDoPedido(rs.getString("STATUS"));
            pedidosVO.add(pedidoVO);
        }
        return pedidosVO;
    } 
    
    public boolean finalizarPedido(int codigoCodigoPedido) {
        String[] camposComValores = {"STATUS==PAGO", "DATA_SAIDA==" +FormataDataParaCadastroNoBanco(null) } ;
        
        update("T_PEDIDO", camposComValores, "COD_PEDIDO", Integer.toString(codigoCodigoPedido));
        
        return true;
    }

    public boolean cadastrarPedido(int codigoDoCliente, int codigoDoServico, int quantidadeDoServico, int codigoDoFuncionario, int codigoDoPedido) {
        String[] campos = {"COD_PEDIDO", "COD_CLIENTE", "COD_SERVICO", "COD_FUNCIONARIO", "QUANTIDADE", "DATA_ENTRADA", "STATUS"};
        String [] valores = {""+codigoDoPedido+"", ""+codigoDoCliente+"", ""+codigoDoServico+"",""+codigoDoFuncionario+"", ""+quantidadeDoServico+"", FormataDataParaCadastroNoBanco(null), "ABERTO"};
        insert("T_PEDIDO", uniOsCamposComOsRespectivosValores(campos, valores));
        return true;
    }
}
