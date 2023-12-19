package dev.zestyblaze.zestylib.event;

public interface ICancelableEvent {
    default void setCanceled(boolean canceled) {
        ((ZLEvent)this).isCanceled = canceled;
    }

    default boolean isCanceled() {
        return ((ZLEvent)this).isCanceled;
    }
}
