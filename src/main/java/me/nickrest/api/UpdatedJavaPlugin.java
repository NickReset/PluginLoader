package me.nickrest.api;

import lombok.Getter;
import lombok.Setter;
import me.nickrest.api.manager.CommandManager;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * This class is the base class for all plugins that are loaded by the loader.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
@Getter @Setter
public class UpdatedJavaPlugin {

    private YamlConfiguration configuration;
    private File dataFolder;
    private CommandManager commandManager;

    public void onEnable(){}
    public void onDisable(){}
}
