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
	static String mode;
	static Scanner inp = new Scanner(System.in);
	static ArrayList<Song> brani_vep = new ArrayList<Song>();
	static boolean logged_in = false;
	static String login_status = "NOT LOGGED IN";

	public static void main (String[] args){
		// LOADING
		System.out.println("LOADING");
		System.out.print("##############################");
		// POPOLARE Song.all_songs 
		Song.CSVReader();
		System.out.print("##############################");
		// POPOLARE Utente.all_users
		Utente.CSVReader();
		System.out.print("#############################");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("##########################");
		
		ArrayList<Playlist> my_playlists = new ArrayList<Playlist>();

		menu_avvio();

		while (true) {

			// MAIN MENU !!! FARE IN MODO CHE RICOMPAIA DOPO CHE VIENE EFFETUATA UN OPERAZIONE !!!
			
			menu();

			// FINE MAIN MENU
			
			// VISUALIZZARE EMOZIONI (LOGIN NON NECESSARIO
			if (mode.equals("0"))
			{			
				System.out.print("Immetti il nome, artista o anno della canzone che vuoi cercare: ");
				String query = inp.nextLine();
				cercaBranoMusicale(query);		
				visualizzaEmozioneBrano();	
			}
			
			// REGISTRAZIONE UTENTE
			if (mode.equals("1"))
			{	
				Registrazione();		
			}

			// CREARE NUOVA PLAYLIST
			if (mode.equals("2"))
			{
				if (logged_in == false){
					login();
				}
				RegistraPlaylist();
			}
			
			// INSERIRE EMOZIONI PER CANZONI DI UNA PLAYLIST
			if (mode.equals("3"))
			{
				if (logged_in == false){
					login();
				}			

			}

			// USCIRE DALL' APPLICAZIONE
			if (mode.equals("exit")){
				System.out.println("###################################################################################################################");
				System.out.println("Grazie per aver usato $WAGSOUND!!! Alla prossima!!! ;)");
				System.exit(0);
			}			
		}		
	}

	public static void menu_avvio() {
		System.out.println(
				"                                                   _ \r\n" + 
				" _____      ____ _  __ _ ___  ___  _   _ _ __   __| |\r\n" + 
				"/ __\\ \\ /\\ / / _ `|/ _` / __|/ _ \\| | | | '_ \\ / _` |\r\n" + 
				"\\__ \\\\ V  V / (_| | (|  \\__ \\ (_) | |_| | | | | (_| |\r\n" + 
				"|___/ \\_/\\_/ \\__,_|\\__, |___/\\___/ \\__,_|_| |_|\\__,_|\r\n" + 
				"                   |___/                                 \n");
		System.out.print("Benvenuto su $WAGSOUND!!! :}\n");

	}
				
	public static void menu() {
		Scanner inp0 = new Scanner(System.in);		
		System.out.println("Cosa vuoi fare?" + padLeft("Login status: " + login_status, 100) + "\n0. Ricerca le emozioni di un brano musicale. \n1. Registrati all'applicazione.\n2. Crea una nuova playlist. \n3. Inserisci emozione per canzone della playlist. " + padLeft("Per uscire dall'app digita 'exit'", 65));		
		mode = inp0.next();

	} 

	public static void visualizzaEmozioneBrano() {
		Scanner inp_vep = new Scanner(System.in);
		System.out.print("Di quale brano vuoi vedere le emozioni?;): ");
		int brano_vep = inp_vep.nextInt();
		System.out.println("###################################################################################################################");
		Song selected_song = brani_vep.get(brano_vep);
		System.out.println("AUTORE: " + selected_song.autore + "\nTITOLO: " + selected_song.titolo + "\nANNO: " + selected_song.anno +"\nEMOZIONI: \n"
		+ "AMAZEMENT: " + selected_song.emo.AMAZEMENT + "\n"
		+ "SOLEMNITY: " + selected_song.emo.SOLEMNITY + "\n"
		+ "TENDERNESS: " + selected_song.emo.TENDERNESS + "\n"
		+ "NOSTALGIA: " + selected_song.emo.NOSTALGIA+ "\n"
		+ "CALMNESS: " + selected_song.emo.CALMNESS + "\n"
		+ "POWER: " + selected_song.emo.POWER + "\n"
		+ "JOY: " + selected_song.emo.JOY + "\n"
		+ "TENSION: " + selected_song.emo.TENSION + "\n"
		+ "SADNESS: " + selected_song.emo.SADNESS );
		System.out.println("###################################################################################################################");
	}
		
	public static void cercaBranoMusicale(String query) {			
		System.out.println("###################################################################################################################");
		int count = 0;	
		for (Song song : Song.all_songs) {			
			if (song.titolo.contains(query) || song.autore.contains(query) || song.anno.contains(query)) {
				System.out.println(count + ". " +  song.autore + " - " + song.titolo + " - " + song.anno);
			}
			brani_vep.add(song);
			count += 1;			
		}
		
		System.out.println("###################################################################################################################");
		
	}

	public static void login() {

		Scanner inp_login = new Scanner(System.in);
		System.out.println("###################################################################################################################");
		System.out.print("LOGIN\nImmetti il nome utente: ");
		String userid_login = inp_login.nextLine();

		for (Utente user : Utente.all_users){
			if (userid_login.equals(user.nome_utente)){
				System.out.print("Immetti la password: ");
				String password_login = inp_login.nextLine();
				if (password_login.equals(user.password)){
					System.out.println("Benvenuto " + user.nome_utente + "!!!");
					login_status = user.nome_utente;
					logged_in = true;
				}
			}
		}
		System.out.println("###################################################################################################################");
	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}

	public static void Registrazione(){
		Scanner inp2 = new Scanner(System.in);
		System.out.print("Immetti nome utente (NON UTILIZZARE '- / \\ | . !'): ");
		String nome_utente = inp2.nextLine();
		if (nome_utente.contains("-") || nome_utente.contains("/") || nome_utente.contains("\\") || nome_utente.contains("|") || nome_utente.contains(".") || nome_utente.contains("!")){
			System.out.println("E' stato utilizzato un carattere non valido. Riprovare.");
		}
		for (Utente user : Utente.all_users){
			if (user.nome_utente.equals(nome_utente))
			{
				System.out.println("Nome utente non disponibile.");
			}
		}				
		System.out.print("Immetti password: ");
		String password = inp2.nextLine();
		System.out.print("Riconferma password: ");
		String password_riconferma = inp2.nextLine();
		if (password.equals(password_riconferma) == false){
			System.out.println("Le due password non corrispondono.");
		}
		Utente new_user = new Utente(nome_utente, password);
		System.out.println(new_user);
	}

	public static void RegistraPlaylist(){
		Scanner inp3 = new Scanner(System.in);
		System.out.print("Nome della nuova playlist: ");
		String nome = inp3.nextLine();
		Playlist playlist = new Playlist(nome);
		
	}
	
		

    
}