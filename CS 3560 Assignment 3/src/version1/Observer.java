package version1;

public interface Observer {
	
	//updates feed when called by subject
	public void updateFeed(String tweet);
	
	//method to update feed when a user you follow tweets
	public void followerUpdateFeed(String message);
}
