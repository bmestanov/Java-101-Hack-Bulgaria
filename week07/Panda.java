package week07;

public class Panda {
	private final String MALE = "male", FEMALE = "female";
	private String name, email, gender;
	
	public Panda(String name, String email, String gender){
		this.setName(name);
		if(isValidEmail(email)){
			this.setEmail(email);
		}
		
		if(isValidGender(gender)){
			this.setGender(gender);
		}
	}
	
	private boolean isValidGender(String gender) {
		gender = gender.toLowerCase();
		return gender.equals(MALE) || gender.equals(FEMALE);
	}

	private boolean isValidEmail(String email){
		String[] parts = email.split("\\@");
		boolean twoParts = parts.length > 1;
		boolean containsDot = false;
		if(twoParts) {
			 containsDot = parts[1].contains(".");
		}
		return email.length()>5 && containsDot;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(" ");
		sb.append(getEmail());
		sb.append(" ");
		sb.append(getGender());
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Panda) && 
				((Panda)obj).name.equals(this.name) &&
				((Panda)obj).email.equals(this.email) &&
				((Panda)obj).gender.equals(this.gender);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws IllegalArgumentException{
		if(isValidEmail(email)){
			this.email = email;
		} else {
			throw new IllegalArgumentException("The given e-mail is not a valid one.");
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if(gender.equals(MALE) || gender.equals(FEMALE)){
			this.gender = gender;
		} else {
			throw new IllegalArgumentException("The given gender is not valid one.");
		}
	}
	
	public boolean isMale(){
		return gender.equals(MALE);
	}
	
	public boolean isFemale(){
		return gender.equals(FEMALE);
	}
}
