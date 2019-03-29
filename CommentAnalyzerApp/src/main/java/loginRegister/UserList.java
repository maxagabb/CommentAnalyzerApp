package loginRegister;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
	/**
	 * constructs UserList with contents of file
	 * @invariant users.size() >= 0
	 * @precondition file != null
	 * @postcondition this != null
	 * @param file
	 */
	public UserList(File file) {
		try {
			Scanner inFile = new Scanner(file);
			while(inFile.hasNextLine()) {

				String str = inFile.nextLine();

				String[] split = str.split(",");
				ArrayList<String> info = new ArrayList<String>();
				info.add(split[0]);
				info.add(split[1]);
				info.add(split[2]);
				User user = new User(info);
				users.add(user);
			}
			inFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	/**
	 * appends newly registered user to 'users.txt' 
	 * @precondition input != null
	 * @postcondition users.size() >= 1
	 * @param input contains registration info
	 * @throws Exception if registration info is incomplete
	 */
	public void printToFile(ArrayList<String> input) throws Exception{
		File file = new File("users.csv");
		for (String in:input) {
			if (in.equals(""))
				throw new Exception();
		}
		try {
			PrintStream out = new PrintStream(file);
			User user = new User(input);
			users.add(user);
			for(User user1: users) {
			out.print(user1.getName());
			out.print(",");
			out.print(user1.getPassword());
			out.print(",");
			out.print(user1.getEmail());
			
			out.println();
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * returns true if login info matches any
	 * user held in UserList
	 * @precondition inputinfo != null
	 * @postcondition this != null
	 * @param inputinfo holds login information
	 * @return true if login is successful
	 */
	public boolean validate(ArrayList<String> inputinfo){
		User info = new User(inputinfo);
		for (User user: users) {
			if(info.validate(user))
				return true;
		}
		return false;
	}
	private ArrayList<User> users = new ArrayList<User>();
}
