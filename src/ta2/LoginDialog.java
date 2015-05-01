package ta2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField serielField;
	private JLabel timeLabel;
	private JSlider timeSlider;

	

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		setSize(new Dimension(300, 200));
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
			
		
		JPanel buttonPane = new JPanel();
		buttonPane.setSize(new Dimension(400, 30));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
			
		
	}
	
	public String[] getValue(){
		String[] s = {nameField.getText(),serielField.getText(),""+timeSlider.getValue()};
		return s;
	}

}
