package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.spookylobby.SpookyLobby;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

public class ScoreboardManager {

    private SpookyLobby plugin;
    private Objective obj;
    private net.minecraft.server.v1_8_R3.Scoreboard sb;

    public ScoreboardManager(SpookyLobby plugin) {
        this.plugin = plugin;
    }



    public void setBoard(Player player) {
        sb = new net.minecraft.server.v1_8_R3.Scoreboard();

        ScoreboardObjective obj = sb.registerObjective("§8» §6Halloween", IScoreboardCriteria.b);
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

        Group group = plugin.getGroup(player);
        BackendPlayer.Data data = plugin.getData(player);

        ScoreboardScore a = new ScoreboardScore(sb, obj, "§a§8§m-----------------");
        ScoreboardScore a1 = new ScoreboardScore(sb, obj, "§7Rang:");
        ScoreboardScore a2 = new ScoreboardScore(sb, obj, " §8» " + group.getFullName());
        ScoreboardScore a3 = new ScoreboardScore(sb, obj, "§d");
        ScoreboardScore a4 = new ScoreboardScore(sb, obj, "§7Coins§8/§7CPR:");
        ScoreboardScore a5 = new ScoreboardScore(sb, obj, " §8» §e" + data.getCoins()+"§8/§e"+data.getCpr());
        ScoreboardScore a6 = new ScoreboardScore(sb, obj, "§0");
        ScoreboardScore a7 = new ScoreboardScore(sb, obj, "§7Twitter:");
        ScoreboardScore a8 = new ScoreboardScore(sb, obj, " §8» §b@QuexerDev");
        ScoreboardScore a10 = new ScoreboardScore(sb, obj, "§4");
        ScoreboardScore a11 = new ScoreboardScore(sb, obj, "§7Website:");
        ScoreboardScore a12 = new ScoreboardScore(sb, obj, " §8» §6Halloween.net");
        ScoreboardScore a9 = new ScoreboardScore(sb, obj, "§7§8§m-----------------");

        a.setScore(12);
        a1.setScore(11);
        a2.setScore(10);
        a3.setScore(9);
        a4.setScore(8);
        a5.setScore(7);
        a6.setScore(6);
        a7.setScore(5);
        a8.setScore(4);
        a10.setScore(3);
        a11.setScore(2);
        a12.setScore(1);
        a9.setScore(0);



        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(a);
        PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a1);
        PacketPlayOutScoreboardScore pa2 = new PacketPlayOutScoreboardScore(a2);
        PacketPlayOutScoreboardScore pa3 = new PacketPlayOutScoreboardScore(a3);
        PacketPlayOutScoreboardScore pa4 = new PacketPlayOutScoreboardScore(a4);
        PacketPlayOutScoreboardScore pa5 = new PacketPlayOutScoreboardScore(a5);
        PacketPlayOutScoreboardScore pa6 = new PacketPlayOutScoreboardScore(a6);
        PacketPlayOutScoreboardScore pa7 = new PacketPlayOutScoreboardScore(a7);
        PacketPlayOutScoreboardScore pa8 = new PacketPlayOutScoreboardScore(a8);
        PacketPlayOutScoreboardScore pa9 = new PacketPlayOutScoreboardScore(a9);
        PacketPlayOutScoreboardScore pa10 = new PacketPlayOutScoreboardScore(a10);
        PacketPlayOutScoreboardScore pa11 = new PacketPlayOutScoreboardScore(a11);
        PacketPlayOutScoreboardScore pa12 = new PacketPlayOutScoreboardScore(a12);
        sendPacket(player, removePacket);
        sendPacket(player, createpacket);
        sendPacket(player, display);
        sendPacket(player, pa);
        sendPacket(player, pa1);
        sendPacket(player, pa2);
        sendPacket(player, pa3);
        sendPacket(player, pa4);
        sendPacket(player, pa5);
        sendPacket(player, pa6);
        sendPacket(player, pa7);
        sendPacket(player, pa8);
        sendPacket(player, pa9);
        sendPacket(player, pa10);
        sendPacket(player, pa11);
        sendPacket(player, pa12);

