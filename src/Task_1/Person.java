package Task_1;

import java.util.Objects;

public class Person {
	private String personName;
	private int age;
	Person(String personName, int age){
		this.personName = personName;
		this.age = age;
	}
	public String getPersonName() {
		return personName;
	}
	public int getAge() {
		return age;
	}
	@Override
	public String toString() {
		return "Person [personName=" + personName + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, personName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && personName.equalsIgnoreCase(other.personName);
	}
	

}
