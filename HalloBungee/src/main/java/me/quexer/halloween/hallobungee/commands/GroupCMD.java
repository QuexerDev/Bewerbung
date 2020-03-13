package me.quexer.halloween.hallobungee.commands;


import me.quexer.halloween.hallobungee.HalloBungee;
import me.quexer.halloween.hallobungee.misc.uuid.UUIDFetcher;
import me.quexer.halloween.hallobungee.models.Group;
import me.quexer.halloween.hallobungee.models.GroupPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;
import java.util.stream.Collectors;

public class GroupCMD extends Command {

    private HalloBungee plugin;

    public GroupCMD(HalloBungee plugin) {
        super("group");
        this.plugin = plugin;

        //group set Quexer Admin -1
        //group get Quexer
        //group set Quexer Admin 3

    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        boolean isPlayer = (sender instanceof ProxiedPlayer);
        boolean allow = false;
        if (isPlayer && plugin.getGroup(((ProxiedPlayer) sender).getUniqueId().toString()).hasPermission(12)) {
            allow = true;
        } else if (!isPlayer && sender.hasPermission("admin")) {
            allow = true;
        } else {
            allow = false;
        }
        if (allow) {
            switch (args.length) {
                case 1: {
                    if (args[0].equalsIgnoreCase("list")) {
                        sender.sendMessage("§8§m-------------------------");
                        sender.sendMessage("§7Spielergruppen:");
                        plugin.getGroupClientManager().getAllGroups().forEach(backendGroup -> {
                            sender.sendMessage("§7- " + backendGroup.getFullName() + " §8/ §e" + backendGroup.getLevelID());
                        });
                        sender.sendMessage("§8§m-------------------------");
                    } else
                        help(sender);

                    break;
                }
                case 2: {
                    if (args[0].equalsIgnoreCase("info")) {
                        UUIDFetcher.getUUID(args[1], uuid -> {
                            if (uuid != null) {
                                GroupPlayer groupPlayer = plugin.getGroupPlayer(UUID.fromString(uuid.toString()).toString());
                                Group group = plugin.getGroupById(groupPlayer.getGroupId());
                                sender.sendMessage(plugin.getPrefix() + "§7Der Spieler " + group.getPrefix() + args[1] + " §7hat die Gruppe§8: §e" + group.getFullName());
                                sender.sendMessage(plugin.getPrefix() + "§7Er hat die Gruppe noch bis zum§8: §e" + plugin.getDate(groupPlayer.getExpires()));
                            } else {
                                sender.sendMessage(plugin.getPrefix() + "§cDieser Spieler existiert nicht!");
                            }
                        });
                    } else
                        help(sender);
                    break;
                }
                case 4: {

                    try {

                        if (args[0].equalsIgnoreCase("set")) {
                            if (Integer.valueOf(args[2]) <= plugin.getGroupClientManager().getAllGroups().size()) {
                                Group group = plugin.getGroupById(Integer.valueOf(args[2]));
                                UUIDFetcher.getUUID(args[1], uuid -> {
                                    if (uuid != null) {
                                        GroupPlayer groupPlayer = plugin.getGroupPlayer(UUID.fromString(uuid.toString()).toString());
                                        plugin.getBackendManager().setGroup(UUID.fromString(uuid.toString()).toString(), Integer.valueOf(args[2]), Long.valueOf(args[3]));
                                        sender.sendMessage(plugin.getPrefix() + "§7Der Spieler " + group.getColor() + args[1] + " §7hat die Gruppe§8: " + group.getFullName() + " §7erhalten§8. §7Bis zum§8: §e" + plugin.getDate(groupPlayer.getExpires() ));

                                    } else {
                                        sender.sendMessage(plugin.getPrefix() + "§cDieser Spieler existiert nicht!");
                                    }
                                });
                            } else
                                sender.sendMessage(plugin.getPrefix() + "§cDiese Gruppe existiert nicht §8[§e/group list§8]");
                        } else
                            help(sender);
                        break;
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(plugin.getError() + "§7Achte auf dein Eingabeformat§8!");
                        break;
                    }
                }
                default:
                    help(sender);
                    break;
            }
        } else {
            GroupPlayer player = plugin.getGroupPlayer(plugin.getProxy().getPlayer(sender.getName()).getUniqueId().toString());
            sender.sendMessage(plugin.getPrefix() + "§7Du hast deine Gruppe noch bis zum§8: §e" + plugin.getDate(player.getExpires()));
        }
    }

    private void help(CommandSender sender) {
        sender.sendMessage(plugin.getPrefix() + "§8▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        sender.sendMessage(plugin.getPrefix() + "§e/group info [Name]");
        sender.sendMessage(plugin.getPrefix() + "§e/group set [Name] [GroupID] [days]");
        sender.sendMessage(plugin.getPrefix() + "§e/group list");
        sender.sendMessage(plugin.getPrefix() + "§8▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
    }
}
