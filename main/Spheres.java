import java.util.*;

public class Spheres extends Formes implements Volume{

    protected double pi = Math.PI; 
    protected int rayon;
    protected Random alea7 = new Random(); 

    public Spheres(){
        super("Sphère", 3); 
        rayon =  1 + alea7.nextInt(4); //4 : borne maximale, 1 : borne minimale
        calculerVolume(); //on appelle cette méthode ici pour faciliter le calcul du volume du sac
    }

    public double calculerVolume(){
        return (4/3) * pi * Math.pow(rayon, 3);
    }

    public double base(){ // La base d'une sphère est considérée comme nulle
        return 0;
    }
}