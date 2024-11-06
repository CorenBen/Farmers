import java.util.Scanner;

public class Joueur {
	public int argent,jours,dette,nb_agents,nb_parcelles,nb_agents_non_assignes;
	public Agent [] agents;
	public Terrain terrain;
	
	private boolean abandon = false;              ///// Option Quitter
	private boolean recolte_ramassage = false;    ///// Empeche la recolte d'etre effectuee plusieurs fois par jour
	
	private boolean arrosoir = false;            ///// Boutique
	private boolean paysan = false;             ///// Boutique
	private boolean vente = false;             ///// Boutique
	
	private int taille_parcelle = 9;
	private int racine_parcelle = 3;           ///// Racine de la taille d'une parcelle
	
	private boolean orge = false;             ///// Boutique
	private boolean mais = false;             ///// Boutique
	private boolean avoine = false;           ///// Boutique

////////////////////////////////////////////////                              CONSTRUCTEURS                               /////////////////////////////////////////////////////////////////////
	
	public Joueur() {
		argent = 0;
		jours = 1;
		dette = 10000;
		nb_agents=1;
		agents = new Agent [9];
		agents[0] = new Agent();
		nb_parcelles=1;
		terrain = new Terrain();
	}
	
	public Joueur(int dette) { ////////////// Constructeur tutoriel
		argent = 200;
		jours = 0;
		this.dette = dette;
		nb_agents=0;
		agents = new Agent [9];
		nb_parcelles=1;
		terrain = new Terrain();
	}

///////////////////////////////////////////////////////////                                 AJOUT                                        ///////////////////////////////////////////////////////////////////////////////////////
	
	public void ajoutAgent() {
		if(nb_agents<9) {
			agents[nb_agents] = new Agent();
			nb_agents+=1;
		}
		else {
			System.out.println("Nombre de paysans deja au maximum.\n");
		}
	}
	
///////////////////////////////////////////////////////////                               ACCESSEURS                                    ///////////////////////////////////////////////////////////////////////////////////////	
	
	public int getAr() {
		return argent;
	}
	
	public int getJour() {
		return jours;
	}
	
	public int getDette() {
		return dette;
	}
	
	public String toString() {
		return ("Argent : "+argent+" pieces\n"
				+ "Dette : "+dette+" pieces\n"
						+ "Nombre de paysans : "+nb_agents+"\n"
								+ "Nombre de parcelles : "+nb_parcelles+"\n");
	}
	
//////////////////////////////////////////////////////////                          VENTE RESSOURCES                          //////////////////////////////////////////////////////////////////////////////////////////////
	
	public void vente() {
		if(vente) {
			for(int i=0;i<nb_agents;i++) {
				argent = argent + (agents[i].recolteAgent() + agents[i].recolteAgent()/10);
			}
		}
		else {
			for(int i=0;i<nb_agents;i++) {
				argent = argent + agents[i].recolteAgent();
			}
		}
	}
	
///////////////////////////////////////////////////////                            BOUTIQUE                                   /////////////////////////////////////////////////////////////////////////////////////////
	
