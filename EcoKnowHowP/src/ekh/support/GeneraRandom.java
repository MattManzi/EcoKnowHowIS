package ekh.support;

import java.util.Random;

public class GeneraRandom {
	public String getRandom() {
		Random r = new Random();
		int n = r.nextInt(999999);

		return String.format("%06d", n);
	}
}
