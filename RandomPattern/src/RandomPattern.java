import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by gmtuk on 23/08/2014.
 */
public final class RandomPattern {

    private static final int TRAP_CHANCE = 10;

    private static final int MIN_NOT_SET = 1;
    private static final int MAX_NOT_SET = 4;

    private static final int MIN_CHAR_RANGE = 1;
    private static final int MAX_CHAR_RANGE = 10;

    private static final List<NodePattern> patternOptions = new ArrayList<NodePattern>();
    static{
        Utils.addItemsToList(
                patternOptions,

                new NodePattern() {
                    @Override
                    public String pattern(char c) {
                        return "[" + Utils.escape(Utils.neighbour(c, Utils.randomInRange(-MAX_CHAR_RANGE, -MIN_CHAR_RANGE))) + "-" + Utils.escape(Utils.neighbour(c, Utils.randomInRange(MIN_CHAR_RANGE, MAX_CHAR_RANGE))) + "]";
                    }
                },

                new NodePattern() {
                    @Override
                    public String pattern(char c) {
                        if(Pattern.matches("\\W", ""+c)) return "\\W";
                        else                             return "\\w";
                    }
                },

                new NodePattern() {
                    @Override
                    public String pattern(char c) {
                        if(Pattern.matches("\\D", ""+c)) return "\\D";
                        else                             return "\\d";
                    }
                },

                new NodePattern() {
                    @Override
                    public String pattern(char c) {
                        if(Pattern.matches("\\S", ""+c)) return "\\S";
                        else                             return "\\s";
                    }
                },

                new NodePattern() {
                    @Override
                    public String pattern(char c) {
                        String notSet = "";
                        for(int i=1; i <= Utils.randomInRange(MIN_NOT_SET,MAX_NOT_SET); i++)
                            notSet += Utils.escape(Utils.randomCharNot(c));
                        return "[^" + notSet + "]";
                    }
                },

                new NodePattern() {
                   @Override
                   public String pattern(char c) {
                       return Utils.escape(c);
                   }
               }
        );
    }


    private static final List<String> trapOptions = new ArrayList<String>();
    static{
        char randomChar = Utils.randomChar();

        String notSet = "";
        for(int i=1; i <= Utils.randomInRange(MIN_NOT_SET,MAX_NOT_SET); i++)
            notSet += Utils.escape(Utils.randomChar());

        Utils.addItemsToList(
                trapOptions,

                "\\W", "\\w","\\D","\\d", "\\S", "\\s", "[^" + notSet + "]",
                "[" + Utils.escape(randomChar) + "-" + Utils.escape(Utils.randomCharAbove(randomChar)) + "]",
                Utils.escape(randomChar)
        );
    }


    public static String patternFromString(String string) {

        if(string == null || string.equals(""))
            return string;

        NodePatternList nodePatterns = new NodePatternList();

        for (char c : string.toCharArray()) {
            String randomPattern = getRandomPattern(nodePatterns, c);

            nodePatterns.add(randomPattern);
            if(!Utils.isQuantifier(nodePatterns.last()) && Utils.luckyDay(15))
                nodePatterns.add("?");

            if(Utils.luckyDay(TRAP_CHANCE)) {
                nodePatterns.add(getRandomTrap());

                if(Utils.luckyDay(70))
                    nodePatterns.add("?");
                else
                    nodePatterns.add("*");
            }

        }


        return nodePatterns.totalPattern();
    }

    private static String getRandomNodePattern(char c){
        return (Utils.randomItem(patternOptions)).pattern(c);
    }

    private static String getRandomTrap(){
        return Utils.randomItem(trapOptions);
        //case 6: return Utils.escape(c) + "?";
    }

    private static String getRandomPattern(NodePatternList nodePatternList, char c) {

        if (nodePatternList != null && !nodePatternList.isEmpty()) {
            String lastPattern = nodePatternList.last();

            if (lastPattern.equals("*")) {
                String beforeLast = nodePatternList.beforeLast(); //index out of bounds?

                if (Pattern.matches(beforeLast, "" + c) && Utils.luckyDay(70))
                    return "";
                else
                    return getRandomNodePattern(c);

            }
            else if(lastPattern.equals("?")){
                return getRandomNodePattern(c);
            }

            if (Pattern.matches(lastPattern, "" + c))
                return "*"; //how about repeating more times!?!?!?!?! {3} also... + also... TODO

        }

        return getRandomNodePattern(c);
    }

}
