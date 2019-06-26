package com.impurity.twitchwebintegrator.domain.osrs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author tmk2003
 */
@Data
public class OsrsSkills {
    @JsonProperty("overall")
    private OsrsSkill overall;
    @JsonProperty("attack")
    private OsrsSkill attack;
    @JsonProperty("defence")
    private OsrsSkill defence;
    @JsonProperty("strength")
    private OsrsSkill strength;
    @JsonProperty("hitpoints")
    private OsrsSkill hitpoints;
    @JsonProperty("ranged")
    private OsrsSkill ranged;
    @JsonProperty("prayer")
    private OsrsSkill prayer;
    @JsonProperty("magic")
    private OsrsSkill magic;
    @JsonProperty("cooking")
    private OsrsSkill cooking;
    @JsonProperty("woodcutting")
    private OsrsSkill woodcutting;
    @JsonProperty("fletching")
    private OsrsSkill fletching;
    @JsonProperty("fishing")
    private OsrsSkill fishing;
    @JsonProperty("firemaking")
    private OsrsSkill firemaking;
    @JsonProperty("crafting")
    private OsrsSkill crafting;
    @JsonProperty("smithing")
    private OsrsSkill smithing;
    @JsonProperty("mining")
    private OsrsSkill mining;
    @JsonProperty("herblore")
    private OsrsSkill herblore;
    @JsonProperty("agility")
    private OsrsSkill agility;
    @JsonProperty("thieving")
    private OsrsSkill thieving;
    @JsonProperty("slayer")
    private OsrsSkill slayer;
    @JsonProperty("farming")
    private OsrsSkill farming;
    @JsonProperty("runecrafting")
    private OsrsSkill runecrafting;
    @JsonProperty("hunter")
    private OsrsSkill hunter;
    @JsonProperty("construction")
    private OsrsSkill construction;
}
