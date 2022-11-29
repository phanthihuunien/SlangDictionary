package GUI;

import Manage.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/21/2022 - 11:49 PM
 * Description: ...
 */
public class SlangSearchScreen extends JFrame implements ActionListener {
    Dictionary dictionary = Dictionary.getCurrentVer();
    private final String slang;

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> listOfDefinition = new JList<>(listModel);
    private final JButton addDefinitionBtn = new JButton("Add another definition");
    private final JButton deleteDefinitionBtn = new JButton("Delete definition");
    private final JButton cancelBtn = new JButton("Cancel");
    private void loadAllDefinition(){
        List<String> definitionList = Dictionary.getCurrentVer().get(slang);
        listModel.clear();
        listModel.addAll(definitionList);
    }
    public SlangSearchScreen(String slangWord){

        slang = slangWord;
        JPanel panelDefinition = new JPanel();
        panelDefinition.setLayout(new BoxLayout(panelDefinition, BoxLayout.Y_AXIS));
        panelDefinition.add(new JLabel("Slang word:" + this.slang));
        panelDefinition.add(new JLabel("DEFINITION LIST"));
        JScrollPane scrollPane = new JScrollPane(listOfDefinition);
        scrollPane.getViewport().setBackground(Color.cyan);
        panelDefinition.add(scrollPane);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension btnSize = new Dimension(200,100);
        addDefinitionBtn.setMaximumSize(btnSize);
        deleteDefinitionBtn.setMaximumSize(btnSize);
        cancelBtn.setMaximumSize(btnSize);
        addDefinitionBtn.addActionListener(this);
        deleteDefinitionBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        panelButton.add(addDefinitionBtn);
        panelButton.add(deleteDefinitionBtn);
        panelButton.add(cancelBtn);
        loadAllDefinition();
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Slang Searching");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600,300);
        this.add(panelDefinition, BorderLayout.CENTER);
        this.add(panelButton, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);
    }


    private  void addDefinition(){
        String definition = JOptionPane.showInputDialog("Enter another definition: ");

        dictionary.addNewSlang(slang, definition);
        dictionary.saveNewDefinitionToFile("UsedSLangFile.txt");
        loadAllDefinition();
        new NotificationScreen(this, "Add successfully!","Add another definition");
    }
    private void deleteDefinition(){
        int selectedItemIndex = listOfDefinition.getSelectedIndex();

        if (selectedItemIndex == -1) {
            new NotificationScreen(this,"Choose a definition to delete!!","Delete definition");
        } else {
            dictionary.deleteDefinition(slang, selectedItemIndex);
            dictionary.saveNewDefinitionToFile("UsedSLangFile.txt");
            loadAllDefinition();
            new NotificationScreen(this,"Delete successfully!","Delete definition");

        }
    }
    public void actionPerformed(ActionEvent e){
        JButton btn = (JButton) e.getSource();
        if(btn == addDefinitionBtn){
            addDefinition();
        }else if(btn == deleteDefinitionBtn){
            deleteDefinition();
        }else if(btn == cancelBtn){
            this.dispose();
        }
    }

}
