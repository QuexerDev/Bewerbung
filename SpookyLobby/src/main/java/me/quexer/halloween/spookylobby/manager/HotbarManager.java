package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.spookylobby.SpookyLobby;
import me.quexer.halloween.spookylobby.hotbar.*;
import me.quexer.halloween.spookylobby.models.HotbarItem;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class HotbarManager {

    private SpookyLobby plugin;
    private List<HotbarItem> items;

    public HotbarManager(SpookyLobby plugin) {
        this.plugin = plugin;
        this.items = new ArrayList<>();

        items.add(new NavigatorItem(plugin));
        items.add(new SelectedGadgetItem());
        items.add(new NickToolItem(plugin));
        items.add(new GadgetItem(plugin));
        items.add(new ProfiItem());

        plugin.registerEvent(PlayerJoinEvent.class, (EventManager.EventListener<PlayerJoinEvent>) event -> {
            event.getPlayer().setFoodLevel(20);
            event.getPlayer().setSaturation(20);
            event.getPlayer().setHealthScale(2);
            event.getPlayer().getInventory().clear();
            items.forEach(hotbarItem -> {
                Group group = plugin.getGroup(event.getPlayer());
                if (group.hasPermission(hotbarItem.permsLevel())) {
                    event.getPlayer().getInventory().setItem(hotbarItem.getSlot(), hotbarItem.item());
                    hotbarItem.onPlayerJoin(event.getPlayer());
                }
            });
        });

        plugin.registerEvent(PlayerInteractEvent.class, (EventManager.EventListener<PlayerInteractEvent>) event -> {
            event.setCancelled(true);
            if(event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getItemMeta() != null) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
                    items.forEach(hotbarItem -> {
                        if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == hotbarItem.item().getItemMeta().getDisplayName()) {
                            hotbarItem.onClick(event.getPlayer());
                            return;
                        }
                    });
                }
            }
        });

        plugin.registerEvent(PlayerItemHeldEvent.class, (EventManager.EventListener<PlayerItemHeldEvent>) event -> {
            try {
                items.forEach(hotbarItem -> {
                    if (event.getPlayer().getInventory().getItem(event.getNewSlot()).getItemMeta().getDisplayName() == hotbarItem.item().getItemMeta().getDisplayName())
                        hotbarItem.onHeld(event.getPlayer());
                });
            } catch (Exception ex){

            }
        });


    }

}
