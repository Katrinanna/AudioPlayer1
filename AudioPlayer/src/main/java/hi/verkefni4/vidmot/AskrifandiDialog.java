package hi.verkefni4.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import vinnsla.Askrifandi;

import java.io.IOException;

public class AskrifandiDialog extends Dialog<Askrifandi> {

    //tilviksbreyta af Askrifandi klasanum
    private Askrifandi askrifandi;

    //fxml breyta fyrir textaboxið þar sem notandi skrifar nafnið sitt
    @FXML
    private TextField fxNafnAskrifanda;


    /**
     * Smiður fyrir Askrifandi dialog
     * Tilviksbreytan er sett sem askrifandi
     * Kallað er á hin tvö föllin líka til að sýna dialoginn oog athuga hvort við fáum svar úr honum
     * svo við getum sett nafnið á áskrifandanum í notendaviðmótið
     * @param askrifandi askrifandi
     */
    public AskrifandiDialog(Askrifandi askrifandi) {
        this.askrifandi = askrifandi;
        setDialogPane(lesaDialog());
        setResultConverter();
    }

    /**
     * Kallar á setResultConverter í Dialog klasanum með lambda falli sem athugar hvort notandi ýtti
     * á OK hnappinn og færir þá nafnið úr textaboxinu og setur í askrifandi hlutinn
     */
    private void setResultConverter() {
        setResultConverter(b -> { // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                //stilla áskrifanda
                askrifandi.setNafn(fxNafnAskrifanda.getText());
                return askrifandi;
            } else {
                return null;
            }
        });

    }

    /**
     * Les askrifandi-view.fxml og skilar rótinni sem er af klasanum DialogPane.
     * @return
     */
    private DialogPane lesaDialog() {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("askrifandi-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {}
}
