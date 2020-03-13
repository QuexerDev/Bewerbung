package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.builder.EntityBuilder;
import me.quexer.api.quexerapi.misc.Particle;
import me.quexer.halloween.spookylobby.SpookyLobby;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class CaseManager {

    private SpookyLobby plugin;
    private Block block;
    private Villager villager;

    public CaseManager(SpookyLobby plugin) {
        this.plugin = plugin;
        this.block = plugin.getLocationManager().getCaseBlock().getBlock();

        block.setType(Material.ENDER_CHEST);
        plugin.setMetadata(block, "case", true);

        plugin.runTaskTimerAsync(40, 10, () -> {
            new Particle(EnumParticle.SMOKE_LARGE, block.getLocation(), true, 1, 1, 1, 0.0001F, 1).sendAll();
        });
        villager = (Villager) plugin.getLocationManager().getCaseShopMob().getWorld().spawnEntity(plugin.getLocationManager().getCaseShopMob(), EntityType.VILLAGER);
        new EntityBuilder(villager, plugin.getInstance()).modify().setInvulnerable(true).setDisplayNameVisible(true).setDisplayName("§3Case§7-§3Shop").setDisplayNameVisible(true).setCanDespawn(false).setNoAI(true);

    }

}
