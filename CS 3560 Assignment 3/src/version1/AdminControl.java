package version1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class AdminControl implements TreeSelectionListener {
	
	private static AdminControl instance = null;

	private JFrame frame;
	private JTextField userTF;
	private JTextField groupTF;
	private String tempID;
	private User newUser;
	private UserGroup newGroup;
	private UserGroup root = new  UserGroup("Root");
	private JTree tree;
	private DefaultMutableTreeNode selectedNode;
	public int totalGroups;
	public int totalUsers;
	public int totalMessages;
	public float totalPositive;
	private JLabel userTotalLbl;
	private JLabel groupTotalLbl;
	private JLabel messageTotalLbl;
	private JLabel positivePercLbl;
	private JLabel verifyLbl;
	private JLabel lastUpdatedLbl;
	private List<String> positiveWords = new ArrayList<String>();
	private boolean duplicate = false;
	
	
	
	//private constructor to control the instances
	private AdminControl() {
			
	}
		
	//new object of AdminControl must call getInstance to ensure that there is only
	// one instance of the Singleton class
	public static AdminControl getInstance() {
		if(instance == null) {
			instance = new AdminControl();
		}
			
		return instance;
	}
	
	private void generateTree() {
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame("Twitter Admin Panel");
		frame.setBounds(100, 100, 725, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton userBtn = new JButton("Add User");
		userBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//selects the node clicked on by the mouse
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				//creates a newNode from the userTextField
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(userTF.getText());
				//adds the new node underneath the 
				selectedNode.add(newNode);
				
				//gets the tree model
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				//reload the tree model
				model.reload();
				
				String selectedGroup = selectedNode.getUserObject().toString();
				newUser = new User(userTF.getText());
				newUser.setCreationTime(System.currentTimeMillis());
				
				//adds the user to the root's user list if root is the selected node
				if(root.getID().equals(selectedGroup) ) {
					root.addUser(newUser);
					newUser.setParent(root.getID());
				}
				else{
					for(UserGroup group: root.getGroup()) {
						if(group.getID().equals(selectedGroup)) {
							group.addUser(newUser);
							newUser.setParent(group.getID());
						}
					}
				}
				
				
				
			}
		});
		userBtn.setBounds(365, 11, 103, 23);
		
		frame.getContentPane().add(userBtn);
		
		JButton groupBtn = new JButton("Add Group");
		groupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(groupTF.getText());
				selectedNode.add(newNode);
				
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.reload();
				
				String selectedGroup = selectedNode.getUserObject().toString();
				newGroup = new UserGroup(groupTF.getText());
				newGroup.setCreationTime(System.currentTimeMillis());
				
				//adds the group to the root's group list if root is the selected node
				if(root.getID().equals(selectedGroup) ) {
					root.addGroup(newGroup);
				}
				else{
					for(UserGroup group: root.getGroup()) {
						if(group.getID().equals(selectedGroup)) {
							group.addGroup(newGroup);
						}
					}
				}
				
			}
		});
		groupBtn.setBounds(365, 45, 103, 23);
		
		frame.getContentPane().add(groupBtn);
		
		
		userTF = new JTextField();
		userTF.setBounds(235, 12, 120, 20);
		frame.getContentPane().add(userTF);
		userTF.setColumns(10);
		
		
		groupTF = new JTextField();
		groupTF.setColumns(10);
		groupTF.setBounds(235, 46, 120, 20);
		frame.getContentPane().add(groupTF);
		
		tree = new JTree();
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
			}
		});
		tree.setEditable(true);
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode(root.getID()) {
				{
				}
			}
		));
		tree.setBounds(10, 11, 196, 279);
		frame.getContentPane().add(tree);
		
		JButton userViewBtn = new JButton("Open User View");
		userViewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
				String user = selectedNode.getUserObject().toString();
				for(User a: root.getUserList()) {
					if(a.getID().equals(user)) {
						UserViewWindow nw = new UserViewWindow(a, root);
						nw.NewScreen(a, root);
					}
				}
				for(UserGroup group: root.getGroup()) {
					for(User a: group.getUserList()) {
						if(a.getID().equals(user)) {
							UserViewWindow nw = new UserViewWindow(a, root);
							nw.NewScreen(a, root);
						}
					}
				}
			}
		});
		userViewBtn.setBounds(235, 79, 233, 23);
		frame.getContentPane().add(userViewBtn);
		
		//button that displays the total number of users when pressed
		JButton userTotalBtn = new JButton("Show User Total");
		userTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalUsers = root.accept(new ShowUserTotalVisitor());
				for(UserGroup group: root.getGroup()) {
					totalUsers += group.accept(new ShowUserTotalVisitor());
				}
				userTotalLbl.setText("Total Number of Users: " + totalUsers);
			}
		});
		userTotalBtn.setBounds(235, 142, 233, 23);
		frame.getContentPane().add(userTotalBtn);
		
		//button that displays the total number of groups when pressed
		JButton groupTotalBtn = new JButton("Show Group Total");
		groupTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalGroups = root.accept(new ShowGroupTotalVisitor());
				for(UserGroup group: root.getGroup()) {
					totalGroups += group.accept(new ShowGroupTotalVisitor());
				}
				groupTotalLbl.setText("Total Number of Groups: " + totalGroups);
			}
		});
		groupTotalBtn.setBounds(235, 176, 233, 23);
		frame.getContentPane().add(groupTotalBtn);
		
		//button that displays the total number of messages when pressed
		JButton messageTotalBtn = new JButton("Show Message Total");
		messageTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalMessages = root.accept(new ShowMessageTotalVisitor());
				for(UserGroup group: root.getGroup()) {
					totalMessages += group.accept(new ShowMessageTotalVisitor());
				}
				messageTotalLbl.setText("Total Number of Messages: " + totalMessages);
			}
		});
		messageTotalBtn.setBounds(235, 210, 233, 23);
		frame.getContentPane().add(messageTotalBtn);
		
		//button that displays the percentage of positive words when sent
		JButton positiveTotalBtn = new JButton("Show Positive Percentage");
		positiveTotalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float stringTotal = 0;
				for(User user: root.getUserList()) {
					for(String string: user.getFeed()) {
						stringTotal++;
					}
				}
				for(UserGroup group: root.getGroup()) {
					for(User user: group.getUserList()) {
						for(String string: user.getFeed()) {
							stringTotal++;
						}
					}
				}
				
				totalPositive = root.accept(new ShowPositivePercentVisitor(), positiveWords);
				for(UserGroup group: root.getGroup()) {
					totalPositive += group.accept(new ShowPositivePercentVisitor(), positiveWords);
				}
				float positivePerc = (totalPositive/stringTotal) * 100;
				positivePercLbl.setText("Positive Percentage: " + positivePerc + "%");
				
			}
		});
		positiveTotalBtn.setBounds(235, 244, 233, 23);
		frame.getContentPane().add(positiveTotalBtn);
		
		userTotalLbl = new JLabel("Total Number of Users:");
		userTotalLbl.setBounds(478, 146, 221, 14);
		frame.getContentPane().add(userTotalLbl);
		
		groupTotalLbl = new JLabel("Total Number of Groups: ");
		groupTotalLbl.setBounds(478, 180, 224, 14);
		frame.getContentPane().add(groupTotalLbl);
		
		messageTotalLbl = new JLabel("Total Number of Messages:");
		messageTotalLbl.setBounds(478, 214, 221, 14);
		frame.getContentPane().add(messageTotalLbl);
		
		positivePercLbl = new JLabel("Positive Percentage:");
		positivePercLbl.setBounds(478, 248, 221, 14);
		frame.getContentPane().add(positivePercLbl);
		
		JButton verifyBtn = new JButton("User/ Group ID Verification");
		verifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				duplicate = root.getDuplicates();
				if(duplicate) {
					verifyLbl.setText("Not all IDs are valid.");
				}
				else {
					verifyLbl.setText("All IDs are valid.");
				}
			}
		});
		verifyBtn.setBounds(235, 108, 233, 23);
		frame.getContentPane().add(verifyBtn);
		
		verifyLbl = new JLabel("");
		verifyLbl.setBounds(478, 112, 221, 14);
		frame.getContentPane().add(verifyLbl);
		
		JButton lastUpdatedBtn = new JButton("Last Updated User");
		lastUpdatedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long latestUpdate = 0;
				String latestUserUpdate ="";
				for(User user: root.getUserList()) {
					//TODO create if/else to find last updated user
					if(user.getLastUpdated() > latestUpdate) {
						latestUpdate = user.getLastUpdated();
						latestUserUpdate = user.getID();
					}
				}
				lastUpdatedLbl.setText("Last Updated User: " + latestUserUpdate);
			}
		});
		lastUpdatedBtn.setBounds(478, 45, 221, 23);
		frame.getContentPane().add(lastUpdatedBtn);
		
		lastUpdatedLbl = new JLabel("");
		lastUpdatedLbl.setBounds(478, 83, 221, 14);
		frame.getContentPane().add(lastUpdatedLbl);
		frame.setVisible(true);
		
	}
	
	public void runWindow() {
		positiveWords.add("good");
		positiveWords.add("great");
		positiveWords.add("nice");
		positiveWords.add("Excellent");
		positiveWords.add("beautiful");
		positiveWords.add("smart");
		positiveWords.add("cool");
		
		initialize();
	}

	@Override
	public void valueChanged(TreeSelectionEvent tsl) {
		if(tsl.getNewLeadSelectionPath() != null) {
			
		}
		
	}

	public List<String> getPositiveWords() {
		return positiveWords;
	}

	public void setPositiveWords(List<String> positiveWords) {
		this.positiveWords = positiveWords;
	}
}
