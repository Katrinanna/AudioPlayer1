package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class NyrLeikurController {

    @FXML
    private Button fxByrjaLeik;

    @FXML
    private Button fxStillingar;

    @FXML
    private ImageView fxMyndStillingar;

    public void initialize() { //reyna að setja mynd á stillingarnar-sunna
        //  Image image = new Image("/media/stillingar.png");
        //  fxMyndStillingar.setImage(image);
    }

    //Þegar ýtum á takkann, á að koma upp til að velja tvo notendur og þaðan inn í leikinn
    //fer þá önnur fxml skrá i staðin fyrir goldrush-view, en var bara að sjá hvort virkaði -sunna
    @FXML
    public void onByrjaLeik(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("goldrush-view.fxml"));
        //FXML_Lestur.lesa(this,"goldrush-view.fxml"); -skil ekki alveg hvernig þú notar þetta en 100% hægt að nota frekar
        //til að gera þetta betri kóða -sunna
        //Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("KubbaKapp");
        stage.setScene(scene);
        stage.show();
        Stage nuverandiStage = (Stage) fxByrjaLeik.getScene().getWindow();
        nuverandiStage.close();

    }

    @FXML
    private void onUmForritid(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um forritið");
        alert.setHeaderText("KubbaKapp");
        alert.setContentText("KubbaKapp er leikur þar sem tveir leikmenn keppast um að safna stigum á vissum tíma. \n" +
                "Tíminn fer eftir erfileikastigi (létt, miðlungs, erfitt) sem hægt er að velja í stillingum. \n" +
                "Hafa skal varann á sprengjum sem birtast af og til, ef leikmaður rekst á sprengju missir hann 1 af 3 lífum. " +
                "Ef öll líf klárast tapar sá hin sami leiknum. \n" + "Stig eru söfnuð með að ná kubbum, sem líkt og sprengjurnar birtast á leikskjánnum. \n" +
                "\n" + "Þegar sigri er náð birtist gluggi sem hefur valmöguleika á að hefja nýjan leik eða hætta.\n" +
                " \n" +
                "KubbaKappar smíðuðu þetta forrit.");
        alert.showAndWait();
    }


    /**
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onStillingar(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(KubbaKappApplication.class.getResource("leidbeiningar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 202, 300);
        stage.setScene(scene);
        stage.show();

    }

}
