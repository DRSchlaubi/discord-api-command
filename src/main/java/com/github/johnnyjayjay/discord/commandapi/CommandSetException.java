package com.github.johnnyjayjay.discord.commandapi;

/**
 * Exception that is thrown in case of any problems concerning the CommandSettings. <p>
 * CommandSetExceptions are RuntimeExceptions.
 * @author Johnny_JayJay
 * @version 3.2
 * @see RuntimeException
 */
public class CommandSetException extends RuntimeException {
    public CommandSetException(String msg) {
        super(msg);
    }

    public CommandSetException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
