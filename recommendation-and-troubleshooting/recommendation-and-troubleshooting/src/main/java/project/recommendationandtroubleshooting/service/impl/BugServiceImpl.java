package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.troubleshooting.Bug;
import project.recommendationandtroubleshooting.service.BugService;

@Service
public class BugServiceImpl implements BugService {

	@Override
	public List<Bug> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Bug> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bug findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bug saveOne(Bug admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bug update(Bug admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
