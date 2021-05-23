package project.recommendationandtroubleshooting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.recommendationandtroubleshooting.model.recommendation.Favorite;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
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
}
