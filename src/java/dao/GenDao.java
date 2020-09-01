/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.ErroSistema;
import java.util.List;

/**
 *
 * @author viviane.wehrmeister
 */
public interface GenDao<E> {//E representa minha entidade
    
    public E salvar(E entidade) throws ErroSistema;
    public void deletar(E entidade) throws ErroSistema;
    public List<E> listar() throws ErroSistema;
    
}
