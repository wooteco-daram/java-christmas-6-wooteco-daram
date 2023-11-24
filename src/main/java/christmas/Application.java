package christmas;

import camp.nextstep.edu.missionutils.Console;
import controller.MainController;
import view.input.reader.ConsoleReader;
import view.output.writer.ConsoleWriter;

public class Application {
    public static void main(String[] args) {
        final ConsoleWriter consoleWriter = new ConsoleWriter();
        final ConsoleReader consoleReader = new ConsoleReader();

        MainController mainController = new MainController(
                consoleWriter,
                consoleReader
        );
        mainController.run();
        Console.close();
    }
}
