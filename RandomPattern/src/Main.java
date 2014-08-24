import org.junit.Test;
import org.junit.Assert;

import java.util.regex.Pattern;

/**
 * Created by gmtuk on 18/08/2014.
 */
public class Main {
    //get a regex from a String

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomPattern.patternFromString("HELLO"));
        }

    }

    @Test
    public void empty(){
        rexTest("", 1);
    }

    @Test
    public void oneChar(){
        final int NUMBER_OF_CHARS = 100;

        for (int i = 0; i < NUMBER_OF_CHARS; i++)
            rexTest(""+Utils.randomChar(), 100);
    }

    @Test
    public void words(){
        final int NUMBER_OF_WORDS = 200;

        final int MIN_WORD_LENGTH = 2;
        final int MAX_WORD_LENGTH = 30;

        final int TESTS_PER_WORD = 100;

        for (int i = 0; i < NUMBER_OF_WORDS; i++) {
            String word = "";
            for (int j = 0; j < Utils.randomInRange(MIN_WORD_LENGTH, MAX_WORD_LENGTH); j++) {
                word += Utils.randomChar();
            }
            rexTest(word, TESTS_PER_WORD);
        }
    }

    private void rexTest(String string, int N){
        for (int i = 0; i < N; i++) {
            String pattern = RandomPattern.patternFromString(string);
            System.out.println(pattern);

            Assert.assertTrue(Pattern.matches(pattern, string));
        }
    }

}
