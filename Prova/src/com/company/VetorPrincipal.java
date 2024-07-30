import java.util.Random;

class VetorPrincipal{

    private Integer[] vetor;

    public VetorPrincipal(){
        this.vetor = new Integer[20];
        for(int i = 0; i < this.vetor.length; i++){
            this.vetor[i] = 0;
        }
    }

    public int valorNaPosicao(int posicao){
        return this.vetor[posicao];
    }


    public void incrementa(int posicao){
        this.vetor[posicao] = this.vetor[posicao] + 1;
    }

    public void decrementa(int posicao){
        this.vetor[posicao] = this.vetor[posicao] - 1;
    }

    public void exibe(){
        System.out.println("Exibindo valores do vetor");
        for(int i = 0; i < this.vetor.length; i++){
            System.out.println("posicao " + i + " valor: " + this.vetor[i]);;
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        VetorPrincipal vetor = new VetorPrincipal();
        vetor.exibe();
        Thread[] threadIncrementa = new Thread[40];
        Thread[] threadDecrementa = new Thread[40];

        for (int i=0; i<40; i++) {
            threadIncrementa[i] = new IncrementaValores(vetor);
            threadIncrementa[i].start();
        }

        for (int i=0; i<40; i++) {
            threadIncrementa[i].join();
        }
        vetor.exibe();

        for (int i=0; i<10; i++) {
            threadDecrementa[i] = new DecrementaValores(vetor);
            threadDecrementa[i].start();
        }

        for (int i=0; i<10; i++) {
            threadDecrementa[i].join();
        }
        vetor.exibe();
    }
}

class IncrementaValores extends Thread {
    private VetorPrincipal vetor;

    IncrementaValores(VetorPrincipal vetor) {
        this.vetor = vetor;
    }
    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 5000; i++) {
            try {
                Thread.sleep(rand.nextInt(200));
            }
            catch (InterruptedException t) {
                t.printStackTrace();
            }
            int n = rand.nextInt(20);
            if (vetor.valorNaPosicao(n) < 10000) {
                vetor.incrementa(n);
            }
        }
    }
}

class DecrementaValores extends Thread {
    private VetorPrincipal vetor;

    DecrementaValores(VetorPrincipal vetor) {
        this.vetor = vetor;
    }
    @Override
    public void run() {
        Random rand = new Random();
        for (int i=0; i<20000; i++) {
            try {
                Thread.sleep(rand.nextInt(200));
            }
            catch (InterruptedException t) {
                t.printStackTrace();
            }
            int n = rand.nextInt(20);
            if (vetor.ValorNaPosicao(n) < 10000) {
                vetor.incrementa(n);
            }
        }
    }
}


