package Contoller;

import Model.Colleague;
import Model.Mediator;
import ViewModel.TeamDetailVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailViewController extends Colleague implements Initializable {
    JavaFxController fxJavaController = new JavaFxController();
    private static final String fxmlPath = "/teameditor.fxml";
    private TeamDetailVM teamVM;
    int referenceOfModel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button saveButton;

    private Parent parentNode;

    public DetailViewController(Mediator mediator, int modelReference) {

        super(mediator, modelReference);
        this.referenceOfModel = modelReference;
        try {

            //FXMLLoader loader = new FXMLLoader(TeamDetailVM.class.getResource("/sample/teameditor.fxml"));
            //loader.setController(this);
            FXMLLoader loader = fxJavaController.setLoader(fxmlPath, this);

            parentNode = loader.load();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    @FXML
    void doSave(ActionEvent event) {
        try {
            mediator.send(teamVM.getReturnString(), teamVM.getReturnScore(), teamVM.getReturnDate(),this, referenceOfModel);
            teamVM.save();
        } catch(RuntimeException e) {
            System.out.println("Save error: " + e);
        }
    }

    public void setViewModel(Node vm) {
        borderPane.setCenter(vm);
        this.teamVM = (TeamDetailVM) vm;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public Parent getParentNode() {
        return parentNode;
    }

    public void setParentNode(Parent parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public void NotifyMediator(String name, String Score, String Date) {
        teamVM.updateDetails(name, Score, Date);
    }
}