	public void boutique() {
		int clavier;
		System.out.println("Argent = "+argent+" pieces.");
		System.out.println("Ameliorations (1)             Paysans (2)              Champs (3)              Cultures (4)                 Annuler (0)\n");
		Scanner sc = new Scanner(System.in);
		//On teste la réponse clavier
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 0));
		
		if(clavier == 1) {amelioration();}
		if(clavier == 2) {paysans();}
		if(clavier == 3) {champs();}
		if(clavier == 4) {ressources();}
		if(clavier == 0) {affichePlan();}
		
	}
	
	public void amelioration() {    /////////// Premier Menu des ameliorations
		int clavier;
		System.out.println("Arrosoir+ = 50 pieces (1)                 Paysans+ = 100 pieces (2)                    Vente+ = 100 pieces (3)                  Annuler (0)\n");
		Scanner sc = new Scanner(System.in);
		//On teste la réponse clavier
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0));
		
		if(clavier != 0) {amelioration2(clavier);}
		else {
			if(clavier == 0) {boutique();}
		}
	}
	
	public void amelioration2(int c) {     ////////// Deuxieme Menu des ameliorations
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Description (1)                 Acheter (2)                    Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 0));

		if(clavier == 0) {amelioration();}
		// Descriptions des ameliorations
		if(clavier == 1) {
			if(c==1) {System.out.println("Arrosoir+ vous permet d'augmenter le rendement de vos cultures.\n");}
			if(c==2) {System.out.println("Paysan+ vous permet d'augmenter l'efficacite de vos paysans qui recolteront alors deux fois plus de recoltes.\n");}
			if(c==3) {System.out.println("Vente+ vous permet d'augmenter vos benefices à la vente de vos recoltes de 10%.\n");}
			amelioration2(c);
		}
		
		// Achat des ameliorations
		if(clavier == 2) {
			if(c==1 && arrosoir == false) {
				if(argent >= 50) {
					System.out.println("Arrosoir+ a ete achete.\n");
					arrosoir = true;
					argent -= 50;
					boutique();
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
					amelioration();
				}
			}
			else {
				if(c==1) {
					System.out.println("Amelioration deja achetee.\n");
					boutique();
				}
			}
			if(c==2 && paysan == false) {
				if(argent >= 100) {
					System.out.println("Paysan+ a ete achete.\n");
					paysan = true;
					argent -= 100;
					boutique();
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
					amelioration();
				}
			}
			else {
				if(c==2) {
					System.out.println("Amelioration deja achetee.\n");
					boutique();
				}
			}
			if(c==3 && vente == false) {
				if(argent >= 100) {
					System.out.println("Vente+ a ete achete.\n");
					vente = true;
					argent -= 100;
					boutique();
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
					amelioration();
				}
			}
			else {
				if(c==3) {
					System.out.println("Amelioration deja achetee.\n");
					boutique();
				}
			}
		}
	}
	
	public void paysans() {             ///////// Menu d'achat de nouveaux paysans
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Engager un nouveau paysan = 200 pieces (1)                    Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 0));
	
		// Engagement du paysan
		if(clavier == 1) {
			if(argent >= 200) {
				ajoutAgent();
				argent -= 200;
				paysans();
			}
			else {
				System.out.println("Vous ne possedez pas assez d'argent.\n");
				boutique();
			}
		}
		
		else {boutique();}
	}
	
	public void champs() {             ////////////// Menu d'amelioration du terrain
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Achat d'une nouvelle parcelle de terrain = 250 pieces (1)                    Agrandissement de vos parcelles = 150 pieces (2)                       Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 0));
	
		// Amélioration du terrain
		if(clavier == 1) {
			if(argent >= 250) {
				terrain.ajoutPar(taille_parcelle);
				System.out.println("Nouvelle parcelle achetee.\n");
				argent -=250;
				nb_parcelles+=1;
				champs();
			}
			else {
				System.out.println("Vous ne possedez pas assez d'argent.\n");
				champs();
			}
		}
		if(clavier == 2) {
			if(argent >= 150) {
				if(racine_parcelle<5) {
					racine_parcelle+=1;
					taille_parcelle = (int)Math.pow(racine_parcelle,2);
					System.out.println("Taille des parcelles augmente.");
					argent-=150;
					terrain.augmente();
					champs();
				}
				else {
					System.out.println("Taille des parcelles deja au maximum.\n");
					boutique();
				}
			}
			else {
				System.out.println("Vous ne possedez pas assez d'argent.\n");
				champs();
			}
		}
		if(clavier == 0) {boutique();}
	}
	
	public void ressources() {       /////////////// Menu d'achat de nouvelles cultures
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Orge = 50 pieces (1)                    Mais = 150 pieces (2)                       Avoine = 300 pieces (3)                  Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0));

		if(clavier == 0) {boutique();}
		// Achat de la culture
		if(clavier == 1 && orge) {
			System.out.println("Orge deja achete.\n");
			ressources();
		}
		else {
			if(clavier==1) {
				if(argent >= 50) {
					System.out.println("Orge a ete achete.\n");
					argent -= 50;
					orge = true;
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
				}
				ressources();
			}
		}
		if(clavier == 2 && mais) {
			System.out.println("Mais deja achete.\n");
			ressources();
		}
		else {
			if(clavier==2) {
				if(argent >= 150) {
					System.out.println("Mais a ete achete.\n");
					argent -= 150;
					mais = true;
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
				}
				ressources();
			}
		}
		if(clavier == 3 && avoine) {
			System.out.println("Avoine deja achete.\n");
			ressources();
		}
		else {
			if(clavier==3) {
				if(argent >= 300) {
					System.out.println("Avoine a ete achete.\n");
					argent -= 300;
					avoine = true;
				}
				else {
					System.out.println("Vous ne possedez pas assez d'argent.\n");
				}
				ressources();
			}
		}
	}

