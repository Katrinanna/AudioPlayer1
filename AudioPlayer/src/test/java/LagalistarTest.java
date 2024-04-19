import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vinnsla.Lagalistar;
import vinnsla.Lagalisti;

public class LagalistarTest {

        @Test
        public void testFjoldaLagalista() {

            Lagalistar lagalistar = new Lagalistar();

            //gá hvort lagalistarnir eru 2 annars verður "test failed"
            Assertions.assertNotNull(lagalistar.lagalistar);
            Assertions.assertEquals(2, lagalistar.lagalistar.length);

        }

        @Test
        public void testLogALista(){

            Lagalistar lagalistar = new Lagalistar();

            // tékka hvort lagalistarnir hafi lög á sér
            for (Lagalisti lagalisti : lagalistar.lagalistar) {
                Assertions.assertNotNull(lagalisti);
                Assertions.assertFalse(lagalisti.getListi().isEmpty());
            }
        }
    }

