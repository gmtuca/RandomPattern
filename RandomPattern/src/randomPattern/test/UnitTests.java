package randomPattern.test;

import org.junit.Test;
import org.junit.Assert;
import randomPattern.utils.CharRange;
import randomPattern.utils.CharUtils;
import randomPattern.PatternGenerator;
import randomPattern.utils.RussianRoulette;

import java.util.regex.Pattern;

/**
 * Created by gmtuk on 18/08/2014.
 */
public class UnitTests {

    public static void main(String[] args){

        String string = "Hello World!";
        String pattern = PatternGenerator.randomPatternFromString(CharRange.LATIN_PRINTABLE, string);
        System.out.println(pattern);

        Assert.assertTrue(Pattern.matches(pattern, string));
    }

    @Test
    public void testEmpty(){
        rexTest(CharRange.LATIN_PRINTABLE, "", 1);
    }

    @Test
    public void testOneChar(){
        final int NUMBER_OF_CHARS = 1000;

        for (int i = 0; i < NUMBER_OF_CHARS; i++)
            rexTest(CharRange.LATIN_PRINTABLE, ""+ CharUtils.randomChar(), 100);
    }

    @Test
    public void testWords(){
        testWords(CharRange.HUMAN_FRIENDLY);
        testWords(CharRange.LATIN_PRINTABLE);
        testWords(CharRange.UPPER_CASE);
        testWords(CharRange.LOWER_CASE);
        testWords(CharRange.DIGITS);
    }

    private void testWords(CharRange charRange){

        final int NUMBER_OF_WORDS = 500;

        final int MIN_WORD_LENGTH = 2;
        final int MAX_WORD_LENGTH = 30;

        final int TESTS_PER_WORD = 150;

        for (int i = 0; i < NUMBER_OF_WORDS; i++) {
            String word = PatternGenerator.randomString(charRange, RussianRoulette.randomInRange(MIN_WORD_LENGTH, MAX_WORD_LENGTH));

            rexTest(charRange, word, TESTS_PER_WORD);
        }
    }

    private void rexTest(CharRange charRange, String string, int N){
        for (int i = 0; i < N; i++) {
            String pattern = PatternGenerator.randomPatternFromString(charRange, string);
            System.out.println(pattern);

            Assert.assertTrue(Pattern.matches(pattern, string));
        }
    }

}
