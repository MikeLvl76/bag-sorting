import java.util.*;

// import jdk.javadoc.internal.doclets.toolkit.resources.doclets_zh_CN;

public class Glomes extends Formes implements Volume{

    protected double pi = Math.PI;
    protected int rayon;
    protected Random alea6 = new Random(); 

    public Glomes(){
        super("Glôme", 4);
        rayon = 1 + alea6.nextInt(6); //6 : borne maximale, 1 : borne minimale
        calculerVolume(); //on appelle cette méthode ici pour faciliter le calcul du volume du sac
    }

    public double calculerVolume(){
        return (double) 1/2 * Math.pow(pi, 2) * Math.pow(rayon, 4);
    }

    public double base(){ // La base d'une sphère est considérée comme nulle
        return 0;
    }
}