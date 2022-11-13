package me.nickrest.api.command;

import lombok.Getter;
import lombok.Setter;
import me.nickrest.api.command.data.CommandInfo;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * The command class is the base class for all commands.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
@Getter @Setter
public abstract class Command extends org.bukkit.command.Command {

    private final CommandInfo info = getClass().getAnnotation(CommandInfo.class);
    private final String fallbackPrefix;

    protected Command() {
        super("");

        if(info == null) throw new RuntimeException("CommandInfo annotation is required for all commands.");

        setName(info.name());
        setupPrefix();
        setAliases(Arrays.asList(info.aliases()));
        fallbackPrefix = info.fallbackPrefix();
    }

    private void setupPrefix() {
        if(!info.permission().isEmpty()) setPermission(info.permission());
    }

    @Override
    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args);
}
