package com.dream.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.model.Marks;
import com.dream.repository.MarksRepository;
import com.dream.service.MarksService;

@Service
public class MarksServiceImpl implements MarksService {

	@Autowired
	private MarksRepository marksRepo; 
	
	@Override
	public boolean save(Marks marks) {
		if(marks != null) {
			marksRepo.save(marks);
			return true;
		}
		return false;
	}

	@Override
	public void update(Marks marks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Marks getMarksByStudent(int id) {
		Marks marks = marksRepo.getMarksByStudent(id);
		return marks;
	}


}
