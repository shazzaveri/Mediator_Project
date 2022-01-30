package Model;

public interface Mediator {
    public abstract void send(String message, String Score , String Date ,Colleague colleague, int modelReference);
    public abstract void registerColleague(Colleague colleague, int modelReference);
    public abstract void unregisterColleague(Colleague colleague);
}
