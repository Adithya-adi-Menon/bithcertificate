package admin;

public class denied {
	
	private String name;
	private String birth_place;
	private String dob;
	private String gender;
	private String address;
    private String phone;
    private String email;
	public denied(String name, String birth_place, String dob, String gender, String address, String phone,
			String email) {
		super();
		this.name = name;
		this.birth_place = birth_place;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    


}
