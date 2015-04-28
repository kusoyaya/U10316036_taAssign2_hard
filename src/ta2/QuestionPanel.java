package ta2;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Dimension;

public class QuestionPanel extends JPanel {
		ReadMachine r = new ReadMachine();
		ArrayList<String> question = new ArrayList<String>();
		int questionNumber=1;
		int score=0;
		boolean jumpOrNot = false;
		
		JTextArea displayText;
		ImageIcon ic;
		JLabel displayPic;
		JButton buttonA;
		JButton buttonB;
		JButton buttonC;
		JButton buttonD;
	/**
	 * Create the panel.
	 */
	public QuestionPanel() {
		setMaximumSize(new Dimension(800, 500));
		question = r.getQuestionArray("question txt/"+this.questionNumber+".txt");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel display = new JPanel();
		display.setMaximumSize(new Dimension(800, 500));
		add(display);
		display.setLayout(new BoxLayout(display, BoxLayout.X_AXIS));
		
		displayText = new JTextArea();
		displayText.setMaximumSize(new Dimension(450, 350));
		displayText.setEditable(false);
		displayText.setColumns(20);
		displayText.setText(question.get(0));
		display.add(displayText);
		
		ic = new ImageIcon(r.getQuestionImage("question image/"+this.questionNumber+".png"));
		displayPic = new JLabel(ic);
		displayPic.setMaximumSize(new Dimension(450, 350));
		
		display.add(displayPic);
		
		JPanel button = new JPanel();
		add(button);
		button.setLayout(new GridLayout(2, 2, 0, 0));
		
		buttonA = new JButton(question.get(1));
		buttonA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(question.get(question.size()-1).equals("A")){
					score += 10;
					JOptionPane.showMessageDialog(buttonA, "正確!", "恭喜",0, null);
				}
				jumpOrNot = true;
				JOptionPane.showMessageDialog(buttonA, "正確答案是 "+question.get(question.size()-1), "錯啦",0, null);
			}
		});
		button.add(buttonA);
		
		buttonB = new JButton(question.get(2));
		buttonB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(question.get(question.size()-1).equals("B")){
					JOptionPane.showMessageDialog(buttonA, "正確!", "恭喜",0, null);
					score += 10;
				}
				jumpOrNot = true;
				JOptionPane.showMessageDialog(buttonA, "正確答案是 "+question.get(question.size()-1), "錯啦",0, null);
			}
		});
		button.add(buttonB);
		
		buttonC = new JButton(question.get(3));
		buttonC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(question.get(question.size()-1).equals("C")){
					JOptionPane.showMessageDialog(buttonA, "正確!", "恭喜",0, null);
					score += 10;
				}
				jumpOrNot = true;
				JOptionPane.showMessageDialog(buttonA, "正確答案是 "+question.get(question.size()-1), "錯啦",0, null);
			}
		});
		button.add(buttonC);
		
		buttonD = new JButton(question.get(4));
		buttonD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(question.get(question.size()-1).equals("D")){
					JOptionPane.showMessageDialog(buttonA, "正確!", "恭喜",0, null);
					score += 10;
				}
				jumpOrNot = true;
				JOptionPane.showMessageDialog(buttonA,"正確答案是 "+question.get(question.size()-1), "錯啦",0, null);
			}
		});
		button.add(buttonD);

	}
	
	public void paint(Graphics g){
		super.paint(g);
		question = r.getQuestionArray("question txt/"+this.questionNumber+".txt");
		ic.setImage(r.getQuestionImage("question image/"+this.questionNumber+".png"));
		displayPic.repaint();
		displayText.setText(question.get(0));
		for(int i=1; i<question.size()-5;i++)
			displayText.append("\n"+question.get(i));
		buttonA.setText(question.get(question.size()-5));
		buttonB.setText(question.get(question.size()-4));
		buttonC.setText(question.get(question.size()-3));
		buttonD.setText(question.get(question.size()-2));
	}

}
