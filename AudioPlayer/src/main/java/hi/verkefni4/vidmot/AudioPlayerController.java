package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import vinnsla.Askrifandi;
import vinnsla.Lagalistar;
import vinnsla.Lagalisti;

import java.util.Optional;

public class AudioPlayerController {

    //viðmótstilviksbreyta
    @FXML
    private Label fxInnskraning;

    //tilviksbreyta til að kalla á lagalistar klasann
    private Lagalistar lagalistar = new Lagalistar();

    /**
     * Náð í nafn áskrifanda ef til er og sett í labelið fyrir innskráðan áskrifanda
     */
    public void initialize() {
        Askrifandi askrifandi = AudioPlayerApplication.getLoggedInAskrifandi();
        if(askrifandi != null) {
            fxInnskraning.setText(askrifandi.getNafn());
        }
    }

    /**
     * Listinn sem valinn var á forsíðunni er fundinn og númer hans í röðinni
     * Náð er í þann lista í lagalistafylkinu og hann settur í breytuna valinnListi
     * Skiptum svo yfir í lagalista view
     * @param mouseEvent
     */
    public void onVeljaLista(ActionEvent mouseEvent) {
        //Hvaða listi var valinn
        Button reitur = (Button) mouseEvent.getSource();
        int j = GridPane.getColumnIndex(reitur);
        Lagalisti valinnListi = lagalistar.lagalistar[j];
        valinnListi.setValinnListi(j);

        //Skiptum yfir í LAGALISTI view og setjum valdaListann inn sem userdata
        ViewSwitcher.setUserData(valinnListi);
        ViewSwitcher.switchTo(View.LAGALISTI);

    }

    /**
     * Dialog glugginn þegar ýtt er á skrá inn
     * Hér hefst modal dialogurinn þar sem áskrifandi getur loggað sig inn
     * og textinn vistast í labelinu fyrir innskráðan notanda.
     */
    @FXML
    protected void onLogin() {
        Askrifandi nyrAskrifandi = new Askrifandi("");
        AskrifandiDialog dialog = new AskrifandiDialog(nyrAskrifandi);
        dialog.initModality(Modality.APPLICATION_MODAL);
        Optional<Askrifandi> result = dialog.showAndWait();

        result.ifPresent(askrifandi -> {fxInnskraning.setText(askrifandi.getNafn());
            AudioPlayerApplication.setLoggedInAskrifandi(askrifandi);

        });
    }
}
