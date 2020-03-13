package me.quexer.halloween.spookylobby.callbacks;

import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class NavigatorCallback implements Consumer<Player> {

    private SpookyLobby plugin;
    private Location location;

    public NavigatorCallback(SpookyLobby plugin, Location location) {
        this.plugin = plugin;
        this.location = location;
    }

    @Override
    public void accept(Player player) {
        player.teleport(location);
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
        player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 1);

    }
}
