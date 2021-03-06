package gui;

import fileHandling.FRestaurant;
import fileHandling.FUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import logic.*;


public class SignupscreenController {
    @FXML
    TextField username;
    @FXML
    TextField name;
    @FXML
    PasswordField password;
    @FXML
    RadioButton customer;
    @FXML
    RadioButton waiter;
    @FXML
    RadioButton cook;
    @FXML
    Label verify;
    @FXML
    Label verifyusername;
    private String role = "";
    @FXML
    TextField pinCode;
    @FXML
    Label forEmployees;
    private String employeesPinCode = "1072000";

    public void setCustomer(ActionEvent event) {
        waiter.setSelected(false);
        cook.setSelected(false);
        role = "Client";
        pinCode.setVisible(false);
        forEmployees.setVisible(false);
    }

    public void setWaiter(ActionEvent event) {
        customer.setSelected(false);
        cook.setSelected(false);
        role = "Waiter";
        pinCode.setVisible(true);
        forEmployees.setVisible(true);
    }

    public void setCook(ActionEvent event) {
        waiter.setSelected(false);
        customer.setSelected(false);
        role = "Cooker";
        pinCode.setVisible(true);
        forEmployees.setVisible(true);
    }

    public void setSignup(ActionEvent event) throws JAXBException {
        GUIfunctions guifunctions = new GUIfunctions();
        User user = new User();
        JAXBContext jaxbContext = JAXBContext.newInstance(FRestaurant.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        FRestaurant fRestaurant = (FRestaurant) unmarshaller.unmarshal(new File("inputData.xml"));
        boolean flag = false;
        if (!customer.isSelected() && !waiter.isSelected() && !cook.isSelected()) {
            flag = true;
            verify.setVisible(true);
        }
        for (FUser fUser : fRestaurant.getFUsers().getFUsers()) {
            if (fUser.getUsername().equalsIgnoreCase(username.getText())) {
                flag = true;
                verifyusername.setVisible(true);
            }
        }

        if (role.equalsIgnoreCase("Client") && password.getText().length() >= 5 && username.getText().length() >= 3 && name.getText().length() >= 3 && !role.equals("") && !flag) {
            user.signUp(name.getText(), username.getText(), password.getText(), role);
            guifunctions.switchScene(event, "loginscreen.fxml");
        } else if (pinCode.getText().equalsIgnoreCase(employeesPinCode) && password.getText().length() >= 5 && username.getText().length() >= 3 && name.getText().length() >= 3 && !role.equals("") && !flag) {
            user.signUp(name.getText(), username.getText(), password.getText(), role);
            guifunctions.switchScene(event, "loginscreen.fxml");
        } else if (!flag)
            verify.setVisible(true);
    }

}




