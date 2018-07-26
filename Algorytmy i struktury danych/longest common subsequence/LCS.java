import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sebastian on 2017-05-19.
 */
public class LCS {

    public static int[][] LCSlength(String s1, String s2, List<Integer> stats){
        int m = s1.length();
        int n = s2.length();

        int[][] c = new int[m+1][n+1];
        int numberOfComparisons = 0;

        for (int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                numberOfComparisons += 1;
                if(s1.charAt(i) == (s2.charAt(j))){
                    c[i+1][j+1] = c[i][j] + 1;
                }else{
                    c[i+1][j+1] = Math.max(c[i+1][j],c[i][j+1]);
                }
            }
        }
        stats.set(1, c[m][n]);
        stats.set(0, numberOfComparisons);
        return c;
    }

    public String getLCS(int[][]c, String s1, String s2){
        String sLCS ="";
        int n = s1.length();
        int m = s2.length();
        for(int i = n, j = m; i!= 0 && j !=0;){
            if(c[i][j] == c[i-1][j]){
                i--;
            }else if(c[i][j] == c[i][j-1]){
                j--;
            }else {
                if( s1.charAt(i-1) == s2.charAt(j-1));
                sLCS += s1.charAt(i-1);
                i--;
                j--;
            }
        }
        String reverse = new StringBuffer(sLCS).reverse().toString();
        return reverse;
    }

    public void stats(){
        List<Integer> stats = new ArrayList<Integer>();
        stats.add(0);
        stats.add(0);

        Random rand = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int j = 0;
        for(int k = 0; k < 14; k++){
            String x = "";
            for (int i = 1; i <= 20+j; i++){
                x += alphabet.charAt(rand.nextInt(alphabet.length()));
            }
            String y = "";
            for (int i = 1; i <= 25+j; i++){
                y += alphabet.charAt(rand.nextInt(alphabet.length()));
            }
            LCSlength(x,y,stats);
            j += 1000;
            System.out.println(k + "\t" + x.length() + "\t" + y.length() + "\t" + (x.length()+y.length()) + "\t" + stats.get(0));
        }
    }

    public static void main(String[] args){
        LCS lcs = new LCS();
        int option = Integer.parseInt(args[0]);
        if(option == 1) {
            lcs.stats();
        }else {

            String a = "ABCBDAB";
            String b = "BDCABA";
            List<Integer> stats = new ArrayList<Integer>();

            stats.add((int) 0);
            stats.add((int) 0);

            System.out.print("Najdłuższy wspolny podciąg: " + lcs.getLCS(lcs.LCSlength(a, b, stats), a, b) + "\nLiczba porownań: " + stats.get(0) + "\nDługość LCS: " + stats.get(1));
        }
    }
}
