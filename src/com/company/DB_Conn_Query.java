package com.company;

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
        String query = "SELECT MANAGEMENT_BOOK.ID, GETTER.NAME, GETTER.PHONENUMBER, LOSTITEM.NAME, LOSTITEM.STATEMENT, MANAGEMENT_BOOK.GET_DATE FROM MANAGEMENT_BOOK, GETTER, LOSTITEM WHERE MANAGEMENT_BOOK.GETTER = GETTER.ID AND MANAGEMENT_BOOK.LOST_ID = LOSTITEM.ID";
        String[] headings = new String[]{"관리번호", "습득자", "전화번호", "습득물", "상태", "날짜"};
        DefaultTableModel model = new DefaultTableModel(headings, 0);

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String row[] = new String[6];

            while (rs.next()) {
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getDate(6).toString();
                model.addRow(row);
            }

            return model;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return null;
        }
    }
}
