package org.ares.foundation.cli.util.events;

public class EventTemplateHandler {

    public String fetchTemplate(String eventType) {

        if (eventType.isEmpty()) {
            return "EventListenerTemplate.vm";
        }

        switch(eventType) {
            case "join":
                return getPlayerJoinTemplate();
            case "quit":
                return getPlayerQuitTemplate();
            case "chat":
                return getPlayerChatTemplate();
            case "interact":
                return getPlayerInteractTemplate();
            case "interacte":
                return getPlayerInteractEntityTemplate();
            case "interactae":
                return getPlayerInteractAtEntityTemplate();
            case "pdeath":
                return getPlayerDeathTemplate();
            case "espawn":
                return getEntitySpawnTemplate();
            case "edamage":
                return getEntityDamageEvent()    ;
            case "edamagebe":
                return getEntityDamageByEntityTemplate();
            case "spawner":
                return getSpawnerSpawnTemplate();
            case "einteract":
                return getEntityInteractTemplate();
            case "bbreak":
                return getBlockBreakEventTemplate();
            case "bplace":
                return getBlockPlaceEventTemplate();
            case "binteract":
                return getBlockInteractEventTemplate();
            case "iopen":
                return getInventoryOpenTemplate();
            case "iclose":
                return getInventoryCloseTemplate();
            case "iclick":
                return getInventoryClickTemplate();
            case "projlaunch":
                return getProjectileLaunchTemplate();
            case "projhit":
                return getProjectileHitTemplate();
        }
        //TODO Test if we ever reach this
        return "EventListenerTemplate.vm";
    }


    /*********************************************************
     Player Event Templates
     *********************************************************/

    private String getPlayerJoinTemplate() {
        return "player/PlayerJoinTemplate.vm";
    }
    private String getPlayerQuitTemplate() {
        return "player/PlayerQuitTemplate.vm";
    }

    private String getPlayerChatTemplate() {
        return "player/PlayerEventJoinTemplate.vm";
    }

    private String getPlayerInteractTemplate() {
        return "player/PlayerChatTemplate.vm";
    }

    private String getPlayerInteractEntityTemplate() {
        return "player/PlayerInteractEntityTemplate.vm";
    }

    private String getPlayerInteractAtEntityTemplate() {
        return "player/PlayerInteractAtEntityTemplate.vm";
    }

    private String getPlayerDeathTemplate() {
        return "player/PlayerDeathTemplate.vm";
    }

    /*********************************************************
     Entity Event Templates
     *********************************************************/

    private String getEntitySpawnTemplate() {
        return "entity/EntitySpawnTemplate.vm";
    }

    private String getEntityDamageEvent() {
        return "entity/EntityDamageEvent.vm";
    }

    private String getEntityDamageByEntityTemplate() {
        return "entity/EntityDamageByEntityTemplate.vm";
    }

    private String getSpawnerSpawnTemplate() {
        return "entity/SpawnerSpawnTemplate.vm";
    }

    private String getEntityInteractTemplate() {
        return "entity/EntityInteractTemplate.vm";
    }

    /*********************************************************
     Block Event Templates
     *********************************************************/

    private String getBlockBreakEventTemplate() {
        return "entity/PlayerEventJoinTemplate.vm";
    }
    private String getBlockPlaceEventTemplate() {
        return "entity/PlayerEventJoinTemplate.vm";
    }
    private String getBlockInteractEventTemplate() {
        return "entity/PlayerEventJoinTemplate.vm";
    }

    /*********************************************************
     Inventory Event Templates
     *********************************************************/

    private String getInventoryOpenTemplate() {
        return "inventory/InventoryOpenTemplate.vm";
    }

    private String getInventoryClickTemplate() {
        return "inventory/InventoryOpenTemplate.vm";
    }

    private String getInventoryCloseTemplate() {
        return "inventory/InventoryCloseTemplate.vm";
    }

    /*********************************************************
     Projectile Event Templates
     *********************************************************/

    private String getProjectileLaunchTemplate() {
        return "projectile/ProjectileLaunchTemplate.vm";
    }

    private String getProjectileHitTemplate() {
        return "projectile/ProjectileHitTemplate.vm";
    }
}

