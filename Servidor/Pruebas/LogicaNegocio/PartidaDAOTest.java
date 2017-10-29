package LogicaNegocio;

import org.junit.Test;
import static org.junit.Assert.*;

public class PartidaDAOTest {
    
    public PartidaDAOTest() {
    }

    @Test
    public void testRegistrarPartida() {
        assertEquals(true, new PartidaDAO().registrarPartida(new LogicaNegocio.Partida("facil", "05:50", 1)));
    }
    
}
