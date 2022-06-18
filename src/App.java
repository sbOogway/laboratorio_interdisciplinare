package src;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Mattia Papaccioli 747053 CO
 * @author
 * @author
 * @author
 * 
 */

public class App {
	
	public static void main (String[] args){

		
			boolean mode2_suc = false;
			// LOADING
			// POPOLARE Song.all_songs 
			Song.CSVReader();
			// POPOLARE Utente.all_users
			Utente.CSVReader();
			
			ArrayList<Playlist> my_playlists = new ArrayList<Playlist>();

			// MAIN MENU !!! FARE IN MODO CHE RICOMPAIA DOPO CHE VIENE EFFETUATA UN OPERAZIONE !!!
			
			Scanner inp0 = new Scanner(System.in);

			System.out.println(
					"                                                   _ \r\n" + 
					" _____      ____ _  __ _ ___  ___  _   _ _ __   __| |\r\n" + 
					"/ __\\ \\ /\\ / / _ `|/ _` / __|/ _ \\| | | | '_ \\ / _` |\r\n" + 
					"\\__ \\\\ V  V / (_| | (|  \\__ \\ (_) | |_| | | | | (_| |\r\n" + 
					"|___/ \\_/\\_/ \\__,_|\\__, |___/\\___/ \\__,_|_| |_|\\__,_|\r\n" + 
					"                   |___/                                 \n");
			
			System.out.println("Benvenuto su $WAGSOUND!!! :}\nCosa vuoi fare?\n1. Ricerca un brano musicale. \n2. Registrati all'applicazione.\n3. Crea una nuova playlist. \n4. Inserisci emozione per canzone della playlist. ");
			
			int mode = inp0.nextInt();

			// FINE MAIN MENU

			if (mode == 1)
			{
				Scanner inp1 = new Scanner(System.in);
				System.out.print("Immetti il nome, artista o anno della canzone che vuoi cercare: ");
				String query = inp1.nextLine();
				cercaBranoMusicale(query);
				inp1.close();
			}
			
			// mettere a posto gli errori e scrivere i nuovi utenti in utenti.csv
			if (mode == 2)
			{	
				while (mode2_suc == false){
					Scanner inp2 = new Scanner(System.in);
					System.out.print("Immetti nome utente: ");
					String nome_utente = inp2.nextLine();
					for (Utente user : Utente.all_users){
						if (user.nome_utente.equals(nome_utente))
						{
							System.out.println("Nome utente non disponibile.");
							break;
						}
						break;
					}				
					System.out.print("Immetti password: ");
					String password = inp2.nextLine();
					System.out.print("Riconferma password: ");
					String password_riconferma = inp2.nextLine();
					if (password != password_riconferma){
						System.out.println("Le due password non corrispondono.");
						break;
					}
					Utente new_user = new Utente(nome_utente, password);
					System.out.println(new_user);
					inp2.close();	
					mode2_suc = true;
				}			
			}

			
			if (mode == 3)
			{
				Scanner inp3 = new Scanner(System.in);
				System.out.print("Nome della nuova playlist: ");
				String nome = inp3.nextLine();
				Playlist playlist = new Playlist(nome);
				my_playlists.add(playlist);
				inp3.close();
			}
			
			if (mode == 4)
			{
			

			}	

			inp0.close();

		}
				
	

		
	public static void cercaBranoMusicale(String query) {
			
		// System.out.println(Song.all_songs);

		for (Song song : Song.all_songs) {
			if (song.titolo.contains(query) || song.autore.contains(query) || song.anno.contains(query)) {
				System.out.println(song.autore + " - " + song.titolo + " - " + song.anno);
			}
		}
		
		
	}
	
		

    
}