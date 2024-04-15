package vinnsla;

public class Lag {

    //tilviksbreytur
    private String hljodskra;
    private String nafnLags;
    private int lengdLags;
    private String myndLags;

    public Lag(String hljodskra, String nafnLags, int lengdLags, String myndLags) {
        this.hljodskra = hljodskra;
        this.nafnLags = nafnLags;
        this.lengdLags = lengdLags;
        this.myndLags = myndLags;
    }

    //getterar fyrir eiginleika Lags:

    public String getHljodskra() {
        return hljodskra;
    }

    public String getNafnLags() {
        return nafnLags;
    }

    public int getLengdLags() {
        return lengdLags;
    }

    public String getMyndLags() {
        return myndLags;
    }

    /**
     * toString aðferð fyrir nafn lags
     * @return nafn lags (úr getter)
     */
    @Override
    public String toString() {
        return (getNafnLags());

    }

    public static void main(String[] args) {

    }
}
