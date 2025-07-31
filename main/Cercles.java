import java.util.*;

public class Cercles extends Formes implements Surface{

    protected double pi = Math.PI;
    protected int rayon;
    

    public Cercles(){
        super("Cercle", 2);
        Random alea2 = new Random(); //on génère un nombre aléatoire
        rayon = 1 + alea2.nextInt(7); //7 : borne maximale, 1 : borne minimale
    }

    public double calculerSurface(){
        return (double) pi * Math.pow(rayon, 2);
    }

    public double base(){ // un cercle n'est pas empilable donc sa base est nulle
        return 0;
    }
}