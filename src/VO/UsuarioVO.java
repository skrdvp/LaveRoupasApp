
package VO;


public class UsuarioVO extends PessoaVO{
    
    private String senha;
    private static String dataUltimoAcesso = null;
    private int tipoDeAcessoDoUsuario = -1;

    public static String getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public static void setDataUltimoAcesso(String dataUltimoAcesso) {
        UsuarioVO.dataUltimoAcesso = dataUltimoAcesso;
    }

    public int getTipoDeAcessoDoUsuario() {
        return tipoDeAcessoDoUsuario;
    }

    public void setTipoDeAcessoDoUsuario(int tipoDeAcessoDoUsuario) {
        this.tipoDeAcessoDoUsuario = tipoDeAcessoDoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public UsuarioVO() {
        super();
    }
    
}
