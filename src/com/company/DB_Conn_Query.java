package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DB_Conn_Query {
    Connection con = null;

    public DB_Conn_Query() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String id = "LOST_PROPERTY";
        String password = "1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url, id, password);
            System.out.println("DB 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public DefaultTableModel getManagementBook() {
        String query = "SELECT MANAGEMENT_BOOK.ID, GETTER.ID, GETTER.NAME, GETTER.PHONENUMBER, LOSTITEM.ID, LOSTITEM.NAME, LOSTITEM.STATEMENT, MANAGEMENT_BOOK.GET_DATE FROM MANAGEMENT_BOOK, GETTER, LOSTITEM WHERE MANAGEMENT_BOOK.GETTER = GETTER.ID AND MANAGEMENT_BOOK.LOST_ID = LOSTITEM.ID";
        String[] headings = new String[]{"관리번호", "습득자 고유번호", "습득자", "전화번호", "습득물 고유번호", "습득물", "상태", "날짜"};
        DefaultTableModel model = new DefaultTableModel(headings, 0);

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String row[] = new String[8];

            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getDate(8).toString();
                model.addRow(row);
            }

            return model;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    public DefaultTableModel getSolution() {
        String query = "SELECT SOLUTION.ID, LOSTITEM.NAME, LOSTITEM.STATEMENT, SOLUTION.SOLUTION_DATE FROM SOLUTION, LOSTITEM WHERE SOLUTION.LOST_ID = LOSTITEM.ID";
        String[] headings = new String[]{"해결번호", "습득물", "상태", "해결일자"};
        DefaultTableModel model = new DefaultTableModel(headings, 0);

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String row[] = new String[4];

            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getDate(4).toString();

                model.addRow(row);
            }

            return model;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    public void register(String studentNumber, String studentName, String phoneNumber, String itemName) {
        try {
            CallableStatement cstmt = con.prepareCall("{call INSERT_LOST(?, ?, ?, ?)}");
            cstmt.setString(1, studentNumber);
            cstmt.setString(2, studentName);
            cstmt.setString(3, phoneNumber);
            cstmt.setString(4, itemName);
            cstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "등록 성공", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "등록 실패", "등록 실패", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public DefaultTableModel search(String searchTerm) {
        String[] headings = new String[]{"관리번호", "습득자 고유번호", "습득자", "전화번호", "습득물 고유번호", "습득물", "상태", "날짜"};
        DefaultTableModel model = new DefaultTableModel(headings, 0);

        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT MANAGEMENT_BOOK.ID, GETTER.ID, GETTER.NAME, GETTER.PHONENUMBER, LOSTITEM.ID, LOSTITEM.NAME, LOSTITEM.STATEMENT, MANAGEMENT_BOOK.GET_DATE FROM MANAGEMENT_BOOK, GETTER, LOSTITEM WHERE MANAGEMENT_BOOK.GETTER = GETTER.ID AND MANAGEMENT_BOOK.LOST_ID = LOSTITEM.ID AND LOSTITEM.NAME LIKE ?");
            pstmt.setString(1, searchTerm);
            ResultSet rs = pstmt.executeQuery();

            String row[] = new String[8];

            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getDate(8).toString();
                model.addRow(row);
            }

            return model;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }

    public void solution(String id, String getterId, String itemId) {
        try {
            CallableStatement cstmt = con.prepareCall("{call INSERT_SOLUTION(?, ?, ?)}");
            cstmt.setString(1, id);
            cstmt.setString(2, getterId);
            cstmt.setString(3, itemId);
            cstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "해결!", "해결!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "오류", "데이터베이스와의 연결이 좋지 않습니다.", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
