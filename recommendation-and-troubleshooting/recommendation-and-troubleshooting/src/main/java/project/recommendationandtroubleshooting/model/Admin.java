package project.recommendationandtroubleshooting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Setter
@Entity
@Table(name = "admins")
public class Admin extends Person {
}
