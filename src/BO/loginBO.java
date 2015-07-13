package BO;


public class LoginBO {

    private String senhaDeAcesso;
    private static String dataUltimoAcesso = null;
    private int tipoDeAcessoDoUsuario = -1;
    private int matriculaDoUsuario = 0;


    public void setSenha(String Senha) {
        this.senhaDeAcesso = Senha;
    }
    
    public static void setDataUltimoAcesso(String dataUltimoAcesso) {
        LoginBO.dataUltimoAcesso = dataUltimoAcesso;
    }

    public void setTipoDeAcessoDoUsuario(int tipoDeAcessoDoUsuario) {
        this.tipoDeAcessoDoUsuario = tipoDeAcessoDoUsuario;
    }

    public void setMatriculaDoUsuario(int codigoDoUsuario) {
        this.matriculaDoUsuario = codigoDoUsuario;
    }

    public String getSenha() {
        return senhaDeAcesso;
    }
    
    public static String getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public int getTipoDeAcessoDoUsuario() {
        return tipoDeAcessoDoUsuario;
    }

    public int getMatriculaDoUsuario() {
        return matriculaDoUsuario;
    }

    public boolean VerificaCampoCodigoVazio(int codParam){
        return codParam != -1;
        
    }
    
    
    
    
}
