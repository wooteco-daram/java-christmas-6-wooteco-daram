package view.input;

import view.input.reader.Reader;

public abstract class InputView {
    protected final Reader reader;

    protected InputView(final Reader reader) {
        this.reader = reader;
    }
}
