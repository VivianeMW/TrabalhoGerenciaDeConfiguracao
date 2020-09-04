/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ErroSistema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import model.Pedido;

/**
 *
 * @author Viviane
 */
public class PedidoDao implements GenDao<Pedido> {
    
    private static final long serialVersionUID = 1L;
    
     private Pedido pedido=new Pedido();

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public PedidoDao() {
        super();
        this.pedido = pedido;
        emf = javax.persistence.Persistence.createEntityManagerFactory("TrabVersao");
        em = emf.createEntityManager();
    }

    
    @Override
    public Pedido salvar(Pedido p) throws ErroSistema{
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
    public void deletar(Pedido p) throws ErroSistema{
        em.getTransaction().begin();
        Long i=p.getId();
        pedido=em.find(Pedido.class, i);
        pedido.getId();
        try {
  
            em.remove(pedido);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        
    }

    public Pedido consultarPorId(int id) {
        Query query = em.createQuery("SELECT c FROM Pedido c WHERE c.id = :id");
        query.setParameter("id", id);
        Pedido resultBean = (Pedido) query.getSingleResult();
        return resultBean;    
    }
    
    @Override
    public List<Pedido> listar() throws ErroSistema {
        List<Pedido> pr;
        pr=em.createQuery("SELECT e FROM Pedido e").getResultList();
        return pr;
    }
    
}
