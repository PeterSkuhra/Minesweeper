package minesweeper.swingui;

import minesweeper.Minesweeper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BestTimesDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;

    public BestTimesDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        textArea1 = new javax.swing.JTextArea();
        textArea1.setText(Minesweeper.getInstance().getBestTimes().toString());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonOKMouseClicked();
            }
        });
    }

    private void buttonOKMouseClicked() {
        setVisible(false);
        dispose();
    }
}
