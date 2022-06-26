/**
 *
 * @author Mattia Papaccioli 747053 CO
 * @author 
 * @author 
 * @author 
 */
package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {

    // CAMPI

    public String nome = "";
    public ArrayList<Song> content = new ArrayList<Song>();

	// COSTRUTTORE
	
    public Playlist(String nome) {
        this.nome = nome;    	
    }

    public void addSong(Song brano) {
        content.add(brano);
    }
    
    public void removeSong(Song brano) {
        content.remove(brano);
    }

    public static void CSVWriter(String content) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/playlist.csv", true))){
			writer.append(content);			
			
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
    } 

}

