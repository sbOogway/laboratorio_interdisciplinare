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
	public Emozioni emo;
	public static ArrayList<Emozioni> array_emozioni = new ArrayList<Emozioni>();
	public static ArrayList<Song> all_songs = new ArrayList<Song>();
	public static ArrayList<Song> all_songs_emo = new ArrayList<Song>();	

	// COSTRUTTORE
	public Song(String info) {
		String[] infos = info.split("-");
		this.titolo = infos[0];
		this.autore = infos[1];
		this.anno = infos[2];	
	}

	public Song(String info, Emozioni emos) {
		String[] infos = info.split("-");
		this.titolo = infos[0];
		this.autore = infos[1];
		this.anno = infos[2];
		this.emo = emos;		
	}

	public static ArrayList<Song> CSVReader_song() {
		try (BufferedReader reader = new BufferedReader(new FileReader("data/canzoni.csv"))){
			String line = null;
			while ((line = reader.readLine()) != null){
				Song brano = new Song(line);
				all_songs.add(brano);
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return all_songs;
	}
	
	public static ArrayList<Song> CSVReader_emo() {
			// LEGGE TUTTE LE EMOZIONI DELLE CANZONI IN data/emozioni.csv	
			try(BufferedReader reader = new BufferedReader(new FileReader("data/emozioni.csv")))
			{
				String line = null;
				while ((line = reader.readLine()) != null){
					//System.out.println(line);
					String[] data = new String[9];
					for (int i = 0; i <= 8; i++) {
						//errore qua
						// System.out.println(i  +" " +  line.split("-")[4].split(",")[i]);
						data[i] = line.split("-")[4].split(",")[i].substring(line.split("-")[4].split(",")[i].length()-1, line.split("-")[4].split(",")[i].length());
					}
					Emozioni emozione = new Emozioni(data);
					Song brano = new Song(line.split("-")[1] + "-" +  line.split("-")[2] + "-" + line.split("-")[3], emozione);
					all_songs_emo.add(brano);
				}
				//System.out.println(all_songs_emo);
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			
			// E QUA IL PROBLEM
			ArrayList<Song> all_songs_copy = new ArrayList<Song>();
			// FARE IN MODO CHE LE CANZONI UGUALI VENGONO MESSE IN UN SOLO OGGETTO SONG
			for (int z = 0;z<all_songs_emo.size();z++){
				for (int q = 0 ; q<all_songs_copy.size();q++){
					if ( all_songs_emo.get(z).autore.equals(all_songs_copy.get(q).autore) && all_songs_emo.get(z).titolo.equals(all_songs_copy.get(q).autore) ) {
						all_songs_copy.add(all_songs_emo.get(z));
					} else {
						all_songs_copy.get(z).emo.AMAZEMENT = (all_songs_copy.get(z).emo.AMAZEMENT + all_songs_emo.get(z).emo.AMAZEMENT ) /2;
					}
				}
			}

			return all_songs_emo;

	} 
	 


}

