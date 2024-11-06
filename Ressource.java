public class Ressource {
	public int valeur;
	public String nom;
	
	public Ressource() {
		valeur = 2;
		nom = "Ble";
	}
	
	public Ressource(int valeur, String nom) {
		this.valeur = valeur;
		this.nom = nom;
	}
	
	public String getNomR() {
		return nom;
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void ble() {
		valeur = 2;
		nom = "Ble";
	}
	
	public void avoine() {
		valeur = 125;
		nom = "Avoine";
	}
	
	public void orge() {
		valeur = 10;
		nom = "Orge";
	}
	
	public void mais() {
		valeur = 50;
		nom = "Mais";
	}
	
	
}
