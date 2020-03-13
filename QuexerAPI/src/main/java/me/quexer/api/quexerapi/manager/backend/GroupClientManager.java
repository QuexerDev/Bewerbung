package me.quexer.api.quexerapi.manager.backend;

import me.quexer.api.quexerapi.models.Group;
import me.quexer.api.quexerapi.models.GroupPlayer;

import java.util.List;

public interface GroupClientManager {

    public GroupPlayer getGroupPlayer(String uuid);

    public void saveGroupPlayer(GroupPlayer groupPlayer);

    public List<Group> getAllGroups();

}
