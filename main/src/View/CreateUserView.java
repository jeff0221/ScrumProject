package View;

import Controllers.UserCreateController;
import Models.SessionModel;
import Models.User;
import Models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by peterzohdy on 23/02/2016.
 */
public class CreateUserView
{
    private static CreateUserView uCV;

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField addressField;
    private TextField phoneNumberField;

    private DatePicker birthdayPicker;

    private RadioButton employeeRadioBtn;
    private RadioButton customerRadioBtn;
    private RadioButton adminRadioBtn;

    public static ObservableList<User> getData()
    {
        ObservableList<User> list = FXCollections.observableArrayList();
        list.addAll(UserModel.getInstance().getUserList());
        return list;
    }

    public BorderPane userCreateView(Stage stage)
    {


        UserCreateController ucc = new UserCreateController();
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();

        SessionModel session = SessionModel.getInstance();
        borderPane = new BorderPane();
        gridPane = new GridPane();
        borderPane.setCenter(gridPane);

        borderPane.setStyle("-fx-background-color: white");

        borderPane.setStyle("-fx-background-color: linear-gradient(white 80%, #DD5100 100%)");

        //Add image
        ImageView imv = new ImageView();
        Image image2 = new Image(LoginView.class.getResourceAsStream("adventure.png"));
        imv.setImage(image2);
        HBox pictureRegion = new HBox();
        pictureRegion.setPadding(new Insets(0, 0, 20, 0));
        pictureRegion.getChildren().add(imv);
        gridPane.add(pictureRegion, 1,0);

        gridPane.setPadding(new Insets(40, 0, 0, 60));
        gridPane.setHgap(20);
        gridPane.setVgap(10);

        Label firstNameLbl = new Label("Firstname:");
        gridPane.add(firstNameLbl, 0, 1);

        firstNameField = new TextField();
        gridPane.add(firstNameField, 1, 1);


        Label lastNameLbl = new Label("Lastname:");
        gridPane.add(lastNameLbl, 0, 2);


        lastNameField = new TextField();
        gridPane.add(lastNameField,1, 2);


        Label emailLbl = new Label("Email:");
        gridPane.add(emailLbl,0,3);


        emailField = new TextField();
        gridPane.add(emailField,1,3);


        Label addressLbl = new Label("Address:");
        gridPane.add(addressLbl, 0, 4);

        addressField = new TextField();
        gridPane.add(addressField, 1, 4);

        Label phoneNumberLbl = new Label("Phone nr.");
        gridPane.add(phoneNumberLbl, 0, 5);

        phoneNumberField = new TextField();
        gridPane.add(phoneNumberField, 1, 5);
        phoneNumberField.setPromptText("12345678");

        Label birthdayLbl = new Label("Birthday:");
        gridPane.add(birthdayLbl, 0, 6);

        birthdayPicker = new DatePicker();
        gridPane.add(birthdayPicker, 1, 6);

        ToggleGroup group = new ToggleGroup();

        employeeRadioBtn = new RadioButton("Employee");
        employeeRadioBtn.setToggleGroup(group);

        gridPane.add(employeeRadioBtn, 1, 8);

        customerRadioBtn = new RadioButton("Costumer");
        customerRadioBtn.setToggleGroup(group);

        gridPane.add(customerRadioBtn, 1, 9);

        adminRadioBtn = new RadioButton("Admin");
        adminRadioBtn.setToggleGroup(group);

        gridPane.add(adminRadioBtn, 1, 10);

        if(!session.isGuest()){
        employeeRadioBtn.setVisible(true);
        customerRadioBtn.setVisible(true);
        adminRadioBtn.setVisible(true);
        }
        Button submitBtn = new Button("Submit");
        gridPane.add(submitBtn, 1, 12);
        submitBtn.setPrefWidth(200);

        submitBtn.setOnAction(e ->
        {
                new UserCreateController().createUser(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    emailField.getText(),
                    addressField.getText(),
                    phoneNumberField.getText(),
                    birthdayPicker.getEditor().getText(),
                    getRadioBtnValues(session.getIsGuest())//getUserNumber()
                );
            if(stage != null)
            {
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.close();
            }
        });

        return borderPane;
    }

    private int getRadioBtnValues(boolean isGuest)
    {
        int valueReturn = -1;
        if(!isGuest)
        {
            if (employeeRadioBtn.isSelected()) {
                valueReturn = 1;
            }
            else if (adminRadioBtn.isSelected()) {
                valueReturn = 2;

            }
            else if (customerRadioBtn.isSelected()) {
                valueReturn = 0;

            }
            else
            {
                System.out.println("Error");
            }
        }
        else
        {
            valueReturn = 0;
        }
        return valueReturn;
    }
}
