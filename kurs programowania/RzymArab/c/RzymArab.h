#ifndef __RzymArab_h__
#define __RzymArab_h__

#include<exception>
#include<string>

using namespace std;


class RzymArabException : public exception {
    private:
        string s;
    public:
        RzymArabException(string ss) throw();
        virtual ~RzymArabException() throw();
        virtual const char* what() const throw();
};


class RzymArab {
    private:
        static string rzymskie[13];
        static int arabskie[13];

    public:
        static int rzym2arab (string rzym) throw (RzymArabException);
        static string arab2rzym (int arab) throw (RzymArabException);
};

#endif
