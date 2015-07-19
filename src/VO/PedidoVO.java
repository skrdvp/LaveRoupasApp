package VO;


public class PedidoVO {
    private int codigoDoPedido;
    private int codigoDoCliente;
    private int codigoDoServico;
    private int quantidade;
    private int codigoDoPagamento;
    private int codigoDoFuncionario;
    private String dataDeEntrada;
    private String dataDeSaida;
    private String statusDoPedido;
    private String descricao;
    private double valorPedido;

    public int getCodigoDoFuncionario() {
        return codigoDoFuncionario;
    }

    public void setCodigoDoFuncionario(int codigoDoFuncionario) {
        this.codigoDoFuncionario = codigoDoFuncionario;
    }

    public int getCodigoDoPedido() {
        return codigoDoPedido;
    }

    public void setCodigoDoPedido(int codigoDoPedido) {
        this.codigoDoPedido = codigoDoPedido;
    }

    public int getCodigoDoCliente() {
        return codigoDoCliente;
    }

    public void setCodigoDoCliente(int codigoDoCliente) {
        this.codigoDoCliente = codigoDoCliente;
    }

    public int getCodigoDoServico() {
        return codigoDoServico;
    }

    public void setCodigoDoServico(int codigoDoServico) {
        this.codigoDoServico = codigoDoServico;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigoDoPagamento() {
        return codigoDoPagamento;
    }

    public void setCodigoDoPagamento(int codigoDoPagamento) {
        this.codigoDoPagamento = codigoDoPagamento;
    }

    public String getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(String dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public String getDataDeSaida() {
        return dataDeSaida;
    }

    public void setDataDeSaida(String dataDeSaida) {
        this.dataDeSaida = dataDeSaida;
    }

    public String getStatusDoPedido() {
        return statusDoPedido;
    }

    public void setStatusDoPedido(String statusDoPedido) {
        this.statusDoPedido = statusDoPedido;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String desc){
        this.descricao = desc;
    }
    
    public void setValor(double nvalor){
        this.valorPedido = nvalor;
    }
    
    public double getValor(){    
        return this.valorPedido;
    }
}
