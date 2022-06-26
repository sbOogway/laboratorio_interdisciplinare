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
		Song.CSVReader_song();
		System.out.print("##############################");
		// POPOLARE Utente.all_users
		Utente.CSVReader();
		System.out.print("#############################");
		// POPOLARE Song.all_songs_emo
		Song.CSVReader_emo();
		System.out.println("##########################");
		
		ArrayList<Playlist> my_playlists = new ArrayList<Playlist>();

		menu_avvio();

		while (true) {

			// MAIN MENU !!! FARE IN MODO CHE RICOMPAIA DOPO CHE VIENE EFFETUATA UN OPERAZIONE !!!
			
			menu();

			// FINE MAIN MENU
			
			// VISUALIZZARE EMOZIONI (LOGIN NON NECESSARIO)
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

			// LOGIN 
			if (mode.equals("4"))
			{
				login();		

			}

			// LOGOUT
			if (mode.equals("logout")){

				if (login_status.equals("NOT LOGGED IN")){
					System.out.println("###################################################################################################################");
					System.out.println("Nessun account connesso.");
					System.out.println("###################################################################################################################");
					continue;
				}	

				System.out.println("###################################################################################################################");
				System.out.println("Disconnesso da " + login_status + ".");
				System.out.println("###################################################################################################################");
				login_status = "NOT LOGGED IN";
				logged_in = false;

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
				"                                                                                  _ \r\n" + 
				"                                _____      ____ _  __ _ ___  ___  _   _ _ __   __| |\r\n" + 
				"                               / __\\ \\ /\\ / / _ `|/ _` / __|/ _ \\| | | | '_ \\ / _` |\r\n" + 
				"                               \\__ \\\\ V  V / (_| | (|  \\__ \\ (_) | |_| | | | | (_| |\r\n" + 
				"                               |___/ \\_/\\_/ \\__,_|\\__, |___/\\___/ \\__,_|_| |_|\\__,_|\r\n" + 
				"                                                  |___/                                 \n");
		System.out.println("###################################################################################################################");
		System.out.print("Benvenuto su $WAGSOUND!!! :}" + "\n");

	}
				
	public static void menu() {
		Scanner inp0 = new Scanner(System.in);		
		System.out.println("Cosa vuoi fare?" + padLeft("Login: " + login_status, 100) + "\n0. Ricerca le emozioni di un brano musicale. \n1. Registrati all'applicazione.\n2. Crea una nuova playlist. \n3. Inserisci emozione per canzone della playlist.\n4. Effettua il login. " + padLeft("Altri comandi: 'exit', 'logout'.", 93));		
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
		for (Song song : Song.all_songs_emo) {			
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
		Utente.CSVWriter("\n" + nome_utente + "-" + password);
		System.out.println("###################################################################################################################");
		System.out.println("Nuovo utente registrato: " + nome_utente + " !!! ;)");
		System.out.println("###################################################################################################################");
		Utente.CSVReader();
	}

	public static void RegistraPlaylist(){
		Scanner inp3 = new Scanner(System.in);
		System.out.print("Nome della nuova playlist: ");
		String nome = inp3.nextLine();
		Playlist playlist = new Playlist(nome);
		ArrayList<Song> all_songs_copy = Song.all_songs;		
		int index = 0;
		String playlist_songs = "";
		while (index != -1) {
			int count = 0;
			System.out.println("###################################################################################################################");
			for (Song brano : all_songs_copy){
				System.out.println(count + ". " + brano.autore + " - " + brano.titolo + " - " + brano.anno);
				count += 1;
			}
			System.out.println("###################################################################################################################");
			System.out.print("Digita il numero della canzone che vuoi inserire (Digita '-1' quando hai finito): ");
			index = inp3.nextInt();
			if (index == -1){
				continue;
			}
			playlist.addSong(all_songs_copy.get(index));
			playlist_songs += all_songs_copy.get(index).autore + ":" + all_songs_copy.get(index).titolo + ":" + all_songs_copy.get(index).anno + ", " ;
			all_songs_copy.remove(index);
		} 
		Playlist.CSVWriter("\n"+login_status+ "-" + playlist.nome + "-" + playlist_songs );
		System.out.println("La playlist " + playlist.nome + " e' stata creata!!! ;)");
		System.out.println("###################################################################################################################");
		
	}
	
		

    
}