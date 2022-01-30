package Contoller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import Model.Team;
import Model.TeamException;
import ViewModel.TeamListCellViewModel;

import java.time.LocalDateTime;

public class JavaFxController {

    //TextField teamScore;
    //TextField teamName;
    String returnString = "";

    public <T> FXMLLoader setLoader(String fxmlPath, T element) {
        FXMLLoader loader = new FXMLLoader(TeamListCellViewModel.class.getResource(fxmlPath));
        loader.setController(element);
        return loader;
    }

    public void performingSave(Team team, String teamName, String teamScore){
        int tempScore;

        if ( !(team.getTeamName().equalsIgnoreCase(teamName))  ||  !(team.getScore() == Integer.valueOf(teamScore))  ){
            LocalDateTime dateTime = LocalDateTime.now(); // Gets the current date and tim
            team.setLastUpdated(dateTime);
        }

        if (!team.isValidName(teamName)){
            nameAlert(); } else{
            if (teamName.isEmpty())
                team.setTeamName(teamName + "No Name Provided");
            else {
                team.setTeamName(teamName); }
        }
        if (teamScore.isEmpty()) {
            tempScore = 0; } else {
            tempScore = parseScore(teamScore); }

        if(!team.isValidScore(tempScore, teamScore)) {
            scoreAlert(); }
        team.setScore(tempScore);

    }

    public void scoreAlert (){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("The score should be between 0 and 2000");
        errorAlert.showAndWait();
        throw new TeamException("Score must be between 0 and 2,000");
    }

    public void nameAlert(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText("The Team Name should be between 5 and 50 Characters");
        errorAlert.showAndWait();
        throw new TeamException("The Team Name should be between 5 and 50 Characters");
    }

    private int parseScore(String scoreText) {
        scoreText = scoreText.replaceAll("[$,]", "");
        return (int) Double.parseDouble(scoreText);
    }

    public String formatScore(String teamScore) {
        String fieldText = "";
        int tempScore;
        try {
            if (teamScore.isEmpty())
                tempScore = 0;
            else {
                tempScore = parseScore(teamScore); }
            if (tempScore >= 1000 ){
                fieldText = ""+tempScore;
                String firstHalf = fieldText.substring(0,1);
                firstHalf+=",";
                firstHalf+=fieldText.substring(1,fieldText.length());
                fieldText = firstHalf;
            } else {
                fieldText = ""+tempScore; }
        } catch(Exception e) {
            e.printStackTrace(); }
        return fieldText;
    }



}
