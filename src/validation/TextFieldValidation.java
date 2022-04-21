/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.regex.Matcher;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author hilel
 */
public class TextFieldValidation {
    
    public static boolean isTextFieldTypeString(TextField tf){
        boolean b = false;
        if(tf.getText().matches("^[a-zA-ZÀ-Ÿ, ]*$") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
    public static boolean isTextFieldTypeString(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldTypeString(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isTextFieldTypeNumber(TextField tf){
        boolean b = false;
        if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
    public static boolean isTextFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldTypeNumber(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
     public boolean testEmail(String email) {
	Matcher matcher = Constantes.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
	return matcher.find();
    }

    public boolean testPassword(String password) {
	Matcher matcher = Constantes.VALID_PASSWORD_REGEX.matcher(password);
	return matcher.find();
    }
     public void information_Box(String title, String message) {
	Alert dg = new Alert(Alert.AlertType.INFORMATION);
	dg.setTitle(title);
	dg.setContentText(message);
	dg.show();
    }
}
