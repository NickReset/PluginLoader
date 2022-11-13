package me.nickrest.loader.load;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * This class is used to load plugins from a file.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
public final class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(File file, ClassLoader parent) throws MalformedURLException {
        super(new URL[] {file.toURI().toURL()}, parent);
    }

}