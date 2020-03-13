package me.quexer.halloween.halloplugin.listeners;

import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.api.quexerapi.event.PlayerNickEvent;
import me.quexer.api.quexerapi.event.PlayerRemoveNickEvent;
import me.quexer.api.quexerapi.misc.AsyncTask;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.help.HelpTopic;

public class PlayerListeners {

    private HalloPlugin plugin;

    public PlayerListeners(HalloPlugin plugin) {
        this.plugin = plugin;

        initListeners();

    }

    private void initListeners() {
        plugin.registerEvent(PlayerJoinEvent.class, (EventManager.EventListener<PlayerJoinEvent>) event -> {
            BackendPlayer player = plugin.getBackendPlayer(event.getPlayer());
            Group group = plugin.getGroup(event.getPlayer());
            event.setJoinMessage(null);
            Bukkit.getOnlinePlayers().forEach(o -> {
                o.hidePlayer(event.getPlayer());
            });
            if (plugin.isTabOnThisServer())
                plugin.getTablistManager().setTablist(event.getPlayer(), plugin.getGroupPlayer(player));
            if (plugin.isNickOnThisServer() && group.hasPermission(3)) {
                if (player.getData().isNick()) {
                    plugin.getBackendManager().nick(event.getPlayer());
                }
            }
            Bukkit.getOnlinePlayers().forEach(o -> {
                o.showPlayer(event.getPlayer());
            });

        });

        plugin.registerEvent(PlayerQuitEvent.class, (EventManager.EventListener<PlayerQuitEvent>) event -> {
            BackendPlayer player = plugin.getBackendPlayer(event.getPlayer());
            Group group = plugin.getGroup(event.getPlayer());

            event.setQuitMessage(null);
            plugin.getBackendManager().saveBackendPlayer(player);
            plugin.getBackendManager().saveGroupPlayer(plugin.getGroupPlayer(event.getPlayer()));
        });


        plugin.registerEvent(AsyncPlayerChatEvent.class, (EventManager.EventListener<AsyncPlayerChatEvent>) event -> {
            event.setFormat(event.getPlayer().getDisplayName() + " §8➜ §7" + event.getMessage());
            String msg = event.getMessage().replace("%", "%%");
            event.setMessage(msg);
            if (!event.isCancelled()) {
                if(msg.startsWith("/")) {
                    String cmd = event.getMessage().split(" ")[0];
                    HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
                    if (topic == null) {
                        event.getPlayer().sendMessage(plugin.getPrefix() + "§7Dieser Befehl existiert nicht, versuche §e/help §7für eine Übersicht");
                        event.setCancelled(true);
                    }
                }
            }

        });

        plugin.registerEvent(PlayerRemoveNickEvent.class, (EventManager.EventListener<PlayerRemoveNickEvent>) event -> {
            new AsyncTask(() -> {
                event.getPlayer().sendMessage("");
                event.getPlayer().sendMessage("§5NICK §8▎ §7Dein §eNickname §7wurde entfernt§8!");
                event.getPlayer().sendMessage("");
                if (plugin.isTabOnThisServer())
                    plugin.getTablistManager().setTablist(event.getPlayer(), plugin.getGroupPlayer(event.getPlayer()));
            });
        });
        plugin.registerEvent(PlayerNickEvent.class, (EventManager.EventListener<PlayerNickEvent>) event -> {
                event.getPlayer().sendMessage("");
                event.getPlayer().sendMessage("§5NICK §8▎ §7Dein neuer §eNickname §7lautet§8: §6" + event.getNick());
                event.getPlayer().sendMessage("");
                if (plugin.isTabOnThisServer())
                    plugin.getTablistManager().setTablist(event.getPlayer(), plugin.getGroupPlayer(event.getPlayer()));
        });

    }

}
