/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.ClienteDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Cliente;

/**
 *
 * @author Viviane
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length() > 0) {
            Long codigo = Long.valueOf(string);
            ClienteDao clienteDao = new ClienteDao();
            return clienteDao.consultarPorId(codigo);
        }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object ocliente) {
        if (ocliente != null) {
            Cliente cliente = (Cliente) ocliente;
            return cliente.getId().toString();
        }

//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

}
