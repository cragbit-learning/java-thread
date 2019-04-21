package com.sanjiv.test;

import java.util.HashSet;
import java.util.Set;

class HashCodeExample{
	
	private String name;
	private Integer id;
	private String email;
	
	public HashCodeExample(String name, Integer id, String email) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "\nHashCodeExample [name=" + name + ", id=" + id + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		HashCodeExample other = (HashCodeExample) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

public class HashCode {

	public static void main(String[] args) {
		
		String name = "sanjiv";
		String name1 = new String("sanjiv");
		
		System.out.println(name.hashCode()); 
		System.out.println(name1.hashCode()); 
		
		System.out.println(name1 == name); 
		System.out.println(name.equals(name1));
		
		Set<HashCodeExample> employees = new HashSet<HashCodeExample>();

		HashCodeExample hashCodeExample1 = new HashCodeExample("sanjiv",1000,"sanjiku5@in.ibm.com");
		HashCodeExample hashCodeExample2 = new HashCodeExample("sanjiv",1001,"sanjiv.kumar@happiestminds.com");
		HashCodeExample hashCodeExample3 = new HashCodeExample("sanjiv",1000,"sanjiv_kumar@in.ibm.com");
		HashCodeExample hashCodeExample4 = new HashCodeExample("sanjiv",1002,"sanjiv.programmer@gmail.com");
		HashCodeExample hashCodeExample5 = new HashCodeExample("sanjiv",1003,"sanjiv.kumar@infinite.com");

		employees.add(hashCodeExample1);
		employees.add(hashCodeExample2);
		employees.add(hashCodeExample3);
		employees.add(hashCodeExample4);
		employees.add(hashCodeExample5);
		
		System.out.println("employee size :::" + employees.size());
		System.out.println(employees);

	}

}
