import org.junit.Before;
import org.junit.jupiter.api.Test;
import vinnsla.Askrifandi;

import java.awt.*;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class AskrifandiTest {



    @Before
    public void testNafn(){
        String nafn = new String();
        Askrifandi askrifandi = new Askrifandi(nafn);

    }

    @Test
    public void testNafnAlogin() {

            String nafn = UUID.randomUUID().toString();

            TextField textField = new TextField();
            textField.setText(nafn);

            Askrifandi askrifandi = new Askrifandi(textField.getText());

            assertEquals(nafn, askrifandi.getNafn());

    }
}
