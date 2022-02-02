package com.pixplaze.simpleeventtracker.json.data;

import java.util.UUID;

public class PlayerProfile {

    final UUID uuid;
    final String name;

    public PlayerProfile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
