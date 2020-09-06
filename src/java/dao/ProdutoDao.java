/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ErroSistema;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import model.Produto;

/**
 *
 * @author viviane.wehrmeister
 */
public class ProdutoDao implements GenDao<Produto>, Serializable {

    private static final Long serialVersionUID = 1L;

    private Produto produto = new Produto();

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public ProdutoDao() {
        super();
        this.produto = produto;
        emf = javax.persistence.Persistence.createEntityManagerFactory("TrabVersao");
        em = emf.createEntityManager();
    }

    @Override
    public Produto salvar(Produto p) throws ErroSistema {
        em.getTransaction().begin();

        try {
            em.merge(p);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        return p;
    }

    @Override
    public void deletar(Produto p) throws ErroSistema {
        em.getTransaction().begin();
        Long i = p.getId();
        produto = em.find(Produto.class, i);
        produto.getId();
        try {

            em.remove(produto);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public Produto consultarPorId(Long id) {
        Query query = em.createQuery("SELECT c FROM Produto c WHERE c.id = :id");
        query.setParameter("id", id);
        Produto resultBean = (Produto) query.getSingleResult();
        return resultBean;
    }

    @Override
    public List<Produto> listar() throws ErroSistema {
        List<Produto> pr;
        pr = em.createQuery("SELECT e FROM Produto e order by e.descricao").getResultList();
        return pr;
    }
}
