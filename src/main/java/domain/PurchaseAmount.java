package domain;

import exception.IllegalArgumentExceptionMessage;
import exception.NumberFormatExceptionMessage;

public class PurchaseAmount {
	private final int amount;
	private final int lottoPurchaseCount;

	public PurchaseAmount(String amount) {
		validateIsDigit(amount);
		validateIsEmpty(amount);
		int convertedAmount = convertStringToInt(amount);
		validateIsPositive(convertedAmount);
		validateIsThousandUnit(convertedAmount);
		this.amount = convertedAmount;
		this.lottoPurchaseCount = calculateLottoPurchaseCount(convertedAmount);
	}

	public int getAmount() {
		return amount;
	}

	public int getLottoPurchaseCount() {
		return lottoPurchaseCount;
	}

	private int calculateLottoPurchaseCount(int amount) {
		return amount / 1000;
	}

	private int convertStringToInt(String amount) {
		return Integer.parseInt(amount);
	}

	private void validateIsDigit(String amount) {
		if (!isDigit(amount)) {
			NumberFormatExceptionMessage.PURCHASE_AMOUNT_NUMBERS_ONLY.throwException();
		}
	}

	private void validateIsThousandUnit(int amount) {
		if (!(amount % 1000 == 0)) {
			IllegalArgumentExceptionMessage.PURCHASE_AMOUNT_THOUSAND_UNIT.throwException();
		}
	}

	private boolean isDigit(String amount) {
		return amount.chars()
				.allMatch(Character::isDigit);
	}

	private void validateIsPositive(int amount) {
		if (!(amount > 0)) {
			IllegalArgumentExceptionMessage.PURCHASE_AMOUNT_NOT_EXCEED_ZERO.throwException();
		}
	}

	private void validateIsEmpty(String amount) {
		if (amount.isBlank()) {
			IllegalArgumentExceptionMessage.PURCHASE_AMOUNT_NUMERIC.throwException();
		}
	}
}
