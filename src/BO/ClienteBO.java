package BO;

import DAO.PessoaDAO;
import java.sql.SQLException;


public class ClienteBO {
    
    public String getNomeDoClientePeloCidogoNoPedido(int codigoFuncionario) throws SQLException{
        PessoaDAO pessoaDAO =  new PessoaDAO();
        return pessoaDAO.getNomeDaPessoaPeloCodigo(codigoFuncionario);
    }
}
