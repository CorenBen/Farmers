import java.util.Scanner;

public class TestJoueur{
	public static void main(String [] args) {
		
		Joueur tuto = new Joueur(1000);
		Joueur console = new Joueur();
		
		console.titre();
		console.clearScreen();
		console.introduction();
		tuto.tutoriel();
		console.partie();
	}
}