package com.dream.service;

import com.dream.model.Marks;

public interface MarksService {

	public boolean save(Marks marks);
	
	public void update(Marks marks);
	
	public Marks getMarksByStudent(int id);
	
}
