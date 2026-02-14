// Original Source - https://stackoverflow.com/a
// Posted by Manuel Moser, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-06, License - CC BY-SA 4.0
//
// This class was modified by myself
package de.o.le.stack.logging;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author                              o.le
 * @version                             1.0
 * @since                               1.0.0
 */
class LogFormatter extends Formatter
{
    // ANSI escape code
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Here you can configure the format of the output and
    // its color by using the ANSI escape codes defined above.

    // format is called for every console log message
    @Override
    public String format(LogRecord record) {
        String color = switch (record.getLevel().getName()) {
            case "INFO" -> ANSI_GREEN;
            case "WARNING" -> ANSI_YELLOW;
            case "SEVERE" -> ANSI_RED;
            case "CONFIG" -> ANSI_BLUE;
            case "FINE" -> ANSI_PURPLE;
            case "FINER" -> ANSI_CYAN;
            default -> ANSI_BLACK;
        };
        StringBuilder builder = new StringBuilder();
        builder.append(color);

        builder.append("[");
        builder.append(calcDate(record.getMillis()));
        builder.append("]");

        builder.append(" [");
        builder.append(record.getLoggerName());
        builder.append("]");

        builder.append(" [");
        builder.append(record.getLevel().getName());
        builder.append("]");

        builder.append(ANSI_WHITE);
        builder.append(" - ");
        builder.append(record.getMessage());

        Object[] params = record.getParameters();

        if (params != null) {
            builder.append("\t");
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String calcDate(long millisecond) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(millisecond);
        return date_format.format(resultdate);
    }
}
