package Controllers;

import Models.*;
import Models.Activities;
import Models.ActivityModel;
import Models.Booking;
import Models.BookingModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lordni on 2/26/16.
 */
public class BookingViewController {

    public static ObservableList<Booking> setBookings()
    {
        ObservableList<Booking> data = FXCollections.observableArrayList();

        if (SessionModel.getInstance().getLoggedInUser() == null || SessionModel.getInstance().getLoggedInUser().getRole() != UserRoleEnum.USER)
        {
            data.setAll(BookingModel.getInstance().getBookings());
        }
        else if (SessionModel.getInstance().getLoggedInUser() != null)
        {
            data.setAll(BookingModel.getInstance().getBookings());
            System.out.println(data.size());
            for(int i = 0; i < data.size(); i++)
            {
                if (data.get(i).getUserID() != SessionModel.getInstance().getLoggedInUser().getID())
                {
                    data.remove(i);
                    i--;
                }
            }
        }

        return data;
    }

    public void btnClickedToShowBookings(TableView<Booking> tableView)
    {
        TableColumn idColumns = new TableColumn("Id");
        idColumns.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn actIDColumn = new TableColumn("Activity");
        actIDColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));

        TableColumn userIdColumn = new TableColumn("User");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn participantsColumn = new TableColumn("Participants");
        participantsColumn.setCellValueFactory(new PropertyValueFactory<>("participants"));

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn startTimeColumn = new TableColumn("Beginning");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn endTimeColumn = new TableColumn("End");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        tableView.getColumns().addAll(idColumns, actIDColumn, userIdColumn, participantsColumn,
                dateColumn, startTimeColumn, endTimeColumn);

        tableView.setItems(setBookings());
    }

    public static void search(TableView<Booking> bookingsTableView, TextField name, TextField user, TextField date)
    {
        List<Booking> bookings = BookingModel.getInstance().getBookings();
        List<Booking> bookingSearch = new ArrayList<>();

        String searchActivityName = name.getText();
        String searchUserName = user.getText();

        for (Booking b : bookings)

        {

            if (searchActivityName.length() != 0 && searchUserName.length() != 0)
            {
                if (b.getUserName().toLowerCase().contains(searchUserName.toLowerCase()) &&
                        b.getActivityName().toLowerCase().contains(searchActivityName.toLowerCase()))
                {
                    bookingSearch.add(b);
                }
            }
            else if(searchActivityName.length()==0 && searchUserName.length()!=0)
            {
                if(b.getUserName().toLowerCase().contains(searchUserName.toLowerCase()))
                {
                    bookingSearch.add(b);
                }
            }

            else if(searchActivityName.length()!=0 && searchUserName.length()==0)
            {
                if(b.getActivityName().toLowerCase().contains(searchActivityName.toLowerCase()))
                {
                    bookingSearch.add(b);
                }
            }

            else
            {
                bookingSearch.add(b);
            }
        }

        ObservableList<Booking> bookingList = FXCollections.observableArrayList(bookingSearch);
        bookingsTableView.setItems(bookingList);
    }
    public static boolean setConfirmAlert(Booking booking)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Slet booking");
        alert.setHeaderText("Er du sikker p? at du vil slette "+booking.getActivityName()+" bookingen for " +booking.getUserName()+ "?");
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