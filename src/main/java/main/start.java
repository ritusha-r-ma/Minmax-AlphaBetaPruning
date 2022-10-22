package main;

import validation.ArgValidation;
import validation.FileValidation;

public class start {
    public static void main (String[] args) {
        ArgValidation argValidation = new ArgValidation(args);
        FileValidation fileValidation = new FileValidation(ArgValidation.getFilePath());
    }
}
