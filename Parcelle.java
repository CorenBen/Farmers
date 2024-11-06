public class Parcelle {
	public int taille, nb_agents;
	public Ressource ressource;
	public Case [] cases;
	public Agent [] agents;
	
////////////////////////////////////////////////                        CONSTRUCTEURS                               ////////////////////////////////////	
	
	public Parcelle(int taille, Ressource ressource, Case [] cases) {
		this.taille = taille;
		this.ressource = ressource;
		this.cases = cases;
		nb_agents = 0;
		agents = new Agent[5];
	}
	
	public Parcelle() {
		taille = 9;
		ressource = new Ressource();
		cases = new Case [25];
		for(int i=0;i<taille;i++) {
			cases[i] = new Case();
		}
		nb_agents = 0;
		agents = new Agent[5];
	}
	
	public Parcelle (int taille, Ressource ressource) {
		this.taille = taille;
		this.ressource = ressource;
		cases = new Case [25];
		for(int i=0;i<taille;i++) {
			cases[i] = new Case();
		}
		nb_agents = 0;
		agents = new Agent[5];
	}
	
	public Parcelle(int taille) {
		this.taille = taille;
		ressource = new Ressource();
		cases = new Case [25];
		for(int i=0;i<taille;i++) {
			cases[i] = new Case();
		}
		nb_agents = 0;
		agents = new Agent[5];
	}
	
//////////////////////////////////////////////                    ACCESSEURS                            //////////////////////////////////////////////////////
	
	public int getTaille() {
		return taille;
	}
	
	public Ressource getRess() {
		return ressource;
	}
	
	public int getAgent() {
		return nb_agents;
	}
	
	
	public String toString() {
		String s = "";
		int n = 1;
		for(int i=0;i<taille;i++) {
			if(i == Math.sqrt(taille)*n) {
				s = s + " |\n";
				n=n+1;
			}
			s = s + " | " + cases[i].getVal();
		}
		return s+" |";
	}
	
//////////////////////////////////////////////                     FONCTIONS STANDARDES                           //////////////////////////////////////////////////////
	
	public void remplir() {
		for(int i=0;i<taille;i++) {
			cases[i].setCase();
		}
	}
	
	public void recolte(boolean paysan) {
		for(int j=0;j<nb_agents;j++) {
			for(int i=0;i<taille;i++) {
				cases[i].ramassage(paysan,agents[j]);
			}
		}
	}
	
	public void agentPlus() {
		agents[nb_agents] = new Agent(ressource);
		nb_agents+=1;
	}
	
	public void agentMoins() {
		agents[nb_agents] = null;
		nb_agents-=1;
	}
	
	public void pousse(boolean arrosoir) {
		for(int i=0;i<taille;i++) {
			cases[i].pousseCase(arrosoir);
		}
	}
	
	public void augmente() {
		if(taille == 9) {
			taille = 16;
			for(int i=9;i<16;i++) {
				cases[i] = new Case();
			}
		}
		else {taille = 25;
			for(int i=16;i<25;i++) {
				cases[i] = new Case();
			}
		}
	}
	
	public void changeRess(int n) {
		if(n==1) {
			ressource.ble();
			for(int i=0;i<nb_agents;i++) {
				agents[i].changeRess(ressource);
			}
		}
		if(n==2) {
			ressource.orge();
			for(int i=0;i<nb_agents;i++) {
				agents[i].changeRess(ressource);
			}
		}
		if(n==3) {
			ressource.mais();
			for(int i=0;i<nb_agents;i++) {
				agents[i].changeRess(ressource);
			}
		}
		if(n==4) {
			ressource.avoine();
			for(int i=0;i<nb_agents;i++) {
				agents[i].changeRess(ressource);
			}
		}
	}
	public int getArgent() {
		int a=0;
		for(int i=0;i<nb_agents;i++) {
			a+=agents[i].recolteAgent();
		}
		return a;
	}
}

