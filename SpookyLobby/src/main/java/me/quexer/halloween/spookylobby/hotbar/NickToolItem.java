package me.quexer.halloween.spookylobby.hotbar;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.TitleBuilder;
import me.quexer.api.quexerapi.misc.Particle;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.halloween.spookylobby.SpookyLobby;
import net.minecraft.server.v1_8_R3.EnumParticle;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sun.plugin2.main.server.Plugin;

import java.util.ArrayList;
import java.util.List;

public class NickToolItem extends me.quexer.halloween.spookylobby.models.HotbarItem {

    private SpookyLobby plugin;

    private List<Player> used;

    public NickToolItem(SpookyLobby plugin) {
        this.plugin = plugin;
        used = new ArrayList<>();
    }

    @Override
    public int getSlot() {
        return 4;
    }

    @Override
    public int permsLevel() {
        return 3;
    }

    @Override
    public ItemStack item() {
        return new ItemBuilder(Material.NAME_TAG).setName("§8➜ §5§lNickTool §7(Rechtsklick)").toItemStack();
    }

    @Override
    public void onPlayerJoin(Player player) {

    }

    @Override
    public void onHeld(Player player) {
        TitleBuilder.sendActionBar(player, "§7Dein §5AutoNick §7ist zurzeit§8: " + (plugin.getData(player).isNick() ? "§aAktiviert" : "§cDeaktiviert"));
    }

    @Override
    public void onClick(Player player) {
        if (!used.contains(player)) {
            BackendPlayer.Data data = plugin.getData(player);
            data.setNick((data.isNick() ? false : true));
            player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 3);
            new Particle(EnumParticle.PORTAL, player.getLocation(), false, 0.5F, 1F, 0.5F, 0.05F, 250).sendPlayer(player);
            TitleBuilder.sendActionBar(player, "§7Dein §5AutoNick §7ist zurzeit§8: " + (plugin.getData(player).isNick() ? "§aAktiviert" : "§cDeaktiviert"));
            plugin.getBackendManager().saveBackendPlayer(plugin.getBackendPlayer(player));
            used.add(player);
            plugin.runTaskLaterAsync(20 * 5, () -> {
                used.remove(player);
            });
        } else {
            player.sendMessage(plugin.getError() + "§cWarte einen Moment bevor du das Item benutzt!");
            player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 3);

        }
    }
}
