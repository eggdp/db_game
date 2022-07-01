package dao;

import Table.Tchar;

import java.sql.*;


public class T_charDao {

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

    //캐릭터 데이터 넣는 메서드
    public void insertT_char(Tchar tchar) {
        //쿼리문 준비
        String sql = "INSERT INTO `the_char` (`name`, `hp` ,`job`) VALUES (?, ?, ?)";

        Connection conn = dbConn(); //db 연결 메소드

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tchar.getName());
            pstmt.setInt(2, tchar.getHp());
            pstmt.setString(3, tchar.getJob());

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

    //캐릭터 데이터 보는 메서드
    public void selectChar() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드
        try {
            String sql = "select * from the_char";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int hp1 = rs.getInt("hp");
                String job = rs.getString("job");
                System.out.println("이름:" + name + " " + "체력:" + hp1 + " " + "직업:" + job);
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
    public Boolean CheckT_char() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드

        boolean result = false;
        try {
            String sql = "select * from the_char";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int hp1 = rs.getInt("hp");
                String job = rs.getString("job");
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
        }return result;
    }
}
