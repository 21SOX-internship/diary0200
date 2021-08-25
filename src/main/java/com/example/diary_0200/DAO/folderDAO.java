package com.example.diary_0200.DAO;

import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;

public class folderDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MARIADB_URL = "jdbc:mysql://localhost:3307/diary0200?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    static final String MARIADB_ID = "SOX_user";
    static final String MARIADB_PW = "user123";

    private Connection con;
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;

    public folderDAO() {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(MARIADB_URL, MARIADB_ID, MARIADB_PW);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makefolder(int seq, String folderName,String folder){
        String SQL = "INSERT INTO folder VALUES(?,?,?,NOW())";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            ps.setString(2, folderName);
            ps.setString(3, folder);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<folderDTO> getfolder(int seq){
        String SQL = "SELECT * FROM folder WHERE seq=? ORDER BY makeday DESC";
        ArrayList<folderDTO> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            rs = ps.executeQuery();
            while(rs.next()){
                folderDTO folder = new folderDTO();
                folder.setFolderName(rs.getString("folderName"));
                System.out.println(folder.getFolderName());
                list.add(folder);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getfolders(int seq, String folderName){
        String SQL = "SELECT * FROM folder WHERE seq=? AND folderName=? ORDER BY makeday DESC";

        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, seq);
            ps.setString(2, folderName);
            rs = ps.executeQuery();
            if(rs.next()){
                JSONArray list = new JSONArray(rs.getString("dates"));
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
