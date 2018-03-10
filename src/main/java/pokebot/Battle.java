package pokebot;

import java.util.Arrays;

public class Battle {
	private int playStyle = 1;
	private String[] myTeam = new String[7];
	private int[][] moves = new int[2][4];
	private boolean canStatus = true;

	public Battle(int style, String[] team) {
		playStyle = style;
		myTeam = team;
	}

	public void setTeam(String[] team) {
		if(!myTeam[1].equals(team[1])) {
			canStatus = true;
		}
		myTeam = team;
	}

	public void setStyle(int style) {
		// 1: offensive, 2:defensive, 3:random
		playStyle = style;
	}

	public void setMoves(int[][] m) {
		moves = m;
	}

	// Battle Methods
	public int oppTest(String path) {
		// Tests opponent vs currentPoke, switches if currentPoke is weak to opponent
		Pokedex dex = new Pokedex(path);
		// returns 0 if currentPoke is resilient to opponent
		if (dex.matchupCompare(myTeam[0], myTeam[1]) <= 0) {
			return 0;
		}
			
		// returns location of pokemon that is most resilient to the opponent
		int bestMatchUp = 2;
		for (int pos = 2; pos < 7; pos++) {
			if (dex.matchupCompare(myTeam[0], myTeam[pos]) <= dex.matchupCompare(myTeam[0],myTeam[bestMatchUp])) {
				
					bestMatchUp = pos;
				
			}
		}
		// returns 0(keeps currentPoke) if there isn't a better option
		// if(bestMatchUp==1) {bestMatchUp=0;}
		return bestMatchUp;
	}

	
	public int faintSwitch(String path) {
Pokedex dex = new Pokedex(path);
		
		int bestMatchUp = 2;
		for (int pos = 2; pos < 7; pos++) {
			if (dex.matchupCompare(myTeam[0], myTeam[pos]) < dex.matchupCompare(myTeam[0],myTeam[bestMatchUp])) {
			
					bestMatchUp = pos;
				
				
			}
		}
		return bestMatchUp;
	}
	
	public int smartAttack(String path) {
		// chooses effective attack
		Pokedex dex = new Pokedex(path);
		
		if(moves[0][0] == -1) {
			return -1;
		}
		System.out.println("Move Decision " + Arrays.toString(moves[1]));
		
		for(int a = 0;a<4;a++) {
			int val = dex.fullCalc(myTeam[0])[moves[0][a]];
			if(moves[1][a]==1) {
			if(val >1) {
				System.out.println("Attacking Move: " + (a+1) );
				return a+1;
			}
			}
		}
		
		for(int a = 0;a<4;a++) {
			if(canStatus) {
				if(moves[1][a] == 0) {
					if(dex.fullCalc(myTeam[0])[moves[0][a]] >-1) {
						System.out.println("Status Move: " + (a+1));
						canStatus = false;
						return a+1;
					}
				}
			}
		}
		
		
		for(int a = 0;a<4;a++) {
			int val = dex.fullCalc(myTeam[0])[moves[0][a]];
			if(moves[1][a]==1) {
			if(val >0) {
				System.out.println("Attacking Move: " + (a+1) );
				return a+1;
			}
			}
		}
		
		for(int a = 0;a<4;a++) {
			int val = dex.fullCalc(myTeam[0])[moves[0][a]];
			if(moves[1][a]==1) {
			if(val >-1) {
				System.out.println("Attacking Move: " + (a+1) );
				return a+1;
			}
			}
		}
		
		for(int a = 0;a<4;a++) {
			int val = dex.fullCalc(myTeam[0])[moves[0][a]];
			if(moves[1][a]==1) {
			if(val >-2) {
				System.out.println("Attacking Move: " + (a+1) );
				return a+1;
			}
			}
		}
		
		for(int a = 0;a<4;a++) {
			int val = dex.fullCalc(myTeam[0])[moves[0][a]];
			if(moves[1][a]==1) {
			if(val >-3) {
				System.out.println("Attacking Move: " + (a+1) );
				return a+1;
			}
			}
		}
		
		return 3;
		
		
		
	}

}
