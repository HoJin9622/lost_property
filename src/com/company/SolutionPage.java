package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SolutionPage extends JFrame {
    SolutionPage() {
        DB_Conn_Query db = new DB_Conn_Query();

        JLabel title = new JLabel(); // 타이틀 텍스트
        title.setText("해결 내역");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(new Color(0x333333));
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel tablePanel = new JPanel();

        DefaultTableModel model = db.getSolution();
        JTable table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(1440, 800));
        table.setFillsViewportHeight(true);
        tablePanel.add(new JScrollPane(table));

        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(0, 30, 1440, 800);

        this.add(tablePanel);
        this.add(title);

        this.setTitle("해결 내역");
        this.setResizable(false);
        this.setSize(1440, 960);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.white);
    }
}
