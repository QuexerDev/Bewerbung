package me.quexer.api.quexerapi.models;

public class GroupPlayer {

    private String uuid;
    private int groupId;
    private long expires;


    public GroupPlayer(String uuid) {
        this.uuid = uuid;

        this.expires = -1;
        this.groupId = 1;


    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }
}
