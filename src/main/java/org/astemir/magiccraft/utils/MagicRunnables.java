package org.astemir.magiccraft.utils;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class MagicRunnables {

    public static CopyOnWriteArrayList<MagicRunnable> runnables;


    public MagicRunnables() {
        runnables = new CopyOnWriteArrayList<>();
    }

    public boolean run(MagicRunnable runnable){
        if (!containsRunnable(runnable.getUUID())) {
            runnables.add(runnable);
            return true;
        }
        return false;
    }

    private boolean containsRunnable(UUID uuid){
        for (MagicRunnable magicRunnable : runnables) {
            if (magicRunnable.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public void update(long ticks){
        for (MagicRunnable runnable : runnables) {
            if (ticks >= runnable.getEnds()){
                runnable.end();
                runnables.remove(runnable);
            }else {
                if (runnable.condition()) {
                    runnable.update();
                } else {
                    runnable.interrupted();
                    runnables.remove(runnable);
                }
            }
        }
    }
}
