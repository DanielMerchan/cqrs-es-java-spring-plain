package com.merchan.cqrses.example.core.domain;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.merchan.cqrses.example.core.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;

public abstract class AggregateRoot {

    @Getter
    protected String id;

    @Getter
    @Setter
    private int version = -1;

    @Getter
    private final List<BaseEvent> uncommittedChanges = new ArrayList<>();
    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public void markChangesAsCommitted() {
        this.uncommittedChanges.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            var method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.log(Level.WARNING, MessageFormat.format("Unable to apply event {0}", event.getClass().getName()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to apply event to aggregate", e);
        } finally {
            if (isNewEvent)
                this.uncommittedChanges.add(event);
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }

}
