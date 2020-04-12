package com.enterprise.version1.ejemplosensorjava.herencia;

import com.enterprise.version1.ejemplosensorjava.clase.Valores;

public class Grados extends Valores {

    public double calcularCentigradosAfarenheit(){

        return ((this.getGrados() * 9/5) + 32);
    }
    public double calcularCentigradosAkelvin(){

        return (this.getGrados() + 273.15);
    }

}
