package me.quexer.halloween.spookylobby.models;

import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class HotbarItem {



    public abstract int getSlot();
    public abstract int permsLevel();
    public abstract ItemStack item();
    public abstract void onPlayerJoin(Player player);
    public abstract void onHeld(Player player);
    public abstract void onClick(Player player);



}
