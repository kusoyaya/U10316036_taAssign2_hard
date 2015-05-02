package ta2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;

public class MainConsole extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel time;
	private QuestionPad question;
	private JLabel score;
	private Timer timer;
	
	private int timeLimit;
	private String userName;
	private String userSeriel;
	private int userTime =0;
	private int maxQuestionNumber = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog dialog = new LoginDialog();
					dialog.setModal(true);
					dialog.setVisible(true);
					MainConsole frame = new MainConsole(dialog.getValue());
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
	public MainConsole(ArrayList<String> s) {
		userName = s.get(0);
		userSeriel = s.get(1);
		timeLimit = Integer.parseInt(s.get(2));
		userTime = timeLimit;
		
		
		setTitle("U10316036");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		time = new JLabel("哈囉，"+userName+"！這題剩下時間："+userTime+"秒");
		contentPane.add(time, BorderLayout.NORTH);
		
		if(s.size()>3){
			maxQuestionNumber =Integer.parseInt(s.get(4));
			question = new QuestionPad(true,s.get(3),maxQuestionNumber);
		}else{
			question = new QuestionPad(false,"",maxQuestionNumber);
		}
		
		contentPane.add(question, BorderLayout.CENTER);
		
		JPanel lowerPad = new JPanel();
		contentPane.add(lowerPad, BorderLayout.SOUTH);
		
		score = new JLabel(userSeriel+"，你的分數是："+question.userScore);
		lowerPad.add(score);
		
		JButton next = new JButton("下一題");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				question.jumpOrNot = true;
			}
		});
		lowerPad.add(next);
		
		timer = new Timer(1000,this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e){
		userTime --;
		time.setText("哈囉，"+userName+"！這題剩下時間："+userTime+"秒");
		if(userTime ==0 || question.jumpOrNot){
			question.questionNumber++;
			if(question.questionNumber > maxQuestionNumber){
				JOptionPane.showMessageDialog(this, "嘿，"+userSeriel+" "+userName+"\n你總共得到了"+question.userScore+" 分。","總結",-1);
				System.exit(0);
			}
			question.repaint();
			userTime = timeLimit;
			question.jumpOrNot = false;
		}
		score.setText(userSeriel+"，你的分數是："+question.userScore);
		
	}
}
