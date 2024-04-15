module hi.verkefni4.vidmot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens hi.verkefni4.vidmot to javafx.fxml;
    exports hi.verkefni4.vidmot;
}
