#ifndef __RozkladLiczby_h__
#define __RozkladLiczby_h__

#include<exception>
#include<string>

using namespace std;


class RangeException : public exception {
    private:
        string s;
    public:
        RangeException(string ss) throw();
        virtual ~RangeException() throw();
        virtual const char* what() const throw();
};


class RozkladLiczby {
    private:
        int *sito;
        int *rm;

    public:
        int dl;
        int* czynnikiPierwsze(int m) throw (RangeException);
        RozkladLiczby(int n);
        ~RozkladLiczby();
};

#endif
