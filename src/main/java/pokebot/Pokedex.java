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

	Pokedex() {
		// upon creation loads a string that has all pokemon in it
		pokeString = getPokedex();
	}

	public String getPokedex() {
		// grabs string from text document pokedex.txt
		String line;
		String full = "";
		try (
				//windows
				 InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\pokedex.txt");
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

	public int getId(String name) {
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

	public static double[] getTypeArray(String line) {
		line = line.substring(line.indexOf("-") + 1);
		double[] tempType = new double[18];
		for (int a = 0; a < 18; a++) {

			if (Double.parseDouble(line.substring(a,a+1)) == 5) {
				tempType[a] = 0.5;
		
			} else {
			
				tempType[a] = Double.parseDouble(line.substring(a,a+1));
				
			}
		}

		return tempType;
	}

	public static double[] damageTo(int type1, int type2) {
		// grabs string from text document pokedex.txt

		double[] matchup = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		String line;
		String type = "";

		int count = 0;
		try (
				//windows
				InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\typing.txt");
				//linux
				//InputStream fis = new FileInputStream("/Program Files/typing");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {

				if (count == type1) {
					for (int a = 0; a < 18; a++) {
						matchup[a] *= getTypeArray(line)[a];
						
					}
				} else if (count == type2) {
					for (int a = 0; a < 18; a++) {
						matchup[a] *= getTypeArray(line)[a];
					}
				}
				count++;

			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return matchup;

	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Pokedex dex = new Pokedex();
		// System.out.println(dex.matchups("machop"));

		for (int a = 0; a < 18; a++) {
			System.out.println(a+ ":" + damageTo(convertType("fighting"), -1)[a]);
		}

		System.out.println("Done!");

	}

}
