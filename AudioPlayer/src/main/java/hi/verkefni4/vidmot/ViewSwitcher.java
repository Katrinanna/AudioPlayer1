package hi.verkefni4.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewSwitcher {

    //static breytur
    private static Scene scene;
    private static Object userData;

    /**
     * setter fyrir senuna
     * @param scene senan
     */
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    /**
     * skipt er yfir í það view sem er sett inn
     * @param view
     */
    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("Vantar að setja senu");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
            Parent root = loader.load();
            if (userData != null && loader.getController() instanceof ControllerInterface) {
                ControllerInterface controller = (ControllerInterface) loader.getController();
                controller.setData(userData); // Set the selected Lagalisti to the controller
                userData = null; // Clear userData after setting
            }
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * setter fyrir UserData
     * @param data hlutur
     */
    public static void setUserData(Object data) {
        userData = data;
    }


    /**
     * interface fyrir ControllerInterface
     */
    public interface ControllerInterface {
        void setData(Object data);
    }


    public static void main(String[] args) {

    }
}
