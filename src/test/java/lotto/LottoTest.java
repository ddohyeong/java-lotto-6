package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Lotto;

class LottoTest {
	@DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
	@Test
	void createLottoByOverSize() {
		assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
	@Test
	void createLottoByDuplicatedNumber() {
		assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 입력시 숫자와 쉼표 사이에 공백이 있으면 제거")
	@Test
	public void shouldRemoveSpacesBetweenNumbersAndCommas() {
		//given
		String inputData = "1,2,3, 4,  5,6";
		String expected = "1,2,3,4,5,6";
		Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

		// when
		String lottoNumbers = lotto.removeSpacesBetweenNumbersAndCommas(inputData);

		//then
		Assertions.assertThat(lottoNumbers).isEqualTo(expected);
	}
}
