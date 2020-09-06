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
import model.Cliente;

/**
 *
 * @author Viviane
 */
public class ClienteDao implements GenDao<Cliente>, Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente cliente = new Cliente();

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public ClienteDao() {
        super();
        this.cliente = cliente;
        emf = javax.persistence.Persistence.createEntityManagerFactory("TrabVersao");
        em = emf.createEntityManager();
    }

    @Override
    public Cliente salvar(Cliente p) throws ErroSistema {
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
    public void deletar(Cliente p) throws ErroSistema {
        em.getTransaction().begin();
        Long i = p.getId();
        cliente = em.find(Cliente.class, i);
        cliente.getId();
        try {

            em.remove(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

    }

    public Cliente consultarPorId(Long id) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id");
        query.setParameter("id", id);
        Cliente resultBean = (Cliente) query.getSingleResult();
        return resultBean;
    }

    @Override
    public List<Cliente> listar() throws ErroSistema {
        List<Cliente> pr;
        pr = em.createQuery("SELECT e FROM Cliente e order by e.nome").getResultList();
        return pr;
    }

}
