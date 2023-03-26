import java.util.Scanner;

public class IntervalloOrario {

	public static boolean isNumeric(String str) {
		try {  
	    	Integer.parseInt(str);
		    return true;
	  	} catch(NumberFormatException e) {  
	    	return false;
		} 
	}

	public static boolean isValid(String time) {
		if (isNumeric(time) && ((time.length() > 2) && (time.length() < 5))) {
			return true;
		} else {
			return false;
		}
	}

	public static String getHour(String time) {
		if (time.length() == 3) {
			return time.substring(0, 1);
		} else {
			if(time.charAt(0) == '0') {
				return time.substring(1, 2);
			}
			else {
				return time.substring(0, 2);
			}
		}
	}

	public static String getMinute(String time) {
		if (time.length() == 3) {
			return time.substring(1);
		} else {
			return time.substring(2);
		}
	}

	public static void main(String[] args) {
		// costanti
		final int MAX_MINUTE = 60;	
		final int MAX_HOUR	 = 24;

		// scanner per lettura valori
		Scanner console = new Scanner(System.in);

		// lettura primo orario
		System.out.print("Inserisci il primo orario (HHMM): ");
		String strTime1 = console.nextLine();

		// lettura secondo orario
		System.out.print("Inserisci il secondo orario (HHMM): ");
		String strTime2 = console.nextLine();

		// elaborazione
		if(isValid(strTime1) && isValid(strTime2)) {
			// primo orario
			int hour1   = Integer.parseInt(getHour(strTime1));
			int minute1 = Integer.parseInt(getMinute(strTime1));

			// secondo orario
			int hour2   = Integer.parseInt(getHour(strTime2));
			int minute2 = Integer.parseInt(getMinute(strTime2));

			// calcolo tempi
			int diffHour   	= hour1   - hour2;
			int diffMinute 	= minute1 - minute2;

			if (diffHour < 0 && diffMinute < 0) {				//   -  - 
				diffHour   = Math.abs(diffHour);
				diffMinute = Math.abs(diffMinute);
			} else if (diffHour > 0 && diffMinute > 0) {		//   +  + 
				diffHour   = (MAX_HOUR - diffHour) - 1;
				diffMinute = MAX_MINUTE - diffMinute;
			} else if (diffHour > 0 && diffMinute < 0 )	{		//   +  - 
				diffHour   = MAX_HOUR - diffHour;
				diffMinute = Math.abs(diffMinute);
			} else if (diffHour < 0 && diffMinute > 0) {		//   -  + 
				diffHour   = Math.abs(diffHour) - 1;
				diffMinute = MAX_MINUTE - diffMinute;
			} else if (diffHour > 0 && diffMinute == 0) {		//   +  0
				diffHour   = MAX_HOUR - diffHour;
			} else if (diffHour < 0 && diffMinute == 0) {		//   -  0
				diffHour   = Math.abs(diffHour);
			} else if (diffHour == 0 && diffMinute < 0) {		//   0  -
				diffMinute = Math.abs(diffMinute);
			} else if (diffHour == 0 && diffMinute > 0) {		//   0  +
				diffHour   = MAX_HOUR - 1;
				diffMinute = MAX_MINUTE - diffMinute;
			} else if (diffHour == 24 && diffMinute == 0) { 	//  24  0
				diffHour = 0;
			} else if (diffHour == -24 && diffMinute == 0) { 	// -24  0
				diffHour = MAX_HOUR;
			}

			System.out.println("\nTempo trascorso: " + diffHour + " ore e " + diffMinute + " minuti");
		} else {
			System.out.println("\nL\'orario inserito non rispetta il formato richiesto");
		}
	}
}