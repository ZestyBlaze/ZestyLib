package dev.zestyblaze.zestylib.events;

import dev.zestyblaze.zestylib.ZestyLib;

public class ZLEvent {
    private boolean cancelled;

    public void cancel() {
        ZestyLib.LOGGER.warn("Event Canceled");
        setCanceled(true);
    }

    public void setCanceled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCanceled() {
        return cancelled;
    }
}
