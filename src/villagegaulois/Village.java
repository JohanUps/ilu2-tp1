package villagegaulois;

import histoire.VillageSansChefExeception;
import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMarche);
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

	public String afficherVillageois() throws VillageSansChefExeception {
		if(chef == null)
			throw new VillageSansChefExeception();
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
	
	//Interaction avec la classe interne debut//
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder message = new StringBuilder(vendeur.getNom());
		message.append(" cherche un etal pour vendre ");
		message.append(nbProduit);
		message.append(" ");
		message.append(produit);
		message.append("\n");
		
		int indiceEtalLibre = marche.trouverEtalLibre();
		if(indiceEtalLibre>-1) {
			marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
			message.append("Le vendeur ");
			message.append(vendeur.getNom());
			message.append(" s'installe à l'étal n°");
			message.append(indiceEtalLibre+1);
		}
		else 
			message.append("Malheureusement il n'y a plus d'etal disponible");
		message.append("\n");
		return message.toString();
		
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder message = new StringBuilder("");
		Etal etalVendeur = marche.trouverVendeur(vendeur);
		if(etalVendeur != null)
			message.append(etalVendeur.libererEtal());
		return message.toString();
	}

	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder message = new StringBuilder();
		Etal[] etalsProduit = marche.trouverEtals(produit);
		
		if(etalsProduit.length == 0) {
			message.append("Malheureusement ce produit n'est pas vendu sur le marche\n");
		}
		else if(etalsProduit.length == 1) {
			message.append("Seul le vendeur ");
			message.append(etalsProduit[0].getVendeur().getNom());
			message.append(" vend des ");
			message.append(produit);
			message.append("\n");
			
		}
		else {
			message.append("Les vendeurs qui proposent des ");
			message.append(produit);
			message.append(" sont :\n");
			for(Etal etal : etalsProduit) {
				message.append("-");
				message.append(etal.getVendeur().getNom());
				message.append("\n");
			}
		}
		return message.toString();
	}
	
	public Etal[] trouverEtals(String produit) {
		return marche.trouverEtals(produit);
	}
	
	public Etal trouverVendeur(Gaulois gaulois) {
		return marche.trouverVendeur(gaulois);
	}
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	
	
	
	
	
	//Interaction avec la classe interne Fin//
	
	
	
	
	
	
	// Intern class March

	private static class Marche {
		private Etal[] etals;

		// Constructor of Marche
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			int numEtalLibre = -1;
			boolean etalLibreDispo = false;
			while (!etalLibreDispo && numEtalLibre < etals.length) {
				numEtalLibre++;
				if (!etals[numEtalLibre].isEtalOccupe()) {
					etalLibreDispo = true;
				}
			}
			if (!etalLibreDispo)
				numEtalLibre = -1;
			return numEtalLibre;
		}

		private Etal[] trouverEtals(String produit) {
			// Premier parcours pour compter le nombre d'etal qui contiennent le proudit
			int nbEtalsContientProduit = 0;
			for (Etal etal : etals) {
				if (etal.isEtalOccupe()) {
					if (etal.contientProduit(produit)) {
						nbEtalsContientProduit++;
					}
				}
			}

			Etal[] etalProduit = new Etal[nbEtalsContientProduit];
			// Deuxième parcour pour remplir le tableau d'etal a renvoyer
			int curseur = 0;
			for (Etal etal2parcour : etals) {
				if (etal2parcour.isEtalOccupe()) {
					if (etal2parcour.contientProduit(produit)) {
						etalProduit[curseur] = etal2parcour;
						curseur++;
					}
				}
			}
			return etalProduit;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			Etal etalVendeur = null;
			for (Etal etal : etals) {
				if (etal.getVendeur().equals(gaulois)) {
					return etal;
				}
			}
			return etalVendeur;
		}

		private String afficherMarche() {
			StringBuilder ch = new StringBuilder();
			int nbEtalOccupe = 0;
			for (Etal etal : etals) {
				if (etal.isEtalOccupe()) {
					ch.append(etal.afficherEtal());
					nbEtalOccupe++;
				}
			}
			int nbEtalLibre = etals.length - nbEtalOccupe ;
			if (nbEtalOccupe == nbEtalLibre) {
				ch.append("Il n'y a plus d'etals disponible");
			} else {
				ch.append("Il reste ");
				ch.append(nbEtalLibre);
				ch.append(" etals disponibles");
			}
			return ch.toString();
		}

	}
	
	//Fin classe interne Marche
	
	
}