package ViewModel;

import Contoller.JavaFxController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import Model.Team;

import java.io.IOException;

public class TeamListCellViewModel extends ListCell<Team> {
    JavaFxController fxJavaController = new JavaFxController();
    private static final String fxmlPath = "/teamlistcell.fxml";
    // the cell will use an fxml loader to load its fxml the first time it is visible
    private FXMLLoader loader;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label teamName;

    @FXML
    private Label teamScore;

    // this is what we need to override for a custom list cell
    @Override
    protected void updateItem(Team team, boolean empty) {
        super.updateItem(team, empty);
        if(empty || team == null) {
            setText(null);
            setGraphic(null);
        } else {
                try {
                    //loader = new FXMLLoader(TeamListCellViewModel.class.getResource(fxmlPath));
                    //loader.setController(this);
                    //loader = new FXMLLoader();
                    loader = fxJavaController.setLoader(fxmlPath, this);
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            teamName.setText(team.getTeamName());
            teamScore.setText(""+ team.getScore());
            setText(null);
            setGraphic(anchorPane);
        }
    }

}
