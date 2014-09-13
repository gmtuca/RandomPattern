package randomPattern.definitions.quantifier;

/**
 * Created by gmtuk on 13/09/2014.
 */
public enum OptionalQuantifierType {
    QUESTION_MARK('?'), STAR('*');

    private final char c;

    OptionalQuantifierType(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return ""+c;
    }
}
