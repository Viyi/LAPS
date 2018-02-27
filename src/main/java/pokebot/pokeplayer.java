package pokebot;

import java.awt.List;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.stream.Stream;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpeciesDexEntry;



public class pokeplayer {
	
	public pokeplayer() {
		
		
	}
	
	
	public static String getInfo() {
		//This method loads username and password into a string for use with login()
		//it reads a login.txt located in Program Files/pokebot
		//Place your login file there
		String line;
		String full = "";
		try (
		    InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
		    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader br = new BufferedReader(isr);
		) {
		    while ((line = br.readLine()) != null) {
		       
		    	full+= line;
		    	
		    }
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return full;
	}
	
	public static void login(WebDriver driver){
		String username = getInfo();
		String password = username.substring(username.indexOf("-")+1);
		username = username.substring(0,username.indexOf("-"));
		System.out.println(username);
		System.out.println(password);
		
		driver.get("https://play.pokemonshowdown.com/");
		
		driver.findElement(By.cssSelector("input[type='button'][value='Open device access']")).click();
		
	}
	
	
	
	
	public static void main(String[]args) {
		//loads up gecko driver, and starts firefox
		System.setProperty("webdriver.gecko.driver","C:\\Program Files\\pokebot\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		login(driver);
		
		

		
		//driver.findElement(By.xpath("/html/body/div[1]/div[3]/button[1]")).click();
		
		System.out.println("done!");
		
	}

}
