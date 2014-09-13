package randomPattern.definitions.quantifier;

/**
 * Created by gmtuk on 13/09/2014.
 */
public class QuantifierGenerator {
    public static Quantifier generate(RepeatingQuantifierType type){
        switch (type){
            case PLUS:
            case STAR:
                return new UnboundedQuantifier(type);
            case EXACTLY:
            case AT_LEAST:
                return new SingleBoundedQuantifier(type);
            case BETWEEN:
                return new DoubleBoundedQuantifier();
            default:        return null;
        }
    }

}

