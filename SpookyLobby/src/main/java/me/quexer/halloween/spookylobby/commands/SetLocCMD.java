package me.quexer.halloween.spookylobby.commands;

import me.quexer.api.quexerapi.models.Group;
import me.quexer.halloween.spookylobby.SpookyLobby;
import org.bukkit.EntityEffect;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;

public class SetLocCMD {

    private SpookyLobby plugin;

    public SetLocCMD(SpookyLobby plugin) {
        this.plugin = plugin;

        plugin.onCommand("setloc", (sender, command, s, args) -> {

            Group group = plugin.getGroup((Player) sender);
            if(group.hasPermission(13)) {
                if(args.length == 1) {
                    String name = args[0];
                    plugin.getLocationAPI().setLocation(name, ((Player) sender).getLocation());
                    sender.sendMessage(plugin.getPrefix()+"§7Du hast die Location§8: §e" + name + " §7erfolgreich gesetzt§8!");
                    ((Player) sender).playNote(((Player) sender).getLocation(), Instrument.STICKS, Note.natural(2, Note.Tone.F));
                    ((Player) sender).playNote(((Player) sender).getLocation(), Instrument.STICKS, Note.natural(2, Note.Tone.A));
                    ((Player) sender).playEffect(EntityEffect.FIREWORK_EXPLODE);
                } else
                    sender.sendMessage(plugin.getPrefix()+"§7Benutze§8: §c/setLoc <Location>");
            }
            return true;
        });

    }
}
