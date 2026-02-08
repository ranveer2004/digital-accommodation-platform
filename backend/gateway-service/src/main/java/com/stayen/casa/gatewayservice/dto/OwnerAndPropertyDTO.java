package com.stayen.casa.gatewayservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OwnerAndPropertyDTO {

    private String ownerId;

    private String propertyId;

    private String message;

    private LocalDateTime createdAt;

    @JsonIgnore
    public Map<String, Object> getPayloadMap() {
        return Map.of(
                "ownerId", this.getOwnerId(),
                "propertyId", this.getPropertyId(),
                "message", this.getMessage(),
                "createdAt", this.getCreatedAt()
        );
    }

    @JsonIgnore
    public static Map<String, Object> getOwnerIdAndPropertyPayload(String ownerId, String propertyId) {
        return Map.of(
                "ownerId", ownerId,
                "propertyId", propertyId
        );
    }

}
