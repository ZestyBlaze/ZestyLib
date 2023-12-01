package dev.zestyblaze.zestylib.events;

public class ZLEvent {
    private boolean cancelled;

    public void cancel() {
        setCanceled(true);
    }

    public void setCanceled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCanceled() {
        return cancelled;
    }
}
