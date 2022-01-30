package Model;

import Model.Colleague;
import Model.Mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamMediator implements Mediator {
   // private List<List<Colleague>> colleagues;
    private Map<Integer, List<Colleague>> colleaguess;

    public TeamMediator() {
        colleaguess = new HashMap<Integer, List<Colleague>>();
    }

    @Override
    public void send(String message, String score, String Date ,Colleague sender, int modelReference) {

        for(Colleague colleague : colleaguess.get(modelReference)) {
            if(colleague != sender)
                colleague.NotifyMediator(message,score,Date);
        }
    }

    @Override
    public void registerColleague(Colleague colleague, int modelReference) {
        if ( !(colleaguess.keySet().contains(modelReference)) ){
            List<Colleague> currentColleague = new ArrayList<>();
            currentColleague.add(colleague);
            colleaguess.put(modelReference, currentColleague);
            //colleaguess.put(modelReference,)
        }

        else {
            colleaguess.get(modelReference).add(colleague);
        }
    }

    @Override
    public void unregisterColleague(Colleague colleague) {

        for (int i : colleaguess.keySet()){
            if (colleaguess.get(i).contains(colleague)){
                colleaguess.get(i).remove(colleague);
            }
        }

    }


}
