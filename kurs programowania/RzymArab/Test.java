public class Test {
    public static void main (String args[]) {
    	for (int i=0; i < args.length; i++) {
	   		int a;
	   		try {
	   			a = Integer.parseInt(args[i]);
	   		}
	   		catch (NumberFormatException ex) {
	   			String tmpS;
	   			int tmpI;
	   			
	   			try {
	   				tmpI = RzymArab.rzym2arab(args[i]);
	   			}
	   			catch (RzymArabException e) {
	   				System.out.println(args[i] + e.getMessage());
	   				continue;
	   			}	   			
	   			
	   			try {
	   				tmpS = RzymArab.arab2rzym(tmpI);
	   			}
	   			catch (RzymArabException e) {
	   				System.out.println(args[i] + e.getMessage());
	   				continue;
	   			}
	   			
	   			if (tmpS.equals(args[i]))
	   				System.out.println(args[i] + " = " + tmpI);
	   			else 
	   				System.out.println(args[i] + ": nie jest liczba rzymska");
	   			continue;
	   		}
	   		
	   		try {
	   			System.out.println(args[i] + " = " + RzymArab.arab2rzym(a));
	   		}
	   		catch (RzymArabException b) {
	   			System.out.println(args[i] + b.getMessage());
	   		}
    	}
   	}
}
