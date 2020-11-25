package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    JButton solutionButton = new JButton(); // 해결내역 버튼
    JButton registerLostItemButton = new JButton(); // 분실물 등록 버튼

    MainFrame() {
        JLabel title = new JLabel(); // 타이틀 텍스트
        title.setText("분실물 관리 시스템");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(new Color(0x333333));
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);

        solutionButton.setBounds(10, 10, 100, 80);
        solutionButton.addActionListener(this);
        solutionButton.setText("해결 내역 확인");
        solutionButton.setBackground(new Color(0x02c488));
        solutionButton.setForeground(Color.white);
        solutionButton.setBorder(BorderFactory.createEtchedBorder());

        registerLostItemButton.setBounds(30, 30, 100, 80);
        registerLostItemButton.addActionListener(this);
        registerLostItemButton.setText("분실물 등록");
        registerLostItemButton.setBackground(new Color(0x02c488));
        registerLostItemButton.setForeground(Color.white);
        registerLostItemButton.setBorder(BorderFactory.createEtchedBorder());

        JPanel tablePanel = new JPanel(); // 중단 테이블 Panel
        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(0, 30, 1440, 800);

        JPanel buttonPanel = new JPanel(); // 하단 버튼 Panel
        buttonPanel.setBackground(Color.white);
        buttonPanel.setBounds(0, 830, 1440, 130);

        this.setTitle("분실물 관리 시스템");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1440, 960);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.white);

        buttonPanel.add(solutionButton);
        buttonPanel.add(registerLostItemButton);
        this.add(tablePanel);
        this.add(buttonPanel);
        this.add(title);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==solutionButton) {
            System.out.println("해결내역버튼 클릭");
        }

        if(e.getSource()==registerLostItemButton) {
            System.out.println("분실물 등록 버튼 클릭");
        }
    }
}
