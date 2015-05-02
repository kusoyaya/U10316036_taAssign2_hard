package ta2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javafx.stage.FileChooser;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField serielField;
	private JLabel timeLabel;
	private JSlider timeSlider;
	private JLabel zipLabel;
	private String questionNumber = "10";
	private String zipPath;
	private boolean isSelectFile = false,isLoadSuccess = false;
	

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		setSize(new Dimension(300, 230));
		setTitle("Wellcome!");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel namePad = new JPanel();
		contentPanel.add(namePad);
		JLabel nameLabel = new JLabel("輸入您的名字：");
		namePad.add(nameLabel);
		nameField = new JTextField();
		namePad.add(nameField);
		nameField.setColumns(15);
			
		
		JPanel serielPad = new JPanel();
		contentPanel.add(serielPad);
		JLabel serielLabel = new JLabel("輸入您的學號：");
		serielPad.add(serielLabel);
		serielField = new JTextField();
		serielField.setColumns(15);
		serielPad.add(serielField);
			
		
		JPanel timePad = new JPanel();
		contentPanel.add(timePad);
		timeLabel = new JLabel("答題時間20秒");
		timePad.add(timeLabel);
		timeSlider = new JSlider();
		timeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				timeLabel.setText("答題時間"+ timeSlider.getValue()+"秒:");
			}
		});
		timeSlider.setValue(20);
		timeSlider.setMinimum(10);
		timeSlider.setMaximum(30);
		timeSlider.setToolTipText("");
		timePad.add(timeSlider);
		
		JPanel zipPad = new JPanel();
		contentPanel.add(zipPad);
		
		zipLabel = new JLabel("若沒有載入自訂題庫，將使用預設題庫");
		zipPad.add(zipLabel);
			
		
		JPanel buttonPane = new JPanel();
		buttonPane.setSize(new Dimension(400, 30));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isSelectFile){
					questionNumber = JOptionPane.showInputDialog(okButton, "請輸入題數：", "確認題數", 1);
					if(ZipReadMachine.isSafe(zipPath, Integer.parseInt(questionNumber))){
						isLoadSuccess = true;
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(okButton, "載入題庫錯誤", "錯誤",  2);
					}
				}else{
					setVisible(false);
				}
				
			}
		});
		
		JButton zipButton = new JButton("載入題庫");
		buttonPane.add(zipButton);
		zipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zipPath = loadFile(contentPanel);
				isSelectFile = true;
				zipLabel.setText(zipPath);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
			
		
	}
	
	public ArrayList<String> getValue(){
		ArrayList<String> s = new ArrayList<String>();
		s.add(nameField.getText());
		s.add(serielField.getText());
		s.add(""+timeSlider.getValue());
		if(isSelectFile && isLoadSuccess){
			s.add(zipPath);
			s.add(questionNumber);
		}
		return s;
	}
	
	public String loadFile(JPanel c){
		String s = "";
		File selectedFile = null;
		
		JFileChooser fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Zip File","zip");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(c);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
		}
		s = selectedFile.getAbsolutePath();
		
		return s ;
	}

}
