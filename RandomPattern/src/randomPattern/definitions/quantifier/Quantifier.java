package randomPattern.definitions.quantifier;

/**
 * Created by gmtuk on 05/09/2014.
 */

/*
    X?	    X, once or not at all
    X*  	X, zero or more times
    X+	    X, one or more times
 */
public abstract class Quantifier {

    public abstract void repeat();
    public abstract String toString();

    private RepeatingQuantifierType type;

    protected Quantifier(RepeatingQuantifierType type) {
        this.type = type;
    }

    public final RepeatingQuantifierType getType() {
        return type;
    }
}


class UnboundedQuantifier extends Quantifier{
    private RepeatingQuantifierType type;
    private char c;

    UnboundedQuantifier(RepeatingQuantifierType type) {
        super(type);

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
class SingleBoundedQuantifier extends Quantifier{
    private int n;

    SingleBoundedQuantifier(RepeatingQuantifierType type) {
        super(type);

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

    protected void setN(int n){
        this.n = n;
    }


    @Override
    public String toString() {
        switch (getType()){
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
    private int n = 2, m = 2;

    DoubleBoundedQuantifier() {
        super(RepeatingQuantifierType.BETWEEN);
    }

    DoubleBoundedQuantifier(int n, int m){
        this();
        set(n,m);
    }

    @Override
    public void repeat() {
        n++;
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