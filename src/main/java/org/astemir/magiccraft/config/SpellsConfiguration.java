package org.astemir.magiccraft.config;


import org.astemir.magiccraft.magic.SpellType;
import org.bukkit.potion.PotionEffectType;

public class SpellsConfiguration {

    public static ConfigurableSpell ARMAGEDON = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(10).level(200).points(20).cooldown(300).
            set("damage",40).set("ticks",300).
            phrases("armagedon","армагедон");

    public static ConfigurableSpell DARK_SHOT = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(2).level(5).points(1).cooldown(2).
            effect("blindness").type(PotionEffectType.BLINDNESS).duration(200).amplifier(0).build().
            set("damage",5).
            phrases("darkness","тьма");

    public static ConfigurableSpell FIRE = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(1).level(1).points(1).cooldown(0).
            set("ticks",100).set("damage",2).
            phrases("fire","огонь");

    public static ConfigurableSpell WATER = new ConfigurableSpell().
            type(SpellType.WATER).
            mana(1).level(1).points(1).cooldown(0).
            set("ticks",20).set("damage",3).
            phrases("water","вода");

    public static ConfigurableSpell WIND = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(1).level(1).points(1).cooldown(0).
            phrases("wind","ветер");

    public static ConfigurableSpell BOULDER = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(4).level(100).points(4).cooldown(10).
            set("damage",10).
            phrases("boulder","валун");

    public static ConfigurableSpell FIRE_PROOF = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(5).level(1).points(2).cooldown(20).
            effect("fire_resistance").type(PotionEffectType.FIRE_RESISTANCE).duration(200).amplifier(0).build().
            phrases("fire proof");

    public static ConfigurableSpell ICE_PIKE = new ConfigurableSpell().
            type(SpellType.ICE).
            mana(2).level(20).points(2).cooldown(10).
            set("damage",3).set("beam_length",10).
            phrases("ice pike","ледяной шип");

    public static ConfigurableSpell AIR_JUMP = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(1).level(3).points(1).cooldown(0).
            phrases("air jump","прыжок воздуха");

    public static ConfigurableSpell AIR_SLIDE = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(1).level(15).points(1).cooldown(0).
            effect("slow_falling").type(PotionEffectType.SLOW_FALLING).amplifier(0).duration(40).build().
            phrases("air slide","воздушное скольжение");

    public static ConfigurableSpell AIR_MAGNET = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(2).level(15).points(2).cooldown(5).
            set("radius",6).
            phrases("air magnet","магнит воздуха");

    public static ConfigurableSpell BELZEBUB = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(10).level(150).points(20).cooldown(0).
            set("damage",60).set("beam_length",4).
            phrases("belzebub","вельзевул");

    public static ConfigurableSpell BLOOD_RITUAL = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(1).level(20).points(1).cooldown(5).
            set("mana_regeneration",1).set("damage",2).
            phrases("blood ritual","ритуал крови","кровававый ритуал");

    public static ConfigurableSpell BLACK_HOLE = new ConfigurableSpell().
            type(SpellType.VOID).
            mana(5).level(100).points(5).cooldown(60).
            set("damage",5).set("beam_length",10).
            phrases("blackhole","черная дыра");

    public static ConfigurableSpell CHARGE = new ConfigurableSpell().
            type(SpellType.LIGHTNING).
            mana(2).level(10).points(2).cooldown(10).
            set("beam_length",8).
            phrases("charge","заряд");

    public static ConfigurableSpell CHAOTIC_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(5).level(60).points(3).cooldown(60).
            set("range",128).set("damage",2).
            phrases("chaotic teleport","хаотичный телепорт");

    public static ConfigurableSpell CAPE_OF_DARKNESS = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(2).level(20).points(1).cooldown(30).
            effect("invisibility").type(PotionEffectType.INVISIBILITY).duration(200).amplifier(0).build().
            effect("speed").type(PotionEffectType.SPEED).duration(200).amplifier(2).build().
            phrases("cape of darkness","плащ тьмы");

