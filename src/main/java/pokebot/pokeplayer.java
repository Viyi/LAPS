package pokebot;

import java.awt.List;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.stream.Stream;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	public static int waitTime = 3;
	public pokeplayer() {
		
		
	}
	
	
	public static String getInfo() {
		//This method loads username and password into a string for use with login()
		//it reads a login.txt located in Program Files/pokebot
		//Place your login file there
		String line;
		String full = "";
		try (
		    
				//windows
				//InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				//linux
				InputStream fis = new FileInputStream("/Program Files/login");	
				
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
	
	public static void click(WebDriver driver, By selector) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		//Waits until it finds the element then clicks
		wait.until(ExpectedConditions.elementToBeClickable(selector));
		driver.findElement(selector).click();
	}
	
	
	public static void type(WebDriver driver, By selector, String input) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		//Waits until it finds the element then sends keys
		wait.until(ExpectedConditions.elementToBeClickable(selector));
		driver.findElement(selector).sendKeys(input);
	}
	
	
	
	public static void login(WebDriver driver){
		//login to pokemon showdown
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		
		String username = getInfo();
		String password = username.substring(username.indexOf("-")+1);
		username = username.substring(0,username.indexOf("-"));
		System.out.println(username);
		System.out.println(password);
		//get password from file
		driver.get("https://play.pokemonshowdown.com/");
		//go to pokemon showdown
		click(driver,By.cssSelector(".userbar > button:nth-child(1)"));
		type(driver, By.cssSelector(".textbox"), username);
		click(driver,By.cssSelector(".buttonbar > button:nth-child(1)"));
		type(driver, By.cssSelector(".textbox"), password);
		click(driver,By.cssSelector(".buttonbar > button:nth-child(1)"));
		click(driver, By.cssSelector(".autofocus"));
	}
	
	public static void battle() {
		
		
	}
	
	
	public static void main(String[]args) {
		//loads up gecko driver, and starts firefox
		//windows
		//System.setProperty("webdriver.gecko.driver","C:\\Program Files\\pokebot\\geckodriver.exe");
		//linux
		System.setProperty("webdriver.gecko.driver","/Program Files/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		login(driver);
		
		

		
		
		
		System.out.println("done!");
		
	}

}
