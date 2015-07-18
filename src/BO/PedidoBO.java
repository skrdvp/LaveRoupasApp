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
           System.out.println(array.get(i).getCodigoDoPedido());
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
}
