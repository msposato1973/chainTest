package chainTest.demo;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

 

@RunWith(Parameterized.class)
public class CoinChangeParamTest {

	   private String inputCurrency;
	   private Boolean expectedResult;
	   private CoinChange coinChange;
	   
	   @Before
	   public void initialize() {
		   coinChange = new CoinChange();
	   }
	   
	   public CoinChangeParamTest(String inputNumber, Boolean expectedResult) {
	      this.inputCurrency = inputNumber;
	      this.expectedResult = expectedResult;
	   }
	   
	   @Parameterized.Parameters
	   public static Collection validNumbers() {
	      return Arrays.asList(new Object[][] {
	         { "5.23", true },
	         { "523", true },
	         { "£2", true }
	      });
	   }
	   
	   @Test
	   public void testPrimeNumberChecker() throws ParseException {
		   coinChange.setCoins(inputCurrency);
	      System.out.println("Parameterized Number is : " + inputCurrency);
	      assertEquals(expectedResult, coinChange.isValidationCurrency());
	   }
}