package pokebot;

import java.awt.List;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
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
	public static int enemyInt = 1;
	public static int currentInt = 0;
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
				// InputStream fis = new FileInputStream("C:\\Program
				// Files\\pokebot\\login.txt");
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
	
	public static void click(WebDriver driver, By selector, int waiter) {
		WebDriverWait wait = new WebDriverWait(driver, waiter);
		// Waits until it finds the element then clicks
		wait.until(ExpectedConditions.elementToBeClickable(selector));
		driver.findElement(selector).click();
	}

	public static String find(WebDriver driver, By selector) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated((selector)));
		return driver.findElement(selector).getText();
	}

	public static String find(WebDriver driver, By selector, int waiter) {
		WebDriverWait wait = new WebDriverWait(driver, waiter);
		wait.until(ExpectedConditions.presenceOfElementLocated((selector)));
		return driver.findElement(selector).getText();
	}

	public static boolean canClose(WebDriver driver) {
		  try {
			  
			    System.out.println("Inside canClose: " + find(driver,By.cssSelector(".controls > p:nth-child(2) > button:nth-child(1)"),1));
			    find(driver,By.cssSelector(".controls > p:nth-child(2) > button:nth-child(1)"),1);
			    return true;
			  }
			catch (org.openqa.selenium.TimeoutException e) {
			    return false;
			  }
			}
	public static boolean isDisabled(WebDriver driver, By selector) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated((selector)));
		if (driver.findElement(selector).getAttribute("class").equals("disabled")) {
			return true;
		} else {
			return false;
		}
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

	public static void mute(WebDriver driver) {
		// mutes the game, I'm so helpful
		click(driver, By.cssSelector("button.icon:nth-child(2)"));
		click(driver, By.cssSelector(".ps-popup > p:nth-child(4) > label:nth-child(1) > input:nth-child(1)"));
	}

	public static int enemyIs(WebDriver driver) {
		
		//while (find(driver, By.cssSelector(".switchmenu > button:nth-child(1)")).equals("")) {

		//}

		String tempEnemy = "pikachu";
		String tempCurrent = "azelf";
		try {
		tempEnemy = find(driver, By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[1]/strong"),1);
		}catch(org.openqa.selenium.TimeoutException Exception  ) {
			
		}
		try {
			tempCurrent = find(driver, By.cssSelector(".switchmenu > button:nth-child(1)"),1);
			}catch(org.openqa.selenium.TimeoutException Exception  ) {
				
			}
		

		if (!tempEnemy.equals("")) {
			if (tempEnemy.indexOf(" L") == -1) {

			} else {
				 tempEnemy = tempEnemy.substring(0, tempEnemy.indexOf(" L"));
			}

			if (!tempEnemy.equals(tempCurrent)) {
				System.out.println("Returning Temp Current 1");
				return 1;
			}
		}
		String tempEnemy2 = find(driver, By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[2]/strong"));
		if (!tempEnemy2.equals("")) {
			if (tempEnemy2.indexOf(" L") == -1) {

			} else {
				tempEnemy2 = tempEnemy2.substring(0, tempEnemy2.indexOf(" L"));
			}

			if (!tempEnemy2.equals(tempCurrent)) {
				// System.out.println("Returning Temp Current 2");
				return 2;
			}
			
		}
		
		if(tempEnemy.equals(tempEnemy2)) {
			return 1;
		}
		// System.out.println("Returning Temp Current -1");
		//return enemyIs(driver);
		return -1;
	}

	public static String[] teamArray(WebDriver driver, Battle b) {
		// creates an array of pokemon names from your team
		String[] team = new String[7];
		if(canClose(driver)) {
			team[0] = "boop";
			System.out.println("Boop!");
			return team;
		}
		
		try {
			team[1] = find(driver, By.cssSelector(".switchmenu > button:nth-child(1)"));
		}catch(NoSuchElementException Exception){
			b.faintSwitch(path);
			team[1] = find(driver, By.cssSelector("button.disabled:nth-child(1)"));
			
		}
		
		System.out.println("Loading Current " + team[1]);
		enemyInt = enemyIs(driver);
		
		System.out.println("Enemy Is" + enemyInt);
		if(enemyInt == -1) {
			team[0] = "shuckle";
		}else {
			team[0] = find(driver, By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[" + enemyInt + "]/strong"));
		}
		
		team[0] = team[0].substring(0, team[0].indexOf(" L"));
		System.out.println("Loading Enemy " + team[0]);

	
		try {
		for (int a = 2; a < 7; a++) {
			if (isDisabled(driver, By.cssSelector(".switchmenu > button:nth-child(" + a + ")"))) {
				team[a] = "Fainted";

			} else {
				team[a] = find(driver, By.cssSelector(".switchmenu > button:nth-child(" + a + ")"),1);
			}

			System.out.println("Loading Pokemon: " + a + " " + team[a]);

		}
		}catch(org.openqa.selenium.TimeoutException e) {
			
		}
		return team;

	}

	public static int[][] setMoves(WebDriver driver) {
		// This method looks at pokemons moves, and makes an array of all the types!
		Pokedex dex = new Pokedex(path);

		String[] moveList = { "taco", "taco", "taco", "taco" };
		// Parse move is very simple, and should be improved in the future
		try {
			moveList[0] = find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[1]"),1);
			System.out.println("1" + isDisabled(driver,By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[1]")));
		}catch(org.openqa.selenium.TimeoutException E ){
			
		}
		try {
			moveList[1] = find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[2]"),1);
			System.out.println("2" + isDisabled(driver,By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[2]")));
		}catch(org.openqa.selenium.TimeoutException Eception ){
			
		}
		try {
			moveList[2] = find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[3]"),1);
		}catch(org.openqa.selenium.TimeoutException Exeption  ){
			
		}
		try {
			moveList[3] = find(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[4]"),1);
		}catch(org.openqa.selenium.TimeoutException Exction){
			
		}
		System.out.println("Move List: "+Arrays.toString(moveList));
		
		
		

		return dex.moveEffect(moveList);
	}

	public static void battle(WebDriver driver) {
		// Sets timer
		
		click(driver, By.cssSelector(".big"));
		System.out.println("Queing for Battle.");
		click(driver, By.cssSelector(".timerbutton"));
		click(driver, By.cssSelector(".ps-popup > p:nth-child(1) > button:nth-child(1)"));
		System.out.println("Timer Disabled.");
		// Waits to Start Battle
		Battle battle = new Battle(1, teamArray(driver,null));
		
		while (!canClose(driver)) {
			
			System.out.println("Setting Team.");
			battle.setTeam(teamArray(driver,battle));
			System.out.println("Here is the close button: " + canClose(driver));
			System.out.println("Setting Moves.");
			battle.setMoves(setMoves(driver));
	
			System.out.println("Finding Best Option.");
			int change = battle.oppTest(path,find(driver,By.xpath("/html/body/div[4]/div[1]/div/div[5]/div[1]/div/div[1]")));
			try {click(driver,By.cssSelector(".megaevo"),1);}catch(org.openqa.selenium.TimeoutException e) {
				}
			
			if(enemyInt == 1) {
				currentInt = 1;
			}else {
				currentInt = 2;
			}
			
			int choice = battle.smartAttack(path,find(driver,By.xpath("/html/body/div[4]/div[1]/div/div[5]/div["+currentInt+"]/div/div[1]")));
			try {
				//click(driver, By.cssSelector(".type-Dragon"))
			}catch(org.openqa.selenium.TimeoutException E ){
				
			}
			if (change != 0) {
				click(driver, By.cssSelector(".switchmenu > button:nth-child(" + change + ")"));
			} else if (choice == -1) {
				click(driver, By.cssSelector(".switchmenu > button:nth-child(" + battle.faintSwitch(path) + ")"));
			} else if (choice > -1) {
				click(driver, By.xpath("/html/body/div[4]/div[5]/div/div[2]/div[2]/button[" + choice + "]"));
			}
			System.out.println("Can close?: " + canClose(driver));
			//enemy hp .hptext
			//Hp Level div.statbar:nth-child(1) > div:nth-child(2) > div:nth-child(1)
			//Instant Replay .controls > p:nth-child(1) > button:nth-child(2)
			//Main Menu .controls > p:nth-child(2) > button:nth-child(1)

		}
		
	}

	public static void main(String[] args) {
		setPath("/home/viyi/Documents/pokebot");
		// loads up gecko driver, and starts firefox
		// windows
		// System.setProperty("webdriver.gecko.driver","C:\\Program
		// Files\\pokebot\\geckodriver.exe");
		// linux
		System.setProperty("webdriver.gecko.driver", path + "/geckodriver");
		WebDriver driver = new FirefoxDriver();

		login(driver);
		mute(driver);

		battle(driver);

		System.out.println("done!");

	}

}
