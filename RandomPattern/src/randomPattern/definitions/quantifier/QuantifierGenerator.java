package randomPattern.definitions.quantifier;

import randomPattern.utils.RussianRoulette;

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
            default:
                return null;
        }
    }

    public static final int MIN_N_OFFSET = -2;
    public static final int MAX_N_OFFSET =  0;

    public static final int MIN_M_OFFSET =  0;
    public static final int MAX_M_OFFSET =  3;

    public static void randomizeRepeatingQuantifier(Quantifier quantifier){
        if(quantifier == null)
            return;

        switch (quantifier.getType()){
            case AT_LEAST:
                SingleBoundedQuantifier sbq = ((SingleBoundedQuantifier) quantifier);
                sbq.setN(RussianRoulette.randomNear(sbq.getN(), MIN_N_OFFSET, MAX_N_OFFSET));
                break;
            case BETWEEN:
                DoubleBoundedQuantifier dbq = ((DoubleBoundedQuantifier) quantifier);
                int new_n = RussianRoulette.randomNear(dbq.getN(), MIN_N_OFFSET, MAX_N_OFFSET);
                int new_m = RussianRoulette.randomNear(dbq.getM(), MIN_M_OFFSET, MAX_M_OFFSET);
                dbq.set(new_n, new_m);
                break;
        }
    }


}

