#include<iostream>
#include<string>
#include<sstream>

#include "RozkladLiczby.h"

using namespace std;

int maxArgv (int argc, char* argv[] ){
    int max = 0, next = 0;
    for (int i=1; i < argc; i++) {
        try {
            istringstream iss(argv[i]);
            iss.exceptions(ios::failbit);
            iss >> next;
        }
        catch (ios::failure e) {
            //cout << argv[i] << ": nie jest liczba" << endl;
            continue;
        }

        if (next > max)
            max = next;
    }
    return max;
}


int main(int argc, char* argv[]) {
        int max = maxArgv(argc, argv);

        RozkladLiczby *rozklad;
        try {
            rozklad = new RozkladLiczby(max);
        }
        catch (bad_alloc& b) {
            cout << max << ": za duza liczba, nie utworzono sita" << endl;
            return 0;
        }

        for (int j = 1; j < argc; j++) {
            int m;
            try {
                istringstream iss(argv[j]);
                iss.exceptions(ios::failbit);
                iss >> m;
            }
            catch (ios::failure e) {
                cout << argv[j] << ": nie jest liczba" << endl;
                continue;
            }

            int *tab;
            try {
                tab = rozklad->czynnikiPierwsze(m);
            }
            catch (RangeException d){
                cout << m << ": podano liczbe ujemna" << endl;
                continue;
            }

            int licz = 1;
            for (int i = 0; i < rozklad->dl; i++){
                cout << tab[i];

                if ((i < rozklad->dl - 1) && (tab[i+1] != tab[i])){
                    cout << " * ";
                    continue;
                } else {
                    licz = 1;
                    while ((i < rozklad->dl - 1) && (tab[i+1] == tab[i])){
                        licz++;
                        i++;
                    }
                    if (licz > 1)
                        cout << "^" << licz;
                }

                if (i < rozklad->dl - 1)
                    cout << " * ";
            }

            cout << " = " << m << endl;
        }
    return 0;
}
