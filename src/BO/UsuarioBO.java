package BO;

import DAO.PessoaDAO;
import java.sql.SQLException;


public class UsuarioBO extends PessoaBO{
    private int matricula;
    private String senha;
    private int tipoDeUsuario;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(int tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
    
    public UsuarioBO() {
        super();
    }

    public void getNomePessoaByMatriculaDoUsuarioLogin(int matriculaDoUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
