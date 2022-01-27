package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static Game gameboard;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduzca el numero total de casillas que desea para su tablero");
		System.out.println("Si no introduce nada se iniciara con uno de 25 casillas");
		
		try {
			String number = reader.readLine();
			if(number.isEmpty()) {				
				gameboard = new Game();
			} else {
				try {
					Integer.parseInt(number);
				} catch (NumberFormatException e) {
					System.out.println("No se ha introducido un numero.");
				}
				int n = Integer.parseInt(number);
				gameboard = new Game(n);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gameboard.startGame();
	}

}
