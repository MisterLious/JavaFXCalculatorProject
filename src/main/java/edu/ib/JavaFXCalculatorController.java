package edu.ib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class JavaFXCalculatorController {

    Calculation calculation = new Calculation();
    boolean toClear = false;
    boolean seriaObliczen = false;
    boolean pressed = false;

    @FXML
    private TextField display;

    @FXML
    private Button btnClr;

    @FXML
    private Button btnSign;

    @FXML
    private Button btnPercent;

    @FXML
    private Button btnDivide;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private Button btn9;

    @FXML
    private Button btnMultiply;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btn0;

    @FXML
    private Button btnDot;

    @FXML
    private Button btnEquals;

    @FXML
    void OnClick(ActionEvent event) {
        Button button = (Button)event.getSource();
        String label = button.getText();
        DecimalFormat df = new DecimalFormat("#.#########");

        try{
            switch (label) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (display.getText().equals("0")) {
                        display.setText(label);
                    } else {
                        display.setText(display.getText() + label);
                    }
                    pressed = true;
                }
                case "0" -> {
                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (Double.parseDouble(display.getText()) % 1 != 0)
                        display.setText(display.getText() + label);
                    else if (display.getText().toCharArray()[0] != '0')
                        display.setText(display.getText() + label);
                    else if (display.getText().toCharArray()[1] == '.')
                        display.setText(display.getText() + label);
                    pressed = true;
                }
                case "." -> {
                    if (toClear) {
                        display.setText("0");
                        toClear = false;
                    }
                    if (Double.parseDouble(display.getText()) % 1 == 0)
                        display.setText(display.getText() + label);
                    pressed = true;
                }
                case "C" -> {
                    calculation.setNum1(0);
                    calculation.setNum2(0);
                    calculation.setOperation(null);
                    seriaObliczen = false;
                    toClear = false;
                    pressed = false;
                    display.setText("0");
                }
                case "+/-" -> display.setText(df.format(-1 * Double.parseDouble(display.getText())));
                case "%" -> display.setText(df.format(Double.parseDouble(display.getText()) / 100));
                case "+", "-", "*", "/" -> {
                    if (!seriaObliczen) {
                        calculation.setOperation(label);
                        calculation.setNum1(Double.parseDouble(display.getText()));
                    } else {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText()), seriaObliczen)));
                        calculation.setOperation(label);
                    }
                    toClear = true;
                    seriaObliczen = true;
                }
                case "=" -> {
                    toClear = true;
                    seriaObliczen = false;
                    if (!pressed) {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText()), false)));
                    } else {
                        display.setText(df.format(calculation.calculate(Double.parseDouble(display.getText()), seriaObliczen)));
                    }
                }
            }
        }catch (Exception e){

        }
    }

}
