package de.o.le.stack.logging;

import java.util.logging.*;

/**
 * This is a simple facade for a custom logger.
 * <br>
 * This logger is <b>not</b> thread safe!
 *
 * @author                              o.le
 * @version                             1.2
 * @since                               1.0.0
 */
public class LogService {

    private static boolean ENABLED = true;

    private static final ConsoleHandler CONSOLE_HANDLER = createConsoleHandler();

    /**
     * Disable logging of <b>all</b> instances of this class.
     */
    public static void disableAll() { ENABLED = false; }

    /**
     * Enables logging of <b>all</b> instances of this class.
     */
    public static void enableAll() { ENABLED = true; }

    private static ConsoleHandler createConsoleHandler() {

        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new LogFormatter());
        ch.setLevel(Level.ALL);
        return ch;
    }

    private final Logger LOGGER;

    /**
     * Create an instance with the help of a class.
     *
     * @param clazz The name from given class will be the name of the new logger.
     */
    public LogService(Class<?> clazz) {
        this(clazz.getName());
    }

    /**
     * Create an instance with a string.
     *
     * @param name The name of the new logger.
     * @param handlers Additional handlers might be injected via this argument. This is highly experimental and can
     *                 lead to unexpected behavior.
     */
    public LogService(String name, Handler... handlers) {
        this.LOGGER = Logger.getLogger(name);
        this.LOGGER.setUseParentHandlers(false);
        this.LOGGER.addHandler(CONSOLE_HANDLER);
        this.LOGGER.setLevel(Level.ALL);

        for (Handler h : handlers) {
            this.LOGGER.addHandler(h);
        }
    }

    /**
     * Use this for info logging.
     *
     * @param msg The logged message.
     */
    public void info(String msg) { if (ENABLED) LOGGER.info(msg); }

    /**
     * Use this for warning logging.
     *
     * @param msg The logged message.
     */
    public void warning(String msg) { if (ENABLED) LOGGER.warning(msg); }

    /**
     * Use this for error logging.
     *
     * @param msg The logged message.
     */
    public void error(String msg) { if (ENABLED) LOGGER.severe(msg); }

    /**
     * Use this for config logging.
     *
     * @param msg The logged message.
     */
    public void config(String msg) { if (ENABLED) LOGGER.config(msg); }

    /**
     * Use this for debug logging. Only for development.
     *
     * @param msg The logged message.
     */
    public void debug(String msg) { if (ENABLED) LOGGER.fine(msg); }
}
