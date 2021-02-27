/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Negocio.*;

/**
 *
 * @author RedHat
 */
public class Analizador {

    NCondominio ncondominio;
    NCalle ncalle;

    public Analizador() {
        ncondominio = new NCondominio();
        ncalle = new NCalle();
    }

    public String procesarPatron(String patron) {
        String resultado = "";
        String[] cuerpo = patron.split(":");
        String codigo;
        String[] aux;
        String crud = "";
        String[] datos = {};
        //si el patron viene asi [MENSAJE,LISTAS]
        if (cuerpo.length == 1) { //Es decir que solo tiene la 1era parte del patron
            codigo = cuerpo[0].replace("[", "").replace("]", "").trim(); //limpiamos y dejamos el comando 
            aux = codigo.split(",");    //verificamos si el 1er cuerpo del patron es de 2 partes
            if (aux.length == 1) {         //si solo es de una parte, es decir solo viene [AYUDA]  => AYUDA
                patron = aux[0].toUpperCase();  //convertimos a mayusculas
            } else {
                patron = aux[0].toUpperCase(); //1era parte del cuerpo MENSAJE  //si el cuerpo es de 2 partes. por ejemplo los listar alguna entidad [MENSAJE,LISTAR]
                crud = aux[1].toUpperCase();// 2era parte del cuerpo LISTAR
            }

        } else { //SI el patron viene asi [MENSAJE,C]:[DATO1,DATO2,DATO3,DATO4,DATO5]
            codigo = cuerpo[0].replace("[", "").replace("]", "").trim(); // ["[MENSAJE,C]","[DATO1,DATO2,DATO3,DATO4]"]
            aux = codigo.split(",");  //["MENSAJE","C"]
            patron = aux[0].toUpperCase();  //DEJAMOS LISTO EL PATRON ==> MENSAJE
            crud = aux[1].toUpperCase();      // LA OPERACION CRUD QUE SE REALIZARA  ==> C
            datos = cuerpo[1].replace("[", "").replace("]", "").trim().split(","); //nos quedara un arreglo string con todos los datos a insertar//["DATO1","DATO2","DATO3","DATO4"]

        }

        switch (patron) {
            case "AYUDA": {

                System.out.println("El patron es:" + patron);
                resultado = "El Resultado de AYUDA son los siguientes Comandos";
            }
            break;
            case "CONDOMINIO": {
                System.out.println("El patron es:" + patron);

                switch (crud) {
                    case "I": {
                        if (datos.length == 3) {
                            resultado = ncondominio.setCondominio(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "R": {
                        resultado = ncondominio.getCondominios();
                    }
                    break;
                    case "U": {
                        if (datos.length == 3) {
                            resultado = ncondominio.actualizarCondominio(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "D": {
                        if (datos.length == 1) {
                            resultado = ncondominio.eliminar(datos);
                        } else {
                            resultado = "Error en la Ccantidad de Parametros";
                        }
                    }
                    break;
                    default: {
                        System.out.println("Error en la Peticion para el C1");
                    }

                }

            }
            break;

            case "CALLE": {

                switch (crud) {
                    case "I": {
                        if (datos.length == 3) {
                            resultado = ncalle.setCalle(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "R": {
                        resultado = ncalle.getCalles();
                    }
                    break;
                    case "U": {
                        if (datos.length == 3) {
                            resultado = ncalle.actualizarCalle(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "D": {
                        if (datos.length == 1) {
                            resultado = ncalle.eliminar(datos);
                        } else {
                            resultado = "Error en la Ccantidad de Parametros";
                        }
                    }
                    break;
                    default: {
                        System.out.println("Error en la Peticion");
                    }

                }

            }
            break;

            case "SERVICIO": {

                switch (crud) {
                    case "I": {
                        if (datos.length == 3) {
                            resultado = ncalle.setCalle(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "R": {
                        resultado = ncalle.getCalles();
                    }
                    break;
                    case "U": {
                        if (datos.length == 3) {
                            resultado = ncalle.actualizarCalle(datos);
                        } else {
                            resultado = "Error en la Cantidad de Parametros";
                        }
                    }
                    break;
                    case "D": {
                        if (datos.length == 1) {
                            resultado = ncalle.eliminar(datos);
                        } else {
                            resultado = "Error en la Ccantidad de Parametros";
                        }
                    }
                    break;
                    default: {
                        System.out.println("Error en la Peticion");
                    }

                }

            }
            break;

            case "USUARIO": {
                
                

            }
            break;
            case "C5": {

            }
            break;
            case "C6": {

            }
            break;
            case "C7": {

            }
            break;
            case "C8": {

            }
            break;
            default: {

                System.out.println("Error en el patron no se pudo analizar");
                resultado = "Error en el patron no se pudo analizar";
            }
        }
        System.out.println("Imprimiendo en el analizador" + resultado);
        return resultado;
    }

}
