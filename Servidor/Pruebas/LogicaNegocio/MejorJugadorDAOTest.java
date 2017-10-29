package LogicaNegocio;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class MejorJugadorDAOTest {
    
    public MejorJugadorDAOTest() {
    }

    @Test
    public void testGetListaJugadores() {
        ArrayList<LogicaNegocio.Jugador> lista = new MejorJugadorDAO().getListaJugadores();
        int contador = 0;
        for (LogicaNegocio.Jugador jugador : lista){
            double porcentaje = jugador.getPartidasJugadas() * 0.8;
            int partidasGanadas = jugador.getPartidasJugadas() - jugador.getPartidasPerdidas();
            if (partidasGanadas > 10 && partidasGanadas >= porcentaje){
                contador ++;
            }
        }
        assertEquals(lista.size(), contador);
    }
    
}
