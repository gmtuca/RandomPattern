package randomPattern.definitions.quantifier;

/**
 * Created by gmtuk on 05/09/2014.
 */

/*
    X?	    X, once or not at all
    X*  	X, zero or more times
    X+	    X, one or more times
 */
public interface Quantifier{
    public abstract void repeat();
    public abstract String toString();
}

class UnboundedQuantifier implements Quantifier{
    private RepeatingQuantifierType type;
    private char c;

    UnboundedQuantifier(RepeatingQuantifierType type) {
        this.type = type;

        switch (type){
           case PLUS:       this.c = '+'; break;
           case STAR:       this.c = '*'; break;
           //case OPTIONAL:   this.c = '?'; break;
           default:         this.c = '\0';
                            throw new IllegalArgumentException("Illegal " + Quantifier.class.getName() + ": " +
                                        type + ", incompatible with class " + UnboundedQuantifier.class.getName());
        }
    }

    @Override
    public void repeat() {}

    @Override
    public String toString() {
        return ""+c;
    }
}

/*
    X{n}	X, exactly n times
    X{n,}	X, at least n times
 */
class SingleBoundedQuantifier implements Quantifier{
    private RepeatingQuantifierType type;
    private int n;

    SingleBoundedQuantifier(RepeatingQuantifierType type) {
        this.type = type;

        switch (type){
            case EXACTLY:
            case AT_LEAST:  this.n = 2; break;
            default:        this.n = -1;
                            throw new IllegalArgumentException("Illegal " + Quantifier.class.getName() + ": " +
                                        type + ", incompatible with class " + SingleBoundedQuantifier.class.getName());
        }

    }

    @Override
    public void repeat() {
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
class DoubleBoundedQuantifier implements Quantifier{
    private int n = 2, m = 3;

    DoubleBoundedQuantifier() {
    }

    DoubleBoundedQuantifier(int n, int m){
        set(n,m);
    }

    @Override
    public void repeat() {
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