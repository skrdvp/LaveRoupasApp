package BO;

import DAO.PedidoDAO;
import DAO.PessoaDAO;
import VO.ClienteVO;
import VO.PedidoVO;
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
}
