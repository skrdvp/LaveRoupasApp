package VO;


public class PagamentoPedidoVO {
    private int codigoDoPagamento;
    private String numeroDoCartao;
    private String codigoDeSegunrança;

    public int getCodigoDoPagamento() {
        return codigoDoPagamento;
    }

    public void setCodigoDoPagamento(int codigoDoPagamento) {
        this.codigoDoPagamento = codigoDoPagamento;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public String getCodigoDeSegunrança() {
        return codigoDeSegunrança;
    }

    public void setCodigoDeSegunrança(String codigoDeSegunrança) {
        this.codigoDeSegunrança = codigoDeSegunrança;
    }
    
}
