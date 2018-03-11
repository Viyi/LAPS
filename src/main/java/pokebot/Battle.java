package pokebot;

import java.util.Arrays;

public class Battle {
	private int playStyle = 1;
	private String[] myTeam = new String[7];
	private int[][] moves = new int[2][4];
	private boolean canStatus = true;
	private int lastMove = -1;
	private int amountTaken = 0;
	private int currentHp = 100;
	private int boost = 0;

	public Battle(int style, String[] team) {
		playStyle = style;
		myTeam = team;
	}

	public void setTeam(String[] team) {
		if(!myTeam[1].equals(team[1])) {
			canStatus = true;
			boost = 0;
			lastMove = -1;
			
		}
	
		myTeam = team;
	}
	
	
	public int optimalMoveType() {
	
		if(currentHp == 100) {
			return -1;
		}
		
		if(currentHp > 50 && amountTaken < 33) {
			return -1;
		}
		
		if(currentHp < 50 && amountTaken < 49) {
			return 3;
		}
		
		if(currentHp < 50) {
			return 1;
		}
		
		
		
		
		return 1;
	}
		

	public void setStyle(int style) {
		// 1: offensive, 2:defensive, 3:random
		playStyle = style;
	}

	public void setMoves(int[][] m) {
		moves = m;
	}

	// Battle Methods
	public int oppTest(String path, String current) {
		// Tests opponent vs currentPoke, switches if currentPoke is weak to opponent
		Pokedex dex = new Pokedex(path);
		current = current.substring(0, current.indexOf("%"));
		int currentInt = Integer.parseInt(current);
		if(currentHp < currentInt) {
			
		}else {
			amountTaken = currentHp - currentInt;
		}
		
		currentHp = currentInt;
		System.out.println("Current Hp: " + currentHp);
		System.out.println("Amount Lost: " + amountTaken);
		// returns 0 if currentPoke is resilient to opponent
		if (dex.matchupCompare(myTeam[0], myTeam[1]) <= -1 || currentInt < 20) {
			return 0;
		}
			
		// returns location of pokemon that is most resilient to the opponent
		int bestMatchUp = 1;
		for (int pos = 2; pos < 7; pos++) {
			if (dex.matchupCompare(myTeam[0], myTeam[pos]) < dex.matchupCompare(myTeam[0],myTeam[bestMatchUp])) {
				
					bestMatchUp = pos;
					
			}
		}
		// returns 0(keeps currentPoke) if there isn't a better option
		 if(bestMatchUp==1) {bestMatchUp=0;}
		return bestMatchUp;
	}

	
	public int faintSwitch(String path) {
		// Tests opponent vs currentPoke, switches if currentPoke is weak to opponent
		Pokedex dex = new Pokedex(path);
		// returns 0 if currentPoke is resilient to opponent
		
			
		// returns location of pokemon that is most resilient to the opponent
		int bestMatchUp = 2;
		for (int pos = 2; pos < 7; pos++) {
			if (dex.matchupCompare(myTeam[0], myTeam[pos]) < dex.matchupCompare(myTeam[0],myTeam[bestMatchUp])) {
				
					bestMatchUp = pos;
				
			}
		}
		// returns 0(keeps currentPoke) if there isn't a better option
		// if(bestMatchUp==1) {bestMatchUp=0;}
		return bestMatchUp;
		
		
	}
	
	public int smartAttack(String path,String hp) {
		// chooses effective attack
		Pokedex dex = new Pokedex(path);
		int optimalMove = optimalMoveType();
		int bestMove = 1;
		int bestVal = -11;
		if(moves[0][0] == -1) {
			return -1;
		}
		
		
		
		
		if(optimalMove == 3) {
			for(int a = 0;a<4;a++) {
				int val = dex.fullCalc(myTeam[0])[moves[0][a]];
				if(moves[1][a]==3 && lastMove != 3) {
				 
					System.out.println("Protection Move: " + (a+1) );
					return a+1;
				
				 }else if(moves[1][a] == 2){
						if(val > bestVal) {
							System.out.println("Heal Move: " + (bestMove) ); 
							return a + 1;
						 }
						
					 }else if(moves[1][a] == 1) {
						 if(val > bestVal) {
							 bestMove = a+1;
							 bestVal = val;
						 }
					 }
					}
			System.out.println("Attacking Move: " + (bestMove) );
			return bestMove;
		}
		
		if(optimalMove == -1) {
			for(int a = 0;a<4;a++) {
				int val = dex.fullCalc(myTeam[0])[moves[0][a]];
				if(moves[1][a]==-1 && boost<3) {
				 
					System.out.println("Boost Move: " + (a+1) );
					boost++;
					return a+1;
				
				 }else if(moves[1][a] == 0 && canStatus){
						if(val > -1) {
							System.out.println("Status Move: " + (bestMove) ); 
							canStatus = false;
							return a + 1;
						 }
						
					 }else if(moves[1][a] == 1) {
						 if(val > bestVal) {
							 bestMove = a+1;
							 bestVal = val;
						 }
					 }
					}
			System.out.println("Attacking Move: " + (bestMove) );
			return bestMove;
		}
		
		for(int a = 0;a<4;a++) {
		int val = dex.fullCalc(myTeam[0])[moves[0][a]];
		if(moves[1][a] == 1) {
			 if(val > bestVal) {
				 bestMove = a+1;
				 bestVal = val;
			 }
		 }
		}
	System.out.println("Attacking Move: " + (bestMove) );


		
	
		return bestMove;
		
		
		
	}

}
