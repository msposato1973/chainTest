package chainTest.demo.main;

import java.util.Scanner;

import chainTest.demo.CoinChange;
import chainTest.demo.error.SensibleException;

public class DemoMainClass {

	public static void main(String[] args) {
		// creates an object of Scanner
	    Scanner input = new Scanner(System.in);

	    System.out.print("Enter your Example Test Data: ");
	    System.out.print("£2 , 253 , 5.23 ");
	    
	 // takes input from the keyboard
	    String coinSelection = input.nextLine();
	    
	 // prints the name
	    System.out.println("this is your  input:  " + coinSelection);
	    
	    CoinChange coinChange = new CoinChange(coinSelection);
	    try {
			
	    	coinChange.fewestCoins();
			
			printSolution(coinChange, coinSelection);
			
		} catch (SensibleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closes the scanner
		    input.close();
		}
	}

	
	public static void printSolution(CoinChange coinChange, String coins) {

		System.out.println("|Input| Output                  |");
		System.out.print("|" + coins + "  | ");
		coinChange.getSolution().stream().forEach((item) -> {
			System.out.print(item.getNumTime() + ",");

		});

		System.out.println("");
	}
}
