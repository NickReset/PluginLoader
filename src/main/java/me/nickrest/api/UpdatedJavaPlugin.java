package me.nickrest.api;

import lombok.Getter;
import lombok.Setter;
import me.nickrest.api.manager.CommandManager;
import me.nickrest.loader.Loader;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.io.File;

/**
 * This class is the base class for all plugins that are loaded by the loader.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
@Getter @Setter
@SuppressWarnings("unused")
public class UpdatedJavaPlugin {

    protected YamlConfiguration configuration;
    protected File dataFolder;
    protected CommandManager commandManager;
    protected PluginManager pluginManager;

    public void onEnable(){}
    public void onDisable(){}

    /**
     * This method is called to register a listener.
     *
     * @param listener The listener to register.
     * */
    public void registerListener(Listener listener) {
        pluginManager.registerEvents(listener, Loader.getPlugin(Loader.class));
    }
}
