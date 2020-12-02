package version1;

public class TwitterDemo {

	public static void main(String[] args) {
		//creates a single instance of AdminControl
		AdminControl twitter = AdminControl.getInstance();
		//calls a method to create the GUI
		twitter.runWindow();
	}

}
