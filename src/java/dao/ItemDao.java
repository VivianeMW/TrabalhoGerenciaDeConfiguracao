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
import model.Item;

/**
 *
 * @author Viviane
 */
public class ItemDao implements GenDao<Item>, Serializable {

    private static final Long serialVersionUID = 1L;

    private Item produto = new Item();

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public ItemDao() {
        super();
        this.produto = produto;
        emf = javax.persistence.Persistence.createEntityManagerFactory("TrabVersao");
        em = emf.createEntityManager();
    }

    @Override
    public Item salvar(Item p) throws ErroSistema {
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
    public void deletar(Item p) throws ErroSistema {
        em.getTransaction().begin();
        Long i = p.getId();
        produto = em.find(Item.class, i);
        produto.getId();
        try {

            em.remove(produto);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public Item consultarPorId(Long id) {
        Query query = em.createQuery("SELECT c FROM Item c WHERE c.id = :id");
        query.setParameter("id", id);
        Item resultBean = (Item) query.getSingleResult();
        return resultBean;
    }

    @Override
    public List<Item> listar() throws ErroSistema {
        List<Item> pr;
        pr = em.createQuery("SELECT e FROM Item e").getResultList();
        return pr;
    }
}
