public class Formes{

    protected String nom; //nom d'une forme (exemple : "Cercle", "Cube", etc....)
    protected int dimension; //dimension d'une forme (2D, 3D ou 4D)

    public Formes(String _nom, int _dimension){
        nom = _nom;
        dimension = _dimension;
        calculerVolume(); //on appelle cette méthode ici pour faciliter le calcul du volume du sac
    }

    public String getNom(){
        return nom;
    }

    public int getDimension(){
        return dimension;
    }

    public double base(){
        return 0;
    }

    public double calculerVolume(){
        return 0;
    }

}