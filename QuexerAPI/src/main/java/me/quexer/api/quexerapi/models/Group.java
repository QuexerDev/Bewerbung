package me.quexer.api.quexerapi.models;

import com.google.gson.Gson;

public class Group {

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new BackendPlayer("12345")));
    }

    private String full;
    private String prefix;
    private String color;
    private int levelID;
    private int tabID;
    private int gid;

    public Group(String full, String prefix, String color, int levelID, int tabID, int gid) {
        this.full = full;
        this.prefix = prefix;
        this.color = color;
        this.levelID = levelID;
        this.tabID = tabID;
        this.gid = gid;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public int getLevelID() {
        return levelID;
    }

    public int tabID() {
        return tabID;
    }

    public boolean hasPermission(int level) {
        return (levelID >= level);
    }

    public String getFullName() {
        return full;
    }

    public String getTabID() {
        return "00"+levelID;
    }

    public int getGid() {
        return gid;
    }
}
