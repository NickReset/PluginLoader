package me.nickrest.loader;

import lombok.Getter;
import me.nickrest.api.UpdatedJavaPlugin;
import me.nickrest.api.manager.CommandManager;
import me.nickrest.loader.load.PluginLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

@Getter
public final class Loader extends JavaPlugin {

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        commandManager = new CommandManager(this);
    }

    @Override
    public void onDisable() {
        PluginLoader.getPlugins().forEach(UpdatedJavaPlugin::onDisable);
    }

    public SimpleCommandMap getCommandMap() {
        try {
            Field field = Bukkit.getServer()
                    .getClass()
                    .getDeclaredField("commandMap");

            field.setAccessible(true);
            return (SimpleCommandMap) field.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Could not get command map", e);
        }
    }

}
