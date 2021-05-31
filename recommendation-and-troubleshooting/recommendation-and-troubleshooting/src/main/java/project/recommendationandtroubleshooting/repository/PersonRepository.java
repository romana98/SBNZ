package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.recommendationandtroubleshooting.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmail(String email);
}
