public class RozkladLiczby {
    private int sito[];
    private int rm[];
    public int dl = 1;

    public int[] czynnikiPierwsze (int m) throws RangeException {
        if (m < 0)
            throw new RangeException();

        int n = m;
        
        dl = 1;
        //int tmp[] = new int[30];
        for (int i = 0; i < 30 && m !=1; i++) {
            if (sito[m-2] == 0) {
                //tmp[i] = m;
                m = 1;
            }
            else {
                //tmp[i] = sito[m-2];
                m = m / sito[m-2];
                dl++;
            }
        }

        int rm[] = new int[dl];
        for (int i = 0; i < dl; i++) {
            if (sito[n-2] == 0) {
                rm[i] = n;
                n = 1;
            }
            else {
                rm[i] = sito[n-2];
                n = n / sito[n-2];
            }
        }
        
        /*int rm[] = new int[dl];
        for (int i=0; i < dl; i++) {
            rm[i] = tmp[i];
        }*/

        return rm;
    }

    RozkladLiczby (int n) {
        sito = new int[n-1];
        for (int i = 0; i*i <= n; i++) {
            if (sito[i] == 0) {
                for(int j = (i+i+2); j < n-1; j = j+i+2) {
                    if (sito[j] == 0)
                        sito[j] = i+2;
                }
            }
        }
    }
}
