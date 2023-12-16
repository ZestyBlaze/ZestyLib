package dev.zestyblaze.zestylib.events;

public interface ICancelableEvent {
    default void setCanceled(boolean canceled) {
        ((ZLEvent)this).isCanceled = canceled;
    }

    default boolean isCanceled() {
        return ((ZLEvent)this).isCanceled;
    }
}
