package randomPattern.utils;

/**
 * Created by gmtuk on 13/09/2014.
 */
public enum CharRange {
    ALL_PRINTABLE       (32,0xFFFF),
    HUMAN_FRIENDLY      (48,122),
    LATIN_PRINTABLE     (32,126),
    UPPER_CASE          (65,90),
    LOWER_CASE          (97,122),
    DIGITS              (48,57);

    final int MIN_CHAR, MAX_CHAR;

    CharRange(int MIN_CHAR, int MAX_CHAR) {
        this.MIN_CHAR = MIN_CHAR;
        this.MAX_CHAR = MAX_CHAR;
    }
}