////////////////////////////////////////////////////////                                  CONSOLE                              /////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void clearScreen() {
		for(int i = 0; i < 25; i++) 
		    System.out.print("\n");
	}  
	
	public void titre() {
		String clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("___________________\r\n" + 
				"||||||||||||||||||\r\n" + 
				" |||||         |||\r\n" + 
				"  ||||         ||\r\n" + 
				"   |||         |\r\n" + 
				"   |||    |          ||||||||||        ||||     |||      ||||||    ||||||           |||||||||||     ||||     |||     ||||||||\r\n" + 
				"   ||||||||         ||       |||        |||||||||      |||    |||||     |||       |||         |||    |||||||||      |||     ||\r\n" + 
				"   |||    |        ||        |||        |||           |||       |        |||     |||         |||     |||             |||\r\n" + 
				"   |||             ||        |||        |||          |||                  |||    ||||||||||||        |||               ||||                 Appuyer sur\r\n" + 
				"   |||             ||        |||        |||          |||                  |||    |||                 |||                  |||                 Entrée\r\n" + 
				"   |||              ||      | || |      |||          |||                  |||     ||||               |||            ||      ||\r\n" + 
				"  |||||               |||||||   ||     |||||         |||                  |||      |||||||||||||    |||||            ||||||||\n");
		do{
            clavier = sc.nextLine();
        }while(!"".equals(clavier));
	}
	
	public void introduction() {
		System.out.println("Bienvenue dans 'Farmers', un petit jeu cree par Benedetti Corentin pour son mini-projet Java 2020.\n"
				+ "Ce jeu est une simulation de recolte de champs qui vous demandera d'atteindre une certaine somme d'argent en le minimum de jours possibles.\n"
				+ "Votre but est de rembourser une dette en ameliorant vos champs et en vendant vos recoltes.\n");
	}
	
	public void tutoriel() {
		System.out.println("Souhaitez-vous un didacticiel ?\n");
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Oui (Appuyez sur 1 puis Entree)                   Non (Appuyez sur 2 puis Entree)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2));
		if (clavier==1) {
			
			System.out.println("Vous incarnez le proprietaire d'un champs de 9 hectares qui doit rembourser une enorme dette en vendant le produit de ses recoltes. Une parcelle se presente sous cette forme :\n");
			System.out.println(terrain.toString());
			terrain.remplir();
			System.out.println("Vous pouvez en avoir jusqu'à 9. Chaque case represente une culture mais toutes les cultures d'une parcelle sont identiques. Vous ne pouvez melangez avoine et orge ou ble et mais.\n");
			System.out.println("Je vais maintenant vous presentez les differentes options qui vous sont proposes.\n");
			afficheJour(jours);
			System.out.println(toString());
			System.out.println("Pour pouvoir recolter vos cultures, vous allez devoir engager un paysan. Pour cela, allez dans la boutique en appuyant sur 2 puis Entree.\n");
			System.out.println("                                                   Boutique (2)\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 2)){
	            	System.out.println("2 puis Entree\n");
	            }
	        }while((clavier != 2));
			System.out.println("Ameliorations (1)             Paysans (2)              Champs (3)              Cultures (4)                 Annuler (0)\n");
			System.out.println("Comme vous pouvez le voir vous avez beaucoup d'options mais ce que nous voulons c'est engager un paysan donc je vous passe les détails.\n");
			System.out.println("Engager un nouveau paysan = 200 pieces (1)                    Annuler (0)\n");
			System.out.println("C'est ici que la magie opere ! Appuyez sur 1 puis Entree pour engager le paysan.");
			
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1)){
	            	System.out.println("Appuyez sur 1 puis Entree.\n");
	            }
	        }while((clavier != 1));
			ajoutAgent();
			argent -= 200;
			System.out.println("Ameliorations (1)             Paysans (2)              Champs (3)              Cultures (4)                 Annuler (0)\n");
			System.out.println("Revenons maintenant au menu principal en appuyant sur 0 puis Entree.");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 0)){
	            	System.out.println("Appuyer sur 0 puis Entree.\n");
	            }
	        }while((clavier != 0));
			System.out.println(toString());
			System.out.println(terrain.toString());
			System.out.println("Comme vous pouvez le voir, vous avez perdu vos 200 pieces mais vous avez un paysan.\n");
			System.out.println("Nous allons maintenant l'assigner a une parcelle. Accedez au menu du terrain en appuyant sur 1 puis Entree.");
			System.out.println("Afficher votre terrain (1)");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1)){
	            	System.out.println("Appuyez sur 1 puis Entree.\n");
	            }
	        }while((clavier != 1));
			System.out.println("Cultures (1)                  Paysans (2)                Recolter (3)              Annuler (0)\n");
			System.out.println("Appuyez sur 2 puis Entree\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 2)){
	            	System.out.println("Appuyez sur 2 puis Entree.\n");
	            }
	        }while((clavier != 2));
			System.out.println("Vous possedez "+nb_agents+" paysan(s)\n");
			System.out.println("Vous possedez "+nb_agents_non_assignes+" paysan(s) non assigne(s).\n");
			for(int i=1;i<=nb_parcelles;i++) {
				System.out.println("Parcelle "+i+" : "+terrain.getAgent(i)+" paysan(s) assigne(s).");
			}
			System.out.println("Assigner paysan (1)                    Retirer paysan (2)                    Annuler(0)");
			System.out.println("Ici vous choisissez si vous voulez assigner ou retirer un paysan, dans notre cas il faut appuyer sur 1 puis Entree.");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1)){
	            	System.out.println("Appuyez sur 1 puis Entree.\n");
	            }
	        }while((clavier != 1));
			terrain.assigne(clavier);
			nb_agents_non_assignes-=1;
			System.out.println("Vous possedez "+nb_agents+" paysan(s)\n");
			System.out.println("Vous possedez "+nb_agents_non_assignes+" paysan(s) non assigne(s).\n");
			for(int i=1;i<=nb_parcelles;i++) {
				System.out.println("Parcelle "+i+" : "+terrain.getAgent(i)+" paysan(s) assigne(s).");
			}
			System.out.println("Felicitations, votre paysan recoltera vos ressources quand vous lui direz de le faire. Mais pour ça, il faudrait planter quelque chose. Choisissez l'option Annuler pour revenir au menu precedent.\n");
			System.out.println("Assigner paysan (1)                    Retirer paysan (2)                    Annuler(0)");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 0)){
	            	System.out.println("Appuyez sur 0 puis Entree.\n");
	            }
	        }while((clavier != 0));
			System.out.println("Cultures (1)                  Paysans (2)                Recolter (3)              Annuler (0)\n");
			System.out.println("Choisissez l'option Cultures");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1)){
	            	System.out.println("Appuyez sur 1 puis Entree.\n");
	            }
	        }while((clavier != 1));
			System.out.println("Voir une parcelle de terrain (1)                  Annuler (0)\n");
			System.out.println("Vous ne possedez qu'une seule parcelle donc je vous passe cela.\n");
			System.out.println("Ble (1)                  Orge (2)                    Mais (3)                       Avoine (4)                  Annuler (0)\n");
			System.out.println("C'est ici que vous attribuez un type de culture a votre parcelle. Malheureusement, vous n'avez que du ble qui, etonnamment, est la ressource se vendant le moins bien. Choisissez donc l'option Ble.\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1)){
	            	System.out.println("Appuyez sur 1 puis Entree.\n");
	            }
	        }while((clavier != 1));
			System.out.println("Bien, nous avons presque fini. Puisque vous êtes dans le bon menu, vous allez pouvoir recolter.\n");
			System.out.println("Cultures (1)                  Paysans (2)                Recolter (3)              Annuler (0)\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 3)){
	            	System.out.println("Appuyez sur 3 puis Entree.\n");
	            }
	        }while((clavier != 3));
			terrain.recolte(paysan);
			System.out.println(terrain.toString());
			System.out.println("Comme vous pouvez le voir, votre parcelle a ete ratisse. Il faut maintenant vendre vos recoltes.\n");
			System.out.println("                                                                                           Vente (3)\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 3)){
	            	System.out.println("Appuyez sur 3 puis Entree.\n");
	            }
	        }while((clavier != 3));
			argent += agents[0].recolteAgent();
			System.out.println(toString());
			System.out.println("L'argent maintenant dans votre poche vous pouvez soit retourner dans la boutique soit rembourser une part de votre dette qui augmente de 1% tous les jours.\n");
			System.out.println("                                                                                                                       Payer votre dette (4)\n");
			System.out.println("Vous pouvez tout aussi bien decider d'aller vous coucher afin que vos cultures repoussent.\n");
			System.out.println("                                                                                                                                                                        Dormir (5)\n");
			System.out.println("Le choix reste votre et c'est ainsi que s'achève ce tutoriel.");
			
		}
	}
	
	public void afficheJour(int n) {
		System.out.println("------------    Jour "+n+"    -------------\n");
	}
	
	public boolean findePartie() {
		return dette>0;
	}
	
	public void afficheTerr() {  /////////////////////////////////// Afficher l'ensemble du terrain
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println(terrain.toString());
		System.out.println("Cultures (1)                  Paysans (2)                Recolter (3)              Annuler (0)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 0));
		if(clavier == 1) {
			afficheCult();
		}
		if(clavier == 2) {
			affichePays();
		}
		if(clavier == 3) {
			if(!recolte_ramassage) {
				terrain.recolte(paysan);
				recolte_ramassage = true;
				afficheTerr();
			}
			else {
				System.out.println("La recolte a deja ete effectuee aujourd'hui.\n");
				afficheTerr();
			}
		}
		if(clavier == 0) {
			affichePlan();
		}
	}
	
	public void afficheCult() {     /////////////////////////////////  Interagir avec les cultures/parcelles une à une
		int clavier,clavier2;
		Scanner sc = new Scanner(System.in);
		if(nb_parcelles==1) {System.out.println("Voir une parcelle de terrain (1)                  Annuler (0)\n");}
		else{System.out.println("Voir une parcelle de terrain (1-"+nb_parcelles+")                   Annuler (0)\n");}
		do{
            clavier = sc.nextInt();
            if(clavier < 0 || clavier > 9){
            	System.out.println("Veuillez choisir le numero d'une parcelle de votre terrain\n");
            }
            if(clavier > nb_parcelles && clavier <= 9) {System.out.println("Vous ne possedez que "+nb_parcelles+" parcelle(s)\n");}
        }while((clavier < 0 || clavier > nb_parcelles));
		
		if(clavier==0) {afficheTerr();}
		else {
			if(clavier==1) {
				terrain.affichePar(1);
				System.out.println("Cultures : "+terrain.retourneRess(1)+"         Changer culture (1)            Annuler (0)\n");
				do{
		            clavier2 = sc.nextInt();
		            if(clavier2 != 0 && clavier2 !=1){
		            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
		            }
		        }while(clavier2 != 0 && clavier2 !=1);
				if(clavier2 == 1) {
					System.out.println("Ble (1)                  Orge (2)                    Mais (3)                       Avoine (4)                  Annuler (0)\n");
					do{
			            clavier2 = sc.nextInt();
			            if((clavier2 != 1) && (clavier2 != 2) && (clavier2 != 3) && (clavier2 != 4) && (clavier2 != 0)){
			            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
			            }
			        }while(((clavier2 != 1) && (clavier2 != 2) && (clavier2 != 3) && (clavier2 != 4) && (clavier2 != 0)) && ((clavier2 == 2 && !orge) || (clavier2 == 3 && !mais) || (clavier2 == 4 && !avoine)));
							
					if(clavier2 == 0) {afficheCult();}
					else {
			            if(((clavier2 == 2) && !orge) || ((clavier2 == 3) && !mais) || ((clavier2 == 4) && !avoine)) {
			            	System.out.println(clavier2+" "+orge+" "+mais+" "+avoine);
			            	System.out.println("Veuillez acheter d'abord cette culture.\n");
			            	afficheCult();
			            }
			            else {
							System.out.println("La culture a ete place.\n");
							terrain.changeRess(clavier,clavier2);
							afficheCult();
			            }
					}
				}
				if(clavier == 0) {
					afficheCult();
				}
			}
			else {
				terrain.affichePar(clavier);
				System.out.println("Cultures : "+terrain.retourneRess(1)+"         Changer culture (1)            Annuler (0)\n");
				do{
		            clavier2 = sc.nextInt();
		            if(clavier2 != 0 && clavier2 !=1){
		            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
		            }
		        }while(clavier2 != 0 && clavier2 !=1);
				if(clavier2 == 1) {
					System.out.println("Blé (1)                  Orge (2)                    Mais (3)                       Avoine (4)                  Annuler (0)\n");
					do{
			            clavier2 = sc.nextInt();
			            if((clavier2 != 1) && (clavier2 != 2) && (clavier2 != 3) && (clavier2 != 4) && (clavier2 != 0)){
			            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
			            }
			        }while(((clavier2 != 1) && (clavier2 != 2) && (clavier2 != 3) && (clavier2 != 4) && (clavier2 != 0)) && ((clavier2 == 2 && !orge) || (clavier2 == 3 && !mais) || (clavier2 == 4 && !avoine)));
							
					if(clavier2 == 0) {afficheCult();}
					else {
			            if((clavier2 == 2 && !orge) || (clavier2 == 3 && !mais) || (clavier2 == 4 && !avoine)) {
			            	System.out.println("Veuillez acheter d'abord cette culture.\n");
			            	afficheCult();
			            }
			            else {
			            	terrain.changeRess(clavier,clavier2);
			            	afficheCult();
			            }
					}
				}
				if(clavier == 0) {
					afficheCult();
				}
			}	
		}
	}
	
	public void affichePays() {                ///////////////////////////    Interagir avec les paysans
		int clavier;
		nb_agents_non_assignes = terrain.nonAssigne(nb_agents);
		Scanner sc = new Scanner(System.in);
		System.out.println("Vous possedez "+nb_agents+" paysan(s)\n");
		System.out.println("Vous possedez "+nb_agents_non_assignes+" paysan(s) non assigne(s).\n");
		for(int i=1;i<=nb_parcelles;i++) {
			System.out.println("Parcelle "+i+" : "+terrain.getAgent(i)+" paysan(s) assigne(s).");
		}
		System.out.println("Assigner paysan (1)                    Retirer paysan (2)                    Annuler(0)");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 0)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 0));
		
		if(clavier == 0) {afficheTerr();}
		if(clavier == 1) {
			if(nb_agents_non_assignes==0) {
				System.out.println("Aucun paysan disponible, achetez-en ou retirez-en de vos parcelles.\n");
				affichePays();
			}
			else {
				if(nb_parcelles==1) {System.out.println("Voir une parcelle de terrain (1)                  Annuler (0)\n");}
				else{System.out.println("Voir une parcelle de terrain (1-"+nb_parcelles+")                   Annuler (0)\n");}
				do{
		            clavier = sc.nextInt();
		            if(clavier < 0 || clavier > 9){
		            	System.out.println("Veuillez choisir le numero d'une parcelle de votre terrain\n");
		            }
		            if(clavier > nb_parcelles && clavier <= 9) {System.out.println("Vous ne possedez que "+nb_parcelles+" parcelle(s)\n");}
		        }while((clavier < 0 || clavier > nb_parcelles));
				if(clavier==0) {afficheTerr();}
				else {
					if(terrain.getAgent(clavier)==5){System.out.println("Vous possedez deja le nombre maximal de paysans sur cette parcelle.\n");}
					else {
						terrain.assigne(clavier);
						nb_agents_non_assignes-=1;
					}
					affichePays();
				}
			}
		}
		if(clavier == 2) {
			if(nb_parcelles==1) {System.out.println("Voir une parcelle de terrain (1)                  Annuler (0)\n");}
			else{System.out.println("Voir une parcelle de terrain (1-"+nb_parcelles+")                   Annuler (0)\n");}
			do{
	            clavier = sc.nextInt();
	            if(clavier < 0 || clavier > 9){
	            	System.out.println("Veuillez choisir le numero d'une parcelle de votre terrain\n");
	            }
	            if(clavier > nb_parcelles && clavier <= 9) {System.out.println("Vous ne possédez que "+nb_parcelles+" parcelle(s)\n");}
	        }while((clavier < 0 || clavier > nb_parcelles));
			if(clavier==0) {affichePays();}
			else {
				if(terrain.estVide(clavier)) {
					System.out.println("La parcelle ne contient deja aucun paysan\n");
				}
				else {
					terrain.retire(clavier);
					nb_agents_non_assignes+=1;
				}
				affichePays();
			}
		}
	}
	
	public void affichePlan() {                     ///////////////////////////            Afficher le menu principal du jeu
		System.out.println(toString());
		System.out.println(terrain.toString());
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Afficher votre terrain (1)                         Boutique (2)                        Vendre (3)                      Payer votre dette (4)                  Dormir (5)                   Quitter le jeu (6)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4) && (clavier != 5) && (clavier != 6));
		
		if(clavier==1) {
			afficheTerr();
		}
		if(clavier==2) {boutique();}
		if(clavier==3) {
			argent+=terrain.getArgent();
			System.out.println("Vous possedez "+argent+" pieces\n");
			affichePlan();
		}
		if(clavier==4) {
			System.out.println("Votre dette est de "+dette+" pièces. De combien souhaitez-vous la rembourser ?");
			do{
	            clavier = sc.nextInt();
	            if(clavier > argent){
	            	System.out.println("Vous ne possedez que"+argent+" pieces. Veuillez entrer une somme valable.\n");
	            }
	            if(clavier < 0) {
	            	System.out.println("Veuillez entrer une somme valable.\n");
	            }
	        }while((clavier < 0) && (clavier > argent));
			argent -= clavier;
			dette -= clavier;
			affichePlan();
		}
		if(clavier==5) {
			jourSuivant();
		}
		if(clavier==6) {
			System.out.println("Etes vous sur de vouloir quitter le jeu : Oui (1)        Non (2)\n");
			do{
	            clavier = sc.nextInt();
	            if((clavier != 1) && (clavier != 2)){
	            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
	            }
	        }while((clavier != 1) && (clavier != 2));
			if(clavier == 1) {
				dette = 0;
				abandon = true;
			}
			if(clavier == 2) {
				affichePlan();
			}
		}
	}
	
	public void resume() {
		System.out.println("Vous avez rembourse votre dette en "+(jours-1)+" jours.");
	}
	
	public void jourSuivant() {            ///// Changements non-visuels avant de passer au jour suivant
		jours+=1;
		terrain.pousse(arrosoir);
		dette+=dette/100;
		recolte_ramassage = false;
		clearScreen();
	}
	
	public void partie() {            ///// Fonction de lancement du jeu
		int clavier;
		Scanner sc = new Scanner(System.in);
		System.out.println("Vous commencez une nouvelle partie.\n");
		///////////// Amelioration de debut de partie
		System.out.println("Choisissez une amelioration de depart : Paysan supplementaire (1)                Parcelle de terrain supplementaire (2)               100 pieces supplementaires (3)               Aucune amelioration (4)\n");
		do{
            clavier = sc.nextInt();
            if((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4)){
            	System.out.println("Veuillez choisir une des propositions ci-dessus.\n");
            }
        }while((clavier != 1) && (clavier != 2) && (clavier != 3) && (clavier != 4));
		
		if(clavier==1) {
			ajoutAgent();
		}
		if(clavier==2) {
			terrain.ajoutPar(taille_parcelle);
			nb_parcelles+=1;
		}
		if(clavier==3) {
			argent+=100;
		}
		//////////////// Remplissage du terrain
		
		terrain.remplir();
		
		/////////////// Boucle du jeu
		while (findePartie()) {
			afficheJour(jours);
			affichePlan();
			if(!abandon && dette == 0) {
				System.out.println("Felicitations vous avez rembourse votre dette ! Le jeu est donc termine.\n");
				resume();
			}
		}
	}
}
