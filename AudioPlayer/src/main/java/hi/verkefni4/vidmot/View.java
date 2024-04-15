package hi.verkefni4.vidmot;

public enum View {

    //fxml skrárnar eru settar í enum klassan
    HEIMA("heima-view.fxml"),
    LAGALISTI("listi-view.fxml");

    private String fileName;

    /**
     * Smiður fyrir View klasann
     * @param fileName nafn á fxml skránni
     */
    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter fyrir nafn á fxml skránni
     * @return fxml skrá
     */
    public String getFileName() {
        return fileName;
    }

    public static void main(String[] args) {

    }
}
