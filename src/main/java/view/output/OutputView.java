package view.output;

import view.output.writer.Writer;

public abstract class OutputView {
    protected Writer writer;

    protected OutputView(final Writer writer) {
        this.writer = writer;
    }

    public void printNewLine() {
        writer.println("");
    }
}
