package com.manutalent.contrasenia.model;

    // Empiezo con enteros para no complicarla
    // son contraseñas de numeros

public abstract class Contrasenia extends Object {

    private int codigo;
    private String nombre;
    private int password;
    private Categoria categoria;

    // Constructor
    public Contrasenia(int codigo,String nombre,int password,Categoria categoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.password = password;
        this.categoria = categoria;
    }

    //getters y setters
    // En este caso los gets estan para cambiarlo?

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getPassword(){
        return password;
    }

    public void setPassword(int password){
        this.password = password;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    // Metodos Abstractos

    public abstract String getTipoContrasenia();
    public abstract String getDetalleEspecifico();


    // toString() + polimorfismo

    @Override
    public String toString(){
        return "Contrasenia{" +
               " \n codigo = " + codigo +
               ", \n nombre ='" + nombre + '\'' +
               ", \n password =" + password +
               //", categoria='" + categoria.getNombre() + '\'' +
               ", \n tipo ='" + this.getTipoContrasenia() + '\'' +
               ", \n detalle ='" + this.getDetalleEspecifico() + '\'' +        
               '}';
    }

}