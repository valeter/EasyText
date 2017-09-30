package javamodularity.easytext.analysis.kincaid;

import java.util.Set;
import javamodularity.easytext.analysis.api.TextAnalyzer;


public class FleshKincaid implements TextAnalyzer {
    private long totalWords;
    private long totalSentences;
    private long totalSyllables;

    private boolean word;
    private boolean sentenceEnd;

    @Override
    public void addChar(char c) {
        if (c >= 'a' && c <= 'z') {
            if (!word) {
                totalWords++;
            }
            word = true;
        } else {
            word = false;
        }

        if (Set.of('!', '.', '?').contains(c)) {
            if (!sentenceEnd) {
                totalSentences++;
            }
            sentenceEnd = true;
        } else {
            sentenceEnd = false;
        }

        if (Set.of('a', 'e', 'i', 'o', 'u').contains(c)) {
            totalSyllables++;
        }
    }

    @Override
    public double score() {
        return 206.835d - 1.015d * ((double)totalWords / totalSentences) - 84.6d * ((double)totalSyllables / totalWords);
    }
}