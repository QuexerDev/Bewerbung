package me.quexer.halloween.halloplugin.manager.backend;

import me.quexer.api.quexerapi.manager.backend.GroupClientManager;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.api.quexerapi.models.GroupPlayer;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.*;

public class GroupClientManagerImpl implements GroupClientManager {

    private HalloPlugin plugin;
    private List<Group> groups;
    private HashMap<String, GroupPlayer> groupPlayerCache;

    public GroupClientManagerImpl(HalloPlugin plugin) {
        this.plugin = plugin;
        this.groups = new ArrayList<>();
        this.groupPlayerCache = new HashMap<>();
        initGroups();
    }



    private void initGroups() {
        try {
            Group[] groupsArray;
            String response = Request.Get(HalloPlugin.URL + "groups").execute().returnContent().asString();

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


    @Override
    public GroupPlayer getGroupPlayer(String uuid) {

        if (groupPlayerCache.containsKey(uuid)) {
            System.out.println("getFromCache");
            return groupPlayerCache.get(uuid);
        }

        GroupPlayer groupPlayer = null;
        try {
            String response = Request.Get(HalloPlugin.URL + "groupplayer/"+uuid).execute().returnContent().asString();
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


    @Override
    public void saveGroupPlayer(GroupPlayer groupPlayer) {
        try {
            Request.Post(HalloPlugin.URL+"groupplayer/"+groupPlayer.getUuid()).bodyString(plugin.getGson().toJson(groupPlayer), ContentType.APPLICATION_JSON).execute().returnContent().asString();
            groupPlayerCache.remove(groupPlayer.getUuid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return groups;
    }

    public HashMap<String, GroupPlayer> getGroupPlayerCache() {
        return groupPlayerCache;
    }
}
