package pokebot;

public class Battle {
	private int playStyle = 1;
	private String[] myTeam = new String[7];
	private String currentPoke = myTeam[1];
	private String opponent = myTeam[0];
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
	public int oppTest(String path) {
		//Tests opponent vs currentPoke, switches if currentPoke is weak to opponent
		Pokedex reference = new Pokedex(path);
		//returns 0 if currentPoke is resilient to opponent
		if(reference.matchupCompare(opponent, currentPoke)<=0)
			return 0;
		//returns location of pokemon that is most resilient to the opponent
		int bestMatchUp = 1;
		for(int pos = 1; pos<8; pos++) {
			if(reference.matchupCompare(opponent, myTeam[pos])< reference.matchupCompare(opponent, myTeam[bestMatchUp])){
				bestMatchUp=pos;
			}
		}
		//returns 0(keeps currentPoke) if there isn't a better option
		if(bestMatchUp==1) {bestMatchUp=0;}
		return bestMatchUp;
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


