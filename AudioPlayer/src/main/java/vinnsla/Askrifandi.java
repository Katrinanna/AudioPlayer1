package vinnsla;

public class Askrifandi {

    //tilviksbreyta
    private String nafn;

    /**
     * smiður fyrir Askrifanda
     * @param nafn nafn askrifanda
     */
    public Askrifandi(String nafn) {
        this.nafn = nafn;
    }

    /**
     * getter fyrir nafn askrifanda
     * @return nafn askrifanda
     */
    public String getNafn() {
        return nafn;
    }

    /**
     * setter fyrir nafn askrifanda
     * @param nafn nafn askrifanda
     */
    public void setNafn(String nafn) {
        this.nafn = nafn;
    }

    public static void main(String[] args) {

    }
}
