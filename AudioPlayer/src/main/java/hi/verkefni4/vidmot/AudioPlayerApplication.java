package hi.verkefni4.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vinnsla.Askrifandi;

import java.io.IOException;

public class AudioPlayerApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AudioPlayerApplication.class.getResource("heima-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        //senan sett og viðmótið sett í heimastöðu
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.HEIMA);
        stage.setTitle("AudioPlayer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //static breyta fyrir innskráðan áskrifanda
    private static Askrifandi loggedInAskrifandi;

    /**
     * getter fyrir innskráðan áskrifanda
     * @return innskráður áskrifandi
     */
    public static Askrifandi getLoggedInAskrifandi() {
        return loggedInAskrifandi;
    }

    /**
     * setter fyrir innskráðan áskrifanda
     * @param askrifandi innskráður áskrifandi
     */
    public static void setLoggedInAskrifandi(Askrifandi askrifandi) {
        AudioPlayerApplication.loggedInAskrifandi = askrifandi;
    }
}
