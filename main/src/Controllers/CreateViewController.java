package Controllers;

import Models.ActivityModel;
import javafx.scene.control.Alert;

/**
 * Created by Nikolaj on 25-02-2016.
 */
public class CreateViewController
{
    public static void submitButton(String name, int minAge, String startTime,String endTime, String description)
    {
        ActivityModel.addActivity(name, minAge, startTime, endTime, description);
    }

    public static boolean validation(String name, String minAge, String startTime,String endTime, String description)
    {
     boolean check = false;


        for(char c : minAge.toCharArray())
        {
            if(Character.isDigit(c) && name.length() > 0 && description.length() > 0)
            {
                check = true;
            }
        }

        if(!check)
        {
            setActivityAlert("Fejl","Fejl 40");
        }

        return check;
    }

    public static void setActivityAlert(String titleText,String headerText)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setContentText("Please fill out all the fields...");
        alert.setHeaderText(headerText);
        alert.show();
    }
}
