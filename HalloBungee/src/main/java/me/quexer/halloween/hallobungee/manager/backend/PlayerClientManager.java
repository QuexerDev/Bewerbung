package me.quexer.halloween.hallobungee.manager.backend;


import me.quexer.halloween.hallobungee.HalloBungee;
import me.quexer.halloween.hallobungee.models.BackendPlayer;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.HashMap;

public class PlayerClientManager {

    private HalloBungee plugin;
    private HashMap<String, BackendPlayer> backendPlayerCache;

    public PlayerClientManager(HalloBungee plugin) {
        this.plugin = plugin;
        backendPlayerCache = new HashMap<>();
    }

    public BackendPlayer getBackendPlayer(String uuid) {
        if (backendPlayerCache.containsKey(uuid))
            return backendPlayerCache.get(uuid);

        BackendPlayer backendPlayer = null;
        try {
            String response = Request.Get(HalloBungee.URL + "bungee/"+uuid).execute().returnContent().asString();
            if (new JSONParser().parse(response) instanceof JSONArray) {
                JSONArray array = (JSONArray) new JSONParser().parse(response);
                System.out.println(array.get(0));
                backendPlayer = (BackendPlayer) plugin.getGson().fromJson(((JSONObject) array.get(0)).toString(), BackendPlayer.class);
            } else {
                backendPlayer = plugin.getGson().fromJson(response, BackendPlayer.class);
            }
            backendPlayerCache.put(backendPlayer.getUuid(), backendPlayer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return backendPlayer;
    }

    public void saveBackendPlayer(BackendPlayer backendPlayer) {
        try {
            Request.Post(HalloBungee.URL+"bungee/"+backendPlayer.getUuid()).bodyString(plugin.getGson().toJson(backendPlayer), ContentType.APPLICATION_JSON).execute().returnContent().asString();
            backendPlayerCache.remove(backendPlayer.getUuid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, BackendPlayer> getBackendPlayerCache() {
        return backendPlayerCache;
    }
}
