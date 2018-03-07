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

	public String parseMove(String s) {
		for(int a = 0;a<18;a++) {
		if(s.toLowerCase().contains(typeList[a])) {
			return typeList[a];
		}
	}
		return "none?";
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
				if(line.toLowerCase().contains(name.toLowerCase())) {
					for(int a = 0;a<18;a++) {
						if(line.toLowerCase().contains(typeList[a])) {
							types[typeCount] = a + 1;
							typeCount++;
							if(typeCount > 1) {
								if(types[1] == types[0]) {
									types[1] = 0;
								}
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
		
		System.out.println(types[1]);
		if(types[1] == types[0]) {
			types[1] = 0;
			
		}
		return types;
	}
	
	public int[] weaknessCalculator(String name) {
		//This method gets all the weaknesses of a pokemon
				String line;
				int[] types = getType(name);
			
				int count = 1;
				
				int[] weaknesses = new int[18];
				try (

						// windows
						// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
						// linux
						
						InputStream fis = new FileInputStream(path + "/weakness");
						//Reads the file, and looks for weaknesses
						InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
						BufferedReader br = new BufferedReader(isr);) {
					while ((line = br.readLine()) != null) {
						if(count == types[0] || count == types[1]) {
							for(int a = 0;a<18;a++) {
							
								if(line.contains(typeList[a])){
							
									weaknesses[a] += 1;
								}
							}
						}
						count++;
								
						
						
					}
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				return weaknesses;
		
	}
	
	public int[] resistanceCalculator(String name) {
		//This method gets all the resistances of a pokemon
		String line;
		int[] types = getType(name);
	
		int count = 1;
		
		int[] weaknesses = new int[18];
		try (

				// windows
				// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				// linux
				
				InputStream fis = new FileInputStream(path + "/resistance");
				//Reads the file, and looks for weaknesses
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				if(count == types[0] || count == types[1]) {
					for(int a = 0;a<18;a++) {
					
						if(line.contains(typeList[a])){
					
							weaknesses[a] -= 1;
						}
					}
				}
				count++;
						
				
				
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return weaknesses;

}
	public int[] fullCalc(String name) {
		//This method combines all weaknesses, and resistances with immunities to create a perfect index
		//Types are ranked as ints moves greater than 0 will be super effective, moves equal to 0 will be neutral
		//moves less than 0 wont be effective, and moves less than -5 won't hit at all
		String line;
		int[] types = getType(name);
		int[] resistances = resistanceCalculator(name);
		int[] weaknesses = weaknessCalculator(name);
		int count = 1;
		
		int[] fullArray = new int[18];
		try (

				// windows
				// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				// linux
				
				InputStream fis = new FileInputStream(path + "/immunities");
				//Reads the file, and looks for weaknesses
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				if(count == types[0] || count == types[1]) {
					for(int a = 0;a<18;a++) {
					
						if(line.contains(typeList[a])){
					
							fullArray[a] -= 10;
						}
					}
				}
				count++;
						
				
				
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		for(int a = 0;a<18;a++) {
			fullArray[a] += resistances[a] + weaknesses[a];
		}
		
		return fullArray;

}
	
	
	public int moveType(String move) {
		//returns move typing by using a file
		//currently obsolete, because parseMove, see parseMove at top of class
		String line;
		
		int type = -1;
		int count = 1;
		
		int[] weaknesses = new int[18];
		try (

				// windows
				// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				// linux
				
				InputStream fis = new FileInputStream(path + "/moves");
				//Reads the file, and looks for weaknesses
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				if(line.toLowerCase().contains(move.toLowerCase())) {
					for(int a = 0;a<18;a++) {
						if(line.toLowerCase().contains(typeList[a])){
					
							type = a + 1;
						}
					}
				}
				count++;
						
				
				
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return type;
		
		
	}
	
	public int matchupCompare(String attacker,String defender) {

		if(getType(attacker)[1] == 0) {
			if(fullCalc(defender)[getType(attacker)[0]-1] < 0) {
				return -1;
			}else if(fullCalc(defender)[getType(attacker)[0]-1] > 0){
				return 1;
			}else {
				return 0;
			}
		}
		
		if(fullCalc(defender)[getType(attacker)[0]-1] < 0 && fullCalc(defender)[getType(attacker)[1]-1] < 0) {
			return -2;
		}else if(fullCalc(defender)[getType(attacker)[0]-1] < 0 || fullCalc(defender)[getType(attacker)[1]-1] < 0 ) {
			return -1;
		}else if(fullCalc(defender)[getType(attacker)[0]-1] > 0 && fullCalc(defender)[getType(attacker)[1]-1] > 0) {
			return 2;
		}else if(fullCalc(defender)[getType(attacker)[0]-1] > 0 || fullCalc(defender)[getType(attacker)[1]-1] > 0) {
			return 1;
		}else {
			return 0;
		}
		 
		//The higher the number the better the matchup is for an attacker, the lower the worse.
		
	}

	
	
		

	public static void main(String[] args)  {
		//The objective of this class is to determine weaknesses, and move types
		//Unlike pokeplayer everything here will not be static
		//Instantiate dex, and give path
		Pokedex dex = new Pokedex("/home/viyi/Documents/pokebot");
		
		System.out.println(dex.getType("Gyarados")[1]);
		System.out.println(dex.matchupCompare("machop","absol"));
		
		
		
		System.out.println("done!");

	}

}
