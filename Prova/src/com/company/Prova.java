package com.company;

class ValorCompartilhado{

    private int valor;

    public ValorCompartilhado(){
        this.valor = 0;
    }

    public void incrementaValor(){
        synchronized (this) {
            this.valor++;
        }
    }

    public void decrementaValor(){
        synchronized (this) {
            this.valor--;
        }
    }

    public int getValor(){
        return this.valor;
    }

}

class DecrementaThread extends Thread{
    private ValorCompartilhado valorCompartilhado;

    public DecrementaThread(ValorCompartilhado valor) throws InterruptedException {
        this.valorCompartilhado = valor;
    }

    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                while (this.valorCompartilhado.getValor() == 0) {
                    Thread.sleep(10l);
                }
                this.valorCompartilhado.decrementaValor();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

class IncrementaThread extends Thread{
    private ValorCompartilhado valorCompartilhado;

    public IncrementaThread(ValorCompartilhado valor) throws InterruptedException {
        this.valorCompartilhado = valor;
    }

    public void run(){
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100l);
                this.valorCompartilhado.incrementaValor();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

public class Prova {
    public static void main(String[] args) throws InterruptedException {
        ValorCompartilhado valor = new ValorCompartilhado();

        IncrementaThread threadIncrementa1 = new IncrementaThread(valor);
        IncrementaThread threadIncrementa2 = new IncrementaThread(valor);
        IncrementaThread threadIncrementa3 = new IncrementaThread(valor);
        DecrementaThread threadDecrementa1 = new DecrementaThread(valor);
        DecrementaThread threadDecrementa2 = new DecrementaThread(valor);

        threadIncrementa1.start();
        threadIncrementa2.start();
        threadIncrementa3.start();

        threadDecrementa1.start();
        threadDecrementa2.start();

        threadIncrementa1.join();
        threadIncrementa2.join();
        threadIncrementa3.join();
        threadDecrementa1.join();
        threadDecrementa2.join();

        System.out.println(valor.getValor());

    }
}

