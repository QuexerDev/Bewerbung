package me.quexer.halloween.halloplugin.manager;

import com.rabbitmq.client.AMQP;
import me.quexer.api.quexerapi.api.NickAPI;
import me.quexer.api.quexerapi.manager.BackendManager;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.GroupPlayer;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.entity.Player;

public class BackendManagerImpl implements BackendManager {

    private HalloPlugin plugin;

    public BackendManagerImpl(HalloPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public GroupPlayer getGroupPlayer(String uuid) {
        return plugin.getGroupClientManager().getGroupPlayer(uuid);
    }

    @Override
    public void saveGroupPlayer(GroupPlayer groupPlayer) {
        plugin.getGroupClientManager().saveGroupPlayer(groupPlayer);
    }

    @Override
    public BackendPlayer getBackendPlayer(String uuid) {
        return plugin.getPlayerClientManager().getBackendPlayer(uuid);
    }

    @Override
    public void saveBackendPlayer(BackendPlayer backendPlayer) {
        plugin.getPlayerClientManager().saveBackendPlayer(backendPlayer);
    }

    @Override
    public void nick(Player player) {
        if (plugin.getGroup(player).hasPermission(3)) {
            NickAPI.setRandomNick(player);
        }
    }

    @Override
    public void unnick(Player player) {
        if (NickAPI.hasNick(player)) {
            NickAPI.removeNick(player);
        }
    }
}
