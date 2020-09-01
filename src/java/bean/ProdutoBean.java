/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProdutoDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Produto;

/**
 *
 * @author viviane.wehrmeister
 */
@ManagedBean
@SessionScoped
public class ProdutoBean extends GenBean<Produto, ProdutoDao> {
    
    private ProdutoDao produtoDao;
    
    @Override
    public ProdutoDao getDao() {
        if(produtoDao == null){
            produtoDao = new ProdutoDao();
        }
        return produtoDao;
    }

    @Override
    public Produto criarNovaEntidade() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new Produto();
    }
  
}
