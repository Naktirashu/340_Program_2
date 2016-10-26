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
import java.io.File;
import java.awt.event.ActionEvent;

public class ListGui extends JFrame {
	
	private File selectedFile;
	

	private JPanel contentPane;
	private JTextField fileSelectedTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
				}
			}
		});
		btnSelectFile.setBounds(10, 11, 120, 38);
		contentPane.add(btnSelectFile);
		
		JButton btnDepthFirst = new JButton("Depth First");
		btnDepthFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIXME
				System.out.println("Not Yet Implemented!");
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
				//FIXME
				System.out.println("Not Yet Implemented!");
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
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}
}
