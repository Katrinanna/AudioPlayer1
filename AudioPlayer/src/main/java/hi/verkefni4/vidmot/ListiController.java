package hi.verkefni4.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import vinnsla.Lag;
import vinnsla.Lagalistar;
import vinnsla.Lagalisti;
import javafx.util.Duration;

import java.net.URL;


public class ListiController implements ViewSwitcher.ControllerInterface {


    //tilviksbreytur
    private Lagalisti lagalisti;
    private Lagalistar lagalistar;
    private Lag validLag;
    private int validIndex;

    // tilviksbreyta - spilarinn
    private MediaPlayer mediaPlayer; // munið að bæta við dependency í pom.xml skrá og í module-info.java

    //viðmótstilviksbreytur
    @FXML
    private ListView<Lag> fxListView;

    @FXML
    private ImageView fxMyndLags;

    @FXML
    private ImageView fxPlayTakki;

    @FXML
    private ProgressBar fxProgressBar;

    @FXML
    private ImageView fxListaCover;

    /**
     *
     * Frumstillir lagalistar (þar með lesast inn lög á listann)
     * Tengir númerið á völdu lagi (validIndex) við ListView viðmótshlut
     */
    @FXML
    void initialize() {
        lagalistar = new Lagalistar();
        lagalisti = new Lagalisti();
        fxListView.setItems(lagalisti.getListi());
        fxListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> {
            //Indexinn í listanum.
            validIndex = fxListView.getSelectionModel().getSelectedIndex();
        });
    }

    /**
     * Tekur inn hlut af taginu Object og ef hluturinn er Lagalisti þá
     * uppfærist ListView í viðmótstrénu með lögunum úr listanum
     * og náð er í númer listans og viðeigandi mynd sett sem cover með því að kalla á setjaCOver
     * Ef hluturinn er ekki Lagalisti er það meðhöndlað
     * @param data tekur inn hlut af taginu Object
     */
    public void setData(Object data) {
        if (data instanceof Lagalisti) {
            lagalisti = (Lagalisti) data;
            fxListView.setItems(lagalisti.getListi());
            setjaCover();
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    /**
     * Hjálparaðferð sem stillir lagalista-myndina inni i listanum
     */
    private void setjaCover() {
        int index = lagalisti.getValinnListi();
        String hlekkur = "media/listi" + (index+1) + ".jpg";
        setjaMynd(fxListaCover,hlekkur);
    }

    /**
     * Bregst við músaratburði og spilar valið lag ef það er ekki null
     * @param mouseEvent ýtt er á lag í listanum
     */
    public void onValidLag(MouseEvent mouseEvent) {
        Lag validLag = fxListView.getSelectionModel().getSelectedItem();
        if (validLag != null) {
            veljaLag(validLag);
            spilaLag();
        }
    }

    /**
     *
     * tilviksbreytan fyrir valið lag er uppfærð
     * kallað er á setjaPlayer() aðferðina
     * myndin er uppfærð fyrir valda lagið
     */
    private void veljaLag(Lag lag) {
        validLag = lag;
        setjaPlayer();
        Image mynd = new Image(getClass().getResourceAsStream(lag.getMyndLags()));
        fxMyndLags.setImage(mynd);
    }

    /**
     * Lagið er pásað ef það er í spilun, lagið er spilað ef það er í pásu
     * Myndin er uppfærð í samræmi við það
     * @param actionEvent ónotað
     */
    public void onPlayPause(ActionEvent actionEvent) {
        if(mediaPlayer == null) {
            return;
        }

        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            setjaMynd(fxPlayTakki,"media/play.jpg");
        } else {
            mediaPlayer.play();
            setjaMynd(fxPlayTakki,"media/pause.jpg");
        }
    }

    /**
     *
     * Ef spilari er til stöðva spilarann
     * Fara aftur í heima view.
     * @param actionEvent ýtt á heim takkann
     */
    public void onHeim(ActionEvent actionEvent) {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
        ViewSwitcher.switchTo(View.HEIMA);
    }

    /**
     * Kallað er á setjaPlayer()
     * Spilun sett í gang ef mediaPlayer er til
     * Play/pause takkinn er settur í gang
     */
    private void spilaLag() {
        setjaPlayer();
        if(mediaPlayer != null) {
            mediaPlayer.play();
        }
        setjaMynd(fxPlayTakki,"media/pause.jpg");
    }

    /**
     * Setja mynd með nafni á ImageView
     *
     * @param fxImageView viðmótshluturinn sem á að uppfærast
     * @param nafnMynd    nafn á myndinni
     */
    private void setjaMynd(ImageView fxImageView, String nafnMynd) {
        URL url = getClass().getResource(nafnMynd);
        if (url != null) {
            Image image = new Image(url.toExternalForm());
            fxImageView.setImage(image);
        } else {
            System.err.println("Skráin fannst ekki: " + nafnMynd);
        }
    }

    /**
     *
     * Player er stoppaður og hent ef hann var ekki stopp
     * Nýr player smíðaður með media sem er  hljóðskrá valda lagsins
     * Lagið er tengt við progressbar
     * Player látinn vita hvenær lag endar
     * Þegar lad endar er kallað í naestaLag
     */
    private void setjaPlayer() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        URL mediaUrl = getClass().getResource(validLag.getHljodskra());
        if(mediaUrl == null) {
            throw new IllegalArgumentException("Skráin fannst ekki: " + validLag.getHljodskra());
        }
        Media media = new Media(mediaUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.currentTimeProperty().addListener((observable, old, newValue) ->
                fxProgressBar.setProgress(newValue.divide(validLag.getLengdLags()).toMillis()));
        mediaPlayer.setStopTime(new Duration(validLag.getLengdLags()));
        mediaPlayer.setOnEndOfMedia(this::naestaLag);
    }


    /**
     * Næsta lag er spilað. Kallað á þessa aðferð þegar fyrra lag á listanum endar
     * validIndex eða númer lagsins er hækkað um 1 og ef það er síðast lagið verður það 0 (sbr. %)
     * ListView uppfærist til samræmis
     * Lagið er valið sem næsta lag og svo spilað
     */
    private void naestaLag() {
    // setja valið lag sem næsta lag á núverandi lagalista
    // uppfæra ListView til samræmis, þ.e. að næsta lag sé valið
    // velja lag
    // spila lag
        validIndex = (validIndex+1) % lagalisti.getListi().size();
        fxListView.getSelectionModel().select(validIndex);
        Lag naestaLag = fxListView.getSelectionModel().getSelectedItem();
        if (naestaLag != null) {
            veljaLag(naestaLag);
            spilaLag();
        }
    }


    public static void main(String[] args) {

    }
}
