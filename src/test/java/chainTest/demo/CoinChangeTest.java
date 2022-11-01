package chainTest.demo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import chainTest.demo.error.SensibleException;

public class CoinChangeTest {

	@Test
	public void test_isPaundCurrency() {
		String coins = "£2";
		Assert.assertTrue(new CoinChange(coins).isPaundCurrency());
	}

	@Test
	public void test_isFloat() throws ParseException {
		String coins = "5.23";
		Assert.assertTrue(new CoinChange(coins).isFloat());
	}

	@Test(expected = ParseException.class)
	public void test_isFloat_ex() throws ParseException {
		String coins = "-5.23";

		new CoinChange(coins).isFloat();
	}

	@Test
	public void test_isNoPaundNoDollarCurrency() {
		String coins = "5.23";

		Assert.assertTrue(new CoinChange(coins).isPaundCurrency());
	}

	@Test(expected = SensibleException.class)
	public void test_isDollarCurrency_ex() throws SensibleException {
		String coins = "$23";

		new CoinChange(coins).isPaundCurrencyPlus();
	}
	
	@Test(expected = SensibleException.class)
	public void test_isEurCurrency_ex() throws SensibleException {
		String coins = "€23";

		new CoinChange(coins).isPaundCurrencyPlus();
	}

	@Test
	public void test_minCoins() throws ParseException, SensibleException {
		List<Integer> setPounds = new ArrayList<Integer>(List.of(2, 1));

		String coins = "5";
		int intCurrency = Integer.parseInt(coins);

		CoinChange coinChange = new CoinChange(coins);
		coinChange.minCoins(setPounds, intCurrency, "£");

		Assert.assertTrue(!coinChange.getSolution().isEmpty());
		Assert.assertEquals(2, coinChange.getSolution().get(0).getValue(), 0);
		Assert.assertEquals(1, coinChange.getSolution().get(1).getValue(), 0);

		printSolution(coinChange, coins);
	}

	@Test
	public void test_minCoins2() throws ParseException, SensibleException {
		List<Integer> setPounds = new ArrayList<Integer>(List.of(2, 1));
		List<Integer> setPenny = new ArrayList<Integer>(List.of(50, 20, 10, 5, 2, 1));

		String coins = "5.3";
		CoinChange coinChange = new CoinChange(coins);

		List<String> list = Stream.of(coins.split("\\.")).map(String::trim).collect(Collectors.toList());

		coinChange.minCoins(setPounds, Integer.parseInt(list.get(0)), "£");
		coinChange.minCoins(setPenny, Integer.parseInt(list.get(1)) * 10, "p");

		Assert.assertTrue(!coinChange.getSolution().isEmpty());
		Assert.assertEquals(2, coinChange.getSolution().get(0).getValue(), 0);
		Assert.assertEquals(1, coinChange.getSolution().get(1).getValue(), 0);

		printSolution(coinChange, coins);
	}

	@Test
	public void test_fewestCoins() throws ParseException, SensibleException {

		String coins = "5.3";
		CoinChange coinChange = new CoinChange(coins);
		coinChange.fewestCoins();

		Assert.assertTrue(!coinChange.getSolution().isEmpty());
		Assert.assertTrue(coinChange.getSolution().size() == 4);

		printSolution(coinChange, coins);
	}

	@Test(expected = SensibleException.class)
	public void test_fewestCoins_EX() throws ParseException, SensibleException {

		String coins = "$5.3";
		CoinChange coinChange = new CoinChange(coins);
		coinChange.fewestCoins();

	}

	@Test
	public void test_fewestCoins_253() throws ParseException, SensibleException {

		String coins = "253";
		CoinChange coinChange = new CoinChange(coins);
		coinChange.fewestCoins();

		Assert.assertTrue(!coinChange.getSolution().isEmpty());
		Assert.assertTrue(coinChange.getSolution().size() == 4);

		printSolution(coinChange, coins);
	}

	@Test(expected = SensibleException.class)
	public void test_fewestCoins253DollarEx() throws ParseException, SensibleException {

		String coins = "$253";
		CoinChange coinChange = new CoinChange(coins);
		coinChange.fewestCoins();

	}

	private void printSolution(CoinChange coinChange, String coins) {

		System.out.println("|Input| Output                  |");
		System.out.print("|" + coins + "  | ");
		coinChange.getSolution().stream().forEach((item) -> {
			System.out.print(item.getNumTime() + ",");

		});

		System.out.println("");
	}

}