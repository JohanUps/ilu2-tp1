package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
	//1c
		Etal etal = new Etal();
		etal.libererEtal();
	//2a
		Gaulois obelix = new Gaulois("obélix", 5);
		etal.acheterProduit(2, obelix);
		
	//2b
		Gaulois joe = new Gaulois("joe", 5);
		etal.occuperEtal(joe, "fleur", 5);
		try {
			etal.acheterProduit(-1, joe);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
	//2c
		etal.libererEtal();
		try {
		etal.acheterProduit(1, joe);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin du test");


	}

}
