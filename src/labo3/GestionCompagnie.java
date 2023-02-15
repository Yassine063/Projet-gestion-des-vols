package labo3;

import java.io.IOException;

import javax.swing.JOptionPane;

public class GestionCompagnie {



	public static void creationCompagnie() {
		//String airRelax = "Cie Air Relax";
		//int nbrMaxVol = 15;
		
		String nomCompagnie;
		int nbrMaxVol;

		nomCompagnie  = JOptionPane.showInputDialog(null,"Le nom de la compagie:", 
				"NOM DE LA COMPAGNIE", 	JOptionPane.PLAIN_MESSAGE);

		nbrMaxVol = Integer.parseInt(JOptionPane.showInputDialog(null,"Entrer le nombre maximun de vol:", 
				"NOMBRE MAXIMUM DE VOLS", JOptionPane.PLAIN_MESSAGE));

		Compagnie compagnie1 = new Compagnie(nomCompagnie, nbrMaxVol);

		
		System.out.println(compagnie1);

	}

	public static int menu() {

		int menuSelectionner;

		menuSelectionner =  Integer.parseInt(JOptionPane.showInputDialog(null,
				"GESTION DES VOLS\n\n1. Listes des vols\n2. Ajout d'un vol\n3. Retrait d'un vol\n4. Modification de la date de depart\n5. Reservation d'un vol\n0. Terminer\n\nFaite votre choix:", 
				"CIE AIR RELAX", JOptionPane.PLAIN_MESSAGE));

		return menuSelectionner;

	}

	public static void menu(int choix) {


		int choixMenu = choix;

		do {

			choixMenu = menu();

			switch (choixMenu) {

			case 0:
				break;
			case 1:	

				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			}


		}while(choixMenu != 0);

	}
	
	public static void main(String[] args) throws IOException {
		String airRelax = "Cie Air Relax";
		int nbrMaxVol = 15;
		Compagnie.remplirTabVols();
	
		//creationCompagnie();

		int choix;

		do {
			choix = menu();

			switch (choix) {

			case 0: Compagnie.ecrireFichier();
			break;
			case 1:	Compagnie.listeVols();
			break;
			case 2:	Compagnie.insererVol();
			break;
			case 3: Compagnie.retirerVol();
			break;
			case 4: Compagnie.modifierDate();
			break;
			case 5: Compagnie.reserverVol();
			break;
			}

		}while(choix != 0);


	}

}
