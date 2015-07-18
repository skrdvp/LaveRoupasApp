package BO;

import DAO.PessoaDAO;
import VO.PessoaVO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PessoaBO {
    
    public boolean validaDadosCliente(PessoaVO pessoa) throws SQLException{
    
        boolean testeNome = validaNome(pessoa.getNome());
        boolean testeCpf = validaCpf(pessoa.getCpf());
        
        if(testeNome == true && testeCpf == true){
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.insereNovoCliente(pessoa);
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean validaNome(String nome){
        int controle = 0;
        
        if(nome.equals("")){
            controle++;
        }else{
            Pattern pattern = Pattern.compile("[0-9]");  
            Matcher matcher = pattern.matcher(nome);  
            if(matcher.find()){  
                controle++;
            }
        }
        if(controle!=0){
            return false;
        }else{
            return true;
        }
    }

    public static boolean validaCpf(String cpf){
        
    if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
        cpf.equals("22222222222") || cpf.equals("33333333333") ||
        cpf.equals("44444444444") || cpf.equals("55555555555") ||
        cpf.equals("66666666666") || cpf.equals("77777777777") ||
        cpf.equals("88888888888") || cpf.equals("99999999999") ||
       (cpf.length() != 11)){    
        
        return(false);
        }
    
    char dig10,dig11;
    int sm,i,r,peso,num;
    
    try{
      sm = 0;
      peso = 10;
      for (i=0; i<9; i++) {
        num = (int)(cpf.charAt(i) - 48); 
        sm = sm + (num * peso);
        peso = peso - 1;
      }
      r = 11 - (sm % 11);
      
      if ((r == 10) || (r == 11)){
        dig10 = '0';
      }else{ 
        dig10 = (char)(r + 48);
      }
      
      sm = 0;
      peso = 11;
      for(i=0; i<10; i++) {
        num = (int)(cpf.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso - 1;
      }
      
      r = 11 - (sm % 11);
      if ((r == 10) || (r == 11)){
        dig11 = '0';
      }else{ 
        dig11 = (char)(r + 48);
      }
      if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))){  
          return(true);
      }else { 
          return(false);
      }
    }catch(InputMismatchException erro) {
        return(false);
    }
    }
    
    public boolean verificaCodDigitadoConsulta(int codigo) throws SQLException{
    
        PessoaDAO pessoaDAO = new PessoaDAO();
        boolean valida = validaCodigoConsulta(codigo);
        if(valida == true){
        boolean verifica = pessoaDAO.verificaTipoDeUsuarioDoPedidoCadastrado(codigo);
            if(verifica==true){
                return true;
            }else{
                return false;
        }
     }
        else{
            return false;
        }
    }
    
    public static boolean validaCodigoConsulta(int cod){
     
        String codigo = Integer.toString(cod);
        int controle = 0;
        
        Pattern pattern = Pattern.compile("[0-9]");  
        Matcher matcher = pattern.matcher(codigo);  
            if(matcher.find()){  
                controle++;
            }
        
        if(controle!=0){
            return true;
        }else{
            return false;
        }
        
    }
}