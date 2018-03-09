package pokebot;

public class Battle {
	private int playStyle = 1;
	private String[] myTeam = new String[7];
	private String currentPoke = "";
	private String opponent = "";
	private int[] moves = new int[4];

	public Battle(int style, String[] team) {
		playStyle = style;
		myTeam = team;
	}

	public void setTeam(String[] team) {
		myTeam = team;
		opponent = myTeam[0];
		currentPoke = myTeam[1];

	}

	public void setStyle(int style) {
		// 1: offensive, 2:defensive, 3:random
		playStyle = style;
	}

	public void setMoves(int[] m) {
		moves = m;
	}

	// Battle Methods
	public int oppTest(String path) {
		// Tests opponent vs currentPoke, switches if currentPoke is weak to opponent
		Pokedex reference = new Pokedex(path);
		// returns 0 if currentPoke is resilient to opponent
		if (reference.matchupCompare(opponent, currentPoke) <= 0)
			return 0;
		// returns location of pokemon that is most resilient to the opponent
		int bestMatchUp = 1;
		for (int pos = 1; pos < 7; pos++) {
			if (reference.matchupCompare(opponent, myTeam[pos]) < reference.matchupCompare(opponent,
					myTeam[bestMatchUp])) {
				System.out.println("Working" + bestMatchUp);
				bestMatchUp = pos;
			}
		}
		// returns 0(keeps currentPoke) if there isn't a better option
		// if(bestMatchUp==1) {bestMatchUp=0;}
		return bestMatchUp;
	}

	public int smartAttack(String path) {
		// chooses effective attack
		Pokedex dex = new Pokedex(path);
		int[] weak = dex.fullCalc(myTeam[0]);
		for (int a = 0; a < 18; a++) {
			for (int b = 0; b < 4; b++) {
				if (weak[a] > 1 && a == moves[b] + 1) {
					return b + 1;
				}
			}

		}
		for (int a = 0; a < 18; a++) {
			for (int b = 0; b < 4; b++) {
				if (weak[a] > 0 && a == moves[b] + 1) {
					return b + 1;
				}
			}
		}
		for (int a = 0; a < 18; a++) {
			for (int b = 0; b < 4; b++) {
				if (weak[a] > -1 && a == moves[b] + 1) {
					return b + 1;
				}
			}
		}
		for (int a = 0; a < 18; a++) {
			for (int b = 0; b < 4; b++) {
				if (weak[a] > -2 && a == moves[b] + 1) {
					return b + 1;
				}
			}
		}

		return 2;
	}

}
