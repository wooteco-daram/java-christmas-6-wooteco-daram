package controller;

import view.input.reader.Reader;
import view.output.writer.Writer;

public abstract class Controller {
    protected final Writer writer;
    protected final Reader reader;

    protected Controller(
            final Writer writer,
            final Reader reader
    ) {
        this.writer = writer;
        this.reader = reader;
    }
}
