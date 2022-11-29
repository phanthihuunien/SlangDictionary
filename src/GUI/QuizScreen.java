package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/27/2022 - 1:09 AM
 * Description: ...
 */
public class QuizScreen extends JFrame implements ActionListener {

    private final int answerIdx;
    JButton[] answerBtn;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        for(int i = 0; i< 4; i++){
            if(btn == answerBtn[i]){
                if(i == answerIdx){
                    new NotificationScreen(this, "Congratulation!, it is correct.", "Quizzes");
                }
                else
                    new NotificationScreen(this, "Hmm sorry, it is not correct!", "Quizzes");

            }
        }
        this.dispose();
    }
    public QuizScreen(String question, List<String> answerList, int answerIdx){
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Quizzes");
        this.setLocation(600, 300);
        this.setResizable(false);
        this.answerIdx = answerIdx;
        this.answerBtn = new JButton[4];
        for(int i = 0; i<4; i++){
            answerBtn[i] = new JButton(answerList.get(i));
            answerBtn[i].addActionListener(this);
        }
        JLabel questionLb = new JLabel(question);
        JPanel questionPl = new JPanel();
        questionPl.add(questionLb);
        JPanel answerPl = new JPanel();
        answerPl.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension btnSize = new Dimension(200,100);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0;i < 2;i++) {
            for (int j = 0;j < 2;j++) {
                gbc.gridx = i;
                gbc.gridy = j;
                answerBtn[2*i + j].setPreferredSize(btnSize);
                answerPl.add(answerBtn[2*i + j],gbc);
            }
        }
        this.add(questionPl, BorderLayout.NORTH);
        this.add(answerPl, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }
}
