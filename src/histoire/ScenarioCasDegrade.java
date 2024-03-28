package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;


public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		//test acheteur null
		Etal etal = new Etal();
		etal.libererEtal();
		//etal.acheterProduit(0, null);
		
		//test quantite negative
		Gaulois obelix = new Gaulois("Obelix",10);
		etal.occuperEtal(obelix,"sanglier", 10);
		try {
			etal.acheterProduit(-1, obelix);
		}catch(IllegalArgumentException e) {
			System.out.println("La quantite acheter doit etre positive");
		}
		
		
		Etal etal2 = new Etal();
		try {
		etal2.acheterProduit(4, obelix);
		}catch(IllegalStateException e) {
			System.out.println("L'etal doit etre occuper pour pouvoir acheter un produit");
		}
		System.out.println("Fin du test");
		
		}

}
