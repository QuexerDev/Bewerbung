package me.quexer.halloween.spookylobby.manager;

import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.inventory.GuiBuilder;
import me.quexer.api.quexerapi.builder.inventory.GuiItem;
import me.quexer.api.quexerapi.builder.inventory.InventoryGui;
import me.quexer.halloween.spookylobby.SpookyLobby;
import me.quexer.halloween.spookylobby.callbacks.NavigatorCallback;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;

public class InventoryManager {

    private SpookyLobby plugin;

    private InventoryGui navigator;
    //Spawn, caseShop, cases, dailyreward, kitsg, ttt, cpr, ffa

    public InventoryManager(SpookyLobby plugin) {
        this.plugin = plugin;

        initNavigator();
    }

    private void initNavigator() {
        GuiBuilder builder = getDefaultInv("§e§lNavigator");

        builder.onClose(player -> player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 1, 0.3F));

        builder.addItem(11, new GuiItem(new ItemBuilder(Material.GOLD_INGOT).setName("§eCPR§7-§eShop").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getCpr())));
        builder.addItem(13, new GuiItem(new ItemBuilder(Material.MAGMA_CREAM).setName("§bSpawn").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getSpawn())));
        builder.addItem(15, new GuiItem(new ItemBuilder(Material.ENDER_CHEST).setName("§3CaseOpening").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getCases())));
        builder.addItem(19, new GuiItem(new ItemBuilder(Material.CHEST).setName("§3Case§7-§3Shop").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getCaseShop())));
        builder.addItem(25, new GuiItem(new ItemBuilder(Material.EMERALD).setName("§aDaily Reward").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getDailyReward())));
        builder.addItem(29, new GuiItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§3KitSG").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getKitSG())));
        builder.addItem(31, new GuiItem(new ItemBuilder(Material.STICK).setName("§4TTT").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getTtt())));
        builder.addItem(33, new GuiItem(new ItemBuilder(Material.IRON_CHESTPLATE).setName("§aFFA").setLore("§8§m-------------------", "§7Klicke um dich zu teleportieren§8!").toItemStack(), new NavigatorCallback(plugin, plugin.getLocationManager().getFfa())));
        navigator = builder.build();
    }

    public GuiBuilder getDefaultInv(String name) {
        GuiBuilder builder = plugin.getQuexerAPI().gui(45).setName(name);
        for (int i = 0; i < builder.getSize(); i++) {
            builder.addItem(i, new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§a").toItemStack(), player -> player.playSound(player.getLocation(), Sound.CREEPER_HISS, 1, 3)));
        }
        return builder;
    }

    public InventoryGui getNavigator() {
        return navigator;
    }
}
