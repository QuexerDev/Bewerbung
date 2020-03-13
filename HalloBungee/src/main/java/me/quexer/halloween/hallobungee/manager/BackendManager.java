package me.quexer.halloween.hallobungee.manager;


import me.quexer.halloween.hallobungee.HalloBungee;
import me.quexer.halloween.hallobungee.models.BackendPlayer;
import me.quexer.halloween.hallobungee.models.Group;
import me.quexer.halloween.hallobungee.models.GroupPlayer;

import java.util.UUID;

public class BackendManager {

    private HalloBungee plugin;

    public BackendManager(HalloBungee plugin) {
        this.plugin = plugin;
    }


    public GroupPlayer getGroupPlayer(String uuid) {
        return plugin.getGroupClientManager().getGroupPlayer(uuid);
    }

    public void saveGroupPlayer(GroupPlayer groupPlayer) {
        plugin.getGroupClientManager().saveGroupPlayer(groupPlayer);
    }

    public void setGroup(String uuid, int ID, long days) {
        GroupPlayer groupPlayer = getGroupPlayer(uuid);
        groupPlayer.setGroupId(ID);
        Group group = plugin.getGroupById(ID);
        long expires = (days == -1 ? -1 : System.currentTimeMillis() + 1000 * 60 * 60 * 24 * days);

        groupPlayer.setExpires(expires);

        UUID id = UUID.fromString(uuid);
        if (plugin.getProxy().getPlayer(id) != null) {
            plugin.getProxy().getPlayer(id).disconnect(
                    "§8▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
                            "§7Du hast die Gruppe " + group.getFullName() + " §7erhalten!\n" +
                            "§7Dauer§8: §e"+plugin.getDate(groupPlayer.getExpires())+"\n" +
                            "§7Bitte betrete den Server erneut§8.\n" +
                            "§8▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
        }
        saveGroupPlayer(groupPlayer);
    }

    public BackendPlayer getBackendPlayer(String uuid) {
        return plugin.getPlayerClientManager().getBackendPlayer(uuid);
    }

    public void saveBackendPlayer(BackendPlayer backendPlayer) {
        plugin.getPlayerClientManager().saveBackendPlayer(backendPlayer);
    }
}
