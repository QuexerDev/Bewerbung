package me.quexer.halloween.halloplugin.manager.backend;

import me.quexer.api.quexerapi.manager.backend.PlayerClientManager;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.GroupPlayer;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.HashMap;

public class PlayerClientManagerImpl implements PlayerClientManager {

    private HalloPlugin plugin;
    private HashMap<String, BackendPlayer> backendPlayerCache;

    public PlayerClientManagerImpl(HalloPlugin plugin) {
        this.plugin = plugin;
        backendPlayerCache = new HashMap<>();
    }

    @Override
    public BackendPlayer getBackendPlayer(String uuid) {
        if (backendPlayerCache.containsKey(uuid))
            return backendPlayerCache.get(uuid);

        BackendPlayer backendPlayer = null;
        try {
            String response = Request.Get(HalloPlugin.URL + "spigot/"+uuid).execute().returnContent().asString();
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

    @Override
    public void saveBackendPlayer(BackendPlayer backendPlayer) {
        try {
            Request.Post(HalloPlugin.URL+"spigot/"+backendPlayer.getUuid()).bodyString(plugin.getGson().toJson(backendPlayer), ContentType.APPLICATION_JSON).execute().returnContent().asString();
            backendPlayerCache.remove(backendPlayer.getUuid());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, BackendPlayer> getBackendPlayerCache() {
        return backendPlayerCache;
    }
}
