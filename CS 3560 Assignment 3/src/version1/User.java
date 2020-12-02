package version1;

import java.util.ArrayList;
import java.util.List;

public class User extends Subject implements SysEntry, Observer {
	private List<User> followers = new ArrayList<User>();
	private List<User> following = new ArrayList<User>();
	private List<String> feed = new ArrayList<String>();
	private String id;
	private String parent;
	private int messageCounter = 0;
	private long creationTime;
	private long lastUpdated = 0;
	
	
	//creates a constructor that takes in the user's name
	public User(String name) {
		id = name;
	}//end constructor
	
	
	
	/***
	 * @param tweet: the string message that the user is sending
	 */
	public void updateFeed(String tweet) {
		feed.add(this.getID() + ": " + tweet);
		messageCounter = getMessageCounter() + 1;
		//uses notifyFollowers from the SUbject parent class
		this.notifyFollowers(followers, tweet, this.getID());
	}//end updateFeed
	
	
	//method to update feed when a user you follow tweets
	/**
	 * @param message: the string message sent to the followers
	 */
	public void followerUpdateFeed(String message) {
		feed.add(message);
	}//end followerUpdateFeed
	
	//returns the display name of the user
	public String getID() {
		return id;
	}//end getID
	
	//sets the display name of the user
	public void setID(String s) {
		id = s;
	}//end setID
	
	//passes a User object as argument
	//adds the passed object into following list
	//adds the user who called into the arguments followers list
	public void followUser(User user) {
		following.add(user);
		user.addFollower(this);
	}//end follow user

	//getter for parent
	public String getParent() {
		return parent;
	}//end getParent
	
	//setter for parent
	public void setParent(String parent) {
		this.parent = parent;
	}//end setParent

	//getter for feed
	public List<String> getFeed() {
		return feed;
	}//end getFeed

	//setter for feed
	public void setFeed(List<String> feed) {
		this.feed = feed;
	}//end setFeed

	//getter for following
	public List<User> getFollowing() {
		return following;
	}//end getFollowing

	//setter for following
	public void setFollowing(List<User> following) {
		this.following = following;
	}//end setFollowing
	
	//getter for followers
	public List<User> getFollowers() {
		return followers;
	}//end getFollowers
	
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}//end setFollowers
	
	//passes a User object as an argument
	//adds the argument into the followers list
	public void addFollower(User user) {
		followers.add(user);
	}//end addFollower
	

	@Override
	public int accept(ShowGroupTotalVisitor visitor) {
		int x = 0;
		return x;
		
	}


	@Override
	public int accept(ShowMessageTotalVisitor visitor) {
		int x = 0;
		return x;
		
	}


	@Override
	public float accept(ShowPositivePercentVisitor visitor, List<String> list) {
		float x = 0;
		return x;
	}


	@Override
	public int accept(ShowUserTotalVisitor visitor) {
		int x = 0;
		return x;
		
	}

	/**
	 * 
	 * @return int: returns the total number of messages for a user
	 */
	public int getMessageCounter() {
		return messageCounter;
	}



	public long getCreationTime() {
		return creationTime;
	}



	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}



	public long getLastUpdated() {
		return lastUpdated;
	}



	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	


	
}
