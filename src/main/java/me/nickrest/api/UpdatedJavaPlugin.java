package me.nickrest.api;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@Getter @Setter
public class UpdatedJavaPlugin {

    private YamlConfiguration configuration;
    private File dataFolder;

    public void onEnable(){}
    public void onDisable(){}
}
