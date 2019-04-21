package com.sanjiv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Student {

	private String name;
	private Integer age;
	private String city;

	public Student(String name, Integer age, String city) {
		super();
		this.name = name;
		this.age = age;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "\nStudent [name=" + name + ", age=" + age + ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

public class ComparatorExample {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("sanjiv", "kumar", "singh", "aarush", "kalindi");
		Collections.sort(names);
		System.out.println("Sorted name : " + names);

		List<Student> students = new ArrayList<Student>();
		
		Set<Student> studentSet = new HashSet<Student>();

		Student student1 = new Student("sanjiv", 40, "patna");
		Student student2 = new Student("kumar", 30, "sasaram");
		Student student3 = new Student("singh", 20, "varansi");
		Student student4 = new Student("aarush", 10, "bangalore");
		Student student5 = new Student("kalindi", 5, "bangalore");
		Student student6 = new Student("kalindi", 4, "sasaram");
		Student student7 = new Student("sambhaw", 15, "patna");
		Student student8 = new Student("kundan", 45, "delhi");
		Student student9 = new Student("kalindi", 4, "bangalore");
		Student student10 = new Student("kalindi", 4, "bangalore");

		studentSet.add(student1);
		studentSet.add(student2);
		studentSet.add(student3);
		studentSet.add(student4);
		studentSet.add(student5);
		studentSet.add(student6);
		studentSet.add(student7);
		studentSet.add(student8);
		studentSet.add(student9);
		studentSet.add(student10);
		
		System.out.println("#######Size of Set : " + studentSet.size()); 
		
		
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		students.add(student10);

		System.out.println("Before Sorted  : " + students);

		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				Integer compareName = (o1.getName().compareToIgnoreCase(o2.getName()));
				Integer compareAge = (o1.getAge() - o2.getAge());
				Integer compareCity = o1.getCity().compareToIgnoreCase(o2.getCity()); 
				
				if (compareName == 0) {
					return (compareAge == 0) ?  (compareCity == 0) ? compareName : compareCity  : compareAge;
				} else {
					return compareName;
				}
			}
		});

		System.out.println("After Sorted  : " + students);

	}

}
