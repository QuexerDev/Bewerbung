package me.quexer.halloween.spookylobby.hotbar;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.TitleBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SelectedGadgetItem extends me.quexer.halloween.spookylobby.models.HotbarItem {
    @Override
    public int getSlot() {
        return 1;
    }

    @Override
    public int permsLevel() {
        return 0;
    }

    @Override
    public ItemStack item() {
        return new ItemBuilder(Material.BARRIER).setName("§8➜ §c§lKein Gadget ausgewählt!").toItemStack();
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onHeld(Player player) {
        TitleBuilder.sendActionBar(player, "§7Du kannst ein Gadget mit der §eKiste §7auswählen");
    }

    @Override
    public void onClick(Player player) {
        player.playSound(player.getLocation(), Sound.CREEPER_HISS, 1F, 3F);
    }
}
