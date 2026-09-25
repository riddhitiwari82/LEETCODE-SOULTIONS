import java.util.*;

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    // Handles union: A,B,C
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: AB, A{B,C}, {A,B}C
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != '}'
                && expression.charAt(index) != ',') {

            Set<String> next = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // Handles:
    // 1. single letter
    // 2. {...}
    private Set<String> parseFactor() {

        if (expression.charAt(index) == '{') {

            index++; // skip '{'

            Set<String> result = parseExpression();

            index++; // skip '}'

            return result;
        }

        // Single lowercase letter
        char ch = expression.charAt(index);
        index++;

        Set<String> result = new HashSet<>();
        result.add(String.valueOf(ch));

        return result;
    }
}