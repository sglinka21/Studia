import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sebastian on 2017-05-18.
 */
public class EditDistance {

    public static int matchChar(char ch1, char ch2){
        if(ch1 == ch2)
            return 0;
        else
            return 1;
    }

    public static int min(int a, int b, int c){
        return Math.min(Math.min(a,b),c);
    }
    public static int editDistance(String s1, String s2, List<Long> list){
        int m = s1.length();
        int n = s2.length();
        long ctr = 0;

        if(n == 0)
            return n;

        if(m == 0)
            return m;

        int[][] solution = new int[m+1][n+1];

        for(int i = 0; i < m; i++){
            solution[i][0] = i;
        }

        for(int j = 0; j < n; j++){
            solution[0][j] = j;
        }

        for(int j = 1; j <= n; j++){
            for(int i =1; i <= m; i++){
                solution[i][j] = min(solution[i-1][j]+1, solution[i][j-1]+1, solution[i-1][j-1]+ matchChar(s1.charAt(i-1),s2.charAt(j-1)));
                ctr +=1;
            }
        }
        list.set(0, ctr);
        return solution[m][n];
    }



    public static void stats(){
        List<Long> stats = new ArrayList<Long>();
        stats.add((long) 0);

        Random rand = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int j = 0;
        for(int k=0; k<=14; k++){
            String x = "";
            for(int i = 1; i <= 20+j; i++){
                x += alphabet.charAt(rand.nextInt(alphabet.length()));
            }
            String y = "";
            for(int i=1; i<=25+j; i++){
                y += alphabet.charAt(rand.nextInt(alphabet.length()));
            }
            int editDistance = editDistance(x, y, stats);
            j += 1000;
            System.out.println(k + "\t" + x.length() + "\t" + y.length() + "\t" + (x.length()+y.length()) + "\t" + stats.get(0) + "\r");
            //System.out.println(stats.get(0));
            //System.out.println((x.length()+y.length()));

        }
    }


    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        List<Long> list = new ArrayList<Long>();
        list.add((long) 0);

        int option = Integer.parseInt(args[0]);
        if (option == 1) {
            stats();

        } else {

            String s1 = "POLYNOMIAL";
            String s2 = "EXPONENTIAL";

            System.out.println("Minimum Edit Distance: " + ed.editDistance(s1, s2, list) + " Liczba porownan: " + list.get(0));

        }
    }
}
