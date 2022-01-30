package Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Team {


    private String teamName;
    private LocalDateTime lastUpdated;
    private int score;

    private ArrayList<PropertyChangeListener> observers;

    public Team(String teamName, LocalDateTime lastUpdated, int score) {
        this.teamName = teamName;
        this.lastUpdated = lastUpdated;
        this.score = score;
        observers = new ArrayList<PropertyChangeListener>();
    }


    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", score=" + score +
                ", observers=" + observers +
                '}';
    }



    // gateway passthru functions
    public void save() throws TeamException {
        System.out.println("*** saved ***");
    }

    // validators
    public static boolean isValidScore(int score, String TeamScore) {
        if(score < 0 || score > 2000 && !(TeamScore.isEmpty()))
            return false;
        return true;
    }

    public static boolean isValidName(String tName) {
        if( (tName.length() < 5 || tName.length() > 50 ) && !(tName.isEmpty()) )
            return false;
        Pattern p = Pattern.compile("[^A-Za-z0-9 ]");
        Matcher m = p.matcher(tName);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b)
            return false;


        //if (tName.is)
        return true;
    }


    //accessors
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
        notifyObservers();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void notifyObservers() {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "teamName", this.teamName, this.teamName);

        for(PropertyChangeListener observer : observers) {
            observer.propertyChange(event);
        }
    }

    public void addObserver(PropertyChangeListener observer) {
        observers.add(observer);
    }

}
