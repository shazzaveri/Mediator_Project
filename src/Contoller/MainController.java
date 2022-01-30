package Contoller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Team;
import ViewModel.ListViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainController {

    public DateTimeFormatter dateTimeFormat(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return formatter;
    }

    public void createTeams(ArrayList<Team> teams){
        LocalDateTime dateTime = LocalDateTime.now(); // Gets the current date and tim
        Team temp = new Team("Los Liberaces", dateTime, 1560);
        teams.add(temp);
        temp = new Team("Bobbys Butchers", dateTime, 1745);
        teams.add(temp);
        temp = new Team("The Shoggoths", dateTime, 590);
        teams.add(temp);
        temp = new Team("Hounds of Tindalos", dateTime, 755);
        teams.add(temp);
        temp = new Team("Glort the Fist", dateTime, 1220);
        teams.add(temp);
    }

    public void setStage(ListViewModel viewModel, Stage stage){
        Scene scene = new Scene((Parent) viewModel);
        stage.setScene(scene);
        stage.setTitle("CS 4473 Assignment 4");
        stage.show();
    }
}
