package org.astemir.magiccraft.magic;

import org.astemir.magiccraft.magic.air.*;
import org.astemir.magiccraft.magic.dark.*;
import org.astemir.magiccraft.magic.earth.*;
import org.astemir.magiccraft.magic.ender.*;
import org.astemir.magiccraft.magic.fire.*;
import org.astemir.magiccraft.magic.god.SpellAbsoluteZero;
import org.astemir.magiccraft.magic.god.SpellExpTransformation;
import org.astemir.magiccraft.magic.god.SpellInstaKill;
import org.astemir.magiccraft.magic.healing.*;
import org.astemir.magiccraft.magic.ice.SpellFreezeBeam;
import org.astemir.magiccraft.magic.ice.SpellIce;
import org.astemir.magiccraft.magic.ice.SpellIcePike;
import org.astemir.magiccraft.magic.light.*;
import org.astemir.magiccraft.magic.lightning.SpellCharge;
import org.astemir.magiccraft.magic.lightning.SpellLightning;
import org.astemir.magiccraft.magic.space.SpellBlackHole;
import org.astemir.magiccraft.magic.space.SpellManaExtract;
import org.astemir.magiccraft.magic.space.SpellVacuumMagnet;
import org.astemir.magiccraft.magic.time.SpellEquinox;
import org.astemir.magiccraft.magic.water.SpellGills;
import org.astemir.magiccraft.magic.water.SpellWater;
import org.astemir.magiccraft.magic.water.SpellWaterBolt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spells {

    public final static List<MagicSpell> registeredSpells = new ArrayList();

    public final static SpellFire FIRE_SPELL = register("fire_spell", SpellFire.class);
    public final static SpellWater WATER_SPELL = register("water_spell", SpellWater.class);
    public final static SpellDirt DIRT_SPELL = register("dirt_spell", SpellDirt.class);
    public final static SpellDarkShot DARK_SHOT_SPELL = register("dark_spell", SpellDarkShot.class);
    public final static SpellHeal HEAL_SPELL = register("heal_spell", SpellHeal.class);
    public final static SpellMediumHeal MEDIUM_HEAL_SPELL = register("medium_heal_spell", SpellMediumHeal.class);
    public final static SpellGreatHeal GREAT_HEAL_SPELL = register("great_heal_spell", SpellGreatHeal.class);
    public final static SpellGodHeal GOD_HEAL_SPELL = register("god_heal_spell", SpellGodHeal.class);
    public final static SpellHealTouch HEAL_TOUCH_SPELL = register("heal_touch_spell", SpellHealTouch.class);
    public final static SpellLightHeal LIGHT_HEAL_SPELL = register("light_heal_spell", SpellLightHeal.class);
    public final static SpellDig DIG_SPELL = register("dig_spell", SpellDig.class);
    public final static SpellPlace PLACE_SPELL = register("place_spell", SpellPlace.class);
    public final static SpellGrow GROW_SPELL = register("grow_spell", SpellGrow.class);
    public final static SpellLightning LIGHTNING_SPELL = register("lightning_spell", SpellLightning.class);
    public final static SpellWind WIND_SPELL = register("wind_spell", SpellWind.class);
    public final static SpellFireBeam FIRE_BEAM_SPELL = register("fire_beam_spell", SpellFireBeam.class);
    public final static SpellHealBeam HEAL_BEAM_SPELL = register("heal_beam_spell", SpellHealBeam.class);
    public final static SpellAirJump AIR_JUMP_SPELL = register("air_jump_spell", SpellAirJump.class);
    public final static SpellSmallTeleport SMALL_TELEPORT_SPELL = register("small_teleport_spell", SpellSmallTeleport.class);
    public final static SpellMediumTeleport MEDIUM_TELEPORT_SPELL = register("medium_teleport_spell", SpellMediumTeleport.class);
    public final static SpellGreatTeleport GREAT_TELEPORT_SPELL = register("great_teleport_spell", SpellGreatTeleport.class);
    public final static SpellVampire VAMPIRE_SPELL = register("vampire_spell", SpellVampire.class);
    public final static SpellBloodRitual BLOOD_RITUAL_SPELL = register("blood_ritual_spell", SpellBloodRitual.class);
    public final static SpellIce ICE_SPELL = register("ice_spell", SpellIce.class);
    public final static SpellFireShield FIRE_SHIELD_SPELL = register("fire_shield_spell", SpellFireShield.class);
    public final static SpellWaterBolt WATER_BOLT_SPELL = register("water_bolt_spell", SpellWaterBolt.class);
    public final static SpellTornado TORNADO_SPELL = register("tornado_spell", SpellTornado.class);
    public final static SpellHurricane HURRICANE_SPELL = register("hurricane_spell", SpellHurricane.class);
    public final static SpellWindHook WIND_HOOK_SPELL = register("wind_hook_spell", SpellWindHook.class);
    public final static SpellManaExtract MANA_EXTRACT_SPELL = register("mana_extract_spell", SpellManaExtract.class);
    public final static SpellLightExplosion LIGHT_EXPLOSION_SPELL = register("light_explosion_spell", SpellLightExplosion.class);
    public final static SpellEquinox EQUINOX_SPELL = register("equinox_spell", SpellEquinox.class);
    public final static SpellCook COOK_SPELL = register("cook_spell", SpellCook.class);
    public final static SpellSmelt SMELTING_SPELL = register("smelting_spell", SpellSmelt.class);
    public final static SpellAirMagnet AIR_MAGNET_SPELL = register("air_magnet_spell", SpellAirMagnet.class);
    public final static SpellVacuumMagnet VACUUM_MAGNET_SPELL = register("vacuum_magnet_spell", SpellVacuumMagnet.class);
    public final static SpellFireArrow FIRE_ARROW_SPELL = register("fire_arrow_spell", SpellFireArrow.class);
    public final static SpellCapeOfDarkness CAPE_OF_DARKNESS_SPELL = register("cape_of_darkness_spell", SpellCapeOfDarkness.class);
    public final static SpellFireExplosion FIRE_EXPLOSION_SPELL = register("fire_explosion_spell", SpellFireExplosion.class);
    public final static SpellWindBullet WIND_BULLET_SPELL = register("wind_bullet_spell", SpellWindBullet.class);
    public final static SpellStoneBullet STONE_BULLET_SPELL = register("stone_bullet_spell", SpellStoneBullet.class);
    public final static SpellGills GILLS_SPELL = register("gills_spell", SpellGills.class);
    public final static SpellSwiftness SWIFTNESS_SPELL = register("swiftness_spell", SpellSwiftness.class);
    public final static SpellCharge CHARGE_SPELL = register("charge_spell", SpellCharge.class);
    public final static SpellIcePike ICE_PIKE_SPELL = register("ice_pike_spell", SpellIcePike.class);
    public final static SpellRandomTeleport RANDOM_TELEPORT_SPELL = register("random_teleport_spell", SpellRandomTeleport.class);
    public final static SpellChaoticTeleport CHAOTIC_TELEPORT_SPELL = register("chaotic_teleport_spell", SpellChaoticTeleport.class);
    public final static SpellRespawnTeleport RESPAWN_TELEPORT_SPELL = register("respawn_teleport_spell", SpellRespawnTeleport.class);
    public final static SpellFreezeBeam FREEZE_BEAM_SPELL = register("freeze_beam_spell", SpellFreezeBeam.class);
    public final static SpellArmagedon ARMAGEDON_SPELL = register("armagedon_spell", SpellArmagedon.class);
    public final static SpellInstaKill INSTAKILL_SPELL = register("instakill_spell", SpellInstaKill.class);
    public final static SpellAbsoluteZero ABSOLUTE_ZERO_SPELL = register("absolute_zero_spell", SpellAbsoluteZero.class);
    public final static SpellExpTransformation EXP_TRANSFORMATION_SPELL = register("exp_transformation_spell", SpellExpTransformation.class);
    public final static SpellUncure UNCURE_SPELL = register("uncure_spell", SpellUncure.class);
    public final static SpellHolyLight HOLY_LIGHT_SPELL = register("holy_light_spell", SpellHolyLight.class);
    public final static SpellPurification PURIFICATION_SPELL = register("purification_spell", SpellPurification.class);
    public final static SpellAirSlide AIR_SLIDE_SPELL = register("air_slide_spell", SpellAirSlide.class);
    public final static SpellIronSkin IRON_SKIN_SPELL = register("iron_skin_spell", SpellIronSkin.class);
    public final static SpellBlackHole BLACK_HOLE_SPELL = register("black_hole_spell", SpellBlackHole.class);
    public final static SpellObsidianSkin OBSIDIAN_SKIN_SPELL = register("obsidian_skin_spell", SpellObsidianSkin.class);
    public final static SpellAcceleration ACCELERATION_SPELL = register("acceleration_spell", SpellAcceleration.class);
    public final static SpellDarknessGuise DARKNESS_GUISE_SPELL = register("darkness_guise_spell", SpellDarknessGuise.class);
    public final static SpellBelzebub BELZEBUB_SPELL = register("belzebub_spell", SpellBelzebub.class);
    public final static SpellBoulder BOULDER_SPELL = register("boulder_spell", SpellBoulder.class);
    public final static SpellFireProof FIRE_PROOF_SPELL = register("fire_proof_spell", SpellFireProof.class);


    public static List<MagicSpell> getRegisteredSpells() {
        return registeredSpells;
    }

    public static MagicSpell getSpellByTranslation(String translation){
        for (MagicSpell registeredSpell : registeredSpells) {
            for (String translationKey : registeredSpell.getChatSignals()){
                if (translationKey.equals(translation)){
                    return registeredSpell;
                }
            }
        }
        return null;
    }

    public static MagicSpell getSpellByKey(String key){
        for (MagicSpell registeredSpell : registeredSpells) {
            if (registeredSpell.getRegisterKey().equals(key)){
                return registeredSpell;
            }
        }
        return null;
    }


    public static MagicSpell getSpellByIndex(int index){
        int i = 0;
        for (MagicSpell registeredSpell : registeredSpells) {
            if (i == index){
                return registeredSpell;
            }
            i++;
        }
        return null;
    }

    public static int getIndexOfSpell(MagicSpell spell){
        int i = 0;
        for (MagicSpell registeredSpell : registeredSpells) {
            if (registeredSpell.equals(spell)){
                return i;
            }
            i++;
        }
        return i;
    }



    public static <T extends MagicSpell> T register(String key,Class<T> spellClass){
        try {
            T spellInstance = spellClass.newInstance();
            spellInstance.setRegisterKey(key);
            registeredSpells.add(spellInstance);
            return spellInstance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
