import java.util.ArrayList;

/**
 * Created by gmtuk on 24/08/2014.
 */
public class NodePatternList extends ArrayList<String> {

    public String last(){
        if(isEmpty())
            return null;

        return get(size()-1);
    }

    public boolean setLast(String p){
        String prevLast = last();

        set(size()-1, p);

        return !prevLast.equals(last());
    }

    public String beforeLast(){
        if(size() < 2)
            return null;

        return get(size()-2);
    }

    @Override
    public boolean add(String s) {
        if(s.equals(""))
            return false;

        String last = last();

        if(s.equals("?")){
            if(last.equals("*"))
                return false;
            if(last.equals("+"))
                return setLast("*");
        }
        else if(s.equals("*")){
            if(last.equals("?"))
                return setLast("*");
        }

        return super.add(s);
    }

    public String totalPattern(){
        String pattern = "";

        for(String p : this)
            pattern += p;

        return pattern;
    }

}
