package version1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class UserViewWindow {

	private JFrame frame;
	private static User u1;
	private JTextField feedTF;
	private JTextField followTF;
	private JLabel updateLbl;
	JTextArea followTA;
	JTextArea feedTA;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(User user, UserGroup root) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					u1 = user;
					UserViewWindow window = new UserViewWindow(u1, root);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserViewWindow(User u1, UserGroup root) {
		initialize(u1, root);
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize(User u1, UserGroup root) {
		frame = new JFrame(u1.getID());
		frame.setBounds(100, 100, 523, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//button used to add a new user to follow
		JButton followBtn = new JButton("Follow User");
		followBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(User user: root.getUserList()) {
					if(user.getID().contentEquals(followTF.getText())) {
						u1.followUser(user);
						String followList = "";
						for(User following: u1.getFollowing()) {
							followList = followList.concat("  " + following.getID()); 
						}
						followTA.setText(followList);
					}
				}
				for(UserGroup group: root.getGroup()) {
					for(User user: group.getUserList()) {
						if(user.getID().contentEquals(followTF.getText())) {
							u1.followUser(user);
							String followList = "";
							for(User following: u1.getFollowing()) {
								followList = followList.concat("  " + following.getID()); 
							}
							followTA.setText(followList);
						}
					}
				}
			}
		});
		followBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		followBtn.setBounds(302, 11, 195, 23);
		frame.getContentPane().add(followBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 45, 487, 159);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		//label used to name the following panel
		JLabel followLbl = new JLabel("Following:");
		followLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		followLbl.setBounds(10, 11, 119, 22);
		panel_1.add(followLbl);
		
		followTA = new JTextArea();
		followTA.setLineWrap(true);
		followTA.setEditable(false);
		followTA.setBounds(10, 44, 444, 104);
		panel_1.add(followTA);
		
		feedTF = new JTextField();
		feedTF.setBounds(10, 215, 272, 23);
		frame.getContentPane().add(feedTF);
		feedTF.setColumns(10);
		
		//button used to send a tweet and update the user's feed
		JButton tweetBtn = new JButton("Post Tweet");
		tweetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				u1.updateFeed(feedTF.getText());
				String feed = "";
				for(String list: u1.getFeed()) {
					feed = feed.concat("    " + list);
				}
				feedTA.setText(feed);
				u1.setLastUpdated(System.currentTimeMillis());
				updateLbl.setText("Last Updated: " + u1.getLastUpdated());
			}
		});
		tweetBtn.setBounds(302, 215, 195, 23);
		frame.getContentPane().add(tweetBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 268, 487, 195);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		//Names the feed Pane
		JLabel feedLbl = new JLabel("Feed:");
		feedLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedLbl.setForeground(Color.BLACK);
		feedLbl.setBounds(10, 11, 57, 14);
		panel_2.add(feedLbl);
		
		feedTA = new JTextArea();
		feedTA.setLineWrap(true);
		feedTA.setEditable(false);
		feedTA.setBounds(10, 36, 444, 148);
		panel_2.add(feedTA);
		
		//the text field for typing in who to follow
		followTF = new JTextField();
		followTF.setBounds(10, 11, 198, 23);
		frame.getContentPane().add(followTF);
		followTF.setColumns(10);
		
		//This button is used to refresh auser's feed
		JButton refreshBtn = new JButton("Refresh Feed");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String feed = "";
				for(String list: u1.getFeed()) {
					feed = feed.concat("    " + list);
				}
				feedTA.setText(feed);
				u1.setLastUpdated(System.currentTimeMillis());
				updateLbl.setText("Last Updated: " + u1.getLastUpdated());
			}
		});
		refreshBtn.setBounds(302, 238, 195, 23);
		frame.getContentPane().add(refreshBtn);
		
		JLabel creationLbl = new JLabel("Creation Time: " + u1.getCreationTime());
		creationLbl.setBounds(10, 474, 198, 14);
		frame.getContentPane().add(creationLbl);
		
		updateLbl = new JLabel("Last Updated:");
		updateLbl.setBounds(302, 474, 172, 14);
		frame.getContentPane().add(updateLbl);
	}
}
