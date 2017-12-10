package LogicaNegocio;

public class Solicitud {
    private int idSolicitante;
    private int idCompañero;
    private TipoDificultad tipoDificultad;
    private int numeroMinas;
    private int filas;
    private int columnas;
    
    private static final int MINAS_DEFAULT = 15;
    private static final int FILAS_DEFAULT = 7;
    private static final int COLUMNAS_DEFAULT = 10;
    
    private void setCuadricula(){
        switch(this.tipoDificultad){
            case FACIL:
                this.establecerValoresDefault();
                break;
            case MEDIO:
                this.numeroMinas = 30;
                this.filas = 10;
                this.columnas = 14;
                break;
            case AVANZADO:
                this.numeroMinas = 45;
                this.filas = 13;
                this.columnas = 17;
                break;
            default:
                this.establecerValoresDefault();
                break;
        }
    }
    private void establecerValoresDefault(){
        this.numeroMinas = Solicitud.MINAS_DEFAULT;
        this.filas = Solicitud.FILAS_DEFAULT;
        this.columnas = Solicitud.COLUMNAS_DEFAULT;
    }
    
    public Solicitud(){
        
    }
    /**
     * Establece los valores de una partida de juego.
     * @param idSolicitante, el id el jugador que solicita la partida.
     * @param idCompañero, el id del compañero de juego seleccionado.
     * @param tipoDificultad, la dificultad de la partida.
     */
    public Solicitud(int idSolicitante, int idCompañero, TipoDificultad tipoDificultad){
        this.idSolicitante = idSolicitante;
        this.idCompañero = idCompañero;
        this.tipoDificultad = tipoDificultad;
        this.setCuadricula();
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }
    public void setIdCompañero(int idCompañero) {
        this.idCompañero = idCompañero;
    }
    public void setTipoDificultad(TipoDificultad tipoDificultad) {
        this.tipoDificultad = tipoDificultad;
    }
    public void setNumeroMinas(int numeroMinas) {
        this.numeroMinas = numeroMinas;
    }
    public void setFilas(int filas) {
        this.filas = filas;
    }
    public void setColumnas(int columnas) {
        this.columnas = columnas;
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
