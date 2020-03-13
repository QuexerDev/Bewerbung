package me.quexer.api.quexerapi;

import com.google.gson.Gson;
import me.quexer.api.quexerapi.api.LocationAPI;
import me.quexer.api.quexerapi.builder.ItemBuilder;
import me.quexer.api.quexerapi.builder.inventory.GuiBuilder;
import me.quexer.api.quexerapi.builder.inventory.GuiItem;
import me.quexer.api.quexerapi.event.EventManager;
import me.quexer.api.quexerapi.manager.BackendManager;
import me.quexer.api.quexerapi.manager.backend.GroupClientManager;
import me.quexer.api.quexerapi.manager.backend.PlayerClientManager;
import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.Group;
import me.quexer.api.quexerapi.models.GroupPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public interface HalloServer {

    public String numberFormat(Number number);

    String getDate(long millis);

    BackendPlayer getBackendPlayer(UUID uuid);

    BackendPlayer getBackendPlayer(String uuid);

    BackendPlayer getBackendPlayer(Player player);

    List<Group> getGroups();

    Group getGroupById(int id);

    Group getGroup(BackendPlayer player);

    Group getGroup(String uuid);

    Group getGroup(UUID uuid);

    Group getGroup(Player player);

    Group getGroup(GroupPlayer player);

    GroupPlayer getGroupPlayer(BackendPlayer player);

    GroupPlayer getGroupPlayer(String uuid);

    GroupPlayer getGroupPlayer(UUID uuid);

    GroupPlayer getGroupPlayer(Player player);

    BackendPlayer.LobbyPlayer getLobbyPlayer(String uuid);

    public BackendPlayer.LobbyPlayer getLobbyPlayer(UUID uuid);

    BackendPlayer.LobbyPlayer getLobbyPlayer(Player player);

    BackendPlayer.LobbyPlayer.Chests getChests(String uuid);

    BackendPlayer.LobbyPlayer.Chests getChests(UUID uuid);

    BackendPlayer.LobbyPlayer.Chests getChests(Player player);

    BackendPlayer.Data getData(String uuid);

    BackendPlayer.Data getData(UUID uuid);

    BackendPlayer.Data getData(Player player);

    void registerEvent(Class<? extends Event> cls, EventManager.EventListener listener);

    void unregisterEvent(Class<? extends Event> cls, EventManager.EventListener listener);

    void onCommand(String command, CommandExecutor executor);

    void removeMetadata(Entity entity, String name);

    void setMetadata(Entity entity, String name, Object value);

    void removeMetadata(Block block, String name);

    void setMetadata(Block block, String name, Object value);

    BukkitTask runTask(Runnable runnable);

    BukkitTask runTaskAsync(Runnable runnable);

    BukkitTask runTaskLater(long delay, Runnable runnable);

    BukkitTask runTaskLaterAsync(long delay, Runnable runnable);

    BukkitTask runTaskTimer(long delay, long repeat, Runnable runnable);

    BukkitTask runTaskTimerAsync(long delay, long repeat, Runnable runnable);

    GuiBuilder gui(int size);

    GuiBuilder gui(Inventory inventory);

    GuiItem guiItem(ItemStack item, Consumer<Player> callback);

    ItemBuilder item(Material material);

    ItemBuilder item(Material material, short data);

    Gson getGson();

    LocationAPI getLocationAPI();

    EventManager getEventManager();

    ExecutorService getExecutor();

    BackendManager getBackendManager();

    GroupClientManager getGroupClientManager();

    PlayerClientManager getPlayerClientManager();

}
