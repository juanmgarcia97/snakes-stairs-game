package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Game;

class GameTest {
	
	private Game gameboard;
	
	void setup1() {
		gameboard = new Game();
	}
	
	void setup2() {
		gameboard = new Game(40);
	}

	@Test
	void testThrowDice() {
		setup1();
		int diceBefore = gameboard.getDice();
		gameboard.throwDice();
		int diceAfter = gameboard.getDice();
		assertNotEquals(diceBefore, diceAfter);
		assertTrue(diceBefore < diceAfter);
		assertTrue(diceAfter > 0);
		assertTrue(diceAfter <= 6);
	}
	
	@Test
	void testMoveBackwards() {
		setup1();
		gameboard.setPlayerSteps(23);
		gameboard.setDice(5);
		int playerSteps = gameboard.getPlayerSteps();
		int dice = gameboard.getDice();
		int finalCell = gameboard.getFinalCell();
		assertTrue(playerSteps + dice > finalCell);
		gameboard.moveBackwards();
		assertEquals(22, gameboard.getPlayerSteps());
	}
	
	@Test
	void testMoveForward() {
		setup1();
		gameboard.setPlayerSteps(6);
		gameboard.setDice(2);
		int playerSteps = gameboard.getPlayerSteps();
		int dice = gameboard.getDice();
		int finalCell = gameboard.getFinalCell();
		gameboard.moveForward();
		assertTrue(playerSteps + dice < finalCell);
		assertEquals(8, gameboard.getPlayerSteps());
	}
	
	@Test
	void testVerifySnake() {
		setup1();
		gameboard.setPlayerSteps(14);
		gameboard.verifySnake();
		int newStep = gameboard.getPlayerSteps();
		assertNotEquals(14, newStep);
		assertTrue(14 > newStep);
	}
	
	@Test
	void testVerifyStair() {
		setup1();
		gameboard.setPlayerSteps(6);
		gameboard.verifyStair();
		int newStep = gameboard.getPlayerSteps();
		assertNotEquals(6, newStep);
		assertTrue(6 < newStep);
	}
	
	@Test
	void testFinishGame() {
		setup1();
		gameboard.setPlayerSteps(23);
		gameboard.setDice(2);
		int playerSteps = gameboard.getPlayerSteps();
		int dice = gameboard.getDice();
		int finalCell = gameboard.getFinalCell();
		assertFalse(gameboard.isGameover());
		gameboard.moveForward();
		assertTrue(playerSteps + dice == finalCell);
		assertEquals(25, gameboard.getPlayerSteps());
		gameboard.finishGame();
		assertTrue(gameboard.isGameover());
	}
	
	@Test
	void testGameWithOtherSize() {
		setup2();
		assertNotEquals(25, gameboard.getFinalCell());
		
		List<Integer> defaultSnakes = Arrays.asList(14, 19, 22, 24);
		List<Integer> defaultStairs= Arrays.asList(3, 6, 9, 10);
		
		assertNotEquals(defaultSnakes, gameboard.getSNAKES());
		assertNotEquals(defaultStairs, gameboard.getSTAIRS());
	}

}
