import java.util.*;

public class Cylindres extends Formes implements Volume{

    protected double pi = Math.PI;
    protected int rayon, hauteur;
    protected Random alea4 = new Random();
    protected Random alea5 = new Random();

    public Cylindres(){
        super("Cylindre", 3);
        rayon = 1 + alea4.nextInt(2); //on génère un nombre borné entre 1 et 4
        hauteur = 1 + alea5.nextInt(5); //nombre borné entre 1 et 5
        calculerVolume(); //on appelle cette méthode ici pour faciliter le calcul du volume du sac
    }

    public double calculerVolume(){
        return pi * Math.pow(rayon, 2) * hauteur;
    }

    public double base(){
        return (double) pi * Math.pow(rayon, 2);
    }
}