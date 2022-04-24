package org.astemir.magiccraft.event;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.item.*;
import org.astemir.magiccraft.magic.IHasUniqueCondition;
import org.astemir.magiccraft.magic.IPassiveSpell;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.ItemUtils;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Horse;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        ItemStack itemStack = e.getItem();
        Item item = Items.getItem(itemStack);
        if (item != null){
            PlayerClickEvent clickEvent = new PlayerClickEvent(e.getPlayer(),e.getAction(),e.getItem(),e.getClickedBlock(),e.getBlockFace(),null,null);
            boolean cancel = false;
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                cancel = item.onRightClick(clickEvent);
            }else
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
                cancel = item.onLeftClick(clickEvent);
            }
            e.setCancelled(cancel);
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e){
        if (e.getEntity() instanceof org.bukkit.entity.Item) {
            ItemStack itemStack = ((org.bukkit.entity.Item) e.getEntity()).getItemStack();
            Item item = Items.getItem(itemStack);
            if (item != null) {
                e.setCancelled(item.onSpawnInWorldAsEntity(e));
            }
        }
    }

    @EventHandler
    public void onMoveItemToInventory(InventoryClickEvent e){
        ItemStack itemStack = e.getCursor();
        Item item = Items.getItem(itemStack);
        if (item != null) {
            e.setCancelled(item.onMoveToAnotherInventory(e));
        }else{
            ItemStack itemStackB = e.getCurrentItem();
            Item itemB = Items.getItem(itemStackB);
            if (itemB != null){
                e.setCancelled(itemB.onMoveToAnotherInventory(e));
            }
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
        ItemStack itemStack = e.getItem();
        Item item = Items.getItem(itemStack);
        if (item != null){
            if (item.onConsume(e)) {
                e.setCancelled(true);
            }else{
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player) {
            ItemStack itemStack = ((Player)e.getDamager()).getItemInHand();
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item.onAttackEntity(e)) {
                    e.setCancelled(true);
                } else {
                    e.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onHurt(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            PlayerHurtEvent hurtEvent = new PlayerHurtEvent((Player)e.getEntity(),e.getCause(),e.getDamage());
            ItemStack itemStack = hurtEvent.getPlayer().getItemInHand();
            if (itemStack != null) {
                Item item = Items.getItem(itemStack);
                if (item != null) {
                    if (item instanceof IQuitEventListener){
                        ((IHurtEventListener)item).onHurt(hurtEvent);
                    }
                }
            }
            Spells.getRegisteredSpells().forEach((spell)->{
                if (spell instanceof IHurtEventListener){
                    ((IHurtEventListener)spell).onHurt(hurtEvent);
                }
            });
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        ItemStack itemStack = e.getItemDrop().getItemStack();
        Item item = Items.getItem(itemStack);
        if (item != null){
            if (item.onDrop(e)) {
                e.setCancelled(true);
            }else{
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onInteractWithEntity(PlayerInteractAtEntityEvent e){
        ItemStack itemStack = e.getPlayer().getItemInHand();
        Item item = Items.getItem(itemStack);
        if (item != null){
            e.setCancelled(item.onEntityClick(e));
        }
    }

    @EventHandler
    public void onInteractDirectlyWithEntity(PlayerInteractEntityEvent e){
        ItemStack itemStack = e.getPlayer().getItemInHand();
        Item item = Items.getItem(itemStack);
        if (item != null){
            PlayerClickEvent clickEvent = new PlayerClickEvent(e.getPlayer(),Action.RIGHT_CLICK_AIR,itemStack,e.getRightClicked().getLocation().getBlock(), BlockFace.DOWN,e.getRightClicked(),null);
            item.onRightClick(clickEvent);
            if (e.getRightClicked() instanceof Horse) {
                if (item.equals(Items.SPELL_BOOK)) {
                    e.setCancelled(true);
                }
            }
            if (item instanceof ItemMagicStick){
                e.setCancelled(true);
            }
            if (e.getRightClicked() instanceof ItemFrame) {
                if (item.equals(Items.SPELL_BOOK_PAGE)) {
                    e.setCancelled(true);
                }
            }
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack != null) {
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item instanceof IJoinEventListener){
                    ((IJoinEventListener)item).onJoin(e);
                }
            }
        }
        MagicCraft.playerDataHandler.loadPlayerData(player);
    }

    @EventHandler
    public void onExit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack != null) {
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item instanceof IQuitEventListener){
                    ((IQuitEventListener)item).onQuit(e);
                }
            }
        }
        MagicCraft.playerDataHandler.savePlayerData(player);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e){
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack != null) {
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item instanceof ISneakListener){
                    ((ISneakListener)item).onSneak(e);
                }
            }
        }
        Spells.getRegisteredSpells().forEach((spell)->{
            if (spell instanceof ISneakListener){
                ((ISneakListener)spell).onSneak(e);
            }
        });
    }

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent e){
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack != null) {
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item instanceof ISprintEventListener){
                    ((ISprintEventListener)item).onSprint(e);
                }
            }
        }
        Spells.getRegisteredSpells().forEach((spell)->{
            if (spell instanceof ISprintEventListener){
                ((ISprintEventListener)spell).onSprint(e);
            }
        });
    }


    @EventHandler
    public void onCraft(PrepareItemCraftEvent e){
        if (e.getRecipe() != null) {
            if (!hasColorable(e.getInventory().getMatrix())) {
                if (e.getRecipe().getResult() != null) {
                    Item res = Items.getItem(e.getRecipe().getResult());
                    if (res == null) {
                        for (ItemStack itemStack : e.getInventory().getMatrix()) {
                            if (Items.getItem(itemStack) != null) {
                                e.getInventory().setResult(null);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean hasColorable(ItemStack[] items){
        for (ItemStack itemStack : items) {
            Item item = Items.getItem(itemStack);
            if (item != null) {
                if (item instanceof IDyeable) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsDye(ItemStack[] items){
        for (ItemStack item : items) {
            if (ItemUtils.isDye(item.getType())){
                return true;
            }
        }
        return false;
    }



    @EventHandler
    public void onSmelt(FurnaceBurnEvent e){
        if (e.getBlock() != null){
            BlockState state = e.getBlock().getState();
            if (state instanceof Furnace){
                FurnaceInventory inventory = ((Furnace)state).getInventory();
                if (inventory.getFuel() != null){
                    if (inventory.getFuel().getType().isFuel()) {
                        Item fuelItem = Items.getItem(inventory.getFuel());
                        if (fuelItem != null) {
                            if (!(fuelItem instanceof FuelItem)) {
                                e.setCancelled(true);
                            } else {
                                e.setBurnTime(((FuelItem) fuelItem).getCookingTime());
                            }
                        }
                    }
                }
                if (inventory.getResult() != null && inventory.getSmelting() != null) {
                    if (Items.getItem(inventory.getSmelting()) != null) {
                        if (Items.getItem(inventory.getResult()) == null) {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void onChat(AsyncChatEvent e){
        if (e.message() instanceof TextComponent) {
            PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
            if (!data.isMage()){
                return;
            }
            String text = ((TextComponent) e.message()).content();
            if (text.startsWith("exp")){
                data.addMagicExperience(Integer.parseInt(text.replace("exp","")));
            }else
            if (text.startsWith("pageOfSpell")){
                MagicSpell magicSpell = Spells.getSpellByTranslation(text.replaceAll("pageOfSpell ",""));
                if (magicSpell != null) {
                    if (!(magicSpell instanceof IPassiveSpell)) {
                        if (data.hasLearnedSpell(magicSpell)) {
                            Item spellPage = Items.SPELL_BOOK_PAGE;
                            ItemStack stack = ((ItemSpellBookPage) spellPage).getRightSpellItemStack(magicSpell.getRegisterKey());
                            e.getPlayer().getInventory().addItem(stack);
                            e.setCancelled(true);
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_GRASS_BREAK, 0.5f, 1.75f);
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1, 1f);
                        }
                    }
                }
            }else
            if (text.startsWith("@")) {
                if (text.length() <= 1){
                    return;
                }
                String spell = text.substring(1);
                MagicSpell magicSpell = Spells.getSpellByTranslation(spell);
                if (magicSpell == null){
                    return;
                }
                if (magicSpell instanceof IPassiveSpell && data.hasLearnedSpell(magicSpell)){
                    return;
                }
                String spellKey = magicSpell.getRegisterKey();
                Component translatedSpellName = Component.translatable("spell.magiccraft." + spellKey);
                e.setCancelled(true);
                if (data.hasLearnedSpell(magicSpell)) {
                    data.setSelectedSpell(magicSpell);
                    if (magicSpell != null) {
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM,1f,0.65f);
                        e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.spell_selected").append(translatedSpellName));
                    } else {
                        e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.spell_not_selected"));
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK,1,0.5f);
                    }
                } else {
                    if (magicSpell instanceof IHasUniqueCondition) {
                        if (!((IHasUniqueCondition) magicSpell).uniqueCondition(e.getPlayer())) {
                            ((IHasUniqueCondition) magicSpell).conditionIsNotSatisfied(e.getPlayer());
                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK,1,0.5f);
                            return;
                        }
                    }
                    if (data.getMagicLevel() < magicSpell.getLevelRequired()) {
                        e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.not_enough_level"));
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK,1,0.5f);
                        return;
                    }
                    if (data.getMagicPoints() >= magicSpell.getPointsCost()) {
                        data.learnSpell(magicSpell);
                        data.setMagicPoints(data.getMagicPoints() - magicSpell.getPointsCost());
                        e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.spell_learned").append(translatedSpellName));
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT,1,1.5f);
                    } else {
                        e.getPlayer().sendMessage(Component.translatable("msg.magiccraft.not_enough_points"));
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK,1,0.5f);
                    }
                }
            }
        }
    }

}
