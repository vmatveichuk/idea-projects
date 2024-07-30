class MapaJogo{
    private Integer[][] mapa;
    private Integer qtJogadasFeitas;
    private Integer qtInconsistencias;
    public MapaJogo(){
        this.qtJogadasFeitas = 0;
        this.qtInconsistencias = 0;
        this.mapa = new Integer[10][10];
        for(int i = 0; i < this.mapa.length; i++){
            for(int j = 0; j < this.mapa[i].length; j++){
                this.mapa[i][j] = 0;
            }
        }
    }
    public synchronized void definePosicaoJogador(int posicaoI, int posicaoJ, int jogador){
        if(this.mapa[posicaoI][posicaoJ] != 0){
            this.qtInconsistencias++;
        }
        this.mapa[posicaoI][posicaoJ] = jogador;
        this.qtJogadasFeitas = this.qtJogadasFeitas + 1;
    }
    public Integer getQtJogadasFeitas(){
        return this.qtJogadasFeitas;
    }
    public Integer getQtInconsistencias(){
        return this.qtInconsistencias;
    }
    public void exibeMapa(){
        System.out.println("Exibindo mapa");
        for(int i = 0; i < this.mapa.length; i++){
            for(int j = 0; j < this.mapa[i].length; j++){
                System.out.print(this.mapa[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
