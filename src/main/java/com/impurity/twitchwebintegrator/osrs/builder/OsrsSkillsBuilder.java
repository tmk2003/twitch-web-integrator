package com.impurity.twitchwebintegrator.osrs.builder;

import com.impurity.twitchwebintegrator.osrs.domain.OsrsSkill;
import com.impurity.twitchwebintegrator.osrs.domain.OsrsSkills;
import com.impurity.twitchwebintegrator.osrs.utils.enums.OsrsHiScore;
import lombok.NonNull;

import static java.util.Arrays.copyOfRange;

/**
 * @author tmk2003
 */
public class OsrsSkillsBuilder {
    private static final String DELIMITER = ",";
    private static final int SKILLS_TOTAL = 24;
    private static final int SKILL_VALUE_TOTAL = 3;

    private OsrsSkillsBuilder() {}
    /**
     * Create all skills based off hiscores
     *
     * @param hiscores the collection of values still delimited by commas
     * @return The osrs skills with proper mapping
     */
    public static OsrsSkills buildSkills(@NonNull final String[] hiscores) {
        OsrsSkill[] skills = buildSkillsFromStringArray(
                copyOfRange(hiscores, OsrsHiScore.OVERALL.ordinal(), OsrsHiScore.CONSTRUCTION.ordinal() + 1)
        );
        int i = -1;
        OsrsSkills osrsSkills = new OsrsSkills();
        osrsSkills.setOverall(skills[++i]);
        osrsSkills.setAttack(skills[++i]);
        osrsSkills.setDefence(skills[++i]);
        osrsSkills.setStrength(skills[++i]);
        osrsSkills.setHitpoints(skills[++i]);
        osrsSkills.setRanged(skills[++i]);
        osrsSkills.setPrayer(skills[++i]);
        osrsSkills.setMagic(skills[++i]);
        osrsSkills.setCooking(skills[++i]);
        osrsSkills.setWoodcutting(skills[++i]);
        osrsSkills.setFletching(skills[++i]);
        osrsSkills.setFishing(skills[++i]);
        osrsSkills.setFiremaking(skills[++i]);
        osrsSkills.setCrafting(skills[++i]);
        osrsSkills.setSmithing(skills[++i]);
        osrsSkills.setMining(skills[++i]);
        osrsSkills.setHerblore(skills[++i]);
        osrsSkills.setAgility(skills[++i]);
        osrsSkills.setThieving(skills[++i]);
        osrsSkills.setSlayer(skills[++i]);
        osrsSkills.setFarming(skills[++i]);
        osrsSkills.setRunecrafting(skills[++i]);
        osrsSkills.setHunter(skills[++i]);
        osrsSkills.setConstruction(skills[++i]);
        return osrsSkills;
    }

    /**
     * Create all skills based off 24 skills
     *
     * @param skillValues the collection of values still delimited by commas
     * @return The collection of skills
     */
    private static OsrsSkill[] buildSkillsFromStringArray(final String[] skillValues) {
        if (skillValues.length != SKILLS_TOTAL) {
            throw new IllegalArgumentException("Invalid playerHiScores length");
        }
        OsrsSkill[] skills = new OsrsSkill[SKILLS_TOTAL];
        for (int i = 0; i < skills.length; i++) {
            skills[i] = buildSkillFromString(skillValues[i]);
        }
        return skills;
    }

    /**
     * Create a skill based off 3 longs
     *
     * @param skillValue - Skill string with comma delimiter
     * @return The Osrs skill object that was created based off the parameter
     */
    private static OsrsSkill buildSkillFromString(final String skillValue) {
        String[] skillValues = skillValue.split(DELIMITER);
        if (skillValues.length != SKILL_VALUE_TOTAL) {
            throw new IllegalArgumentException("Invalid skillValue length");
        }
        OsrsSkill skill = new OsrsSkill();
        skill.setRank(Long.parseLong(skillValues[0]));
        skill.setLevel(Long.parseLong(skillValues[1]));
        skill.setExperience(Long.parseLong(skillValues[2]));
        return skill;
    }
}
