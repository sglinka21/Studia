#include<string>
#include<exception>

#include "RzymArab.h"

using namespace std;

RzymArabException::RzymArabException(string ss) throw() : s(ss) {}

RzymArabException::~RzymArabException() throw() {}

const char*RzymArabException::what() const throw() { return s.c_str(); }

string RzymArab::rzymskie[13] = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
int RzymArab::arabskie[13] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

int RzymArab::rzym2arab (string rzym) throw (RzymArabException) {
    int i = 12, j = 0, wynik = 0, dlugosc = rzym.length();

    while ((i >= 0) && (j < dlugosc)) {
        if (rzym[j] == rzymskie[i][0] && (i % 2 == 0)) {
            wynik += arabskie[i];
            j++;
        }
        else if ((j < dlugosc-1) && (rzym[j] == rzymskie[i][0]) && (rzym[j+1] == rzymskie[i][1])) {
            wynik += arabskie[i];
            j += 2;
        }
        else {
            i--;
        }
    }

    if (wynik == 0 || wynik > 3999) {
        throw RzymArabException(": nie jest liczba rzymska");
    }

    return wynik;
}

string RzymArab::arab2rzym (int arab) throw (RzymArabException) {
		if (arab < 1 || arab > 3999) {
			throw RzymArabException(": liczba spoza zakresu");
		}

		string wynik = "";

		for (int i = 12; i >= 0; i--) {
			if (arabskie[i] <= arab) {
				wynik += rzymskie[i];
				arab -= arabskie[i++];
			}
		}

		return wynik;
	}
