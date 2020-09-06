/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ClienteDao;
import dao.ItemDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import exception.ErroSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import model.Cliente;
import model.Item;
import model.Pedido;
import model.Produto;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Viviane
 */
@ManagedBean
@SessionScoped
public class PedidoBean extends GenBean<Pedido, PedidoDao> implements Serializable {

    private static final Long serialVersionUID = 1L;
    private PedidoDao pedidoDao;
    private List<SelectItem> clientesSelect;
    private List<SelectItem> produtosSelect;

    @Override
    public PedidoDao getDao() {
        if (pedidoDao == null) {
            pedidoDao = new PedidoDao();
        }
        return pedidoDao;
    }

    @Override
    public Pedido criarNovaEntidade() {

        return new Pedido();

    }

    Produto p = new Produto();
    Item item = new Item();
    List<Item> itens = new ArrayList<Item>();

    public void adicionarProdutos() {
        Item item = new Item();
        p = this.getEntidade().getProduto();
        item.setProduto(p);
        item.setQuantidade(1);
        item.setDesconto(this.getEntidade().getCliente().getDesconto());
        this.getEntidade().getItensPedido().add(item);

        calcularTotal();
    }

    ItemDao pd = new ItemDao();

    public void removerProdutos(Item v) {
        this.getEntidade().getItensPedido().remove(v);

        calcularTotal();
    }
    double valor;
    double valordesc;

    public void calcularTotal() {
        valor = 0;
        valordesc = this.getEntidade().getCliente().getDesconto() / 100;
        for (Item c : this.getEntidade().getItensPedido()) {
            valor += c.getQuantidade() * c.getProduto().getPreco();

        }
        valordesc = valor * valordesc;
        this.getEntidade().setValorTotal(valor - valordesc);

    }

    public List<SelectItem> getClientesSelect() throws ErroSistema {

        clientesSelect = new ArrayList<SelectItem>();
        ClienteDao clientedao = new ClienteDao();
        List<Cliente> clientesLista = clientedao.listar();
        if (clientesLista != null && !clientesLista.isEmpty()) {
            SelectItem item;
            for (Cliente listClientes : clientesLista) {
                item = new SelectItem(listClientes, listClientes.getNome());
                clientesSelect.add(item);
            }

        }

        return clientesSelect;
    }

    public List<SelectItem> getProdutosSelect() throws ErroSistema {

        produtosSelect = new ArrayList<SelectItem>();
        ProdutoDao proddao = new ProdutoDao();
        List<Produto> produtosLista = proddao.listar();
        if (produtosLista != null && !produtosLista.isEmpty()) {
            SelectItem item;
            for (Produto listProdutos : produtosLista) {
                item = new SelectItem(listProdutos, listProdutos.getDescricao());
                produtosSelect.add(item);
            }

        }

        return produtosSelect;
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public ItemDao getPd() {
        return pd;
    }

    public void setPd(ItemDao pd) {
        this.pd = pd;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValordesc() {
        return valordesc;
    }

    public void setValordesc(double valordesc) {
        this.valordesc = valordesc;
    }

}
