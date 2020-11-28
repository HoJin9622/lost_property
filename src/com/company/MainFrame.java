package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    JButton solutionListButton = new JButton(); // 해결내역 버튼
    JButton registerLostItemButton = new JButton(); // 분실물 등록 버튼
    JButton solutionButton = new JButton(); // 해결 버튼
    JButton refreshButton = new JButton();
    JButton searchButton = new JButton();
    JTextField searchField = new JTextField();
    String selectedId;
    String selectedGetterId;
    String selectedItemId;
    DB_Conn_Query db = new DB_Conn_Query();

    MainFrame() {
        JLabel title = new JLabel(); // 타이틀 텍스트
        title.setText("분실물 관리 시스템");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setForeground(new Color(0x333333));
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setVerticalAlignment(JLabel.TOP);
        title.setHorizontalAlignment(JLabel.CENTER);

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 40));
        searchField.setFont(new Font("Serif", Font.PLAIN, 35));

        refreshButton.setBounds(10, 10, 100, 80);
        refreshButton.addActionListener(this);
        refreshButton.setText("내역 새로고침");
        refreshButton.setBackground(new Color(0x02c488));
        refreshButton.setForeground(Color.white);
        refreshButton.setBorder(BorderFactory.createEtchedBorder());
        refreshButton.setFont(new Font("Serif", Font.PLAIN, 20));

        searchButton.setBounds(10, 10, 100, 80);
        searchButton.addActionListener(this);
        searchButton.setText("검색");
        searchButton.setBackground(new Color(0x02c488));
        searchButton.setForeground(Color.white);
        searchButton.setBorder(BorderFactory.createEtchedBorder());
        searchButton.setFont(new Font("Serif", Font.PLAIN, 20));

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

        JPanel searchPanel = new JPanel();

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setBackground(Color.white);
        searchPanel.setBounds(0, 30, 1440, 50);

        JPanel tablePanel = new JPanel(); // 중단 테이블 Panel

        model = db.getManagementBook();
        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(1440, 740));
        table.setFillsViewportHeight(true);
        table.addMouseListener(new JTableMouseListener());
        tablePanel.add(new JScrollPane(table));

        tablePanel.setBackground(Color.white);
        tablePanel.setBounds(0, 90, 1440, 740);

        JPanel buttonPanel = new JPanel(); // 하단 버튼 Panel

        buttonPanel.add(refreshButton);
        buttonPanel.add(solutionListButton);
        buttonPanel.add(registerLostItemButton);
        buttonPanel.add(solutionButton);

        buttonPanel.setBackground(Color.white);
        buttonPanel.setBounds(0, 830, 1440, 130);

        this.add(searchPanel);
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
        if (e.getSource() == refreshButton) {
            model.setRowCount(0);
            model = db.getManagementBook();
            table.setModel(model);
        }

        if (e.getSource() == solutionListButton) {
            SolutionPage solutionPage = new SolutionPage();
        }

        if (e.getSource() == registerLostItemButton) {
            RegisterForm form = new RegisterForm();
        }

        if (e.getSource() == solutionButton) {
            db.solution(selectedId, selectedGetterId, selectedItemId);
            model.setRowCount(0);
            model = db.getManagementBook();
            table.setModel(model);
        }

        if (e.getSource() == searchButton) {
            model.setRowCount(0);
            model = db.search(searchField.getText());
            table.setModel(model);
        }
    }

    private class JTableMouseListener implements MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            JTable jtable = (JTable) e.getSource();
            int row = jtable.getSelectedRow();
            selectedId = (String) model.getValueAt(row, 0);
            selectedGetterId = (String) model.getValueAt(row, 1);
            selectedItemId = (String) model.getValueAt(row, 4);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
