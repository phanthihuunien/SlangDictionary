package GUI;

import Manage.DefinitionList;
import Manage.Dictionary;
import Manage.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/21/2022 - 11:31 AM
 * Description: ...
 */
public class HomeMenuScreen extends JFrame implements ActionListener {
    Dictionary dictionary = Dictionary.getCurrentVer();
    JLabel dictHeadingLb = new JLabel("Slang Word Dictionary");
    JButton slangSearchBtn = new JButton("Search With Slang Word");
    JButton definitionSearchBtn = new JButton("Search With Definition");
    JButton historyViewBtn = new JButton("View Searching History");
    JButton addNewSlangWordBtn = new JButton("Add New Slang Word");
    JButton deleteSlangWordBtn = new JButton("Delete A Slang Word");
    JButton resetSlangListBtn = new JButton("Reset Slang List");
    JButton randomSlangBtn = new JButton("Random A Slang Word");
    JButton quizBtn = new JButton("Quiz");
    JButton exitBtn = new JButton("Exit");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public HomeMenuScreen(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Slang Word Dictionary");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);
        dictHeadingLb.setFont(new Font("plain",Font.BOLD,40));

        slangSearchBtn.addActionListener(this);
        definitionSearchBtn.addActionListener(this);
        historyViewBtn.addActionListener(this);
        addNewSlangWordBtn.addActionListener(this);
        deleteSlangWordBtn.addActionListener(this);
        resetSlangListBtn.addActionListener(this);
        randomSlangBtn.addActionListener(this);
        quizBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        Dimension btnDimension = new Dimension(200,100);
        slangSearchBtn.setMaximumSize(btnDimension);
        definitionSearchBtn.setMaximumSize(btnDimension);
        historyViewBtn.setMaximumSize(btnDimension);
        addNewSlangWordBtn.setMaximumSize(btnDimension);
        deleteSlangWordBtn.setMaximumSize(btnDimension);
        resetSlangListBtn.setMaximumSize(btnDimension);
        randomSlangBtn.setMaximumSize(btnDimension);
        quizBtn.setMaximumSize(btnDimension);
        exitBtn.setMaximumSize(btnDimension);

        slangSearchBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        definitionSearchBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        historyViewBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        addNewSlangWordBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        deleteSlangWordBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        resetSlangListBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        randomSlangBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        quizBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        dictHeadingLb.setHorizontalAlignment((int) JPanel.CENTER_ALIGNMENT);
        //dictHeadingLb.setLocation(600, 50);
        panel.add(dictHeadingLb);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(slangSearchBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(definitionSearchBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(historyViewBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(addNewSlangWordBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(deleteSlangWordBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(resetSlangListBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(randomSlangBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(quizBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(exitBtn);

        this.add(panel);

        this.pack();
        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn == slangSearchBtn){
            slangSearching();
        }else if(btn == definitionSearchBtn)
              searchByDefinition();
        else if(btn == historyViewBtn) {
            try {
                new HistoryScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if(btn == addNewSlangWordBtn){
            //new AddNewSlangScreen(dictionary);
            addNewSlang();
        }else  if(btn == deleteSlangWordBtn){
            deleteASlang();
        }else if(btn == resetSlangListBtn){
            try {
                resetSlangList();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if(btn == randomSlangBtn){
            new SlangSearchScreen(dictionary.randomASlangWord());
        }else if(btn == quizBtn){
            new MenuQuizzesScreen();
        }else if(btn == exitBtn)
            this.dispose();
    }

    private void slangSearching(){
        String slangWord = JOptionPane.showInputDialog("Enter a slang word");
        if(slangWord == null)
            return;
        if (dictionary.get(slangWord) == null) {
              new NotificationScreen(this,"Slang word is not found!","Slang searching");

        } else {
            try{
                History.getCurrentVer().addWordToHistory(slangWord);
                History.getCurrentVer().saveHistory();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        new SlangSearchScreen(slangWord);
    }
    private void searchByDefinition() {
        String definition = JOptionPane.showInputDialog("Enter a definition:");
        if(definition == null)
            return;
        //System.out.println("======="+definition);
        List<String> slangResult = dictionary.searchDefinition(definition);
        //System.out.println(slangResult);
         new DefinitionSearchScreen(slangResult, definition);

    }
    private void addNewSlang() {
        JTextField slangTf = new JTextField(10);
        JTextField definitionTf = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Slang: "));
        panel.add(slangTf);
        panel.add(Box.createVerticalGlue());
        panel.add(new JLabel("Definition"));
        panel.add(definitionTf);

        int option = JOptionPane.showConfirmDialog(null, panel,"Add new slang word", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String slang = slangTf.getText();
            String definition = definitionTf.getText();
            DefinitionList definitionList = dictionary.get(slang);
            if(definitionList == null){
                dictionary.addNewSlang(slang, definition);
            }else{
                int chosen = JOptionPane.showOptionDialog(null,"This slang word has existed. Please choose one option!",
                        "Add new slang word",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Duplicate", "Overwrite"}, null);
                if(chosen == 0)
                    dictionary.addNewSlang(slang, definition);
                else {
                    DefinitionList newList = new DefinitionList(definition);
                    dictionary.put(slang, newList);
                }
            }
            dictionary.saveNewDefinitionToFile("UsedSLangFile.txt");
            new NotificationScreen(this, "Add new slang word", "Add successfully");

        }
    }
    private void deleteASlang() {
        String slang = JOptionPane.showInputDialog("Input a slang word to delete: ");
        if (slang == null) {
            return;
        }
        DefinitionList definitions = dictionary.get(slang);
        if (definitions==null) {
            new NotificationScreen(this, "Slang is not found!", "Delete a slang");
        } else {
            int result = JOptionPane.showConfirmDialog(this, "Confirm to delete?", "Delete a slang", JOptionPane.YES_NO_OPTION);
            if (result==JOptionPane.NO_OPTION) {
                return;
            }
            dictionary.remove(slang);
            dictionary.saveNewDefinitionToFile("UsedSLangFile.txt");
           new NotificationScreen(this, "Delete successfully!", "Delete a slang");
        }
    }
    private void resetSlangList() throws IOException {
        dictionary.getDataFromFile("slang.txt");
        dictionary.saveNewDefinitionToFile("UsedSLangFile.txt");
        new NotificationScreen(this, "Reset slang list successfully!","Reset slang list");
    }
}
