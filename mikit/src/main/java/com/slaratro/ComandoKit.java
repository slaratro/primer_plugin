package com.slaratro;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ComandoKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // 1. Verificamos si el que ejecuta el comando es un Jugador.
        // (La consola del servidor no tiene inventario, así que no puede recibir espadas).
        if (sender instanceof Player jugador) {
            

            // 2. Creamos el objeto: Una espada de diamante
            // ItemStack define un item y su cantidad (por defecto 1).
            ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);

            // 3. Añadimos el objeto al inventario del jugador
            jugador.getInventory().addItem(espada);

            // 4. Enviamos un mensaje de confirmación (usando colores)
            jugador.sendMessage(ChatColor.GREEN + "¡Has recibido tu kit de batalla!");
            jugador.sendMessage(ChatColor.AQUA + "Disfruta tu nueva espada.");

            return true; // El comando se ejecutó bien
        } else {
            // Si intenta ejecutarlo la consola:
            sender.sendMessage("Este comando solo lo pueden usar jugadores dentro del juego.");
            return true;
        }
    }
}