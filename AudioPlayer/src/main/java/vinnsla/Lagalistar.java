package vinnsla;

public class Lagalistar {

    //static fylki af stærð 2
    public Lagalisti [] lagalistar = new Lagalisti [2];

    /**
     * Smiður fyrir lagalista sem býr til nýjan lagalista fyrir hvert stak í fylkinu
     * og les inn viðeigandi skrá fyrir lagalistann
     */
    public Lagalistar() {
        for (int i = 0;i < lagalistar.length;i++) {
            lagalistar[i] = new Lagalisti();
            lagalistar[i].lesaLog("listi"+(i+1)+".txt");
        }
    }



}
