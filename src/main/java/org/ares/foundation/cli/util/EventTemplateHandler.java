package org.ares.foundation.cli.util;

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
        return "PlayerJoinTemplate.vm";
    }
    private String getPlayerQuitTemplate() {
        return "PlayerQuitTemplate.vm";
    }

    private String getPlayerChatTemplate() {
        return "PlayerEventJoinTemplate.vm";
    }

    private String getPlayerInteractTemplate() {
        return "PlayerEventJoinTemplate.vm";
    }

    private String getPlayerInteractEntityTemplate() {
        return "PlayerInteractEntityTemplate.vm";
    }

    private String getPlayerInteractAtEntityTemplate() {
        return "PlayerInteractAtEntityTemplate.vm";
    }

    private String getPlayerDeathTemplate() {
        return "PlayerDeathTemplate.vm";
    }

    /*********************************************************
                    Entity Event Templates
     *********************************************************/

    private String getEntitySpawnTemplate() {
        return "EntitySpawnTemplate.vm";
    }

    private String getEntityDamageEvent() {
        return "EntityDamageEvent.vm";
    }

    private String getEntityDamageByEntityTemplate() {
        return "EntityDamageByEntityTemplate.vm";
    }

    private String getSpawnerSpawnTemplate() {
        return "SpawnerSpawnTemplate.vm";
    }

    private String getEntityInteractTemplate() {
        return "EntityInteractTemplate.vm";
    }

    /*********************************************************
                       Block Event Templates
     *********************************************************/

    private String getBlockBreakEventTemplate() {
        return "PlayerEventJoinTemplate.vm";
    }
    private String getBlockPlaceEventTemplate() {
        return "PlayerEventJoinTemplate.vm";
    }
    private String getBlockInteractEventTemplate() {
        return "PlayerEventJoinTemplate.vm";
    }

    /*********************************************************
                    Inventory Event Templates
     *********************************************************/

    private String getInventoryOpenTemplate() {
        return "InventoryOpenTemplate.vm";
    }

    private String getInventoryClickTemplate() {
        return "InventoryOpenTemplate.vm";
    }

    private String getInventoryCloseTemplate() {
        return "InventoryCloseTemplate.vm";
    }

    /*********************************************************
                    Projectile Event Templates
     *********************************************************/

    private String getProjectileLaunchTemplate() {
        return "ProjectileLaunchTemplate.vm";
    }

    private String getProjectileHitTemplate() {
        return "ProjectileHitTemplate.vm";
    }
}
