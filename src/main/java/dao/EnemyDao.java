package dao;

import Table.Enemy;

import java.sql.*;

public class EnemyDao {
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

    //enemy 데이터 넣는 메서드
    public void insertEnemy(Enemy enemy) {
        //쿼리문 준비
        String sql = "INSERT INTO `enemy` (`name`, `hp`) VALUES (?, ?);";

        Connection conn = dbConn(); //db 연결 메소드

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enemy.getName());
            pstmt.setInt(2, enemy.getHp());//db에서는 string이라도 숫자가 드간다.

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

    //몬스터 데이터 보는 메서드
    public void selectEnemy() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드
        try {
            String sql = "select * from enemy";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int hp = rs.getInt("hp");
                System.out.println("이름:" + name + " " + "체력:" + hp);
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
    public boolean CheckEnemy () {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); //db 연결 메소드

        boolean result = false;

        try {
            String sql = "select * from enemy";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int hp = rs.getInt("hp");
                result =true;
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