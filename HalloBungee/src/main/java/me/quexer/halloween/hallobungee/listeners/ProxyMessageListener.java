package me.quexer.halloween.hallobungee.listeners;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedSubChannelMessageEvent;
import de.dytanic.cloudnet.lib.utility.document.Document;
import me.quexer.halloween.hallobungee.HalloBungee;
import me.quexer.halloween.hallobungee.models.BackendPlayer;
import me.quexer.halloween.hallobungee.models.GroupPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.json.simple.JSONObject;

import java.util.UUID;

public class ProxyMessageListener implements Listener {

    private HalloBungee plugin;

    public ProxyMessageListener(HalloBungee plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMessage(ProxiedSubChannelMessageEvent event) {
        System.out.println(event.getDocument());
        String uuid = event.getDocument().getString("uuid");
        String server = CloudAPI.getInstance().getOnlinePlayer(UUID.fromString(uuid)).getServer();
        if(event.getChannel().equalsIgnoreCase("ban")) {
            switch (event.getMessage().toLowerCase()) {
                case "check": {
                    BackendPlayer player = plugin.getBackendPlayer(event.getDocument().getString("target"));
                    GroupPlayer groupPlayer = plugin.getGroupPlayer(event.getDocument().getString("target"));
                    System.out.println(plugin.getGson().toJson(player.getBanPlayer()));
                    System.out.println(plugin.getGson().toJson(player.getMutePlayer()));
                    CloudAPI.getInstance().sendCustomSubServerMessage("ban", "check", new Document("uuid", uuid).append("target", player.getUuid()).append("muteplayer", plugin.getGson().fromJson(plugin.getGson().toJson(player.getMutePlayer()), JSONObject.class)).append("banplayer", plugin.getGson().fromJson(plugin.getGson().toJson(player.getBanPlayer()), JSONObject.class)), server);
                    break;
                }
                case "ban": {
                    String reason = event.getDocument().getString("reason");
                    String target = event.getDocument().getString("target");
                    plugin.getProxyMessageManager().ban(uuid, target, reason);
                    break;
                }
                case "mute": {
                    String reason = event.getDocument().getString("reason");
                    String target = event.getDocument().getString("target");
                    plugin.getProxyMessageManager().ban(uuid, target, reason);
                    break;
                }
                case "unban": {
                    String reason = event.getDocument().getString("reason");
                    String target = event.getDocument().getString("target");
                    plugin.getBanManager().unBan(target, uuid);
                    break;
                }
                case "unmute": {
                    String reason = event.getDocument().getString("reason");
                    String target = event.getDocument().getString("target");
                    plugin.getMuteManager().unMute(target, uuid);
                    break;
                }
            }
        }
    }
}
