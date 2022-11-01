package chainTest.demo;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import chainTest.demo.error.SensibleException;
import chainTest.demo.model.Pair;

public class CoinChange {

	private String coins;

	private List<Integer> setPounds = new ArrayList<Integer>(List.of(2, 1));
	private List<Integer> setPenny = new ArrayList<Integer>(List.of(50, 20, 10, 5, 2, 1));
	private List<Pair> solution = new ArrayList<Pair>();
	
	private final String POUND = "£";
	private final String PENNY = "p";

	public List<Pair> getSolution() {
		return solution;
	}

	public void setSolution(List<Pair> solution) {
		this.solution = solution;
	}

	public String getCoins() {
		return coins;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public CoinChange() {
	}

	public CoinChange(String localCoins) {
		setCoins(localCoins);
	}

	public boolean isPaundCurrency() {
		return (this.coins.startsWith("£") || !this.coins.startsWith("$"));
	}

	public boolean isValidationCurrency() throws ParseException {
		return (isPaundCurrency() || isFloat());
	}

	public boolean isNullValue() throws SensibleException {
		return (this.coins.equals("0") || this.coins.equals("0.00"));
	}

	public boolean isFloat() throws ParseException {
		if (NumberFormat.getInstance().parse(getCoins()).floatValue() > -1)
			return true;
		else
			throw new ParseException(getCoins(), 0);

	}

	public Float getFloatValue() throws ParseException {
		return NumberFormat.getInstance().parse(getCoins()).floatValue();

	}

	public boolean isPaundCurrencyPlus() throws SensibleException {
		String xdigit = getCoins().substring(0, 1);
		if (!xdigit.startsWith("£") && (!Character.isDigit(xdigit.charAt(0)))) {
			throw new SensibleException();
		} else {
			return false;
		}
	}

	public void fewestCoins() throws ParseException, SensibleException {

		if (isNullValue())
			throw new SensibleException();

		isPaundCurrencyPlus();

		if (!isValidationCurrency())
			throw new SensibleException();

		if (getCoins().contains(".")) {
			List<String> items = Stream.of(getCoins().split("\\.")).map(String::trim).collect(Collectors.toList());

			minCoins(setPounds, Integer.parseInt(items.get(0)), POUND);
			
			
			if(items.get(1).length()>1)
				minCoins(setPenny, Integer.parseInt(items.get(1)), PENNY);
			else 
				minCoins(setPenny, Integer.parseInt(items.get(1)) * 10, PENNY);

		} else {

			if (getCoins().length() == 1) {
				minCoins(setPounds, getFloatValue().intValue(), POUND);
			} else if (getCoins().length() == 3) {

				char[] chCoin = getCoins().toCharArray();

				minCoins(setPounds, Integer.parseInt(String.valueOf(chCoin[0])), POUND);

				minCoins(setPenny, Integer.parseInt(String.valueOf(chCoin[1]).concat(String.valueOf(chCoin[2]))), PENNY);
			}
		}

	}

	public void minCoins(List<Integer> list, int intCurrency, String symbol) {

		int val = intCurrency;
		int rest = 0;

		for (int value : list) {
			rest = val % value;
			if (rest >= 0) {
				if ((val / value) != 0) {
					String row = (symbol.equals("£")) ? String.format("%d £%d", (val / value), value)
							: String.format("%d  %dp", (val / value), value);
					this.solution.add(new Pair(value, row));
					val = rest;
				}
			}
		}
	}
}
