package business;

import java.util.ArrayList;

public class User {
	/**
	 * 
	 * @param User
	 * @invariant name != null && password != null
	 * @precondition User != null
	 * @postcondition this != null
	 */
	public User(ArrayList<String> User) {
		name = User.get(0);
		password = User.get(1);
		try{
			email = User.get(2);
		}
		catch(Exception e) {}
	}
	/**
	 * returns name attribute
	 * @precondition this != null
	 * @postcondition this != null
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * returns password attribute
	 * @precondition this != null
	 * @postcondition this != null
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * returns email attribute
	 * @precondition this != null
	 * @postcondition this != null
	 * @return email
	 */
	public String getEmail() {

		return email;
	}
	/*public String toString() {
		return name + ", " + password + ", " + email;
	}*/
	/**
	 * determines whether two users are equal
	 * for the purposes of logging in
	 * @precondition user != null
	 * @postcondition this != null
	 * @param user is login info to be checked
	 * @return true if validation successful
	 */
	public boolean validate(User user) {
		return user.getName().equals(name)
				&& user.getPassword().equals(password);
	}
	private String name;
	private String password;
	private String email;

}
