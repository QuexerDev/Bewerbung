package me.quexer.halloween.halloplugin.utils;

import me.quexer.api.quexerapi.api.NickAPI;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.api.quexerapi.models.GroupPlayer;
import me.quexer.halloween.halloplugin.HalloPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class TablistManager {

    private HalloPlugin plugin;

    private org.bukkit.scoreboard.Scoreboard board;
    private Objective obj;
    private net.minecraft.server.v1_8_R3.Scoreboard sb;
    private HashMap<Integer, Team> groupTeamHashMap = new HashMap<>();

    public TablistManager(HalloPlugin plugin) {
        this.plugin = plugin;

        board = Bukkit.getScoreboardManager().getNewScoreboard();
        obj = board.registerNewObjective("aaaaa", "bbbbb");
        for (int i = 0; i < plugin.getGroupClientManager().getAllGroups().size(); i++) {
            Group group = plugin.getGroupClientManager().getAllGroups().get(i);
            Team team = board.registerNewTeam("" + group.getTabID() + ChatColor.stripColor(group.getPrefix().split(" ")[0]).toUpperCase().toString());
            System.out.println(team.getName());
            System.out.println("" + group.getTabID() + group.toString());
            team.setPrefix(group.getPrefix());
            groupTeamHashMap.put(group.getLevelID(), team);
        }
    }

    public void setTablist(Player player, GroupPlayer backendPlayer) {
        Group group = plugin.getGroup(player);
        Group lowest = plugin.getGroupById(1);
        if(!NickAPI.hasNick(player)) {
            groupTeamHashMap.get(backendPlayer.getGroupId()).addPlayer(player);

            player.setPlayerListName(group.getPrefix() + player.getName());
            player.setDisplayName(group.getPrefix() + player.getName());
        } else {
            groupTeamHashMap.get(1).addPlayer(player);

            player.setPlayerListName(lowest.getPrefix() + player.getName());
            player.setDisplayName(lowest.getPrefix() + player.getName());
        }

        Bukkit.getOnlinePlayers().forEach(o -> {
            o.setScoreboard(board);
        });
    }

}
