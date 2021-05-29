package project.recommendationandtroubleshooting.model;

import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Person {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bug_historyId")
    private Set<BugHistory> bugHistory = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "favoriteId")
    private Set<Favorite> favorites = new HashSet<>();

    public Set<BugHistory> getBugHistory() {
        return bugHistory;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setBugHistory(Set<BugHistory> bugHistory) {
        this.bugHistory = bugHistory;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }
}
