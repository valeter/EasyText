package javamodularity.easytext.cli;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

import javamodularity.easytext.analysis.api.TextAnalyzer;
import javamodularity.easytext.analysis.api.Fast;


public class Main {
    public static void main(String... args) {
        TextAnalyzer fastAnalyzer = TextAnalyzer.analyzers().filter(a -> isFast(a.getClass())).findAny()
            .orElseThrow(() -> new RuntimeException("Fast text analyzer not found"));
        new Main().readFile(args[0], (i) -> fastAnalyzer.addChar((char)i.intValue()));
        System.out.println(fastAnalyzer.getName() + " score: " + fastAnalyzer.score());
    }

    private static boolean isFast(Class<?> clazz) {
        return clazz.isAnnotationPresent(Fast.class);
    }

    private void readFile(String filename, Consumer<Integer> charCallback) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8")))) {
            int c;
            while ((c = in.read()) != -1) {
                charCallback.accept(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}