import java.util.*;

public class Carres extends Formes implements Surface{

    protected int longueur;

    public Carres(){
        super("Carré", 2);
        Random alea = new Random(); //on génère un nombre aléatoire
        longueur = 1 + alea.nextInt(7); //7 : borne maximale, 1 : borne minimale
    }

    public double calculerSurface(){
        return Math.pow(longueur, 2);
    }

    public double base(){ //un carré n'est pas empilable donc sa base est nulle
        return 0;
    }
}
