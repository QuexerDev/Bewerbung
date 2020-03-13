package me.quexer.halloween.spookylobby.listeners;

import me.quexer.api.quexerapi.builder.TitleBuilder;
import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.api.quexerapi.misc.Particle;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.spookylobby.SpookyLobby;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListeners {

    private SpookyLobby plugin;

    public PlayerListeners(SpookyLobby plugin) {
        this.plugin = plugin;

        plugin.registerEvent(PlayerJoinEvent.class, (EventManager.EventListener<PlayerJoinEvent>) event -> {
            event.getPlayer().teleport(plugin.getLocationManager().getSpawn());
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 5, true, false));
            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.ENDER_SIGNAL, 10);
            event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.CAT_MEOW, 3, 0.3F);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.GLASS, 3, 0.3F);
            new Particle(EnumParticle.FLAME, event.getPlayer().getLocation(), false, 0.5F, 1F, 0.5F, 0.001F, 500).sendPlayer(event.getPlayer());
            plugin.runTaskLaterAsync(10, () -> {
                new Particle(EnumParticle.FLAME, event.getPlayer().getLocation(), false, 0.5F, 1F, 0.5F, 0.001F, 500).sendPlayer(event.getPlayer());
                plugin.runTaskLaterAsync(10, () -> {
                    new Particle(EnumParticle.FLAME, event.getPlayer().getLocation(), false, 0.5F, 1F, 0.5F, 0.001F, 500).sendPlayer(event.getPlayer());
                });
            });
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
            event.getPlayer().sendTitle("§e§lWillkommen", "§7aus dem Halloween-Netzwerk!");
            plugin.getScoreboardManager().setBoard(event.getPlayer());
        });

        plugin.registerEvent(EntityDamageEvent.class, (EventManager.EventListener<EntityDamageEvent>) event -> {
            event.setCancelled(true);
            if (event.getEntity() instanceof Player)
                new Particle(EnumParticle.VILLAGER_ANGRY, event.getEntity().getLocation(), false, 1, 1, 1, 0.001F, 50).sendPlayer((Player) event.getEntity());

        });
        plugin.registerEvent(PlayerItemHeldEvent.class, (EventManager.EventListener<PlayerItemHeldEvent>) event -> {
            if(event.getNewSlot() > 1)
                event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.STICKS, Note.flat(1, Note.Tone.values()[event.getNewSlot()-2]));
            else
                event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.STICKS, Note.flat(0, Note.Tone.values()[event.getNewSlot()+5]));
        });
        plugin.registerEvent(FoodLevelChangeEvent.class, (EventManager.EventListener<FoodLevelChangeEvent>) event -> {
            event.setCancelled(true);
        });
        plugin.registerEvent(InventoryClickEvent.class, (EventManager.EventListener<InventoryClickEvent>) event -> {
            event.setCancelled(true);
        });
        plugin.registerEvent(PlayerDropItemEvent.class, (EventManager.EventListener<PlayerDropItemEvent>) event -> {
            event.setCancelled(true);
        });


    }

}
