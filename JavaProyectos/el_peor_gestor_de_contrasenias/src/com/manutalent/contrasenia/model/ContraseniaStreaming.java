package com.manutalent.contrasenia.model;

public class ContraseniaStreaming extends Contrasenia {
    private int cantidadUsuarios;

    public ContraseniaStreaming(int codigo,String nombre,int password,Categoria categoria,int cantidadUsuarios){
        super(codigo,nombre,password,categoria);
        this.cantidadUsuarios = cantidadUsuarios;
    }

    public int getCantidadUsuarios(){
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(int cantidadUsuarios){
        this.cantidadUsuarios = cantidadUsuarios;
    }

    @Override
    public String getTipoContrasenia(){
        return "De Streaming";
    }

    @Override
    public String getDetalleEspecifico(){
        return "El numero de usuarios es: " + cantidadUsuarios + " en la familia";
    }

    public String nroTelFamiliarAdministrador(){
        return "11-9089-4567";
    }
    

}
