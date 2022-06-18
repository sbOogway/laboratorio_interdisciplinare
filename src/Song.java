package src;
/**
 *
 * @author Mattia Papaccioli 747053 CO
 * @author 
 * @author 
 * @author 
 */




import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Song{
	
	// CAMPI
	public String info = "";
	public String autore = "";
	public String titolo = "";
	public String anno = "";
	public static ArrayList <Song> all_songs = new ArrayList<Song>();
	
	

	// COSTRUTTORE
	public Song(String info) {
		String[] infos = info.split("-");
		this.titolo = infos[0];
		this.autore = infos[1];
		this.anno = infos[2];		
	}
	
	public static ArrayList<Song> CSVReader() {

		try(BufferedReader reader = new BufferedReader(new FileReader("data/canzoni.csv")))
		{
			String line = null;
			while ((line = reader.readLine()) != null){
				//System.out.println(line);
				Song brano = new Song(line);
				all_songs.add(brano);
			}
			//System.out.println(all_songs);
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		return all_songs;

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

