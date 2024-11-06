public class Agent {
	public int sac,argent;
	public Ressource ressource;
	
	public Agent() {
		sac = 0;
		ressource = new Ressource();
	}
	
	public Agent(Ressource r) {
		sac = 0;
		ressource = r;
	}
	
	public int getSac() {
		return sac;
	}
	
	public void changeRess(Ressource r) {
		ressource = r;
	}
	
	public void remplitSac(int s) {
		sac += s;
	}
	
	public int recolteAgent() {
		argent = (ressource.getValeur())*sac;
		sac = 0;
		return argent;
	}
	
}