    public static ConfigurableSpell DARKNESS_GUISE = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(5).level(60).points(2).cooldown(60).
            effect("invisibility").type(PotionEffectType.INVISIBILITY).duration(400).amplifier(0).build().
            effect("speed").type(PotionEffectType.SPEED).duration(400).amplifier(2).build().
            phrases("darkness guise","воплощение тьмы");

    public static ConfigurableSpell COOK = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(8).level(10).points(2).cooldown(10).
            set("beam_length",4).
            phrases("cook","готовка","приготовление");

    public static ConfigurableSpell SMELT = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(8).level(50).points(5).cooldown(10).
            set("beam_length",4).
            phrases("smelting","переплавка","плавление");

    public static ConfigurableSpell DIG = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(1).level(3).points(2).cooldown(5).
            set("beam_length",16).
            phrases("dig","копание");

    public static ConfigurableSpell DIRT = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(1).level(1).points(1).cooldown(0).
            set("damage",4).
            phrases("dirt","земля");

    public static ConfigurableSpell EQUINOX = new ConfigurableSpell().
            type(SpellType.TIME).
            mana(10).level(150).points(10).cooldown(180).
            set("day_time",24000).
            set("night_time",14000).
            phrases("equinox","равноденствие");

    public static ConfigurableSpell EXP_TRANSFORMATION = new ConfigurableSpell().
            type(SpellType.DIVINE).
            mana(5).level(1).points(1).cooldown(3).
            set("exp",200000).
            phrases("exp transformation","преобразование опыта");

    public static ConfigurableSpell INSTAKILL = new ConfigurableSpell().
            type(SpellType.DIVINE).
            mana(2).level(1).points(1).cooldown(5).
            set("damage",99999999).
            set("beam_length",64).
            phrases("instakill","мгновенное убийство");

    public static ConfigurableSpell ABSOLUTE_ZERO = new ConfigurableSpell().
            type(SpellType.DIVINE).
            mana(3).level(1).points(1).cooldown(5).
            set("beam_length",24).
            phrases("absolute zero","абсолютный ноль");

    public static ConfigurableSpell FIRE_ARROW = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(3).level(15).points(2).cooldown(5).
            set("damage",8).
            set("ticks",80).
            phrases("fire arrow","стрела огня");

    public static ConfigurableSpell FIRE_BEAM = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(2).level(3).points(1).cooldown(5).
            set("damage",4).
            set("beam_length",12).
            set("ticks",120).
            phrases("fire beam","луч огня");

    public static ConfigurableSpell FIRE_EXPLOSION = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(4).level(25).points(3).cooldown(10).
            set("damage",10).
            set("ticks",300).
            phrases("fire explosion","взрыв огня");

    public static ConfigurableSpell FIRE_SHIELD = new ConfigurableSpell().
            type(SpellType.FIRE).
            mana(2).level(5).points(1).cooldown(30).
            effect("resistance").type(PotionEffectType.DAMAGE_RESISTANCE).amplifier(0).duration(200).build().
            effect("fire_resistance").type(PotionEffectType.FIRE_RESISTANCE).amplifier(0).duration(200).build().
            set("fire_ticks",199).
            phrases("fire shield","щит огня","огненный щит");

    public static ConfigurableSpell IRON_SKIN = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(3).level(25).points(1).cooldown(30).
            effect("resistance").type(PotionEffectType.DAMAGE_RESISTANCE).amplifier(2).duration(120).build().
            effect("slowness").type(PotionEffectType.SLOW).amplifier(1).duration(120).build().
            phrases("iron skin","железная кожа");

    public static ConfigurableSpell OBSIDIAN_SKIN = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(6).level(40).points(4).cooldown(60).
            effect("resistance").type(PotionEffectType.DAMAGE_RESISTANCE).amplifier(2).duration(400).build().
            effect("slowness").type(PotionEffectType.SLOW).amplifier(1).duration(400).build().
            phrases("obsidian skin","обсидиановая кожа");

    public static ConfigurableSpell SWIFTNESS = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(3).level(25).points(1).cooldown(30).
            effect("speed").type(PotionEffectType.SPEED).amplifier(1).duration(120).build().
            phrases("swiftness","стремительность");

    public static ConfigurableSpell ACCELERATION = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(6).level(40).points(4).cooldown(60).
            effect("speed").type(PotionEffectType.SPEED).amplifier(2).duration(400).build().
            phrases("acceleration","ускорение");

    public static ConfigurableSpell FREEZE_BEAM = new ConfigurableSpell().
            type(SpellType.ICE).
            mana(4).level(40).points(2).cooldown(30).
            effect("slowness").type(PotionEffectType.SLOW).amplifier(0).duration(120).build().
            set("damage",2).
            set("ticks",100).
            set("beam_length",12).
            phrases("freeze beam","замораживающий луч");

    public static ConfigurableSpell GILLS = new ConfigurableSpell().
            type(SpellType.WATER).
            mana(4).level(20).points(2).cooldown(30).
            effect("water_breath").type(PotionEffectType.WATER_BREATHING).amplifier(0).duration(400).build().
            phrases("gills","жабры","water breath","подводное дыхание");

    public static ConfigurableSpell GOD_HEAL = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(6).level(60).points(2).cooldown(30).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(4).duration(170).build().
            phrases("god heal","божественное лечение");

    public static ConfigurableSpell GREAT_HEAL = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(4).level(30).points(2).cooldown(15).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(3).duration(200).build().
            phrases("great heal","великое лечение");

    public static ConfigurableSpell HEAL = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(3).level(1).points(1).cooldown(5).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(1).duration(120).build().
            phrases("heal","лечение");

    public static ConfigurableSpell HEAL_BEAM = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(3).level(4).points(1).cooldown(5).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(1).duration(120).build().
            set("beam_length",12).
            phrases("heal beam","луч лечения");

    public static ConfigurableSpell HEAL_TOUCH = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(3).level(10).points(1).cooldown(3).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(2).duration(120).build().
            set("beam_length",3).
            phrases("heal touch","лечение касания","касание лечения");

    public static ConfigurableSpell MEDIUM_HEAL = new ConfigurableSpell().
            type(SpellType.HEAL).
            mana(3).level(10).points(1).cooldown(10).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(2).duration(140).build().
            phrases("medium heal","среднее лечение");

    public static ConfigurableSpell SMALL_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(2).level(5).points(1).cooldown(5).
            set("range",8).
            phrases("small teleport","малый телепорт");

    public static ConfigurableSpell MEDIUM_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(4).level(15).points(2).cooldown(10).
            set("range",16).
            phrases("medium teleport","средний телепорт");

    public static ConfigurableSpell GREAT_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(6).level(30).points(2).cooldown(10).
            set("range",24).
            phrases("great teleport","высший телепорт");

    public static ConfigurableSpell RESPAWN_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(5).level(30).points(2).cooldown(60).
            phrases("respawn teleport","телепорт респавна");

    public static ConfigurableSpell RANDOM_TELEPORT = new ConfigurableSpell().
            type(SpellType.ENDER).
            mana(5).level(30).points(2).cooldown(60).
            set("range",16).
            phrases("random teleport","случайный телепорт","рандомный телепорт");

    public static ConfigurableSpell GROW = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(4).level(8).points(2).cooldown(15).
            set("beam_length",12).
            set("radius",3).
            phrases("grow","выращивание","рост");

    public static ConfigurableSpell HURRICANE = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(10).level(150).points(8).cooldown(600).
            set("life_time",650).
            set("height",48).
            phrases("hurricane","ураган");

    public static ConfigurableSpell TORNADO = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(9).level(50).points(10).cooldown(300).
            set("life_time",320).
            set("height",24).
            phrases("tornado","торнадо","смерч");

    public static ConfigurableSpell ICE = new ConfigurableSpell().
            type(SpellType.ICE).
            mana(1).level(1).points(1).cooldown(0).
            effect("slowness").type(PotionEffectType.SLOW).amplifier(0).duration(120).build().
            set("damage",2).
            phrases("ice","лёд");

    public static ConfigurableSpell LIGHT_EXPLOSION = new ConfigurableSpell().
            type(SpellType.LIGHT).
            mana(2).level(5).points(1).cooldown(3).
            effect("blindness").type(PotionEffectType.BLINDNESS).amplifier(0).duration(20).build().
            set("radius",3).
            set("damage",1).
            phrases("light explosion","вспышка света");

    public static ConfigurableSpell HOLY_LIGHT = new ConfigurableSpell().
            type(SpellType.LIGHT).
            mana(2).level(5).points(1).cooldown(10).
            set("radius",6).
            set("damage",5).
            phrases("holy light","святой свет");

    public static ConfigurableSpell LIGHT_HEAL = new ConfigurableSpell().
            type(SpellType.LIGHT).
            mana(4).level(10).points(1).cooldown(5).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(1).duration(120).build().
            effect("heal").type(PotionEffectType.HEAL).amplifier(0).duration(1).build().
            phrases("light heal","лечение светом","световое лечение");

    public static ConfigurableSpell PURIFICATION = new ConfigurableSpell().
            type(SpellType.LIGHT).
            mana(6).level(40).points(3).cooldown(20).
            effect("regeneration").type(PotionEffectType.REGENERATION).amplifier(2).duration(120).build().
            effect("heal").type(PotionEffectType.HEAL).amplifier(0).duration(1).build().
            phrases("purification","очищение");

    public static ConfigurableSpell LIGHTNING = new ConfigurableSpell().
            type(SpellType.LIGHTNING).
            mana(4).level(30).points(2).cooldown(15).
            set("distance",16).
            phrases("lightning","молния");

    public static ConfigurableSpell MANA_EXTRACT = new ConfigurableSpell().
            type(SpellType.VOID).
            mana(10).level(45).points(2).cooldown(10).
            phrases("mana extract","извлечение маны");

    public static ConfigurableSpell PLACE = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(1).level(10).points(3).cooldown(20).
            set("beam_length",8).
            phrases("place","строительство");

    public static ConfigurableSpell STONE_BULLET = new ConfigurableSpell().
            type(SpellType.EARTH).
            mana(4).level(30).points(3).cooldown(5).
            set("damage",6).
            phrases("stone bullet","каменная пуля");

    public static ConfigurableSpell VACUUM_MAGNET = new ConfigurableSpell().
            type(SpellType.VOID).
            mana(2).level(45).points(2).cooldown(10).
            set("radius",8).
            phrases("vacuum magnet","магнит вакуума");

    public static ConfigurableSpell VAMPIRISM = new ConfigurableSpell().
            type(SpellType.DARK).
            mana(2).level(15).points(2).cooldown(3).
            set("beam_length",12).
            set("damage",1).
            set("life_time",300).
            effect("heal").type(PotionEffectType.HEAL).duration(1).amplifier(0).build().
            phrases("vampirism","вампиризм","vampire");

    public static ConfigurableSpell WATER_BOLT = new ConfigurableSpell().
            type(SpellType.WATER).
            mana(3).level(10).points(2).cooldown(5).
            set("return_cooldown",80).
            set("flying_ticks",80).
            set("damage",12).
            phrases("water bolt","водяной поток","поток воды");

    public static ConfigurableSpell WIND_BULLET = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(1).level(1).points(1).cooldown(5).
            set("damage",8).
            phrases("wind bullet","пуля ветра");

    public static ConfigurableSpell WIND_HOOK = new ConfigurableSpell().
            type(SpellType.AIR).
            mana(2).level(15).points(1).cooldown(5).
            set("life_time",30).
            set("beam_length",32).
            phrases("wind hook","крюк ветра");

    public static ConfigurableSpell UNCURE = new ConfigurableSpell().
            type(SpellType.LIGHT).
            mana(6).level(30).points(3).cooldown(60).
            set("damage",2).
            set("beam_length",5).
            phrases("uncure","освящение");
}
