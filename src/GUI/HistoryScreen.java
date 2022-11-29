package GUI;

import Manage.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/27/2022 - 1:08 AM
 * Description: ...
 */
public class HistoryScreen extends JFrame implements ActionListener {

    JList historyList;
    JButton cancelBtn = new JButton("Cancel");
    History history = History.getCurrentVer();
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn == cancelBtn)
            this.dispose();
    }
    public HistoryScreen() throws IOException {
        System.out.println(history.getHistoryList());
        historyList = new JList(history.getHistoryList().toArray());
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("View history searching");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(600, 300);
        this.setResizable(false);
        JScrollPane scrollPane = new JScrollPane(historyList);
        scrollPane.setPreferredSize(new Dimension(300,300));
        cancelBtn.addActionListener(this);
        JLabel lb = new JLabel("HISTORY");
        this.add(lb, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(cancelBtn, BorderLayout.PAGE_END);
        this.pack();
        this.setVisible(true);
    }
}
