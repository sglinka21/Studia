#include<string>
#include<exception>

#include "RozkladLiczby.h"

using namespace std;

RangeException::RangeException(string ss) throw() : s(ss) {}

RangeException::~RangeException() throw() {}

const char*RangeException::what() const throw() { return s.c_str(); }


int* RozkladLiczby::czynnikiPierwsze (int m) throw (RangeException) {
    if (m < 0)
        throw RangeException(": podano liczbe ujemna");

    dl = 1;
    int *tmp;
    tmp = new int[30];

    for (int i = 0; i < 30 && m !=1; i++) {
        if (sito[m-2] == 0) {
            tmp[i] = m;
            m = 1;
        }
        else {
            tmp[i] = sito[m-2];
            m = m / sito[m-2];
            dl++;
        }
    }

    //int *rm;
    rm = new int[dl];
    for (int i=0; i < dl; i++) {
        rm[i] = tmp[i];
    }

    delete [] tmp;

    return rm;
}

RozkladLiczby::RozkladLiczby(int n) {
    sito = new int[n-1];
    for (int i = 0; i < n-1; i++)
        sito[i] = 0;

    for (int i = 0; i*i <= n; i++) {
        if (sito[i] == 0) {
            for(int j = (2*i +2); j < n-1; j = j+i+2) {
                if (sito[j] == 0)
                    sito[j] = i+2;
            }
        }
    }
}


RozkladLiczby::~RozkladLiczby() {
    delete [] sito;
    delete [] rm;
}

