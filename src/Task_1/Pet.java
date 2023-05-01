package Task_1;

public class Pet implements Comparable<Pet>{
	private String petName;
	private String petType;
	public Pet(String petName, String petType) {
		super();
		this.petName = petName;
		this.petType = petType;
	}
	public String getPetName() {
		return petName;
	}
	public String getPetType() {
		return petType;
	}
	@Override
	public int compareTo(Pet o) {
		return this.getPetType().compareTo(o.getPetType());
	}
	

}
