package pokebot;

import java.awt.List;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



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

public class PokePlayer {
	public static String path = "";
	public static int waitTime = 360;

	public PokePlayer() {

	}
	public static void setPath(String s) {
		path = s;
	}

	public static String getInfo() {
		// This method loads username and password into a string for use with login()
		// it reads a login.txt located in Program Files/pokebot
		// Place your login file there
		String line;
		String full = "";
		try (

				// windows
				// InputStream fis = new FileInputStream("C:\\Program Files\\pokebot\\login.txt");
				// linux
				InputStream fis = new FileInputStream(path + "/login");

				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {

				full += line;

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
		// Waits until it finds the element then clicks
		wait.until(ExpectedConditions.elementToBeClickable(selector));
		driver.findElement(selector).click();
	}
	
	public static String find(WebDriver driver, By selector) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated((selector)));
		return driver.findElement(selector).getText();
	}

	public static void type(WebDriver driver, By selector, String input) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		// Waits until it finds the element then sends keys
		wait.until(ExpectedConditions.elementToBeClickable(selector));
		driver.findElement(selector).sendKeys(input);
	}

	public static void login(WebDriver driver) {
		// login to pokemon showdown
		WebDriverWait wait = new WebDriverWait(driver, waitTime);

		String username = getInfo();
		String password = username.substring(username.indexOf("-") + 1);
		username = username.substring(0, username.indexOf("-"));
		System.out.println(username);
		System.out.println(password);
		// get password from file
		driver.get("https://play.pokemonshowdown.com/");
		// go to pokemon showdown
		click(driver, By.cssSelector(".userbar > button:nth-child(1)"));
		type(driver, By.cssSelector(".textbox"), username);
		click(driver, By.cssSelector(".buttonbar > button:nth-child(1)"));
		type(driver, By.cssSelector(".textbox"), password);
		click(driver, By.cssSelector(".buttonbar > button:nth-child(1)"));
		click(driver, By.cssSelector(".autofocus"));
	}
	
	public static String[] teamArray(WebDriver driver) {
		//creates an array of pokemon names from your team
		String[] team = new String[7];
		
		team[0] = find(driver,By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[2]/strong"));
		team[0] = team[0].substring(0, team[0].indexOf("L")-1);
		team[1] = find(driver,By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[1]/strong"));
		team[1] = team[1].substring(0, team[1].indexOf("L")-1);
		for(int a = 2;a<7;a++) {
			team[a] = find(driver,By.cssSelector(".switchmenu > button:nth-child("+a+")"));
			System.out.println(team[a]);
			
			
		}
		System.out.println(Arrays.toString(team));
		return team;
	}
	
	public static boolean battleOver(WebDriver driver) {
		//the idea here is to see if the battle is over, not working yet
		if(ExpectedConditions.elementToBeClickable(By.cssSelector(".controls > p:nth-child(2) > button:nth-child(1)")) !=null) {
			return true;
		}else {
			return false;
		}
	}

	public static String[] setMoves(WebDriver driver) {
		//This method looks at pokemons moves, and makes an array of all the types!
		Pokedex dex = new Pokedex(path);
		String[] moves = new String[4];
		//Parse move is very simple, and should be improved in the future
		moves[0] = dex.parseMove(find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[1]")));
		moves[1] = dex.parseMove(find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[2]")));
		moves[2] = dex.parseMove(find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[3]")));
		moves[3] = dex.parseMove(find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[4]")));
		System.out.println(Arrays.toString(moves));
		return moves;
	}
	
	public static void battle(WebDriver driver) {
		//Sets timer
		click(driver, By.cssSelector(".timerbutton"));
		click(driver, By.cssSelector(".ps-popup > p:nth-child(1) > button:nth-child(1)"));
		//Waits to Start Battle 
		Battle battle = new Battle(1,teamArray(driver));
		battle.setMoves(setMoves(driver));
		
	
	}

	public static void main(String[] args) {
		setPath("/home/viyi/Documents/pokebot");
		// loads up gecko driver, and starts firefox
		// windows
		// System.setProperty("webdriver.gecko.driver","C:\\Program Files\\pokebot\\geckodriver.exe");
		//linux
		System.setProperty("webdriver.gecko.driver", path + "/geckodriver");
		WebDriver driver = new FirefoxDriver();
		
		login(driver);
		battle(driver);

		System.out.println("done!");

	}

}
