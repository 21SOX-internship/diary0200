package com.example.diary_0200.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3306/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public userDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int signup(userDTO user){
        String SQL = "INSERT INTO user VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getSeq());
            ps.setString(2, user.getName());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getAge());
            ps.setString(5, user.getTel());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getId());
            ps.setString(8, user.getPw());
            ps.executeUpdate();
            return 1;   //저장성공.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;  //DB오류.
    }

    public int signin(userDTO user) {
        String sql_query = "SELECT pw FROM user WHERE id = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, user.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(1).equals(user.getPw())) {
                    return 1;   //로그인 성공.
                } else {
                    return 0;   //비밀번호 불일치.
                }
            }
            return -1;  //아이디 없음.
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; //DB오류.
    }

    public boolean isDuplicatedUserId(String userid){

        String sql_query = "SELECT id FROM user WHERE id = ?";
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, userid);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;   //중복O.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;    //중복X.
    }

    public int generateSeq(){
        String sql_query = "SELECT seq FROM user ORDER BY seq DESC LIMIT 1";
        int seq = 0;
        try {
            ps = con.prepareStatement(sql_query);
            rs = ps.executeQuery();

            if (rs.next()) {
                seq = rs.getInt(1) + 1;
                return seq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSeq(String userid){
        String  sql_query = "SELECT seq FROM user WHERE id = ?";
        int seq = 0;
        try {
            ps = con.prepareStatement(sql_query);
            ps.setString(1, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                seq = rs.getInt(1);
                return seq;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
