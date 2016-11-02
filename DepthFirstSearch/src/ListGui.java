import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class ListGui extends JFrame {
	
	//store the file selected from GUI
	private File selectedFile;
	
	Node node;
	SubNode subNode;

	//Holds all nodes
	static ArrayList<Node> nodeList;
	
	//Search Buffer
	static Stack<Node> stack;
	
	private JPanel contentPane;
	private JTextField fileSelectedTextField;
	private JTextField depthTextField;
	private JTextField incrementTextField;
	
	//our starting depth on search
	private int initialDepth = 0; 
	//what we wish to increment by
	private int incrementLevel = 0;
	//where we are in the search
	private int currentDepth = 0;
	//stores the edge weight of subnode
	private int subNodeValue = 0;
	//goal found at end of iteration
	private boolean goalReached = false;
	//goal found in iteration
	private boolean tmpGoalReached = false;
	//stores goal names found during search
	private String goalName = "";
	//used for printing search header
	private boolean firstRun = true;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//Initialize the arrays
		nodeList = new ArrayList<Node>();
		stack = new Stack<Node>();
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListGui frame = new ListGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListGui() {
		setTitle("Program 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("."));
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					setSelectedFile(fileChooser.getSelectedFile());
					fileSelectedTextField.setText(selectedFile.getName());
					
					parseData(selectedFile);
				}
			}

		});
		btnSelectFile.setBounds(10, 11, 120, 38);
		contentPane.add(btnSelectFile);
		
		JButton btnDepthFirst = new JButton("Depth First");
		btnDepthFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				depthFirsthSearch();

			}

		
		});
		btnDepthFirst.setBounds(10, 113, 172, 38);
		contentPane.add(btnDepthFirst);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(10, 88, 68, 14);
		contentPane.add(lblSearch);
		
		fileSelectedTextField = new JTextField();
		fileSelectedTextField.setBounds(140, 29, 151, 20);
		contentPane.add(fileSelectedTextField);
		fileSelectedTextField.setColumns(10);
		
		JLabel lblFileSelected = new JLabel("File selected: ");
		lblFileSelected.setBounds(141, 11, 97, 14);
		contentPane.add(lblFileSelected);
		
		JButton btnIterativeDeepening = new JButton("Iterative Deepening");
		btnIterativeDeepening.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialDepth = Integer.parseInt(depthTextField.getText());
				incrementLevel = Integer.parseInt(incrementTextField.getText());
				iterativeDeepeningSearch();
			}
		});
		btnIterativeDeepening.setBounds(10, 162, 172, 38);
		contentPane.add(btnIterativeDeepening);
		
		JButton btnBranchAndBound = new JButton("Branch and Bound");
		btnBranchAndBound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIXME
				System.out.println("Not Yet Implemented!");
			}
		});
		btnBranchAndBound.setBounds(10, 211, 172, 38);
		contentPane.add(btnBranchAndBound);
		
		JLabel lblInitialDepth = new JLabel("Initial Depth:");
		lblInitialDepth.setBounds(192, 113, 99, 14);
		contentPane.add(lblInitialDepth);
		
		depthTextField = new JTextField();
		depthTextField.setText("0");
		depthTextField.setBounds(192, 131, 86, 20);
		contentPane.add(depthTextField);
		depthTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Increment:");
		lblNewLabel.setBounds(192, 162, 99, 14);
		contentPane.add(lblNewLabel);
		
		incrementTextField = new JTextField();
		incrementTextField.setText("0");
		incrementTextField.setBounds(192, 180, 86, 20);
		contentPane.add(incrementTextField);
		incrementTextField.setColumns(10);
	}
	
	
	private void parseData(File selectedFile) {
		try{
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile)));
			
			//temp vars, to use below and create new nodes
			String nodeName = "";
			boolean isStart = false;
			boolean isGoal = false;
			String start = "S";
			String goal = "g";
			
			for (int i = 0 ; i < selectedFile.length(); i ++){
				String line = input.readLine();
				
				if (line != null){
					//System.out.println("line is: " + line);
					//System.out.println("Line length = " + line.length());
					
					if (line.length() == 0){
						//this is the blank line, do nothing!
					}
					//this is for non goal nodes
					else if(line.length() == 5){
						String[] tmpArray = line.split("\\s+");
						
						nodeName = tmpArray[0];
						//see if its a start node
						if (tmpArray[1].equals(start)){
							isStart = true;
							
						}
						//see if its a goal node, it shouldn't be
						if (tmpArray[2].equals(goal)){
							isGoal = true;	
						}
						
						if(isStart){
							//if its a start node, put it at the beginning
							nodeList.add(0, new Node(nodeName, isStart, isGoal, !isStart));							
						}else{
							//else add it to the list as normal
							nodeList.add(new Node(nodeName, isStart, isGoal, !isStart));	
						}
						
						//reset isStart
						isStart = false;
						
						
					}//this is for goal nodes
					else if(line.length() == 6){
						String[] tmpArray = line.split("\\s+");
						
						nodeName = tmpArray[0];
						
						//see if its a start node, it shouldn't be
						if (tmpArray[1].equals(start)){
							isStart = true;
						}
						//see if its a goal node, it should be
						if (tmpArray[2].equals(goal)){
							isGoal = true;	
						}
						//add to the list as normal
						nodeList.add(new Node(nodeName, isStart, isGoal, !isStart));
						
						//reset isGoal
						isGoal = false;
						
						//FIXME, delete after debug not needed
						/*for (int j = 0 ; j < tmpArray.length; j++){
							System.out.println("tmpArray[" + j + "]= " + tmpArray[j]);
						}*/
						
					}
					//this is for the creation of new sub nodes	
					else{
						String[] tmpArray = line.split("\\s+");
						for(int j = 0; j < (tmpArray.length - 1); j += 2){
							String tmpNodeName = "";
							int tmpNodeWeight = 0;
							
							tmpNodeName = tmpArray[j];
							tmpNodeWeight = Integer.parseInt(tmpArray[j+1]);
							
							
							for (int k = 0 ; k < nodeList.size(); k ++){
								if(nodeList.get(k).getName() == nodeName){
									nodeList.get(k).getSubNodeArray().add(new SubNode(tmpNodeName, tmpNodeWeight));
								}
							}//end else inner for 2	
						}//end else inner for 1
					}//end else	
				}//end outside if (line != null)
			} //end selectedFile for loop
			
			
			
			//Prints out the nodes and their subNodes
			for(int i = 0; i < nodeList.size(); i++){
				//Sort the subNodes, this will help to traverse later
				sortListByAlphabetic(nodeList.get(i) );
				sortListByWeight(nodeList.get(i));
				
				
				//Print the nodes, and subNodes
				System.out.println("Node Name: " + nodeList.get(i).getName() + "(Has Previous: "+ nodeList.get(i).isHasPrevious()+ ", IsStart: " + nodeList.get(i).isStartNode() + ", IsGoal: " + nodeList.get(i).isGoalNode()+")");
				for (int j = 0; j < nodeList.get(i).getSubNodeArray().size(); j++){
					System.out.println("     SubNode: " +nodeList.get(i).getSubNodeArray().get(j).getName());
					System.out.println("        -Weight: " + nodeList.get(i).getSubNodeArray().get(j).getWeight());
				}
				
			}
				
				
				System.out.println("\n*********************************** \n");
				
				//For all nodes
				for (Node node : nodeList) {
					//Look at all its subnodes
					System.out.println("SubNodeArray Length: " + node.getSubNodeArray().size());
					for(SubNode subNode : node.getSubNodeArray()) {
						//Find the Node object that matches the subNode and add the ref to the neighberNodeArray
						
						for(int j = 0; j< nodeList.size(); j++){
							if(subNode.getName().equals(nodeList.get(j).getName())){
								node.getNeighberNodeArray().add(nodeList.get(j));								
							}
							
						}
					}
				
				
					//Print out the neighbor list
					for(int j = 0; j < node.getNeighberNodeArray().size(); j++){
						System.out.println("Node " + node.getName() + " Neighbor List ["+ j + "]: " + node.getNeighberNodeArray().get(j).getName());						
					}
				}
				
				
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	}
	
	/**
	 * Sort by weight of SubNode (Source From: Program 1)	
	 * @param node
	 */
	public static void sortListByWeight(Node node){
			
			//Sort the subnodes by weight
			Collections.sort(node.getSubNodeArray(), new Comparator<SubNode>() {
	
			    @Override
			    public int compare(SubNode s1, SubNode s2) {
			        if (s1.getWeight() < s2.getWeight())
			            return -1;
			        else if (s1.getWeight() > s2.getWeight())
			            return 1;
			        return 0;
			    }
	
			});
		}
	
	/**
	 * Sort Alphabetically by sub node name (Source from: http://stackoverflow.com/questions/19471005/sorting-an-arraylist-of-objects-alphabetically) 
	 * @param node
	 */
	public static void sortListByAlphabetic(Node node){
		Collections.sort(node.getSubNodeArray(), new Comparator<SubNode>() {
		    public int compare(SubNode s1, SubNode s2) {
		        return s1.getName().compareTo(s2.getName());
		    }
		});
	}
	

	
	/**
	 * Performs Depth FIrst Search (Source from : YouTUbe Balazs Hoczer @ https://www.youtube.com/watch?v=knbGy2tED-Y)
	 */
	private void depthFirsthSearch() {
		System.out.println("\nDepth First Search: ");
		
		for (Node node : nodeList) {
			if( !node.isVisted()){
				node.setVisted(true);
				//System.out.println("Setting visted for Node: " + node.getName());
				dfsWithStack(node);
			}
		}
		//FIXME may want to take this out later
		for (Node node2 : nodeList) {
			node2.setVisted(false);	
		}
	}

	/**
	 * Performs Depth FIrst Search (Source from : YouTUbe Balazs Hoczer @ https://www.youtube.com/watch?v=knbGy2tED-Y)
	 */
	private void dfsWithStack(Node node) {
		stack.push(node);
		//System.out.println("Stack, adding node: " + node.getName());
		node.setVisted(true);
		
		while(!stack.isEmpty() ){
			Node actualNode = stack.pop();
			//System.out.println("Setting actualNode to :" + actualNode.getName());
			System.out.print(actualNode.getName() + " ");
			if(actualNode.isGoalNode()){
				System.out.println("(Goal!)");
				
			}
			
			for (Node node2 : node.getNeighberNodeArray()) {
				
				if(!node2.isVisted()){
					dfsWithStack(node2);
					//node2.setVisted(true);
					//stack.push(node2);
				}
			}
		}
		
	}
	
	/**
	 * Performs Iterative Deepening Depth First Search
	 */
	private void iterativeDeepeningSearch() {
		if(firstRun){
			System.out.println("\nIterative Deepening Search: ");			
		}
		
		firstRun = false;
		
		System.out.println("Depth bound = " + initialDepth);
		for (Node node : nodeList.get(0).getNeighberNodeArray()) {
			if( !node.isVisted()){
				node.setVisted(true);
				//System.out.println("Setting visted for Node: " + node.getName());
				System.out.print("S ");
				
				for(int i =0; i < nodeList.get(0).getNeighberNodeArray().size() ; i++ ){
					if (node.getName().equals(nodeList.get(0).getSubNodeArray().get(i).getName())){
						currentDepth = nodeList.get(0).getSubNodeArray().get(i).getWeight();
						
					}
				}
				iterativeDeepeningWithStack(node);
				
				if(!tmpGoalReached){
					System.out.print("(X)");
				}
				tmpGoalReached = false;
				
				currentDepth = 0;
			}
			System.out.print("\n");
		}
		
		if(!goalReached){
			System.out.println("Search Failed unnaturally\n");
			initialDepth += incrementLevel;
			
			currentDepth = 0;
			//reset nodes
			for (Node node : nodeList) {
				node.setVisted(false);
			}
			iterativeDeepeningSearch();
			
		}else{
			System.out.println("Search succeeded. Goal node found = " + goalName + "\n");
			//reset nodes
			for (Node node : nodeList) {
				node.setVisted(false);
			}
		}
	
	
	}

	/**
	 * Performs Iterative Deepening Depth First Search
	 */
	private void iterativeDeepeningWithStack(Node node) {
		stack.push(node);
		//System.out.println("Stack, adding node: " + node.getName());
		node.setVisted(true);
		
		
		while(!stack.isEmpty() ){
			Node actualNode = stack.pop();
			//System.out.println("Setting actualNode to :" + actualNode.getName());
			if(currentDepth <= initialDepth){
				System.out.print(actualNode.getName() + " ");				
			}
			
			if(actualNode.isGoalNode()){
				System.out.print("(Goal!)");
				goalReached = true;
				tmpGoalReached = true;
				goalName = actualNode.getName();
				//System.out.print("\n");
				
			}
			if(!tmpGoalReached){
				
				for (Node node2 : node.getNeighberNodeArray()) {
					//Node node2 = actualNode.getNeighberNodeArray().get(0);
					//System.out.println("Node2: " + node2.getName());
					if(!node2.isVisted() && (currentDepth < initialDepth)){
						//System.out.println("Calling iterativeDeepening with: " + node2.getName());
						
						for(int i = 0 ; i < actualNode.getSubNodeArray().size(); i++){
							if (node2.getName().equals(actualNode.getSubNodeArray().get(i).getName())){
								
								//System.out.println("SubNode " + node2.getName() + " value =" + actualNode.getSubNodeArray().get(i).getWeight());
								subNodeValue  = actualNode.getSubNodeArray().get(i).getWeight();
							}
						}
						
						//System.out.println((subNodeValue + currentDepth) + " <= " + initialDepth );
						if((subNodeValue + currentDepth) <= initialDepth){
							currentDepth += subNodeValue;
							iterativeDeepeningWithStack(node2);						
						}
						
						//node2.setVisted(true);
						//stack.push(node2);
					}
				}
				
			}
			
			
		}
		
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
}
