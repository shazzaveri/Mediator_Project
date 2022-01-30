package ViewModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import Model.Team;
import Contoller.JavaFxController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TeamDetailVM extends AnchorPane implements Initializable, PropertyChangeListener {
    JavaFxController fxJavaController = new JavaFxController();
    private static final String fxmlPath = "/teameditorviewmodel.fxml";
    private Team team;

    @FXML
    private TextField teamName;

    @FXML
    private TextField teamScore;

    @FXML
    private TextField lastUpdated;


    public TeamDetailVM(Team team) {
        this.team = team;


        try {
 
            FXMLLoader loader = fxJavaController.setLoader(fxmlPath, this);
            GridPane fxmlNode = loader.load();

            this.getChildren().add(fxmlNode);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public void save() {
        fxJavaController.performingSave(team, teamName.getText(), teamScore.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamName.setText(team.getTeamName());
        teamScore.setText("" + team.getScore());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        lastUpdated.setText(team.getLastUpdated().format(formatter));
        lastUpdated.setEditable(false);

        teamScore.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if(!newPropertyValue) {
                    teamScore.setText(fxJavaController.formatScore(teamScore.getText())); } } });
        
        teamName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if(!newPropertyValue) {
                    if (teamName.getText().isEmpty())
                        teamName.setText("No Name Provided"); } }});
    }

    public String getReturnString(){

        return teamName.getText();
    }

    public String getReturnScore(){

        return teamScore.getText();
    }

    public String getReturnDate(){
        return lastUpdated.getText();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
    
    public void updateDetails(String string, String scoreString, String Date){
        if (team.isValidName(string)){
            if (string.isEmpty())
                string = "No Name Provided";
            teamName.setText(string); }
        int scoreValue = Integer.parseInt(scoreString.replaceAll(",", ""));
        if (team.isValidScore(scoreValue, scoreString)) {
            teamScore.setText(scoreString); }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        lastUpdated.setText(dateTime.format(formatter));
    }
}
