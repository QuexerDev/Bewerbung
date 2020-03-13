package me.quexer.halloween.spookylobby.hotbar;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.TitleBuilder;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GadgetItem extends me.quexer.halloween.spookylobby.models.HotbarItem {

    private SpookyLobby plugin;

    public GadgetItem(SpookyLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public int getSlot() {
        return 7;
    }

    @Override
    public int permsLevel() {
        return 1;
    }

    @Override
    public ItemStack item() {
        return new ItemBuilder(Material.CHEST).setName("§8➜ §a§lGadgets §7(Rechtsklick)").toItemStack();
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onHeld(Player player) {
        TitleBuilder.sendActionBar(player, "§7Klicke um deine §eGadgets §7zu sehen");
    }

    @Override
    public void onClick(Player player) {

    }
}
