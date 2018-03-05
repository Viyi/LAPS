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
	//Path to data files
	private String path = "";
	//String Array with all types. Normal is the 1st type not 0, so all methods add one when returning from array.
	private String[] typeList = { "normal", "fighting", "flying", "poison", "ground", "rock", "bug", "ghost", "steel",
			"fire", "water", "grass", "electric", "psychic", "ice", "dragon", "dark", "fairy" };
	Pokedex(String a) {
		//upon initialization sets path for data files
		path = a;
	}

	
	
	public int[] getType(String name) {
		//This method is super effective!
		//get type gets the type of whatever pokemon you input.
		String line;
		//TypeCount counts how many types the pokemon has
		int typeCount = 0;
		//The array to be returned after method completes
		int[] types = new int[2];
		try (

				// windows
				// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				// linux
				//#TODO take out all ability names in pokerepo, it messes up scanning (steelix) 
				InputStream fis = new FileInputStream(path + "/pokerepo");
				//Reads the file, and looks for pokemon names in lower case.
				//Then finds types based on the typeCount array
				//Then adds type id number to the types array to be returned
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				if(line.toLowerCase().contains(name)) {
					for(int a = 0;a<18;a++) {
						if(line.toLowerCase().contains(typeList[a])) {
							types[typeCount] = a + 1;
							typeCount++;
							if(typeCount > 1) {
								return types;
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		
		return types;
	}
	

	

	
	
		

	public static void main(String[] args)  {
		//The objective of this class is to determine weaknesses, and move types
		//Unlike pokeplayer everything here will not be static
		//Instantiate dex, and give path
		Pokedex dex = new Pokedex("/home/viyi/Documents/pokebot");
		String name = "rayquaza";
		System.out.println(dex.getType(name)[0]);
		System.out.println(dex.getType(name)[1]);
		
		
		System.out.println("done!");

	}

}
