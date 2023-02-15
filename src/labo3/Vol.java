package labo3;

public class Vol {
	
	private int numeroVol;
	private String destinationVol;
	private Date dateDepart;
	private int nombreTotReservations;
	
	
	public Vol(int numeroVol, String destinationVol, Date dateDepart, int nombreReservations) {
		this.numeroVol = numeroVol;
		this.destinationVol = destinationVol;
		this.dateDepart = dateDepart;
		this.nombreTotReservations = nombreReservations;
		
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public void setNombreTotReservations(int nombreTotReservations) {
		this.nombreTotReservations = nombreTotReservations;
	}

	public int getNumeroVol() {
		return numeroVol;
	}

	public String getDestinationVol() {
		return destinationVol;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public int getNombreTotReservations() {
		return nombreTotReservations;
	}

	@Override
	public String toString() {
		return  numeroVol + "\t" + destinationVol + "\t\t" + dateDepart + "\t" + nombreTotReservations;
	}

	
}
