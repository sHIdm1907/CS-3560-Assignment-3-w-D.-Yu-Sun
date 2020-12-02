package version1;

import java.util.List;

public class ShowGroupTotalVisitor {
	
	/***
	 * 
	 * @param group: the list that contains UserGroup objects
	 * @return: int that counts the total number of groups
	 */
	public int visit(List<UserGroup> group) {
		int totalGroups = 0;
		for(UserGroup list: group) {
			totalGroups++;
		}
		return totalGroups;
	}//end visit
}
