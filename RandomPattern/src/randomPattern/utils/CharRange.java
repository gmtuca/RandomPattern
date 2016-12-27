package randomPattern.utils;

/**
 * Created by gmtuk on 13/09/2014.
 */
public enum CharRange {
    ALL_PRINTABLE       (32,0xFFFF),
    HUMAN_FRIENDLY      (48,122),
    LATIN_PRINTABLE     (32,126),
    UPPER_CASE          ('A','Z'),
    LOWER_CASE          ('a','z'),
    DIGITS              ('0','9');

    final int MIN_CHAR, MAX_CHAR;

    CharRange(int MIN_CHAR, int MAX_CHAR) {
        this.MIN_CHAR = MIN_CHAR;
        this.MAX_CHAR = MAX_CHAR;
    }

    CharRange(char MIN_CHAR, char MAX_CHAR) {
        this((int)MIN_CHAR, (int)MAX_CHAR);
    }

    public boolean inRange(int i){
        return i >= MIN_CHAR && i <= MAX_CHAR;
    }

    public boolean inRange(char c){
        return inRange((int)c);
    }

}

