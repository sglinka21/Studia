#include<iostream>
#include<string>
#include<sstream>

#include "RzymArab.h"

using namespace std;


int main(int argc, char* argv[]) {
    for (int i=1; i < argc; i++) {
        int a;
        try {
            istringstream iss(argv[i]);
            iss.exceptions(ios::failbit);
            iss >> a;
        }
        catch (ios::failure e) {
            string tmpS;
            int tmpI;

            try {
                tmpI = RzymArab::rzym2arab(argv[i]);
            }
            catch (RzymArabException e) {
                cout << argv[i] << e.what() << endl;
                continue;
            }

            try {
                tmpS = RzymArab::arab2rzym(tmpI);
            }
            catch (RzymArabException e) {
                cout << argv[i] << e.what() << endl;
                continue;
            }

            if (tmpS == argv[i])
                cout << argv[i] << " = " << tmpI << endl;
            else
                cout << argv[i] << ": nie jest liczba rzymska" << endl;
            continue;
        }

        try {
            cout << argv[i] << " = " << RzymArab::arab2rzym(a) << endl;
        }
        catch (RzymArabException b) {
            cout << argv[i] << b.what() << endl;
        }
    }
    return 0;
}
