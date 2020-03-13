package me.quexer.api.quexerapi.manager;

import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.GroupPlayer;
import org.bukkit.entity.Player;

public interface BackendManager {

    GroupPlayer getGroupPlayer(String uuid);

    void saveGroupPlayer(GroupPlayer groupPlayer);

    BackendPlayer getBackendPlayer(String uuid);

    void saveBackendPlayer(BackendPlayer backendPlayer);

    void nick(Player player);

    void unnick(Player player);

}
