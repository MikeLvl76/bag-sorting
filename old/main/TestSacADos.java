import java.io.IOException;

public class TestSacADos{

    public static void main(String[] args) throws FormeNonEmpilable{

        int n = 3; //taille du sac
        Formes f; // création d'une forme auquelle on lui affecte une fonction

       System.out.println("------ Voici le sac (vide et rempli si possible) -----");

        SacADos sac = new SacADos(n); //instanciation du sac (vide pour le moment)
        System.out.println(sac); //affichage du sac
        
        try{
            for(int i = 0; i < n; i++){ //cas où les formes sont identiques malgré la génération aléatoire
                f = sac.aleaForme(); //affectation d'une fonction de même type que la forme à celle-ci
                sac.empilerFormes(f);
            }
        }
        catch (FormeNonEmpilable fe){
            fe.printStackTrace();
            System.exit(1);
        }
        System.out.println(sac); //affichage du sac contenant des formes 
        System.out.println("Volume du sac : " + sac.volumeSac());
    }
}
