package com.example.diary_0200.DAO;

import java.sql.*;

public class friendDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3306/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;

    public friendDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet loadFriendSeq(int seq) {
        String SQL = "SELECT friendSeq FROM friend WHERE seq=? AND isApproved=1";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet loadFriendInfo(int seq) {
        String SQL = "SELECT b.seq, b.time, user.name FROM (SELECT * FROM (SELECT seq, time FROM goal_sw UNION SELECT seq,time FROM goal_t) a WHERE seq=? ORDER BY time DESC LIMIT 1) b INNER JOIN user ON b.seq=user.seq";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void deleteFriend(int seq, int friendSeq) {
        String SQL = "DELETE FROM friend WHERE seq=? AND friendSeq=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            ps.setInt(2, friendSeq);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet loadFriendRequest(int seq) {
        String SQL = "SELECT friendSeq FROM friend WHERE seq=? AND isApproved=0;";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}
