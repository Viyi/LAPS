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
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.Type;

public class Pokedex {
	static String path = "";
	String pokeString = "";

	Pokedex(String a) {
		path = a;
		// upon creation loads a string that has all pokemon in it
		pokeString = getPokedex();
	}

	
	public String getPokedex() {
		// grabs string from text document pokedex.txt
		String line;
		String full = "";
		try (
				//windows
				 InputStream fis = new FileInputStream("pokedex.txt");
				//linux
				//InputStream fis = new FileInputStream("/Program Files/pokedex.txt");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				// places each line of pokedex.txt in string
				full += line;

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		// Returns full pokemon roster to be taken into pokeString
		return full;

	}

	public  int getId(String name) {
		// Takes in a pokemon name, and outputs its id
		int id = 0;
		int length = name.length() - 1;
		String text = "";
		// checks if valid pokemon
		if (pokeString.contains("pokemonSpecies=NamedApiResource(name=" + name)) {
			// finds the index of the pokemon
			id = pokeString.indexOf("pokemonSpecies=NamedApiResource(name=" + name) + 68 + length;
			// checks if text gotten is a string, if so it returns
			if (StringUtils.isNumeric(pokeString.substring((id + 1), id + 4))) {
				text = pokeString.substring((id + 1), id + 4);
				return Integer.parseInt(text);
			} else if (StringUtils.isNumeric(pokeString.substring((id + 1), id + 3))) {
				text = pokeString.substring((id + 1), id + 3);
				return Integer.parseInt(text);
			} else if (StringUtils.isNumeric(pokeString.substring((id + 1), id + 2))) {
				text = pokeString.substring((id + 1), id + 2);
				return Integer.parseInt(text);
			}

		}

		// will return -1 for errors
		return -1;
	}

	public static int convertType(String type) {

		String[] typeList = { "normal", "fighting", "flying", "poison", "ground", "rock", "bug", "ghost", "steel",
				"fire", "water", "grass", "electric", "ice", "dragon", "dark", "fairy" };
		for (int a = 0; a < 19; a++) {
			if (type.equals(typeList[a])) {
				return a + 1;
			}
		}

		return -1;
	}

	public static double convertWeakness(double val) {
		if(val == 5) {
			return 0.5;
		}else {
			//System.out.println(val);
			return val;
			
		}
	}
	public static double[] damageTo(String stype1, String stype2) {
		// grabs string from text document pokedex.txt
		int type1 = convertType(stype1);
		int type2 = convertType(stype2);
		double[] matchup = { -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		String line;
		
		try (
				InputStream fis = new FileInputStream("typing");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
				line = br.readLine();
			
			for(int a = 1;a<19;a++) {
				line = br.readLine();
				line = line.substring(line.indexOf("-") + 1);
				
				matchup[a] *= convertWeakness(Double.parseDouble(line.substring(type1-1,type1)));
				matchup[a] *= convertWeakness(Double.parseDouble(line.substring(type2-1,type2)));
				//System.out.println(matchup[a]);
			}
		}
			 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		return matchup;

	}
		

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Pokedex dex = new Pokedex("");
		 PokeApi pokeApi = new PokeApiClient();
		
		 
		 System.out.println(bulbasaur);
		
	//	System.out.println(damageTo("grass","fairy")[convertType("dragon")]);
		
		
		System.out.println("done!");

	}

}
