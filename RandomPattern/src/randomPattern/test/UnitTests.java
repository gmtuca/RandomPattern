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

    /*
    public static void main(String[] args){

        String string = "Hello World!";
        String pattern = PatternGenerator.randomPattern(CharRange.LATIN_PRINTABLE, string);
        System.out.println(pattern);

        Assert.assertTrue(Pattern.matches(pattern, string));
    }
    */

    @Test
    public void testEmpty(){
        regexAssertRandomPattern(CharRange.LATIN_PRINTABLE, "", 1);
    }

    @Test
    public void testInvalidCharRange(){

        final int NUMBER_OF_WORDS = 500;
        final int STRING_LENGTH = 20;
        final int TESTS_PER_WORD = 100;

        CharRange[][] invalidPairs = new CharRange[][]{
                {CharRange.LOWER_CASE, CharRange.DIGITS},
                {CharRange.DIGITS, CharRange.LOWER_CASE},

                {CharRange.LOWER_CASE, CharRange.UPPER_CASE},
                {CharRange.UPPER_CASE, CharRange.LOWER_CASE},

                {CharRange.UPPER_CASE, CharRange.DIGITS},
                {CharRange.DIGITS, CharRange.UPPER_CASE},
        };

        for(int i = 0; i < NUMBER_OF_WORDS; i++) {
            for (CharRange[] pair : invalidPairs) {
                String string = CharUtils.randomString(pair[0], STRING_LENGTH);

                try {
                    regexAssertRandomPattern(pair[1], string, TESTS_PER_WORD);
                    Assert.fail(IllegalArgumentException.class.getName() + " not thrown for invalid CharRange pairs " + pair[0] + " - " + pair[1]);
                } catch (IllegalArgumentException e) {

                } catch (Exception e) {
                    Assert.fail(e.getMessage());
                }
            }
        }

    }

    @Test
    public void testOneChar(){
        final int NUMBER_OF_CHARS = 1000;

        for (int i = 0; i < NUMBER_OF_CHARS; i++)
            regexAssertRandomPattern(CharRange.LATIN_PRINTABLE, "" + CharUtils.randomChar(CharRange.LATIN_PRINTABLE), 100);
    }

    @Test
    public void testWords(){
        testWords(CharRange.HUMAN_FRIENDLY);
        testWords(CharRange.LATIN_PRINTABLE);
        testWords(CharRange.UPPER_CASE);
        testWords(CharRange.LOWER_CASE);
        testWords(CharRange.DIGITS);
    }

    public void testWords(CharRange charRange){

        final int NUMBER_OF_WORDS = 500;

        final int MIN_WORD_LENGTH = 2;
        final int MAX_WORD_LENGTH = 30;

        final int TESTS_PER_WORD = 150;

        for (int i = 0; i < NUMBER_OF_WORDS; i++) {
            String word = CharUtils.randomString(charRange, RussianRoulette.randomInRange(MIN_WORD_LENGTH, MAX_WORD_LENGTH));

            regexAssertRandomPattern(charRange, word, TESTS_PER_WORD);
        }
    }

    private void regexAssertRandomPattern(CharRange charRange, String string, int tests_per_word){
        for (int i = 0; i < tests_per_word; i++) {
            String pattern = PatternGenerator.randomPattern(charRange, string);
            System.out.println(pattern);

            Assert.assertTrue(Pattern.matches(pattern, string));
        }
    }

}
