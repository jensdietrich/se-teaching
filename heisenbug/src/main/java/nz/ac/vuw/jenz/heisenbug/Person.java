package nz.ac.vuw.jenz.heisenbug;


public class Person {
	
	private String name = null;
	private String address = null;


	
	@Override
	public String toString() {
		return "Person [name=" + this.getName() + ", address=" + this.getAddress() + "]";
	}
	
	public boolean hasBeenInitialised() {
		return this.address != null;
	}
	
	public String getAddress() {
		if (address==null) {
			System.out.println("db access");
			this.address = "some address";
		}
		return address;
	}
	
	// generated !
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
