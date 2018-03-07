package pokebot;

public class Battle {
	private String opponent = "";
	private int playStyle = 1;
	private String[] myTeam = new String[7];
	private String[] moves = new String[4];
	public Battle(int style,String[] team) {
		playStyle = style;
		myTeam = team;
	}
	
	public void setTeam(String[] team) {
		myTeam = team;
	}
	
	public void setStyle(int style) {
		//1: offensive, 2:defensive, 3:random
		playStyle = style;
		}
	public void setOpponent(String opp) {
		opponent = myTeam[0];
	}
	
	public String getOpponent() {
		return opponent;
	}
	
	public void setMoves(String[] m) {
		moves = m;
	}
	
	//Battle Methods
	public int oppTest() {
		//Tests opponent vs current pokemon, returns number based on number decide to switch
		
		return -1;
	}
	
	public int smartSwitch() {
		//chooses good pokemon to switch into opponent
		
		return -1;
	}
	
	public int smartAttack() {
		//chooses effective attack
		return -1;
	}
	
	
	
	
	
	
	
}


