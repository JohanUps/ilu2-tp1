package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nombreEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nombreEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	private class Marche {
		private Etal[] etals;

		private Marche(int nombreEtals) {
			etals = new Etal[nombreEtals];
			for (int i = 0; i < nombreEtals; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		public int trouverEtalLibre() {
			int positionEtalLibre = -1;
			for (int etalCourant = 0; etalCourant < etals.length; etalCourant++) {
				if (!etals[etalCourant].isEtalOccupe()) {
					positionEtalLibre = etalCourant;
					break;
				}
			}
			return positionEtalLibre;
		}

		public Etal[] trouverEtals(String produit) {
			int nbEtalProduit = 0;
			Etal[] tabEtalsProduit;
			int curseurEtalsProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalProduit++;
				}
			}
			tabEtalsProduit = new Etal[nbEtalProduit];
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					tabEtalsProduit[curseurEtalsProduit] = etals[i];
					curseurEtalsProduit++;
				}
			}
			return tabEtalsProduit;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		public void afficherMarche() {
			int nbEtalsVide = 0;
			StringBuilder ch = new StringBuilder("il reste ");

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				} else {
					nbEtalsVide++;
				}
			}
			if (nbEtalsVide > 0) {
				ch.append(nbEtalsVide);
				ch.append(" etals libre");
				System.out.println(ch);
			}
		}
	}

	public void rechercherVendeursProduit(String produit) {
		StringBuilder ch = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		if (etalsProduit == null) {
			ch.append("Il n'y a pas de vendeur qui propose des ");
			ch.append(produit);
			ch.append(" au marché");
		} else {
			for (String etal : etalsProduit) {
	            ch.append(etal.)
		}

	}
}