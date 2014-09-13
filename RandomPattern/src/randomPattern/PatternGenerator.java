package randomPattern;

import randomPattern.definitions.NodePattern;
import randomPattern.definitions.NodePatternList;
import randomPattern.definitions.quantifier.OptionalQuantifierType;
import randomPattern.definitions.quantifier.Quantifier;
import randomPattern.definitions.quantifier.QuantifierGenerator;
import randomPattern.definitions.quantifier.RepeatingQuantifierType;
import randomPattern.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class PatternGenerator {

    private static final int REPEATING_CHANCE        = 70,
                             OPTIONAL_NODE_CHANCE    = 10,

                             MIN_NOT_SET             = 1,
                             MAX_NOT_SET             = 4,

                             MIN_NEIGHBOUR_RANGE     = 1,
                             MAX_NEIGHBOUR_RANGE     = 10;

    /*
        For next version:

        - introduce brackets ()
        - OR and AND: pattern1 | pattern2, pattern1 && pattern 2
        - randomize bounded quantifiers
     */


    /**
     * Generates a random String representing a regular expression which matches the given String!
     * @param string
     * @return
     */
    public static String randomPatternFromString(CharRange charRange, String string) {
        CharUtils.setCharRange(charRange);

        if(string == null || string.isEmpty())
            return string;

        NodePatternList nodePatternList = new NodePatternList();

        for (char c : string.toCharArray()) {
            if(!CharUtils.charIsInRange(c)){
                throw new IllegalArgumentException("Character '" + c + "' is invalid for defined CharRanged " + CharUtils.getCharRange());
            }

            nodePatternList.add(generateRandomPattern(nodePatternList, c));
        }

        return nodePatternList.toString();
    }

    /**
     *
     * @param charRange
     * @param length
     * @return
     */
    public static String randomString(CharRange charRange, int length){
        CharUtils.setCharRange(charRange);
        return CharUtils.randomString(length);
    }

    interface PatternPrototype {
        public String pattern(char c);
    }

    private static final List<PatternPrototype> patternOptions = new ArrayList<>();
    static{
        Utilities.addItemsToCollection(
                patternOptions,

                (PatternPrototype) (c) -> {
                    return PatternUtils.escape(c);
                },
                (PatternPrototype) (c) -> {
                    return Pattern.matches("\\W", "" + c) ? "\\W" : "\\w";
                },
                (PatternPrototype) (c) -> {
                    return Pattern.matches("\\D", "" + c) ? "\\D" : "\\d";
                },
                (PatternPrototype) (c) -> {
                    return Pattern.matches("\\S", "" + c) ? "\\S" : "\\s";
                },
                (PatternPrototype) (c) -> {
                    return "[" + PatternUtils.escape(CharUtils.randomNeighbour(c, -MAX_NEIGHBOUR_RANGE, -MIN_NEIGHBOUR_RANGE))
                            + "-" + PatternUtils.escape(CharUtils.randomNeighbour(c, MIN_NEIGHBOUR_RANGE, MAX_NEIGHBOUR_RANGE)) + "]";
                },
                (PatternPrototype) (c) -> {
                    String notSet = "";
                    for (int i = 1; i <= RussianRoulette.randomInRange(MIN_NOT_SET, MAX_NOT_SET); i++)
                        notSet += PatternUtils.escape(CharUtils.randomCharExcept(c));
                    return "[^" + notSet + "]";
                }
        );
    }

    /**
     *
     * @param c
     * @return
     */
    private static NodePattern getRandomNodePattern(char c){
        NodePattern newPattern = new NodePattern(RussianRoulette.randomItem(patternOptions).pattern(c));

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
    private static NodePattern generateRandomPattern(NodePatternList nodePatternList, char c) {
        if (c == '\0')
            throw new IllegalArgumentException("Cannot generate random pattern from EOF \0");

        NodePattern lastPattern = nodePatternList.getLastPattern();

        if(lastPattern == null) //ie list is empty - base case
            return getRandomNodePattern(c);

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
        return getRandomNodePattern(c);
    }
}

