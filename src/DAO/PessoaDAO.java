package DAO;


import LaveRoupasAppMySQL.LaveRoupasAppMySQL;
import VO.ClienteVO;
import VO.PessoaVO;
import VO.UsuarioVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO extends LaveRoupasAppMySQL{
    
    public PessoaDAO() throws SQLException {
        
    }
    
    public ClienteVO getDadosDeEnderecoDoCliente(ClienteVO cliente) throws SQLException {
        String sql = "";
        
        sql = "SELECT * FROM T_ENDERECO WHERE COD_PESSOA = '" +cliente.getCodigo()+ "' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if (rs.next()) {
            cliente.setEndereco(rs.getString("ENDERECO"));            
            cliente.setBairro(rs.getString("BAIRRO"));
            cliente.setCep(rs.getString("CEP"));
            cliente.setCidade(rs.getString("CIDADE"));
            cliente.setEstado(rs.getString("ESTADO"));
            return cliente;
        }else    
            return cliente;
    }
    
    public ArrayList <UsuarioVO> getTodosOsUsuarios() throws SQLException {
        String sql = "";
        UsuarioVO usuarioVO = null;
        
        sql = "SELECT * FROM T_PESSOA WHERE TIPO IN (1 , 2)";
        
        ResultSet rs = executaQueryConsulta(sql);
        ArrayList <UsuarioVO> usuariosVO = new ArrayList <UsuarioVO> ();
        
        while (rs.next()) {
            usuarioVO = new UsuarioVO();
            usuarioVO.setCodigo(rs.getInt("CODIGO"));
            usuarioVO.setCpf(rs.getString("CPF"));
            usuarioVO.setNome(rs.getString("NOME"));
            usuariosVO.add(usuarioVO);         
        }
        
        return  usuariosVO;
    }
        
    public ArrayList <ClienteVO> getTodosOsClientes() throws SQLException{
        String sql = "";
        ClienteVO cliente = null;
        
        sql = "SELECT * FROM T_PESSOA WHERE TIPO = 0";
        
        ResultSet rs = executaQueryConsulta(sql);
        ArrayList <ClienteVO> clientesVO = new ArrayList <ClienteVO> ();
        
        while (rs.next()) {
            cliente = new ClienteVO();
            cliente.setCodigo(rs.getInt("CODIGO"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setNome(rs.getString("NOME"));
            cliente = getDadosDeEnderecoDoCliente(cliente);
            clientesVO.add(cliente);         
        }
    
        return clientesVO;
    }
    
    public String getNomeDaPessoaPeloCodigo(int codigoPessoa) throws SQLException{
        String sql = "";
        
        sql = "SELECT * FROM T_PESSOA WHERE CODIGO = '" +codigoPessoa+ "' LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
           
        if(rs.next()) {
            return rs.getString("NOME");
        }
    
        return rs.getString("NOME");
    }
    
    public void insereNovoCliente(PessoaVO pessoa) throws SQLException{
        
        String sql = "";
        
        sql = "INSERT INTO T_PESSOA(NOME,CPF,TIPO)VALUES('"+pessoa.getNome()+"','"+pessoa.getCpf()+"', "+pessoa.getTipo()+")";
        
        executaQuery(sql);
    }
    
    public boolean verificaTipoDeUsuarioDoPedidoCadastrado(int codigo) throws SQLException{
    
        String sql = "";
        int aux = 0;
        sql = "SELECT TIPO FROM T_PESSOA WHERE CODIGO = '"+codigo+"'LIMIT 1";
        
        ResultSet rs = executaQueryConsulta(sql);
        
        if(rs.next()== true){
            int tipo = rs.getInt("TIPO");
            if(tipo!=0){
                return false;
            }    
        }
        else{
            aux++;
        }
        
        if(aux!=0){
            return false;
        }else{
            return true;
        }
    }
    
}         