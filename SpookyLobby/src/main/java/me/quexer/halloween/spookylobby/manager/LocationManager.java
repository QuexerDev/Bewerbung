package me.quexer.halloween.spookylobby.manager;

import jdk.nashorn.internal.objects.annotations.Getter;
import me.quexer.api.quexerapi.builder.Hologramm;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;

public class LocationManager {

    private SpookyLobby plugin;

    private Location spawn, caseBlock, caseShop, caseShopMob, cases, dailyReward, dailyRewardMob, kitSG, ttt, cpr, cprMob, ffa;

    public LocationManager(SpookyLobby plugin) {
        this.plugin = plugin;

        spawn = getLocation("spawn");
        caseShop = getLocation("caseshop");
        caseShopMob = getLocation("caseshopmob");
        cases = getLocation("cases");
        caseBlock = getLocation("caseblock");
        dailyReward = getLocation("dailyreward");
        dailyRewardMob = getLocation("dailyrewardmob");
        kitSG = getLocation("kitsg");
        ttt = getLocation("ttt");
        cpr = getLocation("cpr");
        cprMob = getLocation("cprmob");
        ffa = getLocation("ffa");

        initHolos();
    }

    private void initHolos() {
        {
            Hologramm hologramm = new Hologramm(spawn, Arrays.asList(
                    "§7▌§7§k▌§f §e§lSpawn §7§k▌§7▌",
                    "§f",
                    "§8➥ §7Hier befindet sich der §eSpawn"));
        }
    }

    public Location getLocation(String name) {
        if(plugin.getLocationAPI().exist(name)) {
            return plugin.getLocationAPI().getLocation(name);
        } else
            return new Location(Bukkit.getWorld("world"), 35.3, 5, -52.5, 90, 0);
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getCaseShop() {
        return caseShop;
    }

    public Location getCases() {
        return cases;
    }

    public Location getDailyReward() {
        return dailyReward;
    }

    public Location getDailyRewardMob() {
        return dailyRewardMob;
    }

    public Location getKitSG() {
        return kitSG;
    }

    public Location getTtt() {
        return ttt;
    }

    public Location getCpr() {
        return cpr;
    }

    public Location getCprMob() {
        return cprMob;
    }

    public Location getFfa() {
        return ffa;
    }

    public Location getCaseBlock() {
        return caseBlock;
    }

    public Location getCaseShopMob() {
        return caseShopMob;
    }
}
