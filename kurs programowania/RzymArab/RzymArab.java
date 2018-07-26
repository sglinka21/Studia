public class RzymArab {
	private static String[] rzymskie = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
	private static int[] arabskie = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
	private static int dl = 13;
	
	public static int rzym2arab (String rzym) throws RzymArabException {
		int i = dl-1, j = 0, wynik = 0, dlugosc = rzym.length();
		
		while ((i >= 0) && (j < dlugosc)) {
			if (rzym.charAt(j) == rzymskie[i].charAt(0) && (i % 2 == 0)) {
				wynik += arabskie[i];
				j++;
			}
			else if ((j < dlugosc-1) && (rzym.charAt(j) == rzymskie[i].charAt(0)) && (rzym.charAt(j+1) == rzymskie[i].charAt(1))) {
				wynik += arabskie[i];
				j += 2;
			}
			else {
				i--;
			}
		}
		
		if (wynik == 0 || wynik >= 4000) {
			throw new RzymArabException(": nie jest liczba rzymska");
		}
		return wynik;
	}
	
	public static String arab2rzym (int arab) throws RzymArabException {
		if (arab < 1 || arab > 3999) {
			throw new RzymArabException(": liczba spoza zakresu");
		}
		
		String wynik = "";
		
		for (int i = dl-1; i >= 0; i--) {
			if (arabskie[i] <= arab) {
				wynik += rzymskie[i];
				arab -= arabskie[i++];
			}
		}
		
		return wynik;
	}
}