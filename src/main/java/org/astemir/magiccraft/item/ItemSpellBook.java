package org.astemir.magiccraft.item;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.apache.commons.lang.StringUtils;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.magic.*;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.ItemUtils;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ItemSpellBook extends Item implements IDyeable{

    private final TextColor WHITE = TextColor.color(255,255,255);
    private final TextColor BLACK = TextColor.color(0,0,0);
    private final TextColor RED = TextColor.color(255,50,50);


    private final String PRESS_TO_SELECT = "ui.magiccraft.press_to_select";
    private final String PRESS_TO_UNLOCK = "ui.magiccraft.press_to_unlock";
    private final String PRESS_TO_RETURN = "ui.magiccraft.press_to_return";
    private final String HOTKEY = "ui.magiccraft.hotkey_button";
    private final String HOTKEY_DISABLED = "ui.magiccraft.hotkey_button_disabled";


    private final String SPELL_BOOK_TITLE = "ui.magiccraft.spell_book_title";
    private final String MAGIC_EXPERIENCE = "ui.magiccraft.magic_experience";

    private final String MANA_REGENERATION = "ui.magiccraft.mana_regeneration";
    private final String MANA_SPEED = "ui.magiccraft.mana_speed";
    private final String MAGIC_LEVEL = "ui.magiccraft.magic_level";
    private final String MAGIC_POINTS = "ui.magiccraft.magic_points";


    private final String MANA_REGENERATION_STATS = "ui.magiccraft.stats.mana_regeneration";
    private final String MANA_SPEED_STATS = "ui.magiccraft.stats.mana_speed";
    private final String MAGIC_LEVEL_STATS = "ui.magiccraft.stats.magic_level";
    private final String MAGIC_POINTS_STATS = "ui.magiccraft.stats.magic_points";

    private final String REQUIRES = "ui.magiccraft.requires";
    private final String MAGIC_POINTS_REQUIRES = "ui.magiccraft.magic_points_requires";
    private final String LEVEL_REQUIRES = "ui.magiccraft.level_requires";
    private final String SPELL_REQUIRES = "ui.magiccraft.spell_requires";


    public ItemSpellBook(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }


    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        if (!e.getPlayer().isSneaking()) {
            if (data.isMage()) {
                Book.Builder builder = Book.builder();
                createStatsPage(e.getPlayer(), builder);
                createSpellListPages(e.getPlayer(), builder);
                createSpellsPages(e.getPlayer(), builder);
                e.getPlayer().openBook(builder.build());
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 0.8f);
            }
        }else{
            if (Items.isItem(e.getPlayer().getInventory().getItemInOffHand(),ItemSpellBook.class)){
                return super.onRightClick(e);
            }
            if (e.getPlayer().hasCooldown(e.getMaterial())){
                return super.onRightClick(e);
            }
            e.getPlayer().setCooldown(e.getMaterial(),20);

            ItemStack[] items = PlayerUtils.getHotbarItems(e.getPlayer());
            int heldSlot = e.getPlayer().getInventory().getHeldItemSlot();
            if (!hasTag(e.getItem(),"SavedSpells")) {
                NBTTagList list = new NBTTagList();
                ItemMeta meta = e.getItem().getItemMeta();
                List<Component> lore = new ArrayList<>();
                for (int i = 0; i < items.length; i++) {
                    ItemStack item = items[i];
                    if (Items.isItem(item, ItemSpellBookPage.class)) {
                        NBTTagCompound tag = new NBTTagCompound();
                        String key = getStringTag(item, "spell");
                        tag.setInt("Slot", i);
                        tag.setString("Spell", key);
                        list.add(tag);
                        lore.add(Component.translatable("spell.magiccraft."+key));
                        e.getPlayer().getInventory().setItem(i, null);
                    }
                }
                meta.lore(lore);
                ItemStack newItemStack = e.getItem();
                newItemStack.setItemMeta(meta);
                newItemStack = setTag(newItemStack, "SavedSpells", list);
                e.getPlayer().getInventory().setItem(heldSlot, newItemStack);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_WORK_CARTOGRAPHER, 1, 1.2f);
            }else{
                NBTTagList list = (NBTTagList)getTag(e.getItem(),"SavedSpells");
                ItemStack newItemStack = removeTag(e.getItem(), "SavedSpells");
                ItemMeta meta = newItemStack.getItemMeta();
                meta.lore(Arrays.asList());
                newItemStack.setItemMeta(meta);
                boolean needToReplace = true;

                for (int i = 0;i<list.size();i++){
                    NBTTagCompound tag = (NBTTagCompound) list.get(i);
                    int slot = tag.getInt("Slot");
                    String spellKey = tag.getString("Spell");
                    ItemStack pageItemStack = ((ItemSpellBookPage)Items.SPELL_BOOK_PAGE).getRightSpellItemStack(spellKey);
                    ItemStack oldItem = e.getPlayer().getInventory().getItem(slot);

                    if (needToReplace){
                        if (heldSlot == slot) {
                            oldItem = newItemStack;
                            needToReplace = false;
                        }
                    }

                    if (ItemUtils.isEmpty(oldItem)){
                        if (data.hasLearnedSpell(Spells.getSpellByKey(spellKey))){
                            e.getPlayer().getInventory().setItem(slot, pageItemStack);
                        }
                    }else{
                        PlayerUtils.dropItem(e.getPlayer(),oldItem.clone());
                        e.getPlayer().getInventory().setItem(slot,pageItemStack);
                    }
                }

                if (needToReplace) {
                    e.getPlayer().getInventory().setItem(heldSlot, newItemStack);
                }
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_FRAME_BREAK, 1, 0.5f);
            }

        }
        return true;
    }



    private void createStatsPage(Player player, Book.Builder builder){
        Component stats = empty();
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        double manaSpeed = ((double)data.getMaxMana())/((double)data.getManaRegenerationMaxCooldown());
        stats = stats.
                append(newLine()).
                append(translate(SPELL_BOOK_TITLE).style(Style.style(TextDecoration.BOLD))).
                append(newLine()).
                append(newLine()).
                append(text(magicExperienceBar((int)data.getExpToLevelUp(),data.getMagicExperience())).color(WHITE).hoverEvent(HoverEvent.showText(translate(MAGIC_EXPERIENCE)))).
                append(newLine()).
                append(newLine()).
                append(textCentered(((int)data.getMagicExperience())+"/"+data.getExpToLevelUp(),25)).
                append(newLine()).
                append(newLine()).
                append(translate(MAGIC_LEVEL_STATS).hoverEvent(HoverEvent.showText(translate(MAGIC_LEVEL)))).
                append(text(data.getMagicLevel()+"")).
                append(newLine()).
                append(translate(MANA_REGENERATION_STATS).hoverEvent(HoverEvent.showText(translate(MANA_REGENERATION)))).
                append(text(data.getManaRegeneration()+"")).
                append(newLine()).
                append(translate(MANA_SPEED_STATS).hoverEvent(HoverEvent.showText(translate(MANA_SPEED)))).
                append(text(new DecimalFormat("#.#").format(manaSpeed)+"")).
                append(newLine()).
                append(translate(MAGIC_POINTS_STATS).hoverEvent(HoverEvent.showText(translate(MAGIC_POINTS)))).
                append(text(data.getMagicPoints()+"")).
                append(newLine());
        builder.addPage(stats);
    }

    private String magicExperienceBar(int expToLevelUp,double exp){
        String result = " ";
        String barFull = UnicodeSymbols.MAGIC_BAR_FULL;
        String barEmpty = UnicodeSymbols.MAGIC_BAR_EMPTY;
        String barSpace = UnicodeSymbols.MAGIC_BAR_NSPACE;
        double elementsCount = 10;
        double fullnessPercentage = exp/expToLevelUp;
        int fullElementsCount = (int)(elementsCount*fullnessPercentage);
        for (int i = 0;i<elementsCount;i++){
            if (i < fullElementsCount){
                result+=barFull;
            }else{
                result+=barEmpty;
            }
            if (i != elementsCount-1){
                result+=barSpace;
            }
        }
        return result;
    }

    private int getSpellsCount(Player player,PlayerData data) {
        return getNotSecretSpells(player,data).size();
    }

    public List<MagicSpell> getNotSecretSpells(Player player,PlayerData data){
        List<MagicSpell> list = new ArrayList<>();
        for (MagicSpell spell : Spells.getRegisteredSpells()){
            if (!(spell instanceof ISecretSpell)){
                list.add(spell);
            }else{
                if (spell instanceof ISecretSpell){
                    if (((ISecretSpell)spell).showing(this,player,data)) {
                        list.add(spell);
                    }
                }
            }
        }
        list.sort(Comparator.comparing(MagicSpell::getTexture).thenComparing(MagicSpell::getLevelRequired));
        return list;
    }

    private int createSpellListPages(Player player,Book.Builder builder){
        Component spellsList = Component.empty();
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        int spellsListPagesCount = 2+getSpellsCount(player,data)/14;
        for (int i = 0;i<getNotSecretSpells(player,data).size();i++) {
            MagicSpell spell = getNotSecretSpells(player,data).get(i);
            boolean add = !(spell instanceof ISecretSpell);
            if (spell instanceof ISecretSpell){
                add = ((ISecretSpell)spell).showing(this,player,data);
            }
            if (add) {
                boolean learnedSpell = data.hasLearnedSpell(spell);
                String spellName = spellKey(spell.getRegisterKey());
                TextColor spellColor = learnedSpell ? BLACK : RED;
                Component elementBoxComponent = Component.text("\uF809\uF828").color(UnicodeSymbols.colorOfSpellSymbol(spell.getTexture()));
                Component spellComponent = translate(spellName).
                        clickEvent(ClickEvent.changePage(spellsListPagesCount + (i + 1))).
                        hoverEvent(HoverEvent.showText(translate(PRESS_TO_SELECT).color(WHITE))).
                        color(spellColor);
                spellsList = spellsList.append(elementBoxComponent).append(spellComponent);
                spellsList = spellsList.append(newLine());
                if ((i + 1) % 14 == 0) {
                    if (i != 0) {
                        builder.addPage(spellsList);
                        spellsList = Component.empty();
                    }
                } else if (i == getSpellsCount(player,data) - 1) {
                    builder.addPage(spellsList);
                }
            }
        }
        return spellsListPagesCount;
    }

    private Component createSpellRequired(MagicSpell spell){
        if (spell instanceof MasterSpell){
            return newLine().
            append(translate(REQUIRES)).
            append(text(" ")).
            append(translate(SPELL_REQUIRES)).
            append(text(": ")).
            append(Component.translatable("spell.magiccraft."+((MasterSpell)spell).getRequiredSpell().getRegisterKey()));
        }
        return Component.empty();
    }

    private void createSpellsPages(Player player,Book.Builder builder){
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        for (MagicSpell spell : getNotSecretSpells(player,data)) {
            boolean add = !(spell instanceof ISecretSpell);
            if (spell instanceof ISecretSpell){
                add = ((ISecretSpell)spell).showing(this,player,data);
            }
            if (add) {
                String spellKey = spell.getRegisterKey();
                String spellTranslation = spell.getChatSignals().stream().findFirst().get();
                String spacing = "           ";
                String buttonsSpacing = "       ";
                boolean learnedSpell = data.hasLearnedSpell(spell);

                Component spellTexture = text(spacing + spell.getTexture()).
                        color(WHITE);

                Component translatedSpellName = translate(spellKey(spellKey)).
                        style(Style.style(TextDecoration.BOLD)).
                        color(BLACK);

                Component spellLore = translate(spellLore(spellKey)).
                        color(BLACK);

                Component okButton = text(learnedSpell ? UnicodeSymbols.BOOK_SELECT_BUTTON : UnicodeSymbols.BOOK_UNLOCK_BUTTON);

                Component hotKeyButton = text(learnedSpell ? UnicodeSymbols.BOOK_HOTKEY_BUTTON : UnicodeSymbols.BOOK_HOTKEY_BUTTON_DISABLED);

                Component resetButton = text(UnicodeSymbols.BOOK_RESET_BUTTON);

                Component okButtonHover = translate(learnedSpell ? PRESS_TO_SELECT : PRESS_TO_UNLOCK);
                Component hotKeyButtonHover = translate(learnedSpell ? HOTKEY : HOTKEY_DISABLED);
                if (!learnedSpell) {
                    okButtonHover = okButtonHover.
                            append(newLine()).
                            append(translate(REQUIRES)).
                            append(text(" ")).
                            append(translate(LEVEL_REQUIRES)).
                            append(text(": ")).
                            append(text(spell.getLevelRequired() + "").
                                    append(newLine()).
                                    append(translate(REQUIRES)).
                                    append(text(" ")).
                                    append(translate(MAGIC_POINTS_REQUIRES)).
                                    append(text(": ")).
                                    append(text(spell.getPointsCost() + "").
                                            append(createSpellRequired(spell)))
                            );
                }
                Component okButtonCollider = text("    ").
                        hoverEvent(HoverEvent.showText(okButtonHover));

                Component hotKeyButtonCollider = text("    ").
                        hoverEvent(HoverEvent.showText(hotKeyButtonHover));

                if (!(spell instanceof IPassiveSpell) || !learnedSpell){
                    okButtonCollider = okButtonCollider.clickEvent(ClickEvent.runCommand("@" + spellTranslation));
                }
                if (!(spell instanceof IPassiveSpell)) {
                    if (learnedSpell) {
                        hotKeyButtonCollider = hotKeyButtonCollider.clickEvent(ClickEvent.runCommand("pageOfSpell " + spell.getChatSignals().stream().findFirst().get()));
                    }
                }else{
                    hotKeyButtonCollider = text("    ").
                            hoverEvent(HoverEvent.showText(text("Это пассивный скил, его нельзя выбрать в качестве активной способности.")));
                }

                Component resetButtonCollider = text("    ").
                        clickEvent(ClickEvent.changePage(1)).
                        hoverEvent(HoverEvent.showText(translate(PRESS_TO_RETURN)));

                builder.addPage(spellTexture.append(newLine()).
                        append(translatedSpellName).
                        append(newLine()).
                        append(text(spell.getManaUsage() + "").
                                color(BLACK).
                                append(Component.text(UnicodeSymbols.MANA_UI).
                                        color(WHITE))).
                        append(text("  ")).
                        append(text(spell.getTicksCooldown() / 20 + "").
                                color(BLACK).
                                append(Component.text(UnicodeSymbols.COOLDOWN_UI).
                                        color(WHITE))).
                        append(text("\n\n\n\n")).
                        append(spellLore).
                        append(newLine()).
                        append(newLine()).

                        append(text(buttonsSpacing)).
                        append(okButton).
                        append(hotKeyButton).
                        append(resetButton).
                        append(newLine()).

                        append(text(buttonsSpacing)).
                        append(okButtonCollider).
                        append(text(" ")).
                        append(hotKeyButtonCollider).
                        append(text(" ")).
                        append(resetButtonCollider).
                        append(newLine()).

                        append(text(buttonsSpacing)).
                        append(okButtonCollider).
                        append(text(" ")).
                        append(hotKeyButtonCollider).
                        append(text(" ")).
                        append(resetButtonCollider));
            }
        }
    }

    private String spellKey(String spell){
        return "spell.magiccraft."+spell;
    }

    private String spellLore(String spell){
        return "lore.magiccraft."+spell+"_lore";
    }

    private Component text(String text){
        return Component.text(text);
    }

    private Component textCentered(String text,int length){
        text = StringUtils.center(text,length);
        return text(text.toString());
    }

    private Component translateCentered(String text,int length){
        text = StringUtils.center(text,length);
        return translate(text.toString());
    }

    private Component translate(String text){
        return Component.translatable(text);
    }

    private Component newLine(){
        return text("\n");
    }

    private Component empty(){
        return Component.empty();
    }

    @Override
    public ItemStack toItemStack() {
        ItemStack res = super.toItemStack();
        ItemMeta meta = res.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        res.setItemMeta(meta);
        return res;
    }

    public Color randomColor(){
        Color[] colors = new Color[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.PURPLE};
        return colors[RandomUtils.randomInt(colors.length)];
    }

    public ItemStack randomColored(){
        ItemStack res = toItemStack();
        LeatherArmorMeta meta = (LeatherArmorMeta)res.getItemMeta();
        meta.setColor(randomColor());
        res.setItemMeta(meta);
        return res;
    }

}
