package project.recommendationandtroubleshooting.model.troubleshooting;

import java.util.ArrayList;
import java.util.List;

public class Bugs {

    private List<Bug> bugs = new ArrayList<>();

    public Bugs() {

    }

    public Bugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public void addBug(Bug frequentBug) {
        this.bugs.add(frequentBug);
    }
}
