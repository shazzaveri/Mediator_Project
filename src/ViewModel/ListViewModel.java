package ViewModel;

import Model.Mediator;
import Model.Team;
import Model.TeamMediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Contoller.ListViewModelController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListViewModel extends AnchorPane implements PropertyChangeListener {

    private Mediator mediator;
    private ObservableList<Team> models;
    private ListView listView;
    private Parent parentNode;
    ListViewModelController LVMController = new ListViewModelController();

    public ListViewModel(ArrayList<Team> teams){
        super();
        models = FXCollections.observableArrayList(teams);
        mediator = new TeamMediator();
        LVMController.generateObservers(teams, this);
        listView = new ListView();
        LVMController.listViewSetUp(listView, models);



        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if(click.getClickCount() == 2) {
                    if(listView.getSelectionModel().getSelectedItem() == null)
                        return;
                    LVMController.clickedEvents(listView, mediator);
                }
            }
        });
        this.getChildren().add(listView);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        listView.refresh();
    }
}
