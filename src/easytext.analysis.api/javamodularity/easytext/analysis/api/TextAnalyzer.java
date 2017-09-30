package javamodularity.easytext.analysis.api;

import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public interface TextAnalyzer {
    default void addString(String s) {
        for (int i = 0; i < s.length(); i++) {
            addChar(s.charAt(i));
        }
    }

    void addChar(char c);

    double score();

    default String getName() {
    	return this.getClass().getSimpleName();
    }

    static Stream<TextAnalyzer> analyzers() {
        return StreamSupport.stream(ServiceLoader.load(TextAnalyzer.class).spliterator(), false);
    }
}