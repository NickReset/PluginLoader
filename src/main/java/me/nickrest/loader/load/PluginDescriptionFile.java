package me.nickrest.loader.load;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.bukkit.configuration.file.YamlConfiguration.loadConfiguration;

@Getter @Setter
public class PluginDescriptionFile {

    private final YamlConfiguration configuration;
    private final InputStream stream;

    public PluginDescriptionFile(InputStream stream) {
        this.configuration = loadConfiguration(new InputStreamReader(stream));
        this.stream = stream;
    }
}
