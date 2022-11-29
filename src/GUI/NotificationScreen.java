package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI
 * Created by Nien Phan
 * Date 11/25/2022 - 10:12 AM
 * Description: ...
 */
public class NotificationScreen extends JDialog implements ActionListener {
    JLabel notiLb;
    JButton cancelBtn = new JButton("Cancel");
    JPanel panel = new JPanel();

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn == cancelBtn){
            this.dispose();
        }
    }
    public NotificationScreen(JFrame frame, String notif, String title){
        frame.setTitle(title);
        this.setLocation(600, 300);

        cancelBtn.addActionListener(this);
        notiLb = new JLabel(notif);
        panel.setSize(new Dimension(200,200));
        panel.add(notiLb, BorderLayout.PAGE_START);
        panel.add(cancelBtn, BorderLayout.SOUTH);
        this.add(panel);
        this.pack();
        this.setSize(200,100);
        this.setVisible(true);
    }
}
