public class Test {	
    public static int maxArgs (String args[]){
        int max = 0, next = 0;
        for (int i=0; i < args.length; i++) {
            try {
                next = Integer.parseInt(args[i]);
            }
            catch (NumberFormatException a) {
                //System.out.println(args[i] + ": nie jest liczba");
                continue;
            }

            if (next > max)
                max = next;
        }
        return max;
    }

    public static void main (String args[]) {

        int max = maxArgs(args);

        RozkladLiczby rozklad;
        try {
            rozklad = new RozkladLiczby(max);
        }
        catch (OutOfMemoryError b) {
            System.out.println(max + ": za duza liczba, nie utworzono sita");
            return;
        }

        for (int j = 0; j < args.length; j++) {
            int m;
            try {
                m = Integer.parseInt(args[j]);
            }
            catch (NumberFormatException c) {
                System.out.println(args[j] + ": nie jest liczba");
                continue;
            }

            int tab[];
            try {
                tab = rozklad.czynnikiPierwsze(m);
            }
            catch (RangeException d){
                System.out.println(m + ": podano liczbe ujemna");
                continue;
            }

            int licz = 1;
            for (int i = 0; i < rozklad.dl; i++){
                System.out.print(tab[i]);

                if ((i < rozklad.dl - 1) && (tab[i+1] != tab[i])){
                    System.out.print(" * ");
                    continue;
                } else {
                    licz = 1;
                    while ((i < rozklad.dl - 1) && (tab[i+1] == tab[i])){
                        licz++;
                        i++;
                    }
                    if (licz > 1)
                        System.out.print("^" + licz);
                }

                if (i < rozklad.dl - 1)
                    System.out.print(" * ");
            }

            System.out.println(" = " + m);
        }
    }
}
