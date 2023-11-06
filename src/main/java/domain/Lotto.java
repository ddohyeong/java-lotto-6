package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
	private final List<Integer> numbers;

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		validateDuplicateNumber(numbers);
		this.numbers = numbers;
	}

	public Lotto(String numbers) {
		List<String> convertedNumbers = convertStringToList(removeSpacesBetweenNumbersAndCommas(numbers));
		validateIsDigit(convertedNumbers);
		this.numbers = convertStringListToIntegerList(convertedNumbers);
	}

	private List<Integer> convertStringListToIntegerList(List<String> numbers) {
		return numbers.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	private void validateIsDigit(List<String> numbers) {
		if (!isDigit(numbers)) {
			throw new NumberFormatException("[ERROR] 숫자만 입력하세요");
		}
	}

	private boolean isDigit(List<String> numbers) {
		return numbers.stream()
				.allMatch(number -> number.chars().allMatch(Character::isDigit));
	}

	private List<String> convertStringToList(String numbers) {
		return List.of(numbers.split(","));
	}

	public String removeSpacesBetweenNumbersAndCommas(String numbers) {
		return numbers.replace(" ", "");
	}

	private void validate(List<Integer> numbers) {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException("[ERROR] 로또번호는 6개를 쉼표로 구분해서 입력해주세요");
		}
	}

	private void validateDuplicateNumber(List<Integer> numbers) {
		if (numbers.size() != numbers.stream().distinct().count()) {
			throw new IllegalArgumentException("[ERROR] 서로 다른 숫자를 입력하세요");
		}
	}
}
