package me.nickrest.loader.load;

import lombok.Getter;
import me.nickrest.api.UpdatedJavaPlugin;
import me.nickrest.loader.Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Getter
public class PluginLoader {

    @Getter
    private static final List<UpdatedJavaPlugin> plugins = new ArrayList<>();

    private final File file;
    private final UpdatedJavaPlugin plugin;

    public PluginLoader(File file) {
        this.file = file;

        try {
            PluginDescriptionFile description = getDescription();

            if (description.getConfiguration().getString("main") == null) throw new RuntimeException("main is not defined in plugin.yml");
            if (description.getConfiguration().getString("name") == null) throw new RuntimeException("name is not defined in plugin.yml");

            try (PluginClassLoader classLoader = new PluginClassLoader(file, getClass().getClassLoader())) {
                Class<?> loadedClass = classLoader
                        .loadClass(description.getConfiguration().getString("main"))
                        .asSubclass(UpdatedJavaPlugin.class);

                plugin = (UpdatedJavaPlugin) loadedClass.getDeclaredConstructor().newInstance();
                plugin.setConfiguration(description.getConfiguration());

                File dataDir = new File(Loader.getPlugin(Loader.class).getDataFolder(), "plugins/" + description.getConfiguration().getString("name"));
                mkdir(dataDir);

                plugin.setDataFolder(dataDir);
                plugin.setCommandManager(Loader.getPlugin(Loader.class).getCommandManager());
                plugin.onEnable();
            } catch (Exception e) {
                throw new RuntimeException("Failed to load plugin", e);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load plugin", e);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void mkdir(File file) {
        if (!file.exists()) file.mkdir();
    }


    @SuppressWarnings("all")
    public PluginDescriptionFile getDescription() throws IOException {
        InputStream stream = null;

        try(JarFile jarFile = new JarFile(file)) {

            JarEntry entry = jarFile.getJarEntry("plugin.yml");

            if (entry == null) throw new FileNotFoundException("Plugin does not contain plugin.yml");

            stream = jarFile.getInputStream(entry);

            return new PluginDescriptionFile(stream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load script", e);
        } finally {
            assert stream != null;

            stream.close();
        }

    }

    public static void load(File file) {
        PluginLoader loader = new PluginLoader(file);
        plugins.add(loader.getPlugin());
    }


}
