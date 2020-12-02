package version1;

import java.util.List;

public class ShowUserTotalVisitor {
	/**
	 * 
	 * @param userList: list of User objects
	 * @return: int that counts the total number of users
	 */
	public int visit(List<User> userList) {
		int totalUsers = 0;
		for(User list:  userList) {
			totalUsers++;
		}
		return totalUsers;
	}
}
