package com.slaratro;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ComandoKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player jugador) {
            abrirMenu(jugador);
            return true;
        }
        return false;
    }

    private void abrirMenu(Player jugador) {
        // 1. Creamos un inventario de 9 casillas (1 fila)
        Inventory inv = Bukkit.createInventory(null, 9, "Selecciona tu Kit");

        // 2. Creamos los iconos de los Kits
        
        // --- Icono Guerrero (Espada) ---
        ItemStack iconoGuerrero = crearIcono(Material.DIAMOND_SWORD, "§bKit Guerrero", "§7Click para equipar");
        
        // --- Icono Arquero (Arco) ---
        ItemStack iconoArquero = crearIcono(Material.BOW, "§aKit Arquero", "§7Click para equipar");

        // --- Icono Tanque (Hacha Netherite) ---
        ItemStack iconoTanque = crearIcono(Material.NETHERITE_AXE, "§cKit Tanque", "§7Click para equipar");

        // --- Relleno (Cristal Morado) ---
        ItemStack relleno = crearIcono(Material.PURPLE_STAINED_GLASS_PANE, " ", "");

        // 3. Colocamos todo en el inventario
        // Rellenamos todo primero con cristal
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, relleno);
        }

        // Ponemos los kits en el centro de cada seccion
        inv.setItem(2, iconoGuerrero);
        inv.setItem(4, iconoArquero);
        inv.setItem(6, iconoTanque);

        // 4. Abrimos el inventario al jugador
        jugador.openInventory(inv);
    }

    // Método de ayuda para crear items rápido con nombre y lore
    private ItemStack crearIcono(Material material, String nombre, String descripcion) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(nombre);
            if (!descripcion.isEmpty()) {
                meta.setLore(Arrays.asList(descripcion));
            }
            item.setItemMeta(meta);
        }
        return item;
    }
}