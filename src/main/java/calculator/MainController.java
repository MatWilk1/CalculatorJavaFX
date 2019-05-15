package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

	private Double score = null;
	private Double element1 = null;
	private Double element2 = null;
	private String lastClick = "";
	private String operation = "";

	@FXML
	private Label labelScore;

	@FXML
	private Label labelPattern;

	@FXML
	void initialize() {
		labelScore.setText("0");
		labelPattern.setText("");
	}

	@FXML
	public void onActionDigit(ActionEvent e) {
		Button b = (Button) e.getSource();

		if (labelScore.getText().equals("Nie dzielimy przez 0")) {
			return;
		} else if (lastClick.equals("") || lastClick.equals("operation")) {
			labelScore.setText("");
			labelScore.setText(labelScore.getText() + b.getText());
		} else if (lastClick.equals("equel")) {
			score = null;
			element1 = null;
			element2 = null;
			operation = "";
			labelScore.setText("");
			labelScore.setText(labelScore.getText() + b.getText());
		} else {
			labelScore.setText(labelScore.getText() + b.getText());
		}

		System.out.println("digit");
		lastClick = "digit";
	}

	@FXML
	public void onActionDot(ActionEvent e) {
		Button b = (Button) e.getSource();

		if (labelScore.getText().contains(".") || labelScore.getText().equals("Nie dzielimy przez 0")) {
			return;
		} else if (lastClick.equals("") || lastClick.equals("operation") || lastClick.equals("equel")) {
			labelScore.setText("0");
		}

		labelScore.setText(labelScore.getText() + b.getText());

		System.out.println("dot");
		lastClick = "dot";

	}

	@FXML
	public void onActionOperation(ActionEvent e) {
		Button b = (Button) e.getSource();

		if (labelScore.getText().equals("Nie dzielimy przez 0")) {
			return;
		} else if (lastClick.equals("operation")) {
			operation = b.getText();
			labelPattern.setText(labelPattern.getText().substring(0, labelPattern.getText().length() - 3) + " " + operation + " ");
		}
		else if (operation.equals("") || lastClick.equals("equel")) {
			operation = b.getText();
			element1 = Double.parseDouble(labelScore.getText());
			labelPattern.setText(integerIfPossible(element1.toString()) + " " + operation + " ");
		}
		else if (score == null) {
			element2 = Double.parseDouble(labelScore.getText());
			calculate(element2);
			operation = b.getText();
			labelPattern.setText(labelPattern.getText() + integerIfPossible(element2.toString()) + " " + operation + " ");
		}
		else {
			element1 = score;
			element2 = Double.parseDouble(labelScore.getText());
			calculate(element2);
			operation = b.getText();
			labelPattern.setText(labelPattern.getText() + integerIfPossible(element2.toString()) + " " + operation + " ");
		}

		System.out.println("operacja");
		System.out.println("e1 =" + element1);
		System.out.println("e2 =" + element2);
		lastClick = "operation";

	}

	@FXML
	public void onActionEquel() {

		if (lastClick.equals("operation") || labelScore.getText().equals("Nie dzielimy przez 0")) {
			return;
		}
		else if ((lastClick.equals("digit") || lastClick.equals("dot") || lastClick.equals("negative")) && score == null) {
			element2 = Double.parseDouble(labelScore.getText());
			calculate(element2);
			labelPattern.setText("");
		}
		else if (lastClick.equals("digit") || lastClick.equals("dot") || lastClick.equals("negative")) {
			element1 = score;
			element2 = Double.parseDouble(labelScore.getText());
			calculate(element2);
			labelPattern.setText("");
		}

		else if (lastClick.equals("equel")) {
			element1 = score;
			calculate(element2);
			labelPattern.setText("");
		}

		System.out.println("równa");
		System.out.println("operacja " + operation);
		System.out.println("e1 =" + element1);
		System.out.println("e2 =" + element2);
		lastClick = "equel";
	}

	@FXML
	public void onActionClean() {
		score = null;
		element1 = null;
		element2 = null;
		lastClick = "";
		operation = "";
		labelScore.setText("0");
		labelPattern.setText("");
	}

	@FXML
	public void onActionNegative() {

		if (labelScore.getText().equals("Nie dzielimy przez 0") || labelScore.getText().equals("0")) {
			return;
		}

		Double negative = Double.parseDouble(labelScore.getText()) * -1;
		labelScore.setText(integerIfPossible(negative.toString()));
		lastClick = "negative";
		System.out.println("negative");
		System.out.println("e1 =" + element1);
		System.out.println("e2 =" + element2);
	}

	// Makes all calculations
	public void calculate(Double element2) {

		if (operation.equals("+")) {
			score = element1 + element2;
			labelScore.setText(integerIfPossible(score.toString()));
		} else if (operation.equals("-")) {
			score = element1 - element2;
			labelScore.setText(integerIfPossible(score.toString()));
		} else if (operation.equals("*")) {
			score = element1 * element2;
			labelScore.setText(integerIfPossible(score.toString()));
		} else if (operation.equals("/") && element2 != 0) {
			score = element1 / element2;
			labelScore.setText(integerIfPossible(score.toString()));
		} else if (operation.equals("/") && element2 == 0) {
			labelScore.setText("Nie dzielimy przez 0");
		}

		System.out.println(score);
	}

	// Checks if number is integer or double to set proper form on label
	public String integerIfPossible(String s){
		Double number = Double.parseDouble(s);
		if(number%1 == 0){
			return number.toString().substring(0, number.toString().length() - 2);
		}
		else{
			return number.toString();
		}
	}

}
