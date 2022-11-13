package me.nickrest.api.command.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is used to store information about a command.
 *
 * @author NickRest
 * @since 11/13/2022
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String fallbackPrefix() default "";
    String permission() default "";
    String[] aliases() default {};
}
