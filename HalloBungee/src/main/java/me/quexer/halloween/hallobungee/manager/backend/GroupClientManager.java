package me.quexer.halloween.hallobungee.manager.backend;

import me.quexer.halloween.hallobungee.HalloBungee;
import me.quexer.halloween.hallobungee.models.Group;
import me.quexer.halloween.hallobungee.models.GroupPlayer;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupClientManager {

    private HalloBungee plugin;
    private List<Group> groups;
    private HashMap<String, GroupPlayer> groupPlayerCache;

    public GroupClientManager(HalloBungee plugin) {
        this.plugin = plugin;
        this.groups = new ArrayList<>();
        this.groupPlayerCache = new HashMap<>();
        initGroups();
    }



    private void initGroups() {
        try {
            Group[] groupsArray;
            String response = Request.Get(HalloBungee.URL + "groups").execute().returnContent().asString();

            JSONArray array = (JSONArray) new JSONParser().parse(response);
            groupsArray = new Group[array.size()];

            int i = 0;
            for (Object obect : array) {
                JSONObject jsonObject = (JSONObject) obect;
                groupsArray[i] = plugin.getGson().fromJson(jsonObject.toString(), Group.class);
                i++;
            }
            groups.addAll(Arrays.asList(groupsArray));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public GroupPlayer getGroupPlayer(String uuid) {

        if (groupPlayerCache.containsKey(uuid)) {
            System.out.println("getFromCache");
            return groupPlayerCache.get(uuid);
        }

        GroupPlayer groupPlayer = null;
        try {
            String response = Request.Get(HalloBungee.URL + "groupplayer/"+uuid).execute().returnContent().asString();
            if (new JSONParser().parse(response) instanceof JSONArray) {
                JSONArray array = (JSONArray) new JSONParser().parse(response);
                groupPlayer = (GroupPlayer) plugin.getGson().fromJson(((JSONObject) array.get(0)).toString(), GroupPlayer.class);
            } else {
                groupPlayer = plugin.getGson().fromJson(response, GroupPlayer.class);
            }
            groupPlayerCache.put(groupPlayer.getUuid(), groupPlayer);
            System.out.println("getFromDB");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupPlayer;
    }


    public void saveGroupPlayer(GroupPlayer groupPlayer) {
        try {
            Request.Post(HalloBungee.URL+"groupplayer/"+groupPlayer.getUuid()).bodyString(plugin.getGson().toJson(groupPlayer), ContentType.APPLICATION_JSON).execute().returnContent().asString();
            groupPlayerCache.remove(groupPlayer.getUuid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAllGroups() {
        return groups;
    }

    public HashMap<String, GroupPlayer> getGroupPlayerCache() {
        return groupPlayerCache;
    }
}
