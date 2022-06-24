/**
 *
 * @author Mattia Papaccioli 747053 CO
 * @author 
 * @author 
 * @author 
 */
package src;

import java.util.ArrayList;

public class Playlist {

    // CAMPI

    public String nome = "";
    public ArrayList<Song> content = new ArrayList<Song>();

	// COSTRUTTORE
	
    public Playlist(String nome) {

        this.nome = nome;    	
        System.out.println("La Playlist " + nome + " e stata creata!!! ;)");
    }

    public void addSong(Song brano) {
        content.add(brano);
    }
    
    public void removeSong(Song brano) {
        content.remove(brano);
    }

}

