/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;

import dao.PedidoDao;
import dao.ProdutoDao;
import exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;



import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import model.Cliente;
import model.Pedido;
import model.Produto;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Viviane
 */

@ManagedBean
@SessionScoped
public class PedidoBean extends GenBean<Pedido, PedidoDao>{
    
    private PedidoDao pedidoDao;
    private List<SelectItem> clientesSelect;
    private List<SelectItem> produtosSelect;
     
    @Override
    public PedidoDao getDao() {
        if(pedidoDao == null){
            pedidoDao = new PedidoDao();
        }
        return pedidoDao;
    }

    @Override
    public Pedido criarNovaEntidade() {
        
        return new Pedido();
        
    }

   

    
    Produto p=new Produto();
    
    
    public void adicionarProdutos(){
        p=this.getEntidade().getProduto();
        p.setPreco(1);
        p.setQuantidade(1);
        p.setDesconto(0);
        this.getEntidade().getProdutos().add(p);
     calcularTotal();
    }
    
      public void removerProdutos(){
        p=this.getEntidade().getProduto();
        this.getEntidade().getProdutos().remove(p);
     calcularTotal();
    }
      double valor;
      double valordesc;
      public void calcularTotal(){
          valor=0;
          valordesc=0;
          for(Produto c : this.getEntidade().getProdutos()) {
          valor += c.getQuantidade()*c.getPreco();
          valordesc += c.getQuantidade()*c.getDesconto();
          
}
          
          this.getEntidade().setValorTotal(valor-valordesc);
          
      }

    public List<SelectItem> getClientesSelect() throws ErroSistema {
        if(clientesSelect == null){
            clientesSelect=new ArrayList<SelectItem>();
            ClienteDao clientedao=new ClienteDao();
            List<Cliente> clientesLista=clientedao.listar();
            if(clientesLista!=null && !clientesLista.isEmpty()){
                SelectItem item;
                for(Cliente listClientes : clientesLista){
                    item=new SelectItem(listClientes,listClientes.getNome());
                    clientesSelect.add(item);
                }
                
            }
            
        }
        
        return clientesSelect;
    }

    public List<SelectItem> getProdutosSelect() throws ErroSistema {
        
         
            produtosSelect=new ArrayList<SelectItem>();
            ProdutoDao proddao=new ProdutoDao();
            List<Produto> produtosLista=proddao.listar();
            if(produtosLista!=null && !produtosLista.isEmpty()){
                SelectItem item;
                for(Produto listProdutos : produtosLista){
                    item=new SelectItem(listProdutos,listProdutos.getDescricao());
                    produtosSelect.add(item);
                }
                
            
            
        }
        
        
        
        
        
        
        return produtosSelect;
    }

    
      
    
}
