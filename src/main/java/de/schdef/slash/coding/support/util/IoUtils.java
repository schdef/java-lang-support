package de.schdef.slash.coding.support.util;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IoUtils {

    private static final int BUFFER_SIZE = 512;

    private static final Logger LOG = LoggerFactory.getLogger(IoUtils.class);

    public static void flush(Flushable flushable) {
        if (flushable != null) {
            try {
                flushable.flush();
            }
            catch (IOException ioe) {
                throw new RuntimeException("error flushing output", ioe);
            }
        }
    }

    public static void writeTo(Writer target, String text) {
        try {
            target.write(text);
        }
        catch (IOException ioe) {
            throw new RuntimeException("error writing text", ioe);
        }
    }

    public static String readAsString(Reader source) {
        StringWriter target = new StringWriter();
        try {
            copy(source, target);
            return target.toString();
        }
        finally {
            closeSafely(target);
        }
    }

    public static void copy(InputStream source, OutputStream target) {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int length = source.read(buffer);
            while (length != -1) {
                target.write(buffer, 0, length);
                length = source.read(buffer);
            }
            target.flush();
        }
        catch (IOException ioe) {
            throw new RuntimeException("error copying data", ioe);
        }
    }

    public static void copy(Reader source, Writer target) {
        try {
            char[] buffer = new char[BUFFER_SIZE];
            int length = source.read(buffer);
            while (length != -1) {
                target.write(buffer, 0, length);
                length = source.read(buffer);
            }
            target.flush();
        }
        catch (IOException ioe) {
            throw new RuntimeException("error copying data", ioe);
        }
    }

    public static void closeSafely(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            }
            catch (IOException ioe) {
                LOG.warn("error closing stream", ioe);
            }
        }
    }

    private IoUtils() {
    }

}
