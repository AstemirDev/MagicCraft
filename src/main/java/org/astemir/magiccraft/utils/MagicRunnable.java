package org.astemir.magiccraft.utils;

import org.astemir.magiccraft.MagicCraft;

import java.util.UUID;

public abstract class MagicRunnable {

    private long ends;

    private UUID uuid;

    public MagicRunnable(int ticks) {
        this(UUID.randomUUID(),ticks);
    }

    public MagicRunnable(UUID uuid, int ticks) {
        this.uuid = uuid;
        ends = MagicCraft.ticks+ticks;
    }

    public UUID getUUID() {
        return uuid;
    }

    public long getEnds() {
        return ends;
    }

    public long getLeft(){
        return ends-MagicCraft.ticks;
    }

    protected abstract void update();

    protected abstract void end();

    protected abstract void interrupted();

    protected abstract boolean condition();
}
