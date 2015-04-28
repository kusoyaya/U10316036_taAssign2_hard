package ta2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField serielField;
	private JLabel timeLimitLabel = null;
	private JSlider timeLimitSlider = null;
	boolean openOrNot = true;
	
	
	/**
	 * Launch the application.
	 */
	
	public Login(){
		super();
	}
	
	public Login(Frame owner,boolean modal){
		super();
	}
	/**
	 * Create the dialog.
	 */
	public void go() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel name = new JPanel();
		contentPanel.add(name);
			
		JLabel nameLabel = new JLabel("輸入名字:");
		name.add(nameLabel);
			
			
		nameField = new JTextField();
		name.add(nameField);
		nameField.setColumns(10);
			
		
		
		JPanel seriel = new JPanel();
		contentPanel.add(seriel);
			
		JLabel serielLabel = new JLabel("輸入學號:");
		seriel.add(serielLabel);
			
			
		serielField = new JTextField();
		seriel.add(serielField);
		serielField.setColumns(10);
			
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
				
		timeLimitLabel = new JLabel("設定每題時間:20秒");
		panel.add(timeLimitLabel);
				
				
		timeLimitSlider = new JSlider();
		timeLimitSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(timeLimitSlider.getValueIsAdjusting()){
					timeLimitLabel.setText("設定每題時間:"+timeLimitSlider.getValue()+"秒");
					}
			}
		});
		timeLimitSlider.setValue(20);
		timeLimitSlider.setMinimum(10);
		timeLimitSlider.setMaximum(30);
		panel.add(timeLimitSlider);
			
			
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
			
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
			
		
	}
	
	public String[] getValue(){
		String[] s = {nameField.getText(),serielField.getText(),""+timeLimitSlider.getValue()};
		return s;
	}
 
}
