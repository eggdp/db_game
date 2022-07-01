package dao;

import Table.Item;

import java.sql.*;
import java.util.Scanner;

public class ItemDao {

    //db 연결
    public Connection dbConn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "mydb";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (
                ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (
                SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }

    public void insertItem(Item item) {
        //쿼리문 준비
        String sql = "INSERT INTO `item` (`name`, `att`, `dem`, `hyo`) VALUES (?, ?, ?, ?);";

        Connection conn = dbConn(); //db 연결 메소드

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName()); //private로 해놔서 itme.name 으로 안됨 > item.getName()
            pstmt.setString(2, item.getAtt());
            pstmt.setInt(3, item.getDem());//db에서는 string이라도 숫자가 드간다.
            pstmt.setString(4, item.getHyo());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("Board데이터 삽입 성공!");

            }

        } catch (Exception e) {
            System.out.println("Board데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    //아이템 데이터 보는 메서드
    public void selectItem() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드
        try {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String att = rs.getString("att");
                String dem = rs.getString("dem");
                String hyo = rs.getString("hyo");
                System.out.println("이름:" + name + " " + "속성:" + att + " " + "공격:" + dem + " " + "효과:" + hyo);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //db 체크 메서드
    public boolean CheckItem() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드

        boolean result =false;

        try {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String att = rs.getString("att");
                String dem = rs.getString("dem");
                String hyo = rs.getString("hyo");
                result=true;
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
