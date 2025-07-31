import java.util.*;
import java.io.IOException;

public class SacADos{

    private int taille;
    private Formes sac[][];

    public SacADos(int n){
        try{
            taille = n; //cas où la taille est négative
            if(n < 0){
                throw new FormeNonEmpilable("La taille doit être positive !");
            }
        }
        catch (FormeNonEmpilable fe){
            fe.printStackTrace();
            System.exit(1);
        }
        setTaille(n);
        sac = new Formes[getTaille()][getTaille()];
    }

    public int getTaille(){
        return taille;
    }

    public void setTaille(int n){
        taille = n;
    }

    public Formes aleaForme(){ //génération aléatoire de formes
        Random alea = new Random(); 
        int aleaForme = alea.nextInt(6); //on lance un nombre prenant 6 valeurs possibles (0 jusqu'à 5)

        switch (aleaForme) { 
            case 0: 
            Carres carre = new Carres(); //si le nombre lancé vaut 0
            return carre;
        
            case 1:
            Cercles cercle = new Cercles(); //si le nombre lancé vaut 1
            return cercle;

            case 2:
            Cylindres cylindre = new Cylindres(); //si le nombre lancé vaut 2
            return cylindre;

            case 3:
            Cubes cube = new Cubes(); //si le nombre lancé vaut 3
            return cube;
        
            case 4:
            Spheres sphere = new Spheres(); //si le nombre lancé vaut 4
            return sphere;
        
            default:
            Glomes glome = new Glomes(); //si le nombre lancé vaut 5
            return glome;
        }
    }


    public boolean estEmpilable(Formes f1, Formes f2){ //conditions d'empilement
        if(f1.getDimension() == 3 && f2.getDimension() == 3){ //les figures doivent être en 3D
            if(f1.base() >= f2.base()){ //une des deux figures doit avoir une plus base plus grande
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void empilerFormes(Formes f) throws FormeNonEmpilable{

        for(int i = 0; i < getTaille(); i++){
           if(sac[getTaille() - 1][i] == null){ //si la première ligne est nulle
               sac[getTaille() - 1][i] = f; //on insère une forme
           }
        }

        int k = 0;
        while(k < getTaille()){ 
            int j = getTaille() - 1;

            if(sac[0][k] != null){ // si la colonne est pleine on passe à la suivante
                k++;
                System.out.println("La colonne est pleine ! ");
            } else {
                while(sac[j][k] != null){ //sinon, on cherche l'endroit où insérer une forme
                    j--;
                }

                if(estEmpilable(sac[j + 1][k], f)){ //et on regarde si on peut empiler
                    sac[j][k] = f;
                    return;
                } else { //si on ne peut pas, on avance
                    k++;
                }
            }
        }

        //si on a pas pu empiler, on renvoie une exception
        System.out.println("...Remplissage impossible...");
        System.out.println("Forme non empilable : " + f.getNom() + " et sa base : " + f.base());
        throw new FormeNonEmpilable("Empilement impossible. Veuillez réessayer.");
    }
    
    public double volumeSac(){
        double volume = 0;
        for(int i = 0; i < getTaille(); i++){
            for(int j = 0; j < getTaille(); j++){
                if(sac[i][j].getDimension() == 3 && sac[i][j] != null){
                    return volume += sac[i][j].calculerVolume();
                } 
            }
        }
        return volume;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < getTaille(); i++){
            for(int j = 0; j < getTaille(); j++){
                if(sac[i][j] == null){
                    res += ("(" + "x" + ")"); //sac vide ou sac non-rempli totalement
                } else {
                    res += ("(" + sac[i][j].getNom() + ")"); //nom des formes dans le sac
                }
            }
            res += "\n";
        }
        return res;
    }
}