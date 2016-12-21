package Controllers;

import Models.Activities;
import Models.ActivityModel;
import Models.User;
import Models.UserModel;
import View.ActivityView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by AlexanderFalk on 23/02/2016.
 */
public class ActivityViewController
{
    public ObservableList<Activities> setActivities()
    {
        ObservableList<Activities> data = FXCollections.observableArrayList();
        data.setAll(ActivityModel.getInstance().getActivities());
        return data;
    }

    public void btnClickedToShowActivities(TableView<Activities> tableView)
    {

        TableColumn idColumns = new TableColumn("Id");
        idColumns.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn nameColumns = new TableColumn("Name");
        nameColumns.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn minAgeColumns = new TableColumn("Minimum age");
        minAgeColumns.setCellValueFactory(new PropertyValueFactory<>("minAge"));

        TableColumn startTimeColumns = new TableColumn("Start Time");
        startTimeColumns.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn endTimeColumns = new TableColumn("End Time");
        endTimeColumns.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        TableColumn descriptionColumns = new TableColumn("Description");
        descriptionColumns.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(idColumns, nameColumns, minAgeColumns,
                                                      startTimeColumns, endTimeColumns, descriptionColumns);

        tableView.setItems(setActivities());
    }

    public static void search(TableView<Activities> activitiesTableView, TextField name, TextField minAge)
    {
        List<Activities> arrActivity = ActivityModel.getInstance().getActivities();
        List<Activities> arrActivitySearch = new ArrayList<>();

        String seachName = name.getText();
        int intAge = 0;
        try
        {
            if (minAge.getText().length() != 0)
            {
                intAge = Integer.parseInt(minAge.getText());
            }
        }
        catch (Exception e)
        {
            System.out.println("ikke en int");
        }
        for (Activities a : arrActivity)
        {
            if (seachName.length() != 0 && minAge.getText().length() != 0)
            {
                if (a.getName().toLowerCase().contains(seachName.toLowerCase()) && a.getMinAge() >= intAge)
                {
                    arrActivitySearch.add(a);
                }
            }
            else if (seachName.length() == 0 && minAge.getText().length() != 0)
            {
                if (a.getMinAge() >= intAge)
                {
                    arrActivitySearch.add(a);
                }
            }
            else if (seachName.length() != 0 && minAge.getText().length() == 0)
            {
                if (a.getName().toLowerCase().contains(seachName.toLowerCase()))
                {
                    arrActivitySearch.add(a);
                }
            }
            else
            {
                arrActivitySearch.add(a);
            }
        }

        System.out.println(arrActivity.size());
        System.out.println(arrActivitySearch.size());
        ObservableList<Activities> activitiesList = FXCollections.observableArrayList(arrActivitySearch);
        activitiesTableView.setItems(activitiesList);
    }
    public static boolean setConfirmAlert(String name)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Slet aktivitet");
        alert.setHeaderText("Er du sikker på at du vil slette " + name +"?");
        alert.setContentText("Den vil blive slettet permanent");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}