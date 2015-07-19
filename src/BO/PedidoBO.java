package BO;

import DAO.PedidoDAO;
import VO.PedidoVO;
import java.sql.SQLException;
import java.util.ArrayList;


public class PedidoBO {
    
   public boolean verificaCodDigitado(int codigo) throws SQLException{
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        
        int aux = 0;
        ArrayList<PedidoVO> array = pedidoDAO.verificaCodExisteTabela();
        
        for(int i = 0; i < array.size();i++){
           
            if(array.get(i).getCodigoDoPedido() == codigo){   
                aux++;
            }
            }
        
        if(aux!=0){
            return true;
        }else{
            return false;
        }
    } 
   
   public ArrayList<PedidoVO> verificaPedidoPeloCod(int codigo) throws SQLException{
       
       ArrayList<PedidoVO> pedidoVO = new ArrayList<PedidoVO>();
       PedidoDAO pedidoDAO = new PedidoDAO();
       
       pedidoVO = pedidoDAO.getPedidoBanco(codigo);
      
       return pedidoVO;
   }
   
   public boolean alteraDadosPedido(int codPedido,int op) throws SQLException{
       
       PedidoDAO pedidoDAO = new PedidoDAO();
       boolean updatePedido = pedidoDAO.alterarPedidosPorCodigo(codPedido, op);
       if(updatePedido){
           return true;
       }
      else
       return false;
       }
   
    public boolean alteraQuantidadePedido(int codPedido,int op) throws SQLException{
    
       PedidoDAO pedidoDAO = new PedidoDAO();
       boolean updatePedido = pedidoDAO.alterarQuantPedidoPorCodigo(codPedido, op);
       if(updatePedido){
           return true;
       }
      else
       return false;
       }
   }
  
