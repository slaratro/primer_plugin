package com.slaratro;

import org.bukkit.plugin.java.JavaPlugin;

public class MiKit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Mensaje en la consola al iniciar
        getLogger().info("¡Plugin de Kits iniciado correctamente!");

        // Aquí registramos el comando "kit".
        // Le decimos al servidor: "Cuando alguien escriba 'kit', usa la clase ComandoKit".
        this.getCommand("kit").setExecutor(new ComandoKit());
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin de Kits desactivado.");
    }
}