package version1;

import java.util.List;

public class Subject {
	
	
	/**
	 * 
	 * @param followers: list of Users that follow a specific user
	 * @param tweet: a string message that the user types
	 * @param notifier: a string that is the id of the user who is notifing their followers
	 */
	public void notifyFollowers(List<User> followers, String tweet, String notifier) {
		for(User follower: followers) {
			follower.followerUpdateFeed(notifier + ": " + tweet);
		}
	}//end notifyFollowers
}
