package Main;

import ViewModel.ListViewModel;
import javafx.application.Application;
import javafx.stage.Stage;
import Contoller.MainController;
import Model.Team;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainController controller = new MainController();
        ArrayList<Team> teams = new ArrayList<Team>();
        controller.createTeams(teams);
        ListViewModel viewModel = new ListViewModel(teams);
        controller.setStage(viewModel, stage);
    }
    public static void main(String [] args) {
        launch(args);
    }
}
