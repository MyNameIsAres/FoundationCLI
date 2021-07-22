package org.ares.foundation.cli.util.string;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringUtil {

    private static String checkCapital(String name) {
        boolean isUpperCase = Character.isUpperCase(name.codePointAt(0));

        return isUpperCase ? name : StringUtils.capitalize(name);

    }

    public static String getCommandName(String name) {
        if (!name.contains("Command")) {
            return name;
        }

        return Arrays.toString(name.split("Command"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addCommandLabel(String className) {
        return !className.contains("Command") ? StringUtils.capitalize(className) + "Command" : StringUtils.capitalize(className);
    }

    public static String getCommandGroupName(String name) {
        if (!name.contains("CommandGroup")) {
            return name;
        }

        return Arrays.toString(name.split("CommandGroup"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addCommandGroupLabel(String className) {
        return !className.contains("CommandGroup") ?  StringUtils.capitalize(className) + "CommandGroup" :  StringUtils.capitalize(className);
    }


    public static String getBossName(String name) {
        if (!name.contains("Boss")) {
            return name;
        }

        return Arrays.toString(name.split("Boss"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addBossLabel(String className) {
        checkCapital(className);
        return !className.contains("Boss") ? "Boss" +  className : className;
    }

    public static String getNPCName(String name) {
        if (!name.contains("NPC")) {
            return name;
        }

        return Arrays.toString(name.split("NPC"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addNpcLabel(String className) {
        return !className.contains("NPC") ? "NPC" + className  : className;
    }

    public static String getBossSkill(String name) {
        if (!name.contains("Skill")) {
            return name;
        }

        return Arrays.toString(name.split("Skill"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addBossSkillLabel(String className) {
        return !className.contains("Skill") ? "BossSkill" + className : className;
    }

    public static String getRankName(String name) {
        if (!name.contains("Rank")) {
            return name;
        }

        return Arrays.toString(name.split("Rank"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addRankLabel(String className) {
        return !className.contains("Rank") ? "Rank" + className : className;
    }

    public static String getQuestName(String name) {
        if (!name.contains("Quest")) {
            return name;
        }

        return Arrays.toString(name.split("Quest"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addQuestLabel(String className) {
        return !className.contains("Quest") ? "Quest" + className : className;
    }

    public static String getClassName(String name) {
        if (!name.contains("Class")) {
            return name;
        }

        return Arrays.toString(name.split("Class"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addClassLabel(String className) {
        return !className.contains("Class") ? "Class" + className : className;
    }

    public static String getBlockToolName(String name) {
        if (!name.contains("BlockTool")) {
            return name;
        }

        return Arrays.toString(name.split("BlockTool"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addBlockToolLabel(String className) {
        return !className.contains("BlockTool") ? className + "BlockTool": className;
    }

    public static String getRocketName(String name) {
        if (!name.contains("Rocket")) {
            return name;
        }

        return Arrays.toString(name.split("Rocket"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addRocketLabel(String className) {
        return !className.contains("Rocket") ? className + "Rocket" : className;
    }

    public static String getToolName(String name) {
        if (!name.contains("Tool")) {
            return name;
        }

        return Arrays.toString(name.split("Tool"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addToolLabel(String className) {
        return !className.contains("Tool") ? "Tool" + className : className;
    }

    public static String getPromptName(String name) {
        if (!name.contains("Prompt")) {
            return name;
        }

        return Arrays.toString(name.split("Prompt"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addPromptLabel(String className) {
        return !className.contains("Prompt") ? className + "Prompt" : className;
    }

    public static String getConversationName(String name) {
        if (!name.contains("Conversation")) {
            return name;
        }

        return Arrays.toString(name.split("Conversation"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addConversationLabel(String className) {
        return !className.contains("Conversation") ? className + "Conversation" : className;
    }


    public static String getSettingsName(String name) {
        if (!name.contains("Settings")) {
            return name;
        }

        return Arrays.toString(name.split("Settings"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addSettingsLabel(String className) {
        return !className.contains("Settings") ? className + "Settings" : className;
    }

    public static String getTaskName(String name) {
        if (!name.contains("Task")) {
            return name;
        }

        return Arrays.toString(name.split("Task"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addTaskLabel(String className) {
        return !className.contains("Task") ? className + "Task" : className;
    }


    public static String getEnchantmentName(String name) {
        if (!name.contains("Enchant")) {
            return name;
        }

        return Arrays.toString(name.split("Enchant"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addEnchantmentLabel(String className) {
        return !className.contains("Enchantment") ? className + "Enchantment" : className;
    }

    public static String addMenuLabel(String className) {
        return className.contains("Menu") ? className + "Menu" : className;
    }

    // By convention we do want the "Listener" postfix
    public static String addListenerLabel(String className) {
        return !className.contains("Listener") ? className + "Listener" : className;
    }

    public static String addCacheLabel(String className) {
        return !className.contains("Cache") ? className + "Cache" : className;
    }

    public static String getCacheName(String name) {
        if (!name.contains("Cache")) {
            return name;
        }

        return Arrays.toString(name.split("Cache"))
                .replace("[", "")
                .replace("]", "");

    }

    public static String addDatabaseLabel(String className) {
        return !className.contains("Database") ? className + "Database" : className;
    }
}
