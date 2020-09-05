/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import dao.ClienteDao;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import model.Cliente;

/**
 *
 * @author Viviane
 */
@ManagedBean
@SessionScoped
public class ClienteBean extends GenBean<Cliente, ClienteDao> implements Serializable {

    private static final Long serialVersionUID = 1L;

    private ClienteDao clienteDao;

    @Override
    public ClienteDao getDao() {
        if (clienteDao == null) {
            clienteDao = new ClienteDao();
        }
        return clienteDao;
    }

    @Override
    public Cliente criarNovaEntidade() {
        return new Cliente();
    }

    private Client c = Client.create();
    private Gson gson = new Gson();
    private Cliente cliente = new Cliente();

    public void consultaCep() {
        this.getEntidade().setCep(this.getEntidade().getCep().replace(".", ""));
        this.getEntidade().setCep(this.getEntidade().getCep().replace("-", ""));
        WebResource wr = c.resource("https://viacep.com.br/ws/" + this.getEntidade().getCep() + "/json/");
        String json = wr.get(String.class);
        this.cliente = gson.fromJson(json, new TypeToken<Cliente>() {
        }.getType());
        this.getEntidade().setLogradouro(this.cliente.getLogradouro());
        this.getEntidade().setBairro(this.cliente.getBairro());
        this.getEntidade().setLocalidade(this.cliente.getLocalidade());
        this.getEntidade().setUf(this.cliente.getUf());

    }

}
