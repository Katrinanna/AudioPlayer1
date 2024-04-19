import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vinnsla.Lag;
import vinnsla.Lagalisti;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LagalistiTest {

    @Test
    public void testLesaLog() {

        Lagalisti lagalisti = new Lagalisti();

        lagalisti.lesaLog("listi1.txt");

        ObservableList<Lag> listi1 = lagalisti.getListi();

        // tékka hvort listin er tómur
        Assertions.assertFalse(listi1.isEmpty());
    }

    @Test
    public void testLagIlista1(){

        Lagalisti lagalisti = new Lagalisti();

        // Call the lesaLog method to read a test file
        lagalisti.lesaLog("listi1.txt");

        // Get the list of songs
        ObservableList<Lag> listi1 = lagalisti.getListi();

        // tékka hvort lagið media/sample-3s.mp3 er á lagalistanum
        boolean lagFundid = false;
        for (Lag lag : listi1) {
            if (lag.getHljodskra().equals("media/sample-3s.mp3")) {
                lagFundid = true;
                break;
            }
        }

        assertTrue(lagFundid, "Lag ekki fundið");
    }

    @Test
    public void testLagILista2(){
        Lagalisti lagalisti = new Lagalisti();


        lagalisti.lesaLog("listi2.txt");


        ObservableList<Lag> listi2 = lagalisti.getListi();

        // tékka hvort lagið media/sample-9s.mp3 er á lagalistanum
        boolean lagFundid = false;
        for (Lag lag : listi2) {
            if (lag.getHljodskra().equals("media/sample-9s.mp3")) {
                lagFundid = true;
                break;
            }
        }

        assertTrue(lagFundid, "Lag ekki fundið");
    }
}