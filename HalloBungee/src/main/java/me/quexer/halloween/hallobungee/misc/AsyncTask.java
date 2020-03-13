package me.quexer.halloween.hallobungee.misc;


import me.quexer.halloween.hallobungee.HalloBungee;

public class AsyncTask {


    public AsyncTask(Runnable run) {
        HalloBungee.getInstance().getExecutor().execute(run);
    }
}
