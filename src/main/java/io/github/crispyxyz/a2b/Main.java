package io.github.crispyxyz.a2b;

import module java.base;

public class Main {
    static void main(String[] args) {
        String fileName = args.length == 0 ? IO.readln("Input file name: ") : args[0];
        var program = new Program(fileName);

        String memory = args.length >= 2 ? args[1] : null;

        if (memory == null) {
            IO.println("Running REPL mode. Press Enter to exit the program.");
            memory = IO.readln(">>> ");
            while(!memory.isBlank()) {
                program.loadMemory(memory);
                program.executeProgram();
                IO.println(program.getMemory());
                program.reset();
                memory = IO.readln(">>> ");
            }
        } else {
            program.loadMemory(memory);
            program.executeProgram();
            IO.println(program.getMemory());
        }

    }
}