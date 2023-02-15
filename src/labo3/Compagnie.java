package labo3;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import javax.swing.*;
import java.awt.Font;

public class Compagnie {
	
	private static int MAX_PLACES = 340;

	private static String nomCompagnie;
	private static int nombreMaxVol;
	static ArrayList<Vol> volCompagnie;
	private static int nombreVolActifs;
	
public Compagnie(String nomCompagnie, int nombreMaxVol) {
		
		Compagnie.nomCompagnie = nomCompagnie;
		Compagnie.nombreMaxVol = nombreMaxVol;
	
	}
	
	
public static void remplirTabVols() throws IOException {
		
		volCompagnie = new ArrayList<Vol>();
		FileReader lectureFic = new FileReader("cieAirRelax.txt");
		BufferedReader lectureFichier = new BufferedReader(lectureFic);
		
		int k = 0;
		String ligne;
		String[] temTab;
		
		int numeroVol;
		String destinationVol;
		int jour;
		int mois;
		int annee;
		Date dateDepart;
		int nbrTotReservations;
		
		ligne = lectureFichier.readLine();
		
		while(ligne != null && k < nombreMaxVol) {
			
			temTab = ligne.split(";");
			
			numeroVol = Integer.parseInt(temTab[0]);
			destinationVol = temTab[1];
			jour = Integer.parseInt(temTab[2]);
			mois  = Integer.parseInt(temTab[3]);
			annee  = Integer.parseInt(temTab[4]);
			nbrTotReservations  = Integer.parseInt(temTab[5]); 
			
			dateDepart = new Date(jour, mois, annee);
			
			volCompagnie.add(new Vol(numeroVol, destinationVol, dateDepart, nbrTotReservations));
			k++;
			nombreVolActifs++;

			ligne = lectureFichier.readLine();
		}
		
		lectureFichier.close();
			
	}
	
private static int rechercherVol(ArrayList<Vol> volCompagnie, int numVol) {
		
		int position = -1;
		boolean trouver = false;
		
	for (int i = 0; i < volCompagnie.size(); i++) {
		
		if(volCompagnie.get(i).getNumeroVol()  == numVol) {
			position = i;
			trouver = true;
		}
	}
	
	return position;
}

private static void insere(Vol unVol) {
	
			volCompagnie.add(unVol);		
			nombreVolActifs++;
		
}

public static void listeVols() throws IOException {
		
		JTextArea zoneTexte = new JTextArea(4,20);
		
		zoneTexte.append("LISTE DES VOLS");

		zoneTexte.append("\n\nNumero\tDestination\t\tDate depart\tReservations");
		
		for(int i = 0; i < volCompagnie.size(); i++) {
			zoneTexte.append("\n" + volCompagnie.get(i));
		}
		
		zoneTexte.setFont(new Font("Courier", Font.PLAIN, 12));
		zoneTexte.setTabSize(15);
		JOptionPane.showMessageDialog(null, zoneTexte, nomCompagnie, JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public static void insererVol() {
		
		
		if(volCompagnie.size() == nombreMaxVol) {
			
			JOptionPane.showMessageDialog(null,
					"Nombre maximun (" + nombreMaxVol +") de vols actifs atteint, " +
					 "vous ne pouvez pas ajouter de nouveau vol.", 
					"VOLS ACTIFS", JOptionPane.PLAIN_MESSAGE);
			
		} else {
			
			int numVol;
			String destination;
			Date date;
			int nombreTotReservations;

			String dateVo;
			
			int jour;
			int mois;
			int annee;
			
			numVol = Integer.parseInt(JOptionPane.showInputDialog(
					null,
					"Numero du vol", 
					"NUMERO VOL", 
					JOptionPane.PLAIN_MESSAGE));
			
			int position = rechercherVol(volCompagnie,numVol);
			
			if(position != -1) {
				
				JOptionPane.showMessageDialog(null,
						"Le numero du vol fournit correspond a un vol actif enregistrer dans le systeme.", 
						"VOLS ACTIFS", JOptionPane.PLAIN_MESSAGE);
		}else {
			
		destination = JOptionPane.showInputDialog(
				null,
				"Destination du vol", 
				"DESTINATION", 
				JOptionPane.PLAIN_MESSAGE);
		
		dateVo = JOptionPane.showInputDialog(
				null,
				"Date du vol au Format JJ/MM/AAAA", 
				"DATE DE VOL", 
				JOptionPane.PLAIN_MESSAGE);
		
		nombreTotReservations = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Nombres de reservations", 
				"NOMBRES DE RESERVATIONS", 
				JOptionPane.PLAIN_MESSAGE));
		
		//jour = Integer.parseInt(dateVo.substring(0,2));
		//mois = Integer.parseInt(dateVo.substring(2,4));
		//annee = Integer.parseInt(dateVo.substring(4));
		
		jour = Integer.parseInt(dateVo.substring(0,2));
		mois = Integer.parseInt(dateVo.substring(3,5));
		annee = Integer.parseInt(dateVo.substring(6));
		
		date = new Date(jour, mois, annee);
		Vol volAdd = new Vol(numVol, destination, date, nombreTotReservations);
		
		insere(volAdd);
		
		}
		}
	}	 
	
	
	public static void retirerVol() {
		
		int numVol = Integer.parseInt(JOptionPane.showInputDialog(	null,"Numero du vol",
				"RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
		
		int position = rechercherVol(volCompagnie,numVol);
		
		if(position != 1) {
			
			
			int numVolARetirer = volCompagnie.get(position).getNumeroVol();
			String destination = volCompagnie.get(position).getDestinationVol();
			Date date  = volCompagnie.get(position).getDateDepart();
			int reservations = volCompagnie.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + numVolARetirer + "\nDestination: " + destination + "\nDate depart: " + date + "\nReservations: " + reservations;
			
			
			String reponse = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nDÃ©sirez-vous vraiment retirer ce vol (O/N)", "RETRAIT D'UN VOL", 
					JOptionPane.WARNING_MESSAGE);
			
			char reposeUpper = reponse.toUpperCase().charAt(0);
			
			if(reposeUpper == 'O') {
				
				volCompagnie.remove(position);
				
				JOptionPane.showMessageDialog(null, "Vols retirer avec succes", "RETRAIT D'UN VOL",
						JOptionPane.PLAIN_MESSAGE);
				
			}else if(reposeUpper == 'N') {
				JOptionPane.showMessageDialog(null, "Operation annulee", "RETRAIT D'UN VOL",
						JOptionPane.PLAIN_MESSAGE);
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null,	"Aucun vol actif enregistrer correspond au numero du vol fournit", 
					"RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
		}
		
	}

	public static void modifierDate() {
		
		int numVol = Integer.parseInt(JOptionPane.showInputDialog(null,	"Numero du vol", 
				"MODIFICATION DATE", JOptionPane.PLAIN_MESSAGE));
		
		int position = rechercherVol(volCompagnie,numVol);
		
		if(position != 1) {
			
			int numVolARetirer = volCompagnie.get(position).getNumeroVol();
			String destination = volCompagnie.get(position).getDestinationVol();
			Date date  = volCompagnie.get(position).getDateDepart();
			int reservations = volCompagnie.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + numVolARetirer + "\nDestination: " + destination + "\nDate depart: " + date + "\nReservations: " + reservations;
			
			String reponse = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nNouvelle date", "MODIFICATION DATE", 
					JOptionPane.WARNING_MESSAGE);
			
			int jour = Integer.parseInt(reponse.substring(0,2));
			int mois = Integer.parseInt(reponse.substring(3,5));
			int annee = Integer.parseInt(reponse.substring(6));
			
			volCompagnie.get(position).setDateDepart(new Date(jour, mois, annee));
			}

	}
	
	public static void reserverVol() {
		
		int numVol = Integer.parseInt(JOptionPane.showInputDialog(
				null,
				"Numero du vol", 
				"MODIFICATION DATE", 
				JOptionPane.PLAIN_MESSAGE));
		
		int position = rechercherVol(volCompagnie,numVol);
		int nbrReservationDispo = volCompagnie.get(position).getNombreTotReservations();

		if(position != 1) {
			
			if(nbrReservationDispo >= MAX_PLACES) {
				JOptionPane.showMessageDialog(null,
						"Nombres maximum (" + MAX_PLACES + ") de place atteint sur ce vol.", 
						"RESERVATIONS", JOptionPane.PLAIN_MESSAGE);
			}else {
			
			int numVolAReservation = volCompagnie.get(position).getNumeroVol();
			String destination = volCompagnie.get(position).getDestinationVol();
			Date date  = volCompagnie.get(position).getDateDepart();
			int reservations = volCompagnie.get(position).getNombreTotReservations();
			
			String message = "\nNumero: " + numVolAReservation + "\nDestination: " + destination + "\nDate depart: " + date + "\nReservations: " + reservations;
			
			String reponse = JOptionPane.showInputDialog(null,
					"Details du vol\n" + message + "\n\n\nNombres de reservations", "RESRERVATION", 
					JOptionPane.WARNING_MESSAGE);
			
			int reservat = Integer.parseInt(reponse);
			
			volCompagnie.get(position).setNombreTotReservations(volCompagnie.get(position).getNombreTotReservations() + reservat);
				
				 }
			
			}else{
				JOptionPane.showMessageDialog(null,
						"Le numero du vol fournit ne correspond aucun vol actif enregistrer dans le systeme.", 
						"RESERVATIONS", JOptionPane.PLAIN_MESSAGE);
			}
	}
	
	public static void ordreVol() {
		
		String ligne;
		int numero;
		String Destination,tempo2;
		int indicemin,tempo1,tempo4;
		Date date,tempo3;
		int reservations;
		int tab1 []= new int [volCompagnie.size()] ;
		String tab2 []= new String  [volCompagnie.size()] ;
		Date tab3 []= new Date [volCompagnie.size()] ;
		int tab4 []= new int [volCompagnie.size()] ;
		
		for (int i = 0; i < volCompagnie.size(); i++) 
		{
			tab1[i]=volCompagnie.get(i).getNumeroVol();
			tab2[i]=volCompagnie.get(i).getDestinationVol();
			tab3[i]=volCompagnie.get(i).getDateDepart();
			tab4[i]=volCompagnie.get(i).getNombreTotReservations();
			
		}
		for (int i=0;i<volCompagnie.size();i++)
		{
			indicemin =i;
			for (int j=i+1;j<volCompagnie.size();j++) 
			{
				if(tab1[j]<tab1[indicemin])
					indicemin =j;
			}
			if (indicemin!=i)
			{
				tempo1=tab1[i];
				tab1[i]=tab1[indicemin];
				tab1[indicemin]=tempo1;
			
				tempo2=tab2[i];
				tab2[i]=tab2[indicemin];
				tab2[indicemin]=tempo2;
				
				tempo3=tab3[i];
				tab3[i]=tab3[indicemin];
				tab3[indicemin]=tempo3;
				
				tempo4=tab4[i];
				tab4[i]=tab4[indicemin];
				tab4[indicemin]=tempo4;
				
			}
			
			}
		
		volCompagnie.clear();
		
		for (int i=0;i<tab1.length;i++) {
		
		volCompagnie.add(new Vol(tab1[i], tab2[i],tab3[i],tab4[i]));	
		
		}
	
	/*	//Collection<Vol> vCompagnie = new LinkedList <Forme>();
	
		
	for ( Iterator <Vol> p=volCompagnie.iterator (); p.hasNext (); ) {
		Vol b;
		b=p.next ();   
		System.out.println("vol =" + b);
	}
	Collections.sort(volCompagnie);   // methode de tri générique
	System.out.println("Apres le tri");
	
	for ( Iterator <Vol> p=volCompagnie.iterator (); p.hasNext (); ) {
		  
		System.out.println("vol =" +(Vol) p.next ());
	}*/
	
	}
	
	public static void ecrireFichier() throws IOException {
		
		ordreVol() ;
		FileWriter ecritureFich = new FileWriter("cieAirRelax.txt");
		BufferedWriter ecritureFichier = new BufferedWriter(ecritureFich);
		
		String ligne;
		int numero;
		String Destination;
		Date date;
		int reservations;
		int jour;
		int mois;
		int annee;
		String lignedate="";
		String tabdate[]= new String[3];
		
		for (int i = 0; i < volCompagnie.size(); i++) {
			
			
			numero = volCompagnie.get(i).getNumeroVol();
			Destination = volCompagnie.get(i).getDestinationVol(); 
			date  = volCompagnie.get(i).getDateDepart();
			
			lignedate= date+"";
			tabdate = lignedate.split("/");
			jour = Integer.parseInt(tabdate[0]);
			 mois = Integer.parseInt(tabdate[1]);
			 annee = Integer.parseInt(tabdate[2]);
			
			 reservations = volCompagnie.get(i).getNombreTotReservations();
			
			ligne = numero + ";" + Destination + ";" + jour+ ";"+mois+";"+ annee + ";" + reservations;
			
			ecritureFichier.write(ligne);
			ecritureFichier.newLine();


		}
		ecritureFichier.close();
		
	}
	

}
