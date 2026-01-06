package com.slaratro;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public class EventoGUI implements Listener {

    @EventHandler
    public void alClickearInventario(InventoryClickEvent e) {
        // 1. Verificamos que sea nuestro inventario (por el título)
        if (!e.getView().getTitle().equals("Selecciona tu Kit")) {
            return;
        }

        // 2. Cancelamos el evento para que NO puedan robarse los items del menú
        e.setCancelled(true);

        // 3. Verificamos quién clickeó y qué clickeó
        if (e.getWhoClicked() instanceof Player jugador && e.getCurrentItem() != null) {
            
            Material itemClickeado = e.getCurrentItem().getType();

            // Limpiamos el inventario actual del jugador antes de darle el kit (Opcional, evita mezclas)
            // jugador.getInventory().clear(); 

            if (itemClickeado == Material.DIAMOND_SWORD) {
                darKitGuerrero(jugador);
                jugador.closeInventory(); // Cerramos el menú
                jugador.sendMessage("§b¡Has equipado el Kit Guerrero!");
            } 
            else if (itemClickeado == Material.BOW) {
                darKitArquero(jugador);
                jugador.closeInventory();
                jugador.sendMessage("§a¡Has equipado el Kit Arquero!");
            } 
            else if (itemClickeado == Material.NETHERITE_AXE) {
                darKitTanque(jugador);
                jugador.closeInventory();
                jugador.sendMessage("§c¡Has equipado el Kit Tanque!");
            }
        }
    }

    // --- Lógica para crear los items de cada kit ---

    private void darKitGuerrero(Player p) {
        // Armadura Diamante (Prot 3, Irromp 3)
        p.getInventory().setHelmet(crearItem(Material.DIAMOND_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setChestplate(crearItem(Material.DIAMOND_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setLeggings(crearItem(Material.DIAMOND_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setBoots(crearItem(Material.DIAMOND_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));

        // Armas
        p.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        p.getInventory().addItem(new ItemStack(Material.SHIELD));

        // Comida
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 16));
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    }

    private void darKitArquero(Player p) {
        // Armadura Hierro (Prot 4, Irromp 3)
        p.getInventory().setHelmet(crearItem(Material.IRON_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.DURABILITY, 3));
        p.getInventory().setChestplate(crearItem(Material.IRON_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.DURABILITY, 3));
        p.getInventory().setLeggings(crearItem(Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.DURABILITY, 3));
        p.getInventory().setBoots(crearItem(Material.IRON_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 4, Enchantment.DURABILITY, 3));

        // Arco (Poder 2, Infinidad, Irromp 3)
        ItemStack arco = new ItemStack(Material.BOW);
        ItemMeta meta = arco.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        arco.setItemMeta(meta);
        p.getInventory().addItem(arco);

        // Flecha y comida
        p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 16));
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    }

    private void darKitTanque(Player p) {
        // Armadura Netherite (Prot 3, Irromp 3)
        p.getInventory().setHelmet(crearItem(Material.NETHERITE_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setChestplate(crearItem(Material.NETHERITE_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setLeggings(crearItem(Material.NETHERITE_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));
        p.getInventory().setBoots(crearItem(Material.NETHERITE_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 3, Enchantment.DURABILITY, 3));

        // Hacha (Smite 3, Irromp 3)
        ItemStack hacha = new ItemStack(Material.NETHERITE_AXE);
        ItemMeta metaHacha = hacha.getItemMeta();
        metaHacha.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true); // Smite es DAMAGE_UNDEAD
        metaHacha.addEnchant(Enchantment.DURABILITY, 3, true);
        hacha.setItemMeta(metaHacha);
        p.getInventory().addItem(hacha);

        p.getInventory().addItem(new ItemStack(Material.SHIELD));

        // Poción de Fuerza (Requiere tratamiento especial)
        ItemStack pocion = new ItemStack(Material.POTION);
        PotionMeta metaPocion = (PotionMeta) pocion.getItemMeta();
        // Nota: En versiones muy nuevas de Java/Spigot esto cambia un poco, 
        // pero esto es lo estándar para 1.20+
        metaPocion.setBasePotionType(PotionType.STRENGTH); 
        pocion.setItemMeta(metaPocion);
        p.getInventory().addItem(pocion);

        // Comida
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT, 16));
        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    }

    // Helper para crear armaduras con encantamientos rápido
    private ItemStack crearItem(Material mat, Enchantment enc1, int lvl1, Enchantment enc2, int lvl2) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enc1, lvl1, true);
            meta.addEnchant(enc2, lvl2, true);
            item.setItemMeta(meta);
        }
        return item;
    }
}