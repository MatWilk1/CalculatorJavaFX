package calculator;

public class Model {
	
	Double score1;
	
	public String calculate(Double element1, Double element2, String operation) {

		if (operation.equals("+")) {
			score1 = element1 + element2;
			Controller.setScore(score1);
			return integerIfPossible(score1.toString());
		} else if (operation.equals("-")) {
			score1 = element1 - element2;
			Controller.setScore(score1);
			return integerIfPossible(score1.toString());
		} else if (operation.equals("*")) {
			score1 = element1 * element2;
			Controller.setScore(score1);
			return integerIfPossible(score1.toString());
		} else if (operation.equals("/") && element2 != 0) {
			score1 = element1 / element2;
			Controller.setScore(score1);
			return integerIfPossible(score1.toString());
		} else if (operation.equals("/") && element2 == 0) {
			return "Nie dzielimy przez 0";
		}
		
		return "";

	}
	
	// Checks if number is integer or double to set proper form on label
	public String integerIfPossible(String s){
		Double number = Double.parseDouble(s);
		if(number % 1 == 0){
			return number.toString().substring(0, number.toString().length() - 2);
		}
		else{
			return number.toString();
		}
	}
	
	

}
