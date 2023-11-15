package view.input;

import view.input.reader.Reader;

public abstract class InputView {
    protected final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }
}
