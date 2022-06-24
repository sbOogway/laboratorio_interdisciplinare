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
	public static ArrayList <Song> all_songs = new ArrayList<Song>();	

	// COSTRUTTORE
	public Song(String info, Emozioni emos) {
		String[] infos = info.split("-");
		this.titolo = infos[0];
		this.autore = infos[1];
		this.anno = infos[2];
		this.emo = emos;		
	}
	
	public static ArrayList<Song> CSVReader() {
			// LEGGE TUTTE LE EMOZIONI DELLE CANZONI IN data/canzoni.csv	
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
					all_songs.add(brano);
				}
				//System.out.println(all_songs);
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
			
			// E QUA IL PROBLEM
			ArrayList<Song> all_songs_copy = new ArrayList<Song>();
			// FARE IN MODO CHE LE CANZONI UGUALI VENGONO MESSE IN UN SOLO OGGETTO SONG
			for (int z = 0;z<all_songs.size();z++){
				for (int q = 0 ; q<all_songs_copy.size();q++){
					if ( all_songs.get(z).autore.equals(all_songs_copy.get(q).autore) && all_songs.get(z).titolo.equals(all_songs_copy.get(q).autore) ) {
						all_songs_copy.add(all_songs.get(z));
					} else {
						all_songs_copy.get(z).emo.AMAZEMENT = (all_songs_copy.get(z).emo.AMAZEMENT + all_songs.get(z).emo.AMAZEMENT ) /2;
					}
				}
			}

			// System.out.println(all_songs_copy.toString());

				
						

			/* 
			for (int i =0;i<= all_songs.size() - 1;i++){
				for (int j = 0; j <= all_songs.size() - 1 - count1; j++) {
					if (all_songs.get(i).titolo.equals(all_songs.get(j).titolo) && all_songs.get(i).autore.equals(all_songs.get(j).autore) && all_songs.get(i).anno.equals(all_songs.get(j).anno)){
						System.out.println(i + " ciclo");
						all_songs.get(i).emo.AMAZEMENT = (all_songs.get(i).emo.AMAZEMENT + all_songs.get(j).emo.AMAZEMENT) / 2;
						all_songs.get(i).emo.SOLEMNITY = (all_songs.get(i).emo.SOLEMNITY + all_songs.get(j).emo.SOLEMNITY) / 2;
						all_songs.get(i).emo.TENDERNESS = (all_songs.get(i).emo.TENDERNESS + all_songs.get(j).emo.TENDERNESS) / 2;
						all_songs.get(i).emo.NOSTALGIA = (all_songs.get(i).emo.NOSTALGIA + all_songs.get(j).emo.NOSTALGIA) / 2;
						all_songs.get(i).emo.CALMNESS = (all_songs.get(i).emo.CALMNESS + all_songs.get(j).emo.CALMNESS) / 2;
						all_songs.get(i).emo.POWER = (all_songs.get(i).emo.POWER + all_songs.get(j).emo.POWER) / 2;
						all_songs.get(i).emo.JOY = (all_songs.get(i).emo.JOY + all_songs.get(j).emo.JOY) / 2;
						all_songs.get(i).emo.TENSION = (all_songs.get(i).emo.TENSION + all_songs.get(j).emo.TENSION) / 2;
						all_songs.get(i).emo.SADNESS = (all_songs.get(i).emo.SADNESS + all_songs.get(j).emo.SADNESS) / 2;
						all_songs.remove(all_songs.get(j));
						count1 += 1;
					}
				} 

			}
			*/
			

			//all_songs = all_songs_copy;

			return all_songs;
		/* 
		// LEGGE TUTTE LE CANZONI IN data/canzoni.csv	
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
		*/

	} 
	 


}

