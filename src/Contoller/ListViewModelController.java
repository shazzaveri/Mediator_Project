package Contoller;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import Model.Mediator;
import Model.Team;
import ViewModel.TeamDetailVM;
import ViewModel.TeamListCellViewModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListViewModelController {

    public void generateObservers(ArrayList<Team> teams, PropertyChangeListener propertyChangeListener){
        for(Team team: teams) {
            team.addObserver(propertyChangeListener);
        }
    }

    public void listViewSetUp(ListView listView, ObservableList modelsList ){
        listView.setItems(modelsList);

        listView.setCellFactory((whoCares) -> {
            return new TeamListCellViewModel();
        });

        listView.setMinWidth(600);
        listView.setPrefHeight(300);
    }

    public void clickedEvents(ListView listView, Mediator mediator){
        Team selected = (Team) listView.getSelectionModel().getSelectedItem();
        int selectedModel = listView.getSelectionModel().getSelectedIndex();
        Stage stage = new Stage();
        DetailViewController controller = new DetailViewController(mediator, selectedModel);
        Parent teamDetailVM = new TeamDetailVM(selected);
        controller.setViewModel(teamDetailVM);
        Scene scene = new Scene(controller.getParentNode());
        stage.setScene(scene);
        stage.setTitle("Editing "+ selected.getTeamName());
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Unregistering colleague");
                mediator.unregisterColleague(controller);
            }
        });
        stage.show();
    }
}
