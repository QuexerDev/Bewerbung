package me.quexer.halloween.spookylobby.hotbar;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.TitleBuilder;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NavigatorItem extends me.quexer.halloween.spookylobby.models.HotbarItem {

    private SpookyLobby plugin;

    public NavigatorItem(SpookyLobby plugin) {
        this.plugin = plugin;
    }

    @Override
    public int getSlot() {
        return 0;
    }

    @Override
    public int permsLevel() {
        return 1;
    }

    @Override
    public ItemStack item() {
        return new ItemBuilder(Material.COMPASS).setName("§8➜ §e§lNavigator §7(Rechtsklick)").toItemStack();
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onHeld(Player player) {
        TitleBuilder.sendActionBar(player, "§7Klicke, um die §eNavigation §7zu öffnen");
    }

    @Override
    public void onClick(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1, 0.3F);
        plugin.getInventoryManagerLobby().getNavigator().open(player);
    }
}
