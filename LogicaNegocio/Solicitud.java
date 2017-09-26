package LogicaNegocio;

public class Solicitud {
    private int idSolicitante;
    private int idCompañero;
    private TipoDificultad tipoDificultad;
    private int numeroMinas;
    private int filas;
    private int columnas;
    
    private void setCuadricula(){
        switch(this.tipoDificultad){
            case facil:
                this.numeroMinas = 10;
                this.filas = 8;
                this.columnas = 5;
                break;
            case medio:
                this.numeroMinas = 15;
                this.filas = 10;
                this.columnas = 7;
                break;
            case avanzado:
                this.numeroMinas = 30;
                this.filas = 13;
                this.columnas = 10;
                break;
        }
    }
    
    public Solicitud(int idSolicitante, int idCompañero, TipoDificultad tipoDificultad){
        this.idSolicitante = idSolicitante;
        this.idCompañero = idCompañero;
        this.tipoDificultad = tipoDificultad;
        this.setCuadricula();
    }
    
    public int getIdSolicitante(){
        return this.idSolicitante;
    }
    public int getIdCompañero(){
        return this.idCompañero;
    }
    public int getNumeroFilas(){
        return this.filas;
    }
    public int getNumeroColumnas(){
        return this.columnas;
    }
    public int getNumeroMinas(){
        return this.numeroMinas;
    }
    public TipoDificultad getTipoDificultad(){
        return this.tipoDificultad;
    }
}
