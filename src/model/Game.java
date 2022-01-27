package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * Clase que representa el juego de serpientes
 * y escaleras.
 * @author Juan Martin Garcia
 */
public class Game {

	/*
	 * Declaracion de variables pertinentes para la solucion del juego.
	 * 
	 * @author Juan Martin Garcia
	 */
	private int finalCell;
	private Random rnd;
	private boolean gameover;
	private int playerSteps;
	private int dice;

	/*
	 * Declaracion de listas con las casillas de escaleras y serpientes.
	 * 
	 * @author Juan Martin Garcia
	 */
	private List<Integer> SNAKES = new ArrayList<>();
	private List<Integer> STAIRS = new ArrayList<>();

	/*
	 * Constructor del juego cuando el usuario no elige la cantidad de casillas que
	 * quiere.
	 * 
	 * @author Juan Martin Garcia
	 */
	public Game() {
		this.gameover = false;
		this.rnd = new Random();
		this.playerSteps = 0;
		this.dice = 0;
		this.finalCell = 25;
		this.SNAKES = Arrays.asList(14, 19, 22, 24);
		this.STAIRS = Arrays.asList(3, 6, 9, 10);
	}

	/*
	 * Constructor del juego cuando el usuario elige la cantidad de casillas que
	 * quiere.
	 * 
	 * @author Juan Martin Garcia
	 */
	public Game(int cellsNumber) {
		this.finalCell = cellsNumber;
		this.gameover = false;
		this.rnd = new Random();
		this.playerSteps = 0;
		this.dice = 0;
		double squareRoot = Math.sqrt(Double.parseDouble(cellsNumber + ""));
		for (int i = 0; i < squareRoot; i++) {
			int number = rnd.nextInt(cellsNumber) + 1;
			if (!STAIRS.stream().anyMatch(n -> n == number)) {
				if (number % 2 == 0) {
					SNAKES.add(number + 1);
				} else {
					STAIRS.add(number);
				}
			}
		}
	}


	/*
	 * Metodo para empezar el juego.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void startGame() {
		while (!gameover) {
			throwDice();
			if (playerSteps + dice > finalCell) {
				moveBackwards();
			} else if (playerSteps + dice == finalCell) {
				finishGame();
			} else {
				moveForward();
			}
		}
	}

	/*
	 * Se lanza un numero aleatorio entre 1 y 6.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void throwDice() {
		dice = rnd.nextInt(6) + 1;
		System.out.println("Dado arroja " + dice);
	}

	/*
	 * Se retrocede la posicion del jugador por exceder el limite del tablero.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void moveBackwards() {
		int overflow = playerSteps + dice - finalCell;
		System.out.println("Jugador supera el cuadro " + finalCell + " y se devuelve " + overflow + " cuadros");
		playerSteps = finalCell - overflow;
		System.out.println("Jugador vuelve al cuadro " + playerSteps);
	}

	/*
	 * Se avanza la posicion del jugador, sumando la posicion actual con el numero
	 * del dado obtenido. Verificando si cae en una casilla con escalera o
	 * serpiente, donde avanza o retrocede casillas adicionales.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void moveForward() {
		playerSteps += dice;
		System.out.println("Jugador avanza a cuadro " + playerSteps);
		verifySnake();
		verifyStair();
	}

	/*
	 * Verifica si la posicion del usuario corresponde con una serpiente, si es
	 * cierto el usuario retrocede un numero aleatorio de casillas entre 1 y 10.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void verifyStair() {
		if (STAIRS.stream().anyMatch(number -> number == playerSteps)) {
			playerSteps += rnd.nextInt(11) + 1;
			System.out.println("Jugador sube por escalera al cuadro " + playerSteps);
		}
	}

	/*
	 * Verifica si la posicion del usuario corresponde con una escalera, si es
	 * cierto el usuario avanza un numero aleatorio de casillas entre 1 y 10.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void verifySnake() {
		if (SNAKES.stream().anyMatch(number -> number == playerSteps)) {
			playerSteps -= rnd.nextInt(11) + 1;
			System.out.println("Jugador desciende por serpiente al cuadro " + playerSteps);
		}
	}

	/*
	 * Metodo para terminar el juego.
	 * 
	 * @author Juan Martin Garcia
	 */
	public void finishGame() {
		gameover = true;
		System.out.println("Jugador alcanza exactamente el cuadro " + finalCell);
		System.out.println("Fin");
	}
	
	//Se crean getters y setters con el fin 
	//de hacer las pruebas correspondientes.
	public int getFinalCell() {
		return finalCell;
	}

	public Random getRnd() {
		return rnd;
	}

	public boolean isGameover() {
		return gameover;
	}

	public int getPlayerSteps() {
		return playerSteps;
	}

	public int getDice() {
		return dice;
	}

	public List<Integer> getSNAKES() {
		return SNAKES;
	}

	public List<Integer> getSTAIRS() {
		return STAIRS;
	}

	public void setFinalCell(int finalCell) {
		this.finalCell = finalCell;
	}
	
	public void setRnd(Random rnd) {
		this.rnd = rnd;
	}
	
	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}
	
	public void setPlayerSteps(int playerSteps) {
		this.playerSteps = playerSteps;
	}
	
	public void setDice(int dice) {
		this.dice = dice;
	}
	
	public void setSNAKES(List<Integer> sNAKES) {
		SNAKES = sNAKES;
	}
	
	public void setSTAIRS(List<Integer> sTAIRS) {
		STAIRS = sTAIRS;
	}
}
