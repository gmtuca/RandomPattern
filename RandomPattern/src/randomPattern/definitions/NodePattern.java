package randomPattern.definitions;

import randomPattern.definitions.quantifier.OptionalQuantifierType;
import randomPattern.definitions.quantifier.Quantifier;
import randomPattern.definitions.quantifier.QuantifierGenerator;
import randomPattern.definitions.quantifier.RepeatingQuantifierType;

import java.util.regex.Pattern;

/**
 * Created by gmtuk on 01/09/2014.
 */
public class NodePattern {
    private final String pattern;
    private Quantifier quantifier = null;
    private OptionalQuantifierType optionalQuantifier = null;

    public NodePattern(String pattern) {
        this.pattern = pattern;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(Quantifier quantifier) {
        this.quantifier = quantifier;
    }

    protected boolean isOptional() {
        return optionalQuantifier != null;
    }

    public void setOptionalQuantifier(OptionalQuantifierType optionalQuantifier) {
        if(optionalQuantifier == OptionalQuantifierType.STAR && quantifier == null){
            //if there are no repeating quantifiers yet, we can set one for *
            this.quantifier = QuantifierGenerator.generate(RepeatingQuantifierType.STAR);
        } else {
            this.optionalQuantifier = optionalQuantifier;
        }
    }

    protected String getPattern() {
        return pattern;
    }

    public boolean matches(char c){
        return Pattern.matches(pattern, "" + c);
    }

    @Override
    public String toString() {
        return pattern +
                (quantifier == null ?         "" : quantifier.toString()) +
                (optionalQuantifier == null ? "" : optionalQuantifier.toString());
    }
}