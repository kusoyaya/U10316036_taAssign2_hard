package ta2;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestionPad extends JPanel {
	
	private ReadMachine r = new ReadMachine();
	private JTextArea text;
	private JButton buttonA;
	private JButton buttonB;
	private JButton buttonC;
	private JButton buttonD;
	
	private ArrayList<String> question;
	int questionNumber =1;
	double userScore = 0;
	boolean jumpOrNot = false;
	/**
	 * Create the panel.
	 */
	public QuestionPad() {
		setSize(new Dimension(800, 400));
		setLayout(new BorderLayout(0, 0));
		
		JPanel questionPanel = new JPanel();
		add(questionPanel, BorderLayout.CENTER);
		questionPanel.setLayout(null);
		
		text = new JTextArea();
		text.setFont(new Font("Dialog", Font.PLAIN, 24));
		text.setEditable(false);
		text.setLineWrap(true);
		text.setText("歡迎！");
		JScrollPane scroller = new JScrollPane(text);
		scroller.setBounds(6, 6, 394, 450);
		questionPanel.add(scroller);
		
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		buttonA = new JButton("A");
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(question.get(question.size()-1).equals("A")){
					r.playSound(true);
					userScore += 10;
					JOptionPane.showMessageDialog(buttonA, "正確","恭喜啦！",JOptionPane.PLAIN_MESSAGE);
				}else{
					r.playSound(false);
					JOptionPane.showMessageDialog(buttonA, "答案是"+question.get(question.size()-1), "答錯了！",JOptionPane.ERROR_MESSAGE);
				}
				jumpOrNot = true;
			}
		});
		buttonPanel.add(buttonA);
		
		buttonB = new JButton("B");
		buttonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(question.get(question.size()-1).equals("B")){
					r.playSound(true);
					userScore += 10;
					JOptionPane.showMessageDialog(buttonB, "正確","恭喜啦！",JOptionPane.PLAIN_MESSAGE);
				}else{
					r.playSound(false);
					JOptionPane.showMessageDialog(buttonB, "答案是"+question.get(question.size()-1), "答錯了！",JOptionPane.ERROR_MESSAGE);
				}
				jumpOrNot = true;
			}
		});
		buttonPanel.add(buttonB);
		
		buttonC = new JButton("C");
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(question.get(question.size()-1).equals("C")){
					r.playSound(true);
					userScore += 10;
					JOptionPane.showMessageDialog(buttonC, "正確","恭喜啦！",JOptionPane.PLAIN_MESSAGE);
				}else{
					r.playSound(false);
					JOptionPane.showMessageDialog(buttonC, "答案是"+question.get(question.size()-1), "答錯了！",JOptionPane.ERROR_MESSAGE);
				}
				jumpOrNot = true;
			}
		});
		buttonPanel.add(buttonC);
		
		buttonD = new JButton("D");
		buttonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(question.get(question.size()-1).equals("D")){
					r.playSound(true);
					userScore += 10;
					JOptionPane.showMessageDialog(buttonD, "正確","恭喜啦！",JOptionPane.PLAIN_MESSAGE);
				}else{
					r.playSound(false);
					JOptionPane.showMessageDialog(buttonD, "答案是"+question.get(question.size()-1), "答錯了！",JOptionPane.ERROR_MESSAGE);
				}
				jumpOrNot = true;
			}
		});
		buttonPanel.add(buttonD);
		
		repaint();
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		question = r.getQuestionArray("/text/"+questionNumber+".txt");
		g.drawImage(r.getQuestionImage("/image/"+questionNumber+".png"), 400, 6, 400, 450, null);
		if(question.size() >= 5){
			text.setText(question.get(0));
			for(int i = 1; i < question.size()-5;i++)
				text.append("\n"+question.get(i));
			buttonA.setText(question.get(question.size()-5));
			buttonB.setText(question.get(question.size()-4));
			buttonC.setText(question.get(question.size()-3));
			buttonD.setText(question.get(question.size()-2));
		}
	}

}
