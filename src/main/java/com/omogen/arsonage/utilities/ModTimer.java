package com.omogen.arsonage.utilities;
import java.util.Timer;
import java.util.TimerTask;

public class ModTimer {
    private Timer timer;

    public ModTimer() {
        this.timer = new Timer();
    }

    public void schedule(Runnable task, int delayInTicks) {
        int delayInMillis = delayInTicks * 50; // Convert ticks to milliseconds (20 ticks = 1 second)
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.run();
            }
        }, delayInMillis);
    }

    public void cancel() {
        timer.cancel();
    }
}