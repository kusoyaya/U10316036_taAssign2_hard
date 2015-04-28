package ta2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Dimension;

public class GUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel questionNumber;
	QuestionPanel question;
	private JLabel scoreLabel;
	private Timer timer;
	String userName;
	String userSeriel;
	
	int time = 11;
	int TIMELIMIT;

	
	public static void main(String[] args) {
		
		
		try {
			GUI frame = new GUI();
			
			
			frame.go();
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
			
		
	}

	/**
	 * Create the frame.
	 */
	
	
	
	
	public void go(){
		String[] temp =null;
		try{
			Login dialog = new Login(this,false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.go();
			dialog.setVisible(true);
			temp = dialog.getValue();
			userName = temp[0];
			userSeriel = temp[1];
			TIMELIMIT= Integer.parseInt(temp[2]);
		}catch(Exception ea){
			
		}
		this.setVisible(true);
		setTitle("U10316036_question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		questionNumber = new JLabel("");
		questionNumber.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(questionNumber);
		question= new QuestionPanel();
		question.go();
		question.setEnabled(true);
		question.setVisible(true);
		contentPane.add(question);
		
		JPanel result = new JPanel();
		contentPane.add(result);
		
		scoreLabel = new JLabel("你的分數:"+question.score);
		result.add(scoreLabel);
		
		JButton next = new JButton("下一題");
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				time=0;
			}
		});
		result.add(next);
		
		
		time = TIMELIMIT;
		timer = new Timer(1000,this);
		timer.start();
		
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		time--;
		questionNumber.setText("哈囉，"+userSeriel+""+userName+" 第"+question.questionNumber+"題 "+"剩餘時間"+time+"秒");
		scoreLabel.setText("你的分數:"+question.score);
		if(time <=0||question.jumpOrNot){
			question.questionNumber++;
			if(question.questionNumber > 10){
				JOptionPane.showConfirmDialog(this, "你的分數是"+question.score+"分", "結果", -1, 1, null);
				System.exit(0);
			}else{
				question.repaint();
				time = TIMELIMIT;
				question.jumpOrNot=false;
			}
		}
		
	}

}
