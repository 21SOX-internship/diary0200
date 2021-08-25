package com.example.diary_0200.DAO;

import org.json.JSONArray;

public class folderDTO {

    private int seq;
    private String folderName;
    private JSONArray dates;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public JSONArray getDates() {
        return dates;
    }

    public void setDates(JSONArray dates) {
        this.dates = dates;
    }
}
