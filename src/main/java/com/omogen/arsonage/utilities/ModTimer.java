package com.omogen.arsonage.utilities;
import java.util.Map;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ModTimer {
    private static final Map<UUID, ModTimerTask> timers = new ConcurrentHashMap<>();

    public static UUID scheduleTask(int ticks, Runnable task) {
        UUID id = UUID.randomUUID();
        timers.put(id, new ModTimerTask(ticks, task));
        return id;
    }

    public static boolean cancelTimer(UUID id) {
        return timers.remove(id) != null;
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
    	Iterator<Map.Entry<UUID, ModTimerTask>> timerIterator = timers.entrySet().iterator();
    	while (timerIterator.hasNext()) {
    		Map.Entry<UUID, ModTimerTask> entry = timerIterator.next();
    		ModTimerTask task = entry.getValue();
    		if (task.tick()) {
    			timerIterator.remove();
    		}
    	}
	}

    public static class ModTimerTask {
        @SuppressWarnings("unused")
		private final int totalTicks;
        private int remainingTicks;
        private final Runnable task;

        public ModTimerTask(int ticks, Runnable task) {
            this.totalTicks = ticks;
            this.remainingTicks = ticks;
            this.task = task;
        }

        public boolean tick() {
            if (--remainingTicks <= 0) {
                task.run();
                return true;
            }
            return false;
        }

        public int getRemainingTicks() {
            return remainingTicks;
        }
    }
}
