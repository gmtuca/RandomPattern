package randomPattern.definitions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gmtuk on 24/08/2014.
 */
public class NodePatternList extends ArrayList<NodePattern> {

    protected NodePatternList(int initialCapacity) {
        super(initialCapacity);
    }

    public NodePatternList() {
    }

    public NodePatternList(Collection<? extends NodePattern> c) {
        super(c);
    }

    /**
     *
     * @param nodePattern
     * @return
     */
    @Override
    public boolean add(NodePattern nodePattern) {
        if(nodePattern == null)
            return false;

        return super.add(nodePattern);
    }

    /**
     * Returns the last randomPattern.definitions.NodePattern of this list.
     * @return
     */
    public NodePattern getLastPattern(){
        if(isEmpty())
            return null;

        return get(size()-1);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(NodePattern nodePattern : this)
            stringBuilder.append(nodePattern.toString());

        return stringBuilder.toString();
    }
}