package project.recommendationandtroubleshooting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;

import java.util.List;

public interface BugService {

    List<Bug> findAll();

    Page<Bug> findAll(Pageable pageable);

    Bug findOne(Integer id);

    Bug saveOne(Bug bug);

    void delete(Integer id);

    Bug update(Bug bug);
}
