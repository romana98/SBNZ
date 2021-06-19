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
    @JoinColumn(name = "user_id")
    private Set<BugHistory> bugHistory = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Favorite> favorites = new HashSet<>();

    public User() {

    }
    public User(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public User(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public User(Integer id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return super.isVerified();
    }
}