        plugin.setMetadata(player, "scoreboard",sb);
    }

    private void sendPacket(Player p, Packet packet) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }

    public void updateScoreboard(Player player) {
        Scoreboard sb = (Scoreboard) player.getMetadata("scoreboard").get(0).value();


        ScoreboardObjective obj = sb.getObjective("§8» §6Halloween");
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);

        Group group = plugin.getGroup(player);
        BackendPlayer.Data data = plugin.getData(player);

        ScoreboardScore a = new ScoreboardScore(sb, obj, "§a§8§m-----------------");
        ScoreboardScore a1 = new ScoreboardScore(sb, obj, "§7Rang:");
        ScoreboardScore a2 = new ScoreboardScore(sb, obj, " §8» " + group.getFullName());
        ScoreboardScore a3 = new ScoreboardScore(sb, obj, "§d");
        ScoreboardScore a4 = new ScoreboardScore(sb, obj, "§7Coins§8/§7CPR:");
        ScoreboardScore a5 = new ScoreboardScore(sb, obj, " §8» §e" + data.getCoins()+"§8/§e"+data.getCpr());
        ScoreboardScore a6 = new ScoreboardScore(sb, obj, "§0");
        ScoreboardScore a7 = new ScoreboardScore(sb, obj, "§7Twitter:");
        ScoreboardScore a8 = new ScoreboardScore(sb, obj, " §8» §b@QuexerDev");
        ScoreboardScore a10 = new ScoreboardScore(sb, obj, "§4");
        ScoreboardScore a11 = new ScoreboardScore(sb, obj, "§7Website:");
        ScoreboardScore a12 = new ScoreboardScore(sb, obj, " §8» §6Halloween.net");
        ScoreboardScore a9 = new ScoreboardScore(sb, obj, "§7§8§m-----------------");


        a.setScore(12);
        a1.setScore(11);
        a2.setScore(10);
        a3.setScore(9);
        a4.setScore(8);
        a5.setScore(7);
        a6.setScore(6);
        a7.setScore(5);
        a8.setScore(4);
        a10.setScore(3);
        a11.setScore(2);
        a12.setScore(1);
        a9.setScore(0);


        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(a);
        PacketPlayOutScoreboardScore pa1 = new PacketPlayOutScoreboardScore(a1);
        PacketPlayOutScoreboardScore pa2 = new PacketPlayOutScoreboardScore(a2);
        PacketPlayOutScoreboardScore pa3 = new PacketPlayOutScoreboardScore(a3);
        PacketPlayOutScoreboardScore pa4 = new PacketPlayOutScoreboardScore(a4);
        PacketPlayOutScoreboardScore pa5 = new PacketPlayOutScoreboardScore(a5);
        PacketPlayOutScoreboardScore pa6 = new PacketPlayOutScoreboardScore(a6);
        PacketPlayOutScoreboardScore pa7 = new PacketPlayOutScoreboardScore(a7);
        PacketPlayOutScoreboardScore pa8 = new PacketPlayOutScoreboardScore(a8);
        PacketPlayOutScoreboardScore pa9 = new PacketPlayOutScoreboardScore(a9);
        PacketPlayOutScoreboardScore pa10 = new PacketPlayOutScoreboardScore(a10);
        PacketPlayOutScoreboardScore pa11 = new PacketPlayOutScoreboardScore(a11);
        PacketPlayOutScoreboardScore pa12 = new PacketPlayOutScoreboardScore(a12);
        sendPacket(player, removePacket);
        sendPacket(player, createpacket);
        sendPacket(player, display);
        sendPacket(player, pa);
        sendPacket(player, pa1);
        sendPacket(player, pa2);
        sendPacket(player, pa3);
        sendPacket(player, pa4);
        sendPacket(player, pa5);
        sendPacket(player, pa6);
        sendPacket(player, pa7);
        sendPacket(player, pa8);
        sendPacket(player, pa9);
        sendPacket(player, pa10);
        sendPacket(player, pa11);
        sendPacket(player, pa12);

        plugin.setMetadata(player, "scoreboard", sb);
    }
}
