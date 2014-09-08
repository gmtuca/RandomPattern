/**
 * Created by gmtuk on 05/09/2014.
 */
enum OptionalQuantifier  {
    QUESTION_MARK('?'), STAR('*');

    private final char c;

    OptionalQuantifier(char c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return ""+c;
    }
}

enum RepeatingQuantifier { PLUS, STAR, EXACTLY, AT_LEAST, BETWEEN }

abstract class Quantifier{
    protected abstract void repeat();
    public abstract String toString();

    protected static Quantifier generateQuantifier(RepeatingQuantifier type){
        switch (type){
            case PLUS:
            case STAR:
            //case OPTIONAL:
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

/*
    X?	    X, once or not at all
    X*  	X, zero or more times
    X+	    X, one or more times
 */
class UnboundedQuantifier extends Quantifier{
    private RepeatingQuantifier type;
    private char c;

    UnboundedQuantifier(RepeatingQuantifier type) {
        this.type = type;

        switch (type){
           case PLUS:       this.c = '+'; break;
           case STAR:       this.c = '*'; break;
           //case OPTIONAL:   this.c = '?'; break;
           default:         this.c = '\0';
                            throw new IllegalArgumentException("Illegal QuantifierType " +
                                        type + ", incompatible with class UnboundedQuantifier");
        }
    }

    @Override
    protected void repeat() {}

    @Override
    public String toString() {
        return ""+c;
    }
}

/*
    X{n}	X, exactly n times
    X{n,}	X, at least n times
 */
class SingleBoundedQuantifier extends Quantifier{
    private RepeatingQuantifier type;
    private int n;

    SingleBoundedQuantifier(RepeatingQuantifier type) {
        this.type = type;

        switch (type){
            case EXACTLY:
            case AT_LEAST:  this.n = 2; break;
            default:        this.n = -1;
                            throw new IllegalArgumentException("Illegal QuantifierType " +
                                        type + ", incompatible with class SingleBoundedQuantifier");
        }

    }

    @Override
    protected void repeat() {
        n++;
    }

    protected int getN(){
        return n;
    }

    @Override
    public String toString() {
        switch (type){
            case EXACTLY:   return "{" + n + "}";
            case AT_LEAST:  return "{" + n + ",}";
            default:        return null;
        }
    }
}

/*
    X{n,m}	X, at least n but not more than m times
 */
class DoubleBoundedQuantifier extends Quantifier{
    private int n = 2, m = 3;

    DoubleBoundedQuantifier() {
    }

    DoubleBoundedQuantifier(int n, int m){
        set(n,m);
    }

    @Override
    protected void repeat() {
        m++;
    }

    protected void set(int n, int m){
        this.n = n;
        this.m = m;
    }

    protected int getN(){
        return n;
    }
    protected int getM(){
        return m;
    }

    @Override
    public String toString() {
        return "{" + n + "," + m + "}";
    }
}