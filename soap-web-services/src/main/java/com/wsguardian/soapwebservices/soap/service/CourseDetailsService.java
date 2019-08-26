package com.wsguardian.soapwebservices.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wsguardian.soapwebservices.soap.bean.Course;

@Component
public class CourseDetailsService {
	
	private static List<Course> courses = new ArrayList<Course>();
	
	public enum Status{
		SUCCESS, FAILURE;
	}
	
	static {
		Course course1 = new Course(1,"Spring", "10 Steps");
		courses.add(course1);
		
		Course course2 = new Course(2,"Spring MVC", "10 examples");
		courses.add(course2);
		
		Course course3 = new Course(3,"Maven", "6k students");
		courses.add(course3);
		
		Course course4 = new Course(4,"Spring Boot", "new opportunities");
		courses.add(course4);
		
	}
	
	//getCourseDetails from an id
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId() == id )
				return course;
		}
		
		return null;
	}
	
	//getall courses findall
	public List<Course> findAll(){
		return courses;
	}
	
	//Delete courses
	public Status deleteById(int id) {
		Iterator<Course> iterator = courses.iterator();
		
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		
		return Status.FAILURE;
	}
	
	
}
