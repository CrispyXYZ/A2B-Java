package io.github.crispyxyz.a2b;

import module java.base;

public class Program {
    private final StringBuilder memory = new StringBuilder();
    private Statement[] programContent;
    private int pointer = 0;

    public Program(String fileName) {
        initProgram(fileName);
    }

    private void initProgram(String fileName) {
        var path = Path.of(fileName);
        // TODO May optimize here while loading program
        try {
            programContent = Files.readAllLines(path)
                                  .stream()
                                  .map(line -> new Statement(line.split("=")))
                                  .toArray(Statement[]::new);
        } catch (IOException e) {
            throw new RuntimeException("Error while loading program", e);
        }
    }

    public void loadMemory(String memory) {
        this.memory.setLength(0);
        this.memory.append(memory);
    }

    private boolean evaluateReplacement(String source, String target) {
        int index = memory.indexOf(source);
        if (index == -1) {
            return false;
        }

        memory.replace(index, index + source.length(), target);
        return true;
    }

    // TODO May optimize here while executing statement
    public void executeCurrentStatement() {
        Statement statement = programContent[pointer];
        if (evaluateReplacement(statement.left(), statement.right())) {
            pointer = 0;
        } else {
            pointer++;
        }
    }

    // TODO Detect infinite loop
    public void executeProgram() {
        while (pointer < programContent.length) {
            executeCurrentStatement();
        }
    }

    public void reset() {
        pointer = 0;
        memory.setLength(0);
    }

    public String getMemory() {
        return memory.toString();
    }

}
