package me.quexer.api.quexerapi.manager.backend;

import me.quexer.api.quexerapi.models.BackendPlayer;
import me.quexer.api.quexerapi.models.GroupPlayer;

public interface PlayerClientManager {

    public BackendPlayer getBackendPlayer(String uuid);

    public void saveBackendPlayer(BackendPlayer backendPlayer);

}
