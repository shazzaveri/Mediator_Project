package Model;

import Model.Mediator;

public abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator, int modelReference) {
        this.mediator = mediator;
        this.mediator.registerColleague(this, modelReference);
    }

    public abstract void NotifyMediator(String name, String score, String Date);
}
