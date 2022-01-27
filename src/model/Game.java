package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {

	/*
	 * Declaracion de variables pertinentes
	 * para la solucion del juego.
	 */
	private int finalCell;
	private Random dice;
	private boolean gameover;
	private int playerSteps;
	private int turn;

	/*
	 * Declaracion de listas con las casillas
	 * de escaleras y serpientes.
	 */
	private List<Integer> SNAKES = new ArrayList<>();
	private List<Integer> STAIRS = new ArrayList<>();

	/*
	 * Constructor del juego cuando el usuario 
	 * no elige la cantidad de casillas que quiere.
	 */
	public Game() {
		this.gameover = false;
		this.dice = new Random();
		this.playerSteps = 0;
		this.turn = 0;
		this.finalCell = 25;
		this.SNAKES = Arrays.asList(14, 19, 22, 24);
		this.STAIRS = Arrays.asList(3, 6, 9, 10);
	}
	
	/*
	 * Constructor del juego cuando el usuario
	 * elige la cantidad de casillas que quiere.
	 */
	public Game(int cellsNumber) {
		this.finalCell = cellsNumber;
		this.gameover = false;
		this.dice = new Random();
		this.playerSteps = 0;
		this.turn = 0;
		double squareRoot = Math.sqrt(Double.parseDouble(cellsNumber + ""));
		for(int i = 0; i < squareRoot; i++) {
			int number = dice.nextInt(cellsNumber) + 1;
			if(number%2==0) {				
				SNAKES.add(number + 1);
			} else {
				STAIRS.add(number);				
			}
		}
	}

	/*
	 * Metodo para empezar el juego
	 */
	public void startGame() {
		while (!gameover) {
			throwDice();
			if(playerSteps + turn > finalCell) {
				moveBackwards();
			} else if(playerSteps + turn == finalCell) {
				finishGame();
			} else {				
				moveForwards();
			}
		}
	}
	
	/*
	 * Se lanza un numero aleatorio entre 1 y 6
	 */
	private void throwDice() {
		turn = dice.nextInt(6) + 1;
		System.out.println("Dado arroja " + turn);
	}
	
	/*
	 * Se retrocede la posicion del jugador por exceder
	 * el limite del tablero.
	 */
	private void moveBackwards() {
		int overflow = playerSteps + turn - finalCell;
		System.out.println("Jugador supera el cuadro " + finalCell + " y se devuelve " + overflow + " cuadros");
		playerSteps = finalCell - overflow;
		System.out.println("Jugador vuelve al cuadro " + playerSteps);
	}
	
	/*
	 * Se avanza la posicion del jugador, sumando
	 * la posicion actual con el numero del dado
	 * obtenido. Verificando si cae en una casilla
	 * con escalera o serpiente, donde avanza o
	 * retrocede casillas adicionales.
	 */
	private void moveForwards() {
		playerSteps += turn;
		System.out.println("Jugador avanza a cuadro " + playerSteps);
		verifySnake();
		verifyStair();
	}
	
	/*
	 * Verifica si la posicion del usuario corresponde con
	 * una serpiente, si es cierto el usuario retrocede un
	 * numero aleatorio de casillas entre 1 y 10.
	 */
	private void verifySnake() {
		if (STAIRS.stream().anyMatch(number -> number == playerSteps)) {
			playerSteps += dice.nextInt(11) + 1;
			System.out.println("Jugador sube por escalera al cuadro " + playerSteps);
		}
	}
	
	/*
	 * Verifica si la posicion del usuario corresponde con
	 * una escalera, si es cierto el usuario avanza un
	 * numero aleatorio de casillas entre 1 y 10.
	 */
	private void verifyStair() {
		if(SNAKES.stream().anyMatch(number -> number == playerSteps)) {
			playerSteps -= dice.nextInt(11) + 1;
			System.out.println("Jugador desciende por serpiente al cuadro " + playerSteps);
		}
	}
	
	/*
	 * Metodo para terminar el juego.
	 */
	private void finishGame() {
		gameover = true;
		System.out.println("Jugador alcanza exactamente el cuadro " + finalCell);
		System.out.println("Fin");
	}
}
