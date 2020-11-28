package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    JButton solutionListButton = new JButton(); // 해결내역 버튼
    JButton registerLostItemButton = new JButton(); // 분실물 등록 버튼
    JButton solutionButton = new JButton(); // 해결 버튼
    String[] headings = new String[]{"분실물", "상태", "습득자", "휴대폰번호", "날짜"};
    DefaultTableModel model = new DefaultTableModel(headings, 0);
    JTable table = new JTable(model);

    MainFrame() {
        JLabel title = new JLabel(); // 타이틀 텍스트
        title.setText("분실물 관리 시스템");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(new Color(0x333333));
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);

        solutionListButton.setBounds(10, 10, 100, 80);
        solutionListButton.addActionListener(this);
        solutionListButton.setText("해결 내역 확인");
        solutionListButton.setBackground(new Color(0x02c488));
        solutionListButton.setForeground(Color.white);
        solutionListButton.setBorder(BorderFactory.createEtchedBorder());
        solutionListButton.setFont(new Font("Serif", Font.PLAIN, 20));

        registerLostItemButton.setBounds(30, 10, 100, 80);
        registerLostItemButton.addActionListener(this);
        registerLostItemButton.setText("분실물 등록");
        registerLostItemButton.setBackground(new Color(0x02c488));
        registerLostItemButton.setForeground(Color.white);
        registerLostItemButton.setBorder(BorderFactory.createEtchedBorder());
        registerLostItemButton.setFont(new Font("Serif", Font.PLAIN, 20));

        solutionButton.setBounds(50, 10, 100, 80);
        solutionButton.addActionListener(this);
        solutionButton.setText("해결!");
        solutionButton.setBackground(new Color(0x02c488));
        solutionButton.setForeground(Color.white);
        solutionButton.setBorder(BorderFactory.createEtchedBorder());
        solutionButton.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel tablePanel = new JPanel(); // 중단 테이블 Panel

        table.setPreferredScrollableViewportSize(new Dimension(1440, 800));
        table.setFillsViewportHeight(true);
        tablePanel.add(new JScrollPane(table));

        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(0, 30, 1440, 800);

        JPanel buttonPanel = new JPanel(); // 하단 버튼 Panel

        buttonPanel.add(solutionListButton);
        buttonPanel.add(registerLostItemButton);
        buttonPanel.add(solutionButton);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setBounds(0, 830, 1440, 130);

        this.add(tablePanel);
        this.add(buttonPanel);
        this.add(title);

        this.setTitle("분실물 관리 시스템");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1440, 960);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.white);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solutionListButton) {
            System.out.println("해결내역버튼 클릭");
            SolutionPage solutionPage = new SolutionPage();
        }

        if (e.getSource() == registerLostItemButton) {
            System.out.println("분실물 등록 버튼 클릭");
        }

        if (e.getSource() == solutionButton) {
            System.out.println("해결 버튼 클릭");
        }
    }
}
