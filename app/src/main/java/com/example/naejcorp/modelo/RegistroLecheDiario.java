package com.example.naejcorp.modelo;

public class RegistroLecheDiario {

    private String fechaDia;
    private Double lecheProducida;

    public RegistroLecheDiario() {

    }

    public RegistroLecheDiario(String fechaDia, Double lecheProducida) {
        this.fechaDia = fechaDia;
        this.lecheProducida = lecheProducida;
    }



    public String getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(String fechaDia) {
        this.fechaDia = fechaDia;
    }

    public Double getLecheProducida() {
        return lecheProducida;
    }

    public void setLecheProducida(Double lecheProducida) {
        this.lecheProducida = lecheProducida;
    }
}
