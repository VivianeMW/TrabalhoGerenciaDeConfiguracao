/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProdutoDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Produto;

/**
 *
 * @author viviane.wehrmeister
 */
@ManagedBean
@ViewScoped
public class ProdutoBean extends GenBean<Produto, ProdutoDao> implements Serializable {

    private static final Long serialVersionUID = 1L;
    private ProdutoDao produtoDao;

    @Override
    public ProdutoDao getDao() {
        if (produtoDao == null) {
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
