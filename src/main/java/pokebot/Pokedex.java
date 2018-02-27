package pokebot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.Type;

public class Pokedex {
	String pokeString = "";
	Pokedex(){
		//upon creation loads a string that has all pokemon in it
		 pokeString = getPokedex();
	}

	public String getPokedex() {
		//grabs string from text document pokedex.txt
		String line;
		String full = "";
		try (
		    InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\pokedex.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
		    while ((line = br.readLine()) != null) {
		    	//places each line of pokedex.txt in string
		    	full+=line;
		    	
		    }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//Returns full pokemon roster to be taken into pokeString
		return full;
	
	}
	
	public int getId(String name) {
		//Takes in a pokemon name, and outputs its id
		int id = 0;
		int length = name.length()-1;
		String text = "";
		//checks if valid pokemon
		if(pokeString.contains("pokemonSpecies=NamedApiResource(name=" + name)) {
			//finds the index of the pokemon
			id = pokeString.indexOf("pokemonSpecies=NamedApiResource(name="+name ) + 68 + length;
			//checks if text gotten is a string, if so it returns
			if(StringUtils.isNumeric(pokeString.substring((id+1),id+4))) {
				text = pokeString.substring((id+1),id+4);
				return Integer.parseInt(text);
			}else if(StringUtils.isNumeric(pokeString.substring((id+1),id+3))) {
				text = pokeString.substring((id+1),id+3);
				return Integer.parseInt(text);
			}else if(StringUtils.isNumeric(pokeString.substring((id+1),id+2))){
				text = pokeString.substring((id+1),id+2);
				return Integer.parseInt(text);
			}
			
			
		}
		
		//will return -1 for errors
		return -1;
	}
	
	public int[] matchups(String name) {
		int id = getId(name);
		System.out.println(id);
		  PokeApi pokeApi = new PokeApiClient();
		  Pokemon saur = pokeApi.getPokemon(id);
		  System.out.println(saur);
		  Type poke = pokeApi.getType(18);
	     System.out.println(poke.component3().getDoubleDamageTo());
		//Here is the typing in the array
		//Normal:1 Fighting:2, Flying:3, Poison:4, Ground:5, Rock:6, Bug:7, Ghost:8, Steel:9
		//Fire:10, Water:11, Grass:12, Electric:13, Physic:14, Ice:15, Dragon:16, Dark:17, Fairy:18
		int[] types = new int[18];
		
		
		
		return types;
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
	Pokedex dex = new Pokedex();
	System.out.println(dex.matchups("graveler"));
	
	
	
	
	
	
	}

}
