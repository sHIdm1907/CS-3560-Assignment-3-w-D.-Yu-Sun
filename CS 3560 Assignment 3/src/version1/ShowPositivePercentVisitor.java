package version1;

import java.util.List;

public class ShowPositivePercentVisitor {
	/**
	 * 
	 * @param feed: list of strings that contains the messages sent by a user
	 * @param list: list of strings that contains positive words
	 * @return: float that counts the number of strings containing positive words that appear in feed
	 */
	public float visit(List<String> feed, List<String> list) {
		float positive = 0;
		for(String string: feed) {
			for(String word: list) {
				if(string.contains(word)) {
					positive++;
					break;
				}
			}
		}//end for
		return positive;
	}
}
