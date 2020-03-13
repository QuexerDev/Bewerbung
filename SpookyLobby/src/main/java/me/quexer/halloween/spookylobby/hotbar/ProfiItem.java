package me.quexer.halloween.spookylobby.hotbar;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.TitleBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ProfiItem extends me.quexer.halloween.spookylobby.models.HotbarItem {
    @Override
    public int getSlot() {
        return 8;
    }

    @Override
    public int permsLevel() {
        return 0;
    }

    @Override
    public ItemStack item() {
        return new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§8➜ §3§lDein Profil §7(Rechtsklick)").toItemStack();
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onHeld(Player player) {
        TitleBuilder.sendActionBar(player, "§7Klicke, um dein §eProfil §7zu verwalten");
    }

    @Override
    public void onClick(Player player) {

    }
}
