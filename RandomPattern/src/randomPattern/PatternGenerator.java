package randomPattern;

import randomPattern.definitions.NodePattern;
import randomPattern.definitions.NodePatternList;
import randomPattern.definitions.quantifier.OptionalQuantifierType;
import randomPattern.definitions.quantifier.Quantifier;
import randomPattern.definitions.quantifier.QuantifierGenerator;
import randomPattern.definitions.quantifier.RepeatingQuantifierType;
import randomPattern.utils.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class PatternGenerator {

    private static final int REPEATING_CHANCE        = 55,
                             OPTIONAL_NODE_CHANCE    = 10,

                             MIN_NOT_SET             = 1,
                             MAX_NOT_SET             = 4,

                             MIN_NEIGHBOUR_RANGE     = 1,
                             MAX_NEIGHBOUR_RANGE     = 10;

    public static void main(String[] args){
        System.out.println(benchmark());

        /*
        stream(CharRange.ASCII_PRINTABLE, "Hello World!")
                .limit(50)
                .forEach(System.out::println);
        */
    }

    private static Hashtable<Integer, Double> benchmark(){
        Hashtable<Integer, Double> b = new Hashtable<>();

        for(int i=0; i<16; i++){
            int len = 2 << i; //2^i
            String randomString = CharUtils.randomString(CharRange.ASCII_PRINTABLE, len);

            long s = System.nanoTime();

            for(int a = 0; a < 10000; a++){
                randomPattern(CharRange.ASCII_PRINTABLE, randomString);
            }

            long f = System.nanoTime();

            double diff = ((f - s) / 1000000.0) / 10000; //time per item

            System.out.println(len + "," + diff);

            b.put(len, diff);
        }

        return b;
    }

    public static Stream<String> stream(CharRange charRange, String string){
        return Stream.generate(()-> randomPattern(charRange, string));
    }

    /**
     * Generates a random String representing a regular expression which matches the given String!
     * @param string
     * @return
     */
    public static String randomPattern(CharRange charRange, String string) {

        if(string == null || string.isEmpty())
            return string;

        NodePatternList nodePatternList = new NodePatternList();

        for (char c : string.toCharArray()) {
            if(!charRange.inRange(c)){
                throw new IllegalArgumentException(
                        "Character '" + c + "' is invalid for defined "
                                + CharRange.class.getSimpleName());
            }

            nodePatternList.add(generateRandomPattern(charRange, nodePatternList, c));
        }


        for(NodePattern nodePattern : nodePatternList){
            QuantifierGenerator.randomizeRepeatingQuantifier(nodePattern.getQuantifier());
        }

        return nodePatternList.toString();
    }

    interface PatternPrototype {
        String pattern(CharRange r, char c);
    }

    private static final List<PatternPrototype> patternOptions = new ArrayList<>();
    static{
        PatternPrototype fromTo = (r, c) -> PatternUtils.fromToPattern(
                CharUtils.randomNeighbour(r, c, -MAX_NEIGHBOUR_RANGE, -MIN_NEIGHBOUR_RANGE),
                CharUtils.randomNeighbour(r, c, MIN_NEIGHBOUR_RANGE, MAX_NEIGHBOUR_RANGE)
        );

        Utilities.addItemsToCollection(
                patternOptions,

                (r, c) -> PatternUtils.escaping(c),
                (r, c) -> PatternUtils.escaping(c),

                fromTo,
                fromTo,

                PatternUtils::lowerUpperOrNumber,
                PatternUtils::lowerUpperOrNumber,

                (r, c) -> Pattern.matches("\\W", "" + c) ? "\\W" : "\\w",
                (r, c) -> Pattern.matches("\\D", "" + c) ? "\\D" : "\\d",
                (r, c) -> Pattern.matches("\\S", "" + c) ? "\\S" : "\\s",

                (r, c) -> {
                    String notSet = "";
                    for (int i = 1; i <= RussianRoulette.randomInRange(MIN_NOT_SET, MAX_NOT_SET); i++)
                        notSet += PatternUtils.escaping(CharUtils.randomCharExcept(r,c));
                    return PatternUtils.notPattern(notSet);
                }
        );
    }

    /**
     *
     * @param c
     * @return
     */
    private static NodePattern getRandomNodePattern(CharRange r, char c){
        NodePattern newPattern = new NodePattern(RussianRoulette.randomItem(patternOptions).pattern(r,c));

        if(RussianRoulette.luckyDay(OPTIONAL_NODE_CHANCE))
            newPattern.setOptionalQuantifier(RussianRoulette.randomItem(OptionalQuantifierType.values()));

        return newPattern;
    }

    /**
     * Produces a random randomPattern.definitions.NodePattern, taking advantage of patterns currently on {@param nodePatternList}.
     * Returns null if the last pattern of the list (with the addition of a quantifier) can be used instead.
     * @param nodePatternList
     * @param c
     * @return
     */
    private static NodePattern generateRandomPattern(CharRange r, NodePatternList nodePatternList, char c) {
        if (c == '\0')
            throw new IllegalArgumentException("Cannot generate random pattern from EOF \0");

        NodePattern lastPattern = nodePatternList.getLastPattern();

        if(lastPattern == null) //ie list is empty - base case
            return getRandomNodePattern(r,c);

        if (lastPattern.matches(c) && RussianRoulette.luckyDay(REPEATING_CHANCE)) { //quantify | repeat last pattern

            Quantifier lastQuantifier = lastPattern.getQuantifier();
            if(lastQuantifier == null){
                lastPattern.setQuantifier(QuantifierGenerator.generate(RussianRoulette.randomItem(RepeatingQuantifierType.values()))); //add quantifier
                return null;
            } else {
                lastQuantifier.repeat();
            }

            return null; //returning null means we are getting a ride with the last pattern. No need to add something new
        }

        //if we cannot repeat (or we don't want to)
        //introduce a new pattern to append
        return getRandomNodePattern(r,c);
    }
}

