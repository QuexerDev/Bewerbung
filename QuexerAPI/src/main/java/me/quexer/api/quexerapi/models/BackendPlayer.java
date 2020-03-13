package me.quexer.api.quexerapi.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackendPlayer {


    private String uuid;
    private Data data;
    private LobbyPlayer lobbyPlayer;



    public BackendPlayer(String uuid) {
        this.uuid = uuid;

        data = new Data();
        lobbyPlayer = new LobbyPlayer();

    }





    public static class Data {

        private long coins;
        private long keys;
        private long cpr;
        private long elo;
        private boolean nick;

        public Data() {
            coins = 10000;
            keys = 3;
            cpr = new Random().nextInt(200) + 300;
            elo = 1000;
            nick = false;
        }

        public long getCoins() {
            return coins;
        }

        public void setCoins(long coins) {
            this.coins = coins;
        }

        public long getKeys() {
            return keys;
        }

        public void setKeys(long keys) {
            this.keys = keys;
        }

        public long getCpr() {
            return cpr;
        }

        public void setCpr(long cpr) {
            this.cpr = cpr;
        }

        public long getElo() {
            return elo;
        }

        public void setElo(long elo) {
            this.elo = elo;
        }

        public boolean isNick() {
            return nick;
        }

        public void setNick(boolean nick) {
            this.nick = nick;
        }
    }

    public static class LobbyPlayer {

        private List<String> gadgets;
        private Chests chests;

        public LobbyPlayer() {
            gadgets = new ArrayList<>();
            chests = new Chests();
        }

        public List<String> getGadgets() {
            return gadgets;
        }

        public void setGadgets(List<String> gadgets) {
            this.gadgets = gadgets;
        }

        public Chests getChests() {
            return chests;
        }

        public void setChests(Chests chests) {
            this.chests = chests;
        }

        public class Chests {
            private int epic;
            private int common;
            private int rare;
            private int ultimate;
            private int legendary;

            public Chests() {
                epic = 1;
                common = 1;
                rare = 1;
                ultimate = 1;
                legendary = 1;
            }

            public int getEpic() {
                return epic;
            }

            public void setEpic(int epic) {
                this.epic = epic;
            }

            public int getCommon() {
                return common;
            }

            public void setCommon(int common) {
                this.common = common;
            }

            public int getRare() {
                return rare;
            }

            public void setRare(int rare) {
                this.rare = rare;
            }

            public int getUltimate() {
                return ultimate;
            }

            public void setUltimate(int ultimate) {
                this.ultimate = ultimate;
            }

            public int getLegendary() {
                return legendary;
            }

            public void setLegendary(int legendary) {
                this.legendary = legendary;
            }
        }
    }

    public String getUuid() {
        return uuid;
    }

    public Data getData() {
        return data;
    }

    public LobbyPlayer getLobbyPlayer() {
        return lobbyPlayer;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setLobbyPlayer(LobbyPlayer lobbyPlayer) {
        this.lobbyPlayer = lobbyPlayer;
    }
}
