package de.schdef.slash.coding.support.id;

import java.util.UUID;

public class SimpleUUIDService
    implements IdService {

    private static final int MAX_ID_LENGTH = 32;

    @Override
    public String newId() {
        return toString(UUID.randomUUID());
    }

    private String toString(UUID uuid) {
        String uuidAsString = uuid.toString();
        uuidAsString = uuidAsString.replaceAll("-", "");
        if (uuidAsString.length() > MAX_ID_LENGTH) {
            uuidAsString = uuidAsString.substring(0, MAX_ID_LENGTH);
        }
        return uuidAsString.toUpperCase();
    }

}
