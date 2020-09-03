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
import model.Cliente;

/**
 *
 * @author Viviane
 */
public class ClienteDao implements GenDao<Cliente> {
    
    private static final long serialVersionUID = 1L;
    
    private Cliente produto=new Cliente();

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public ClienteDao() {
        super();
        this.produto = produto;
        emf = javax.persistence.Persistence.createEntityManagerFactory("TrabVersao");
        em = emf.createEntityManager();
    }

    
    @Override
    public Cliente salvar(Cliente p) throws ErroSistema{
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
    public void deletar(Cliente p) throws ErroSistema{
        em.getTransaction().begin();
        Long i=p.getId();
        produto=em.find(Cliente.class, i);
        produto.getId();
        try {
  
            em.remove(produto);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        
    }

    public Cliente consultarPorId(int id) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id");
        query.setParameter("id", id);
        Cliente resultBean = (Cliente) query.getSingleResult();
        return resultBean;    
    }
    
    @Override
    public List<Cliente> listar() throws ErroSistema {
        List<Cliente> pr;
        pr=em.createQuery("SELECT e FROM Cliente e").getResultList();
        return pr;
    }
    

    
}
