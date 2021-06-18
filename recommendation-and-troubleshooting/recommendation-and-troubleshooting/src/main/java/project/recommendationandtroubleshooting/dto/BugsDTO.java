package project.recommendationandtroubleshooting.dto;

import java.util.ArrayList;
import java.util.List;

public class BugsDTO {
    private List<BugDTO> bugs = new ArrayList<>();

    public BugsDTO() {

    }

    public BugsDTO(List<BugDTO> bugs) {
        this.bugs = bugs;
    }

    public List<BugDTO> getBugs() {
        return bugs;
    }

    public void setBugs(List<BugDTO> bugs) {
        this.bugs = bugs;
    }

    public void addBug(BugDTO frequentBug) {
        this.bugs.add(frequentBug);
    }
}
