package src;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Mattia Papaccioli 747053 CO
 * @author 
 * @author 
 * @author 
 */


public class Utente {
	
	//CAMPI
	public String nome_utente = "";
	public String password = "";
	public String cf;
	public String nome;
	public String cognome;
	public String via;
	public String numero_civico;
	public String cap;
	public String comune;
	public String provincia;
	public String email; 
	public static ArrayList<Utente> all_users = new ArrayList<Utente>();	
	
	// COSTRUTTORE
	public Utente(String nome, String password) {
		this.nome_utente = nome;
		this.password = password;
		
	}
	public static ArrayList<Utente> CSVReader() {

		try(BufferedReader reader = new BufferedReader(new FileReader("data/utenti.csv")))
		{
			String line = null;
			while ((line = reader.readLine()) != null){
				//System.out.println(line);
				String [] info = line.split("-");
				Utente user = new Utente(info[0], info[1]);
				all_users.add(user);
			}
			// System.out.println(all_users);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		return all_users;

		/*
		try(Scanner scanner = new Scanner(Paths.get("data/canzoni.csv").toFile())){
			
			String DELIMITER = ",";
			scanner.useDelimiter(DELIMITER);
			while (scanner.hasNext()) {
				System.out.println(scanner.next());
				Song canzone = new Song(scanner.next());
				all_songs.add(canzone);
				System.out.println(all_songs + "############################################");
				}
			
			}
			
		 catch (IOException ex) {
			ex.printStackTrace();
		 }
		return all_songs; */
	} 
}