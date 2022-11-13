package me.nickrest.api.manager;

import lombok.Getter;
import lombok.Setter;
import me.nickrest.api.command.Command;
import me.nickrest.loader.Loader;

import java.util.ArrayList;

/**
 * The command manager is responsible for registering and unregistering commands.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
@Getter @Setter
@SuppressWarnings("unused")
public class CommandManager {

    private final ArrayList<Command> commands = new ArrayList<>();
    private final Loader plugin;

    public CommandManager(Loader plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers a command.
     *
     * @param command The command to register.
     * */
    public void register(Command command) {
        commands.add(command);
    }

    /**
     * Unregisters a command.
     *
     * @param command The command to unregister.
     * */
    public void unregister(Command command) {
        commands.remove(command);
        plugin.getCommandMap().register(command.getFallbackPrefix(), command);
    }

}
