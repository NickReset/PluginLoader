package me.nickrest.loader;

import me.nickrest.api.UpdatedJavaPlugin;
import me.nickrest.loader.load.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class Loader extends JavaPlugin {

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        PluginLoader.getPlugins().forEach(UpdatedJavaPlugin::onDisable);
    }
}
