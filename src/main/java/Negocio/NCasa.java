/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.*;

/**
 *
 * @author RedHat
 */
public class NCasa {

    Casa dcasa;

    public NCasa() {
        this.dcasa = new Casa();

    }

    public String setCasa(String[] d) {

        dcasa.setNumero(Integer.parseInt(d[0]));
        dcasa.setTipo(d[1]);
        dcasa.setCipropietario(Integer.parseInt(d[2]));
        dcasa.setCodigocondominio(Integer.parseInt(d[2]));
        return dcasa.registrar();

    }

    public String getCasas() {

        return dcasa.getCasas();

    }

    public String actualizarCasa(String[] d) {
        dcasa.setNumero(Integer.parseInt(d[0]));
        dcasa.setTipo(d[1]);
        dcasa.setCipropietario(Integer.parseInt(d[2]));
        dcasa.setCodigocondominio(Integer.parseInt(d[2]));
        return dcasa.actualizar();

    }

    public String eliminar(String[] d) {
        dcasa.setNumero(Integer.parseInt(d[0]));
        return dcasa.eliminar();

    }

}
