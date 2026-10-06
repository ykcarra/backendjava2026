package com.manutalent.contrasenia.model;

public class ContraseniaBancos extends Contrasenia{
    
    private int diasParaVencimiento;

    public ContraseniaBancos(int codigo,String nombre,int password,Categoria categoria,int diasParaVencimiento){
        super(codigo,nombre,password,categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento(){
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento){
        this.diasParaVencimiento = diasParaVencimiento;
    }

    // Es necesario el Override, si no hay ninguno implementado antes?

    @Override
    public String getTipoContrasenia(){
        return "Bancaria";
    }

    @Override
    public String getDetalleEspecifico(){
        return "Dias para vencimiento: " + diasParaVencimiento;
    }
}
