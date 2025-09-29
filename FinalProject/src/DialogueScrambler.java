
public class DialogueScrambler {
	private static String cypher = "!@#$%^&*=+~5";
	
	public static String Scramble(String input) {
		String result = "";
		int currentVal = 0;
		boolean intFound = false;
		
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i))) {
				intFound = true;
				currentVal = 10 * currentVal + Integer.parseInt(input.substring(i, i + 1));
				continue;
			}
			else if (intFound) {
				intFound = false;
				String convertedInt = "";
				do {
					int modVal = currentVal % 12;
					convertedInt = cypher.charAt(modVal) + convertedInt;
					currentVal = currentVal / 12;
				} while (currentVal > 0);
				result += convertedInt;
			}
			
			result += input.charAt(i);
		}
		
		
		return result;
	}
}
