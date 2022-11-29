package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/27/2022 - 1:06 AM
 * Description: ...
 */
public class DefinitionSearchScreen extends JFrame implements ActionListener {

    JButton cancelBtn = new JButton("Cancel");
    List<String> definitionList;
    JList<String> slangList;

    public DefinitionSearchScreen(List<String> definitionListPr, String definition){
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Definition Searching");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        this.setResizable(false);
        cancelBtn.addActionListener(this);
        definitionList = definitionListPr;
        slangList = new JList<>(this.definitionList.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(slangList);
        scrollPane.setPreferredSize(new Dimension(300,300));
        JLabel lb = new JLabel("Definition: "+definition);
        JLabel lb2 = new JLabel("SLANG WORD LIST");
        this.add(lb, BorderLayout.PAGE_START);
        this.add(lb2, BorderLayout.LINE_START );
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(cancelBtn, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn == cancelBtn){
            this.dispose();
        }
    }

}
