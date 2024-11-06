public class Case {
	public int valeur;
	
	public Case(int valeur) {
		this.valeur = valeur;
	}
	
	public Case() {
		valeur = 0;
	}
	
	public int getVal() {
		return valeur;
	}
	
	public void setCase() {
		valeur =(int)(Math.random()*(4-1)+1);
	}
	
	public void ramassage(boolean paysan, Agent a) {
		if(paysan) {
			if(valeur > 1) {
				valeur -= 2;
				a.remplitSac(2);
			}
			if(valeur == 1) {
				valeur -=1;
				a.remplitSac(1);
			}
		}
		else {
			if(valeur>0) {
				valeur -=1;
				a.remplitSac(1);
			}
		}
	}
	
	public void pousseCase(boolean arrosoir) {
		if(arrosoir) {
			valeur +=(int)(Math.random()*(7-2)+2);
		}
		else {
			valeur +=(int)(Math.random()*(3-1)+1);
		}
		if(valeur > 9) {
			valeur = 9;
		}
	}
	
}
