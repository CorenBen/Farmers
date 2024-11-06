public class Terrain {
	public int nb_parcelles;
	public Parcelle [] terr;
	
////////////////////////////////////////////////                 CONSTRUCTEURS                               ////////////////////////////////////
	
	public Terrain() {
		nb_parcelles = 1;
		terr = new Parcelle [9];
		terr[0] = new Parcelle();	
	}
	
	public Terrain(int nb_parcelles, Parcelle [] terr) {
		this.nb_parcelles = nb_parcelles;
		this.terr = terr;
	}
	
	public Terrain(int nb_parcelles) {
		this.nb_parcelles = nb_parcelles;
		terr = new Parcelle [9];
		for(int i=0;i<9;i++) {
			terr[i] = new Parcelle();
		}
	}
	
//////////////////////////////////////////////                  ACCESSEURS                            //////////////////////////////////////////////////////
	

	public boolean estVide(int n) {
		return terr[n-1].getAgent()==0;
	}
	
	public int getTaille() {
		return nb_parcelles;
	}
	
	public int getAgent(int n) {
		return terr[n-1].getAgent();
	}
	
	public int nonAssigne(int n) {
		int i=0;
		for(int j=0;j<nb_parcelles;j++) {
			i += terr[j].getAgent();
		}
		return n-i;
	}	
	
	public String toString() {
		String s = "";
		for(int i=0;i<nb_parcelles;i++) {
			s = s  + terr[i].toString() + "\n\n";
		}
		return s;
	}
	
//////////////////////////////////////////////                      FONCTIONS STANDARDES                           //////////////////////////////////////////////////////
	
	public void ajoutPar(int taille) {
		if(nb_parcelles<9) {
			terr[nb_parcelles] = new Parcelle(taille);
			nb_parcelles+=1;
		}
		else {
			System.out.println("Nombre de parcelles déjà au maximum.");
		}
	}
	
	
	
	public void remplir() {
		for(int i=0;i<nb_parcelles;i++) {
			terr[i].remplir();
		}
	}
	
	public void pousse(boolean arrosoir) {
		for(int i=0;i<nb_parcelles;i++) {
			terr[i].pousse(arrosoir);
		}
	}
	
	public void recolte(boolean paysan) {
		for(int i=0;i<nb_parcelles;i++) {
			terr[i].recolte(paysan);
		}
	}
	
	public void augmente () {
		for(int i = 0; i < nb_parcelles ; i++) {
			terr[i].augmente();
		}
	}
	
	
//////////////////////////////////////////////                FONCTIONS APPEL                         //////////////////////////////////////////////////////
	
	public void affichePar(int n) {
		terr[n-1].toString();
	}
	
	public String retourneRess(int n) {
		return terr[n-1].getRess().getNomR();
	}
	
	public void changeRess(int n, int culture) {
		terr[n-1].changeRess(culture);
	}

	public void assigne(int n) {
		terr[n-1].agentPlus();
	}
	
	public void retire(int n) {
		terr[n-1].agentMoins();
	}
	
	public int getArgent() {
		int a=0;
		for(int i=0; i<nb_parcelles;i++) {
			a+=terr[i].getArgent();
		}
		return a;
	}
	
}
