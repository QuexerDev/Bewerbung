package me.cholesterinlord.coinsgame.session;

import me.cholesterinlord.coinsgame.CoinsGame;
import me.cholesterinlord.coinsgame.event.EventListener;
import me.cholesterinlord.coinsgame.inventory.GuiBuilder;
import me.cholesterinlord.coinsgame.inventory.GuiItem;
import me.cholesterinlord.coinsgame.inventory.InventoryGui;
import me.quexer.api.quexerapi.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class CalcSession {

    private CoinsGame coinsGame;
    private Player player;
    private int done;

    private InventoryGui difficultyInventory;

    private EventListener<AsyncPlayerChatEvent> chatEventEventListener;

    private EventListener<PlayerQuitEvent> playerQuitEventEventListener;

    private int currentResult = 0;

    private BukkitTask countDown;

    private CalcDifficulty calcDifficulty;

    public CalcSession(Player player) {
        this.coinsGame = CoinsGame.getInstance();
        this.player = player;
        this.done = 0;

        this.difficultyInventory = new GuiBuilder(27, coinsGame)
                .addItem(11, new GuiItem(new ItemBuilder(Material.GREEN_WOOL, 1).toItemStack(), clicker -> startGame(CalcDifficulty.EASY)))
                .addItem(13, new GuiItem(new ItemBuilder(Material.ORANGE_WOOL, 1).toItemStack(), clicker -> startGame(CalcDifficulty.MEDIUM)))
                .addItem(15, new GuiItem(new ItemBuilder(Material.RED_WOOL, 1).toItemStack(), clicker -> startGame(CalcDifficulty.HARD)))
                .build();

        chatEventEventListener = (AsyncPlayerChatEvent event) -> {
            if(this.countDown == null)
                return;
            if(!coinsGame.getPlayer_CalcSession().containsKey(event.getPlayer()))
                return;
            if(countDown.isCancelled() || countDown == null)
                return;
            event.setCancelled(true);
            if(event.getMessage().equalsIgnoreCase("stop")) {
                end();
                return;
            }

            try {
                int input = Integer.valueOf(event.getMessage());
                player.sendMessage("§7Eingabe: §e"+input);
                if(input == this.currentResult) {
                    player.sendMessage(CoinsGame.PREFIX + "Die eingabe war §arichtig! §8(§e+"+this.calcDifficulty.getCoins()+" Coins§8)");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 1, 1);
                    addCoins(this.calcDifficulty.getCoins());
                    sendNextCalc();
                } else {
                    player.sendMessage(CoinsGame.PREFIX + "§cDie Eingabe war falsch :(");
                    end();
                }
            } catch (NumberFormatException ex) {
                player.sendMessage(CoinsGame.PREFIX + "§cDu musst eine ganze Zahl angeben (Ohne Punkt oder Komma)");
                return;
            }


        };

        playerQuitEventEventListener = (PlayerQuitEvent event) -> {
            if(!coinsGame.getPlayer_CalcSession().containsKey(event.getPlayer()))
                return;

            end();
        };

        this.coinsGame.getEventManager().registerEvent(AsyncPlayerChatEvent.class, chatEventEventListener);
        this.coinsGame.getEventManager().registerEvent(PlayerQuitEvent.class, playerQuitEventEventListener);

        join();
    }

    public void join() {
        if(coinsGame.getPlayer_CalcSession().containsKey(player)) {
            player.sendMessage(coinsGame.PREFIX + "§cDu bist bereits in einer §eCoinsGame §crunde");
            return;
        }
        coinsGame.getPlayer_CalcSession().put(this.player, this);
        this.player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 5);
        this.player.closeInventory();
        this.difficultyInventory.open(this.player);

    }

    public void startGame(CalcDifficulty difficulty) {
        player.sendMessage(CoinsGame.PREFIX + "Du hast das §eCoinsGame §7auf der Schwierigkeit §8["+difficulty.written+"§8] §7gestartet §a:)");
        player.sendMessage(CoinsGame.PREFIX + "Schreibe §cSTOP §7,um das Spiel zu beenden");
        this.calcDifficulty = difficulty;
        player.closeInventory();
        if(!this.coinsGame.getPlayer_Coins().containsKey(this.player))
            this.coinsGame.getPlayer_Coins().put(this.player, 0);
        sendNextCalc();
    }

    public void end() {
        if(this.countDown == null)
            return;
        this.player.sendMessage(CoinsGame.PREFIX + "§cDas Spiel wurde abgebrochen");
        this.coinsGame.getPlayer_CalcSession().remove(player);
        if(!this.countDown.isCancelled())
            this.countDown.cancel();

        this.coinsGame.getEventManager().unregisterEvent(AsyncPlayerChatEvent.class ,chatEventEventListener);
        this.coinsGame.getEventManager().unregisterEvent(PlayerQuitEvent.class ,playerQuitEventEventListener);

        this.difficultyInventory.destroy();
    }

    public void sendNextCalc() {
        if(countDown != null)
            if(!this.countDown.isCancelled())
                this.countDown.cancel();
        switch (this.calcDifficulty) {
            case EASY: {
                    int first = new Random().nextInt(9)+1;
                    int second = new Random().nextInt(9)+1;
                    boolean add = new Random().nextBoolean();

                    this.currentResult = add ?
                            first + second : first - second;

                    player.sendMessage(CoinsGame.PREFIX + "Was ergibt: §e" + (add ? first+"+"+second : first+"-"+second)+"§7? (§a5 Sekunden§7)");
                    break;
                }

            case MEDIUM: {
                    int first = new Random().nextInt(49)+1;
                    int second = new Random().nextInt(49)+1;
                    boolean add = new Random().nextBoolean();

                    this.currentResult = add ?
                            first + second : first - second;

                    player.sendMessage(CoinsGame.PREFIX + "Was ergibt: §e" + (add ? first+"+"+second : first+"-"+second)+"§7? (§a5 Sekunden§7)");
                    break;
                }

            case HARD: {
                    int first = new Random().nextInt(49)+1;
                    int second = new Random().nextInt(49)+1;
                    int third = new Random().nextInt(49)+1;
                    boolean add = new Random().nextBoolean();

                    this.currentResult = add ?
                            first + second + third : first - second - third;

                    player.sendMessage(CoinsGame.PREFIX + "Was ergibt: §e" + (add ? first+"+"+second+"+"+third : first+"-"+second+"-"+third)+"§7? (§a5 Sekunden§7)");
                break;
            }
        }

        final int[] count = {5};
        this.countDown = coinsGame.getServer().getScheduler().runTaskTimerAsynchronously(this.coinsGame, () -> {

            if(count[0] == 0) {
                this.player.sendMessage(CoinsGame.PREFIX + "§cDu hast zu lange gebraucht");
                end();
                this.countDown.cancel();
            } else
                player.sendMessage(CoinsGame.PREFIX + "--> §e"+count[0]);
            count[0]--;
        }, 20L, 20L);
    }

    public void addCoins(int amount) {
        if(!this.coinsGame.getPlayer_Coins().containsKey(this.player))
            return;
        this.coinsGame.getPlayer_Coins().replace(this.player, this.coinsGame.getPlayer_Coins().get(this.player)+amount);
    }

    public InventoryGui getDifficultyInventory() {
        return difficultyInventory;
    }

    public int getDone() {
        return done;
    }

    public enum CalcDifficulty {

        EASY("§aEasy", 5),
        MEDIUM("§6Medium", 10),
        HARD("§4Hard", 15);

        private String written;
        private int coins;

        CalcDifficulty(String written, int coins) {
            this.written = written;
            this.coins = coins;
        }

        public int getCoins() {
            return coins;
        }

        public String getWritten() {
            return written;
        }
    }
}
