package com.example.diary_0200.DAO;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public interface mypageDAO {
    List<mypageDTO> selectUsers(mypageDTO param) throws Exception;
}

