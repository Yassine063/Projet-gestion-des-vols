package labo3;

public class Date {

	private int jour;
	private int mois;
	private int annee;

	public Date(int jour, int mois, int annee) {

		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	

	
	@Override
	public String toString() {
		return String.format("%02d", jour) + "/" + String.format("%02d", mois) + "/" + annee;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
