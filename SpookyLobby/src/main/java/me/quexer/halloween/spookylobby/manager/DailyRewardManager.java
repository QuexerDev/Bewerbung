package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.builder.EntityBuilder;
import me.quexer.halloween.spookylobby.SpookyLobby;
import net.minecraft.server.v1_8_R3.WorldGenVillagePieces;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class DailyRewardManager {

    private SpookyLobby plugin;
    private Villager villager;

    public DailyRewardManager(SpookyLobby plugin) {
        this.plugin = plugin;
        villager = (Villager) plugin.getLocationManager().getDailyRewardMob().getWorld().spawnEntity(plugin.getLocationManager().getDailyRewardMob(), EntityType.VILLAGER);
        new EntityBuilder(villager, plugin.getInstance()).modify().setInvulnerable(true).setDisplayNameVisible(true).setDisplayName("§aDaily Reward").setDisplayNameVisible(true).setCanDespawn(false).setNoAI(true);

    }

}
