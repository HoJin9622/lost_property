package com.company;

import javax.swing.*;
import java.awt.*;

public class SolutionPage extends JFrame {
    JLabel label = new JLabel("해결 내역 페이지");

    SolutionPage() {
        label.setBounds(0, 0, 100, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        this.add(label);

        this.setTitle("해결 내역");
        this.setResizable(false);
        this.setSize(1440, 960);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.white);
    }
}
