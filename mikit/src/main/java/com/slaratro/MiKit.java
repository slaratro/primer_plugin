package com.slaratro;

import org.bukkit.plugin.java.JavaPlugin;

public class MiKit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Mensaje en la consola al iniciar
        getLogger().info("¡Plugin de Kits iniciado correctamente!");

        // Aquí registramos el comando "kit".
        this.getCommand("kit").setExecutor(new ComandoKit());
        
        // Registramos el evento de la GUI
        getServer().getPluginManager().registerEvents(new EventoGUI(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin de Kits desactivado.");
    }
}