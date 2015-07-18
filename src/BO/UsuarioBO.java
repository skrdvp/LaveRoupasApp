package BO;

import DAO.PedidoDAO;
import DAO.PessoaDAO;
import DAO.ServicoDAO;
import VO.ClienteVO;
import VO.PedidoVO;
import VO.ServicoVO;
import VO.UsuarioVO;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioBO extends PessoaBO{
    
    public ArrayList <ClienteVO> getTodosOsClientes() throws SQLException{
        PessoaDAO pessoaDAO =  new PessoaDAO();
        return pessoaDAO.getTodosOsClientes();
    }
    
    public ArrayList <PedidoVO> getTodosOsPedidos(String status) throws SQLException{
        PedidoDAO pedidoDAO =  new PedidoDAO();
        return pedidoDAO.getTodosOsPedidosPorStatus(status);
    }
    
    public ArrayList <UsuarioVO> getTodosOsUsuarios() throws SQLException{
        PessoaDAO pessoaDAO =  new PessoaDAO();
        return pessoaDAO.getTodosOsUsuarios();
    }
    
    public String getNomeDoFuncinarioOuClientePeloCidogoNoPedido(int codigoFuncionario) throws SQLException{
        PessoaDAO pessoaDAO =  new PessoaDAO();
        return pessoaDAO.getNomeDaPessoaPeloCodigo(codigoFuncionario);
    }
    
    public boolean finalizarPedidoPeloCidogo(int codigoCodigoPedido) throws SQLException{
        PedidoDAO pedidoDAO =  new PedidoDAO();
        return pedidoDAO.finalizarPedido(codigoCodigoPedido);
    }

    public ArrayList<ServicoVO> getTodosOsServicos() throws SQLException {
        ServicoDAO servicoDAO = new ServicoDAO();
        return servicoDAO.getTodosOsServicos();
    }

    public boolean cadastrarPedido(int codigoDoCliente, int codigoDoServico, int quantidadeDoServico, int codigoDoFuncionario, int codigoDoPedido) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.cadastrarPedido(codigoDoCliente, codigoDoServico, quantidadeDoServico, codigoDoFuncionario, codigoDoPedido);
    }
}
