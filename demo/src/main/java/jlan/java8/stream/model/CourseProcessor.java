package jlan.java8.stream.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import algorithm.probability.PoissonRandom;

public class CourseProcessor {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Course> list = new ArrayList<>();
		Random random = new Random();
		PoissonRandom prand = new PoissonRandom(80);
		for(int i=0;i<10000;i++) {
			Course course = new Course();
			course.setCourseID(random.nextInt(10));
			course.setStudentName(UUID.randomUUID().toString());
			int grade = prand.next();
			while(grade>100) {
				grade = prand.next();
			}
			course.setGrade(grade);
			list.add(course);
		}
		
		list.stream()
			.filter(t->t.getGrade()>90).sorted((x,y) -> Integer.compareUnsigned(y.getGrade(), x.getGrade()))
			.forEach(System.out::println);
		
	}

}
