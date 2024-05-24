package com.merchan.cqrses.example.core.event;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private String id;
    private Instant timestamp;
    private String aggregateId;
    private String aggregateType;
    private int version;
    private String eventType;
    private BaseEvent eventData;
}
