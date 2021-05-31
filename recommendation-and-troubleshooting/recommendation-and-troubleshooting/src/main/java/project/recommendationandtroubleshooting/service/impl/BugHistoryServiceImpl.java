package project.recommendationandtroubleshooting.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import project.recommendationandtroubleshooting.model.troubleshooting.BugHistory;
import project.recommendationandtroubleshooting.service.BugHistoryService;

@Service
public class BugHistoryServiceImpl implements BugHistoryService {

	@Override
	public List<BugHistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BugHistory> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BugHistory findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BugHistory saveOne(BugHistory admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BugHistory update(BugHistory admin) {
		// TODO Auto-generated method stub
		return null;
	}

}
