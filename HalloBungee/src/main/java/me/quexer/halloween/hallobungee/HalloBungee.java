package me.quexer.halloween.hallobungee;

import com.google.gson.Gson;
import me.quexer.halloween.hallobungee.commands.GroupCMD;
import me.quexer.halloween.hallobungee.listeners.PlayerChatListener;
import me.quexer.halloween.hallobungee.listeners.PlayerConnectListener;
import me.quexer.halloween.hallobungee.listeners.ProxyMessageListener;
import me.quexer.halloween.hallobungee.manager.*;
import me.quexer.halloween.hallobungee.manager.backend.GroupClientManager;
import me.quexer.halloween.hallobungee.manager.backend.PlayerClientManager;
import me.quexer.halloween.hallobungee.models.BackendPlayer;
import me.quexer.halloween.hallobungee.models.Group;
import me.quexer.halloween.hallobungee.models.GroupPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class HalloBungee extends Plugin {

    public static final String URL = "http://192.168.2.113:8000/";

    private static HalloBungee instance;

    private String error;
    private Gson gson;

    private BackendManager backendManager;
    private GroupClientManager groupClientManager;
    private PlayerClientManager playerClientManager;
    private String prefix;
    private String banPrefix;
    private ExecutorService executor;
    private BanManager banManager;
    private MuteManager muteManager;
    private ProxyMessageManager proxyMessageManager;
    private ReportManager reportManager;
    @Override
    public void onEnable() {
        instance = this;
        prefix = "§6§lHalloween §8➜ ";
        banPrefix = "§4§lSpookyBan §8➜ ";
        error = "§c§lFEHLER §8➜ ";
        gson = new Gson();
        executor = Executors.newCachedThreadPool();

        backendManager = new BackendManager(this);
        banManager = new BanManager(this);
        muteManager = new MuteManager(this);
        proxyMessageManager = new ProxyMessageManager(this);
        reportManager = new ReportManager(this);
        groupClientManager = new GroupClientManager(this);
        playerClientManager = new PlayerClientManager(this);

        getProxy().getPluginManager().registerListener(this, new PlayerConnectListener(this));
        getProxy().getPluginManager().registerListener(this, new PlayerChatListener(this));
        getProxy().getPluginManager().registerListener(this, new ProxyMessageListener(this));
        getProxy().getPluginManager().registerCommand(this, new GroupCMD(this));

    }

    @Override
    public void onDisable() {
        groupClientManager.getGroupPlayerCache().forEach((s, groupPlayer) -> {
            groupClientManager.saveGroupPlayer(groupPlayer);
        });
        playerClientManager.getBackendPlayerCache().forEach((s, backendPlayer) -> {
            playerClientManager.saveBackendPlayer(backendPlayer);
        });
    }

    
    public String numberFormat(Number number) {
        return new DecimalFormat("###,###,###,###,###,###").format(number);
    }

    
    public String getDate(long millis) {
        if(millis == -1) {
            return "§4§lPERMANENT";
        } else {
            SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date now = new Date();
            now.setTime(millis);
            return sdfDate.format(now);
        }
    }

    
    public BackendPlayer getBackendPlayer(UUID uuid) {
        return getBackendPlayer(uuid.toString());
    }

    
    public BackendPlayer getBackendPlayer(String uuid) {
        return backendManager.getBackendPlayer(uuid);
    }

    
    public BackendPlayer getBackendPlayer(ProxiedPlayer player) {
        return getBackendPlayer(player.getUniqueId().toString());
    }

    
    public List<Group> getGroups() {
        return groupClientManager.getAllGroups();
    }

    
    public Group getGroupById(int id) {
        for (Group group : groupClientManager.getAllGroups()) {
            if(group.getGid() == id)
                return group;
        }
        return null;
    }

    
    public Group getGroup(BackendPlayer player) {
        return getGroup(player.getUuid());
    }

    
    public Group getGroup(String uuid) {
        return getGroupById(backendManager.getGroupPlayer(uuid).getGroupId());
    }

    
    public Group getGroup(UUID uuid) {
        return getGroup(uuid.toString());
    }

    
    public Group getGroup(ProxiedPlayer player) {
        return getGroup(player.getUniqueId().toString());
    }

    
    public Group getGroup(GroupPlayer player) {
        return getGroup(player.getUuid());
    }

    
    public GroupPlayer getGroupPlayer(BackendPlayer player) {
        return getGroupPlayer(player.getUuid());
    }

    
    public GroupPlayer getGroupPlayer(String uuid) {
        return backendManager.getGroupPlayer(uuid);
    }

    
    public GroupPlayer getGroupPlayer(UUID uuid) {
        return getGroupPlayer(uuid.toString());
    }

    
    public GroupPlayer getGroupPlayer(ProxiedPlayer player) {
        return getGroupPlayer(player.getUniqueId().toString());
    }


    public BackendPlayer.Date getData(String uuid) {
        return getBackendPlayer(uuid).getDate();
    }

    
    public BackendPlayer.Date getData(UUID uuid) {
        return getBackendPlayer(uuid.toString()).getDate();
    }

    
    public BackendPlayer.Date getData(ProxiedPlayer player) {
        return getBackendPlayer(player).getDate();
    }

    public static HalloBungee getInstance() {
        return instance;
    }

    public String getError() {
        return error;
    }

    public Gson getGson() {
        return gson;
    }

    public BackendManager getBackendManager() {
        return backendManager;
    }

    public GroupClientManager getGroupClientManager() {
        return groupClientManager;
    }

    public PlayerClientManager getPlayerClientManager() {
        return playerClientManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getBanPrefix() {
        return banPrefix;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public MuteManager getMuteManager() {
        return muteManager;
    }

    public ProxyMessageManager getProxyMessageManager() {
        return proxyMessageManager;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }
}
