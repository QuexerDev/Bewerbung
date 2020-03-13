package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.builder.EntityBuilder;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class CprManager {

    private SpookyLobby plugin;
    private Villager villager;

    public CprManager(SpookyLobby plugin) {
        this.plugin = plugin;
        villager = (Villager) plugin.getLocationManager().getCprMob().getWorld().spawnEntity(plugin.getLocationManager().getCprMob(), EntityType.VILLAGER);
        new EntityBuilder(villager, plugin.getInstance()).modify().setInvulnerable(true).setDisplayNameVisible(true).setDisplayName("§eCPR§7-§eShop").setDisplayNameVisible(true).setCanDespawn(false).setNoAI(true);

    }

}
