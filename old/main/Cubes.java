import java.util.*;

public class Cubes extends Formes implements Volume{

    protected int arete;
    protected Random alea3 = new Random();

    public Cubes(){
        super("Cube", 3);
        arete = 1 + alea3.nextInt(4); //4 : borne maximale, 1 : borne minimale
        calculerVolume(); //on appelle cette méthode ici pour faciliter le calcul du volume du sac
    }

    public double calculerVolume(){
        return Math.pow(arete, 3);
    }

    public double base(){
        return Math.pow(arete, 2);
    }
}