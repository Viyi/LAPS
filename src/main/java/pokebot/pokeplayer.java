package pokebot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpecies;
import me.sargunvohra.lib.pokekotlin.model.PokemonSpeciesDexEntry;

public class pokeplayer {
	
	public static void main(String[]args) {
		//set this to your gecko driver path
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Viyi\\Desktop\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		
		//Open pokemon showdown, and login
		driver.get("http://play.pokemonshowdown.com/");
		
		driver.findElement(By.cssSelector(".userbar > button:nth-child(1)")).click();
		
		
	}

}
