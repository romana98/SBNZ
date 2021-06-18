package project.recommendationandtroubleshooting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.model.troubleshooting.Description;

import java.util.List;
import java.util.Set;

@Repository
public interface BugRepository extends JpaRepository<Bug, Integer> {

    @Query(value = "select * from bugs where id in (select bug_id from bugs_descriptions where descriptions_id in ?1 group by bug_id having count(bug_id) =?2)", nativeQuery = true)
    Bug findByDescriptions(List<Integer> descriptions, Integer size);

}
