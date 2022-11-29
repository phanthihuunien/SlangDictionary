package GUI;

import Manage.DefinitionList;
import Manage.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MenuQuizzesScreen extends JFrame implements ActionListener {
    private final JButton slangBtn = new JButton("Slang Quizzes");
    private final JButton definitionBtn = new JButton("Definition Quizzes");
    JLabel lb = new JLabel("Choose your preferred option: ");
    Dictionary dictionary = Dictionary.getCurrentVer();
    public MenuQuizzesScreen(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Menu quizzes option");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        slangBtn.addActionListener(this);
        definitionBtn.addActionListener(this);
        panel.add(slangBtn);
        panel.add(definitionBtn);
        this.add(lb, BorderLayout.NORTH);
        this.add(panel, BorderLayout.PAGE_END);
        this.pack();
        this.setSize(400,100);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn == slangBtn){
            slangQuizzes();
        }else if(btn == definitionBtn)
            definitionQuizzes();
    }

    private void slangQuizzes(){
        List<String> slangList = new ArrayList<>();
        while (slangList.size() < 4) {
            String randomSlang = dictionary.randomASlangWord();
            if (!slangList.contains(randomSlang)) {
                slangList.add(randomSlang);
            }
        }
        int answer = new Random().nextInt(4);
        String question = slangList.get(answer);
        List<String> answerList = new ArrayList<>();
        for (int i = 0;i < 4;i++) {
            DefinitionList definitionList = dictionary.get(slangList.get(i));
            answerList.add(definitionList.get(new Random().nextInt(definitionList.size())));
        }
//        System.out.println(question);
//        System.out.println(answers);
//        System.out.println(answer);
        this.dispose();
        new QuizScreen(question,answerList,answer);
    }
    private void definitionQuizzes(){
        List<String> slangList = new ArrayList<>();
        while (slangList.size()<4) {
            String randomSlang = dictionary.randomASlangWord();
            if (!slangList.contains(randomSlang)) {
                slangList.add(randomSlang);
            }
        }
        int answer = new Random().nextInt(4);
        DefinitionList definitionList = dictionary.get(slangList.get(answer));
        String question = definitionList.get(new Random().nextInt(definitionList.size()));
//        System.out.println(question);
//        System.out.println(slangList);
//        System.out.println(answer);
        this.dispose();
        new QuizScreen(question,slangList,answer);
    }
}
