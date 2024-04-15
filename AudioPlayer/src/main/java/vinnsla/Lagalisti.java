package vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Lagalisti {


    //tilviksbreytur
    private ObservableList<Lag> listi = FXCollections.observableArrayList();
    private int valinnListi;

    //tómur smiður
    public Lagalisti() {}

    /**
     * getter fyrir
     * @return
     */
    public ObservableList<Lag> getListi() {
        return listi;
    }

    public void lesaLog(String nafnASkra) {
        this.listi.clear();

        try (InputStream is = getClass().getResourceAsStream("/" + nafnASkra);
             Scanner inntak = new Scanner(is, StandardCharsets.UTF_8)) {

            while (inntak.hasNextLine()) {
                String line = inntak.nextLine();
                String[] parts = line.split("\\s+");
                if (parts.length == 4) {
                    String hljodskra = parts[0];
                    String nafnLags = parts[1];
                    int lengdLags = Integer.parseInt(parts[2]);
                    String myndLags = parts[3];
                    Lag lag = new Lag(hljodskra, nafnLags, lengdLags, myndLags);
                    this.listi.add(lag);
                } else {
                    System.out.println("Ólögleg lína: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Villa við lestur skráar: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Tala á vitlausi formi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * getter fyrir valda listann
     * @return valdi listinn
     */
    public int getValinnListi() {
        return valinnListi;
    }

    /**
     * setter fyrir valdinn lista
     * @param valinnListi valinn listi
     */
    public void setValinnListi(int valinnListi) {
        this.valinnListi = valinnListi;
    }

    public static void main(String[] args) {}
}
