package me.quexer.halloween.hallobungee.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BackendPlayer {

    public static void main(String[] args) {
        String s = "[{\"id\":1,\"groupID\":1,\"data\":\"{\\\"gid\\\" : 1, \\\"full\\\" : \\\"\\u00a7aSpieler\\\", \\\"prefix\\\" : \\\"\\u00a7aSpieler \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7a\\\", \\\"levelID\\\" : 1, \\\"tabID\\\" : 13 }\",\"created_at\":null,\"updated_at\":null},{\"id\":2,\"groupID\":2,\"data\":\"{ \\\"gid\\\" : 2, \\\"full\\\" : \\\"\\u00a76Premium\\\", \\\"prefix\\\" : \\\"\\u00a76Premium \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a76\\\", \\\"levelID\\\" : 2, \\\"tabID\\\" : 12 }\",\"created_at\":null,\"updated_at\":null},{\"id\":3,\"groupID\":3,\"data\":\"{ \\\"gid\\\" : 3, \\\"full\\\" : \\\"\\u00a7eVIP\\\", \\\"prefix\\\" : \\\"\\u00a7eVIP \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7e\\\", \\\"levelID\\\" : 3, \\\"tabID\\\" : 11 }\",\"created_at\":null,\"updated_at\":null},{\"id\":4,\"groupID\":4,\"data\":\"{ \\\"gid\\\" : 4, \\\"full\\\" : \\\"\\u00a73Master\\\", \\\"prefix\\\" : \\\"\\u00a73Master \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a73\\\", \\\"levelID\\\" : 4, \\\"tabID\\\" : 10 }\",\"created_at\":null,\"updated_at\":null},{\"id\":5,\"groupID\":5,\"data\":\"{ \\\"gid\\\" : 5, \\\"full\\\" : \\\"\\u00a75YouTuber\\\", \\\"prefix\\\" : \\\"\\u00a75YT \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a75\\\", \\\"levelID\\\" : 5, \\\"tabID\\\" : 9 }\",\"created_at\":null,\"updated_at\":null},{\"id\":6,\"groupID\":6,\"data\":\"{ \\\"gid\\\" : 6, \\\"full\\\" : \\\"\\u00a79Supporter\\\", \\\"prefix\\\" : \\\"\\u00a79Sup \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a79\\\", \\\"levelID\\\" : 6, \\\"tabID\\\" : 8 }\",\"created_at\":null,\"updated_at\":null},{\"id\":7,\"groupID\":7,\"data\":\"{ \\\"gid\\\" : 7, \\\"full\\\" : \\\"\\u00a7bContent\\\", \\\"prefix\\\" : \\\"\\u00a7bContent \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7b\\\", \\\"levelID\\\" : 7, \\\"tabID\\\" : 7 }\",\"created_at\":null,\"updated_at\":null},{\"id\":8,\"groupID\":8,\"data\":\"{ \\\"gid\\\" : 8, \\\"full\\\" : \\\"\\u00a72Builder\\\", \\\"prefix\\\" : \\\"\\u00a72Builder \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a72\\\", \\\"levelID\\\" : 8, \\\"tabID\\\" : 6 }\",\"created_at\":null,\"updated_at\":null},{\"id\":9,\"groupID\":9,\"data\":\"{ \\\"gid\\\" : 9, \\\"full\\\" : \\\"\\u00a7cModerator\\\", \\\"prefix\\\" : \\\"\\u00a7cMod \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7c\\\", \\\"levelID\\\" : 9, \\\"tabID\\\" : 5 }\",\"created_at\":null,\"updated_at\":null},{\"id\":10,\"groupID\":10,\"data\":\"{ \\\"gid\\\" : 10, \\\"full\\\" : \\\"\\u00a7bDeveloper\\\", \\\"prefix\\\" : \\\"\\u00a7bDev \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7b\\\", \\\"levelID\\\" : 10, \\\"tabID\\\" : 4 }\",\"created_at\":null,\"updated_at\":null},{\"id\":11,\"groupID\":11,\"data\":\"{ \\\"gid\\\" : 11, \\\"full\\\" : \\\"\\u00a7cSrModerator\\\", \\\"prefix\\\" : \\\"\\u00a7cSrMod \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7c\\\", \\\"levelID\\\" : 11, \\\"tabID\\\" : 3 }\",\"created_at\":null,\"updated_at\":null},{\"id\":12,\"groupID\":12,\"data\":\"{ \\\"gid\\\" : 12, \\\"full\\\" : \\\"\\u00a7bSrDev\\\", \\\"prefix\\\" : \\\"\\u00a7bSrDev \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a7b\\\", \\\"levelID\\\" : 12, \\\"tabID\\\" : 2 }\",\"created_at\":null,\"updated_at\":null},{\"id\":13,\"groupID\":13,\"data\":\"{ \\\"gid\\\" : 13, \\\"full\\\" : \\\"\\u00a74Administrator\\\", \\\"prefix\\\" : \\\"\\u00a74Admin \\u00a78| \\u00a77\\\", \\\"color\\\" : \\\"\\u00a74\\\", \\\"levelID\\\" : 13, \\\"tabID\\\" : 1 }\",\"created_at\":null,\"updated_at\":null}]";
        //System.out.println(s.replace("\\", ""));
        JsonArray object = new Gson().fromJson(s, JsonArray.class);
        String obj = object.get(7).getAsJsonObject().get("data").toString();
        System.out.println(new Gson().fromJson(obj.substring(1, obj.length()-1).replace("\\", ""), JsonObject.class));
        //System.out.println(new Gson().toJson(new Group("§aAdministrator", "§aAdmin", "§a", 1, 1, 1)));

    }


    private String uuid;
    private BanPlayer banPlayer;
    private BanPlayer mutePlayer;
    private FriendPlayer friendPlayer;
    private Date date;

    public BackendPlayer(String uuid) {
        this.uuid = uuid;
        banPlayer = new BanPlayer();
        mutePlayer = new BanPlayer();
        friendPlayer = new FriendPlayer();
        date = new Date();
    }

    public static class FriendPlayer {

        private List<String> requests;
        private List<String> friends;
        private List<String> blocked;
        private Settings settings;

        public FriendPlayer() {
            requests = new ArrayList<>();
            friends = new ArrayList<>();
            blocked = new ArrayList<>();
            settings = new Settings();
        }

        public List<String> getRequests() {
            return requests;
        }

        public void setRequests(List<String> requests) {
            this.requests = requests;
        }

        public List<String> getFriends() {
            return friends;
        }

        public void setFriends(List<String> friends) {
            this.friends = friends;
        }

        public List<String> getBlocked() {
            return blocked;
        }

        public void setBlocked(List<String> blocked) {
            this.blocked = blocked;
        }

        public Settings getSettings() {
            return settings;
        }

        public void setSettings(Settings settings) {
            this.settings = settings;
        }

        public class Settings {
            private boolean SEND_REQUEST;
            private boolean MSG;
            private boolean JUMP;
            private boolean SERVER;
            private boolean PARTY_INVITE;

            public Settings() {
                this.SEND_REQUEST = true;
                this.MSG = true;
                this.JUMP = true;
                this.SERVER = true;
                this.PARTY_INVITE = true;
            }

            public void setSEND_REQUEST(boolean SEND_REQUEST) {
                this.SEND_REQUEST = SEND_REQUEST;
            }

            public void setMSG(boolean MSG) {
                this.MSG = MSG;
            }

            public void setJUMP(boolean JUMP) {
                this.JUMP = JUMP;
            }

            public void setSERVER(boolean SERVER) {
                this.SERVER = SERVER;
            }

            public void setPARTY_INVITE(boolean PARTY_INVITE) {
                this.PARTY_INVITE = PARTY_INVITE;
            }
        }
    }

    public static class Date {
        private long created_at;
        private long lastLogin;
        private long lastOffline;

        public Date() {
            created_at = System.currentTimeMillis();
            lastLogin = System.currentTimeMillis();
            lastOffline = System.currentTimeMillis();
        }

        public long getCreated_at() {
            return created_at;
        }

        public long getLastLogin() {
            return lastLogin;
        }

        public long getLastOffline() {
            return lastOffline;
        }

        public void setCreated_at(long created_at) {
            this.created_at = created_at;
        }

        public void setLastLogin(long lastLogin) {
            this.lastLogin = lastLogin;
        }

        public void setLastOffline(long lastOffline) {
            this.lastOffline = lastOffline;
        }
    }

    public static class BanPlayer {

        private boolean isPunished;
        private String reason;
        private long end;
        private long punished_at;
        private int banPoints;
        private List<String> history;
        private String punished_from;

        public BanPlayer() {
            isPunished = false;
            reason = "NONE";
            end = 0;
            punished_at = 0;
            banPoints = 1;
            history = new ArrayList<>();
            punished_from = "NONE";
        }

        public boolean isPunished() {
            return isPunished;
        }

        public String getReason() {
            return reason;
        }

        public long getEnd() {
            return end;
        }

        public long getPunished_at() {
            return punished_at;
        }

        public List<String> getHistory() {
            return history;
        }

        public String getPunished_from() {
            return punished_from;
        }

        public void setPunished(boolean punished) {
            isPunished = punished;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public void setPunished_at(long punished_at) {
            this.punished_at = punished_at;
        }

        public void setHistory(List<String> history) {
            this.history = history;
        }

        public void setPunished_from(String punished_from) {
            this.punished_from = punished_from;
        }

        public int getBanPoints() {
            return banPoints;
        }

        public void setBanPoints(int banPoints) {
            this.banPoints = banPoints;
        }
    }

    public String getUuid() {
        return uuid;
    }

    public BanPlayer getBanPlayer() {
        return banPlayer;
    }

    public BanPlayer getMutePlayer() {
        return mutePlayer;
    }

    public FriendPlayer getFriendPlayer() {
        return friendPlayer;
    }

    public Date getDate() {
        return date;
    }

}
