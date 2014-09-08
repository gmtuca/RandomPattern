import java.util.regex.Pattern;

/**
 * Created by gmtuk on 01/09/2014.
 */
class NodePattern {
    private final String pattern;
    private Quantifier quantifier = null;
    private OptionalQuantifier optionalQuantifier = null;

    protected NodePattern(String pattern) {
        this.pattern = pattern;
    }

    protected Quantifier getQuantifier() {
        return quantifier;
    }
    protected void setQuantifier(Quantifier quantifier) {
        this.quantifier = quantifier;
    }

    protected boolean isOptional() {
        return optionalQuantifier != null;
    }

    protected void setOptionalQuantifier(OptionalQuantifier optionalQuantifier) {
        if(optionalQuantifier == OptionalQuantifier.STAR && quantifier == null){
            //if there are no repeating quantifiers yet, we can set one for *
            this.quantifier = Quantifier.generateQuantifier(RepeatingQuantifier.STAR);
        } else {
            this.optionalQuantifier = optionalQuantifier;
        }
    }

    protected String getPattern() {
        return pattern;
    }

    protected boolean matches(char c){
        return Pattern.matches(pattern, "" + c);
    }

    @Override
    public String toString() {
        return pattern +
                (quantifier == null ?         "" : quantifier.toString()) +
                (optionalQuantifier == null ? "" : optionalQuantifier.toString());
    }
}