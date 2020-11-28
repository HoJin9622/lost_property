package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame implements ActionListener {
    DB_Conn_Query db = new DB_Conn_Query();
    JButton submitButton;
    JTextField studentNumberField;
    JTextField studentNameField;
    JTextField studentPhoneNumberField;
    JTextField itemNameField;

    RegisterForm() {
        this.setLayout(new GridLayout(5, 2));

        submitButton = new JButton("등록");
        submitButton.addActionListener(this);

        studentNumberField = new JTextField();
        studentNumberField.setPreferredSize(new Dimension(250, 40));
        studentNumberField.setFont(new Font("Serif", Font.PLAIN, 35));

        studentNameField = new JTextField();
        studentNameField.setPreferredSize(new Dimension(250, 40));
        studentNameField.setFont(new Font("Serif", Font.PLAIN, 35));

        studentPhoneNumberField = new JTextField();
        studentPhoneNumberField.setPreferredSize(new Dimension(250, 40));
        studentPhoneNumberField.setFont(new Font("Serif", Font.PLAIN, 35));

        itemNameField = new JTextField();
        itemNameField.setPreferredSize(new Dimension(250, 40));
        itemNameField.setFont(new Font("Serif", Font.PLAIN, 35));

        this.add(new JLabel("습득자 학번"));
        this.add(studentNumberField);
        this.add(new JLabel("습득자 이름"));
        this.add(studentNameField);
        this.add(new JLabel("습득자 휴대폰 번호"));
        this.add(studentPhoneNumberField);
        this.add(new JLabel("습득물 이름"));
        this.add(itemNameField);
        this.add(submitButton);
        this.setTitle("습득물 등록");
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submitButton.setEnabled(false);
            db.register(studentNumberField.getText(), studentNameField.getText(), studentPhoneNumberField.getText(), itemNameField.getText());
            this.dispose();
        }
    }
}
