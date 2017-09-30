package javamodularity.easytext.analysis.coleman;

import java.util.Set;

import javamodularity.easytext.analysis.api.TextAnalyzer;
import javamodularity.easytext.analysis.api.Fast;


@Fast
class ColemanLiau implements TextAnalyzer {
    private long totalLetters;
    private long totalSentences;
    private long totalWords;

    private boolean word;
    private boolean sentenceEnd;

    @Override
    public void addChar(char c) {
        if (c >= 'a' && c <= 'z') {
            if (!word) {
                totalWords++;
            }
            word = true;
            totalLetters++;
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
    }

    @Override
    public double score() {
        double averageLettersPer100Words = totalLetters * 100.0d / totalWords;
        double averageSentencesPer100Words = totalSentences * 100d / totalWords;
        return 0.0588d * averageLettersPer100Words - 0.296d * averageSentencesPer100Words - 15.8d;
    }
}