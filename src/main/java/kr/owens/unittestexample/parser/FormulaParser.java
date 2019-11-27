package kr.owens.unittestexample.parser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import kr.owens.unittestexample.data.ParseInfo;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2019-11-26 13:37
 * <p>
 * Providing features related to FormulaParser class
 */
public class FormulaParser {

  private static final String DELIMITER = "+-*/=";
  private static final String BLINK = " ";
  private static final String EMPTY = "";

  private static final char NEGATIVE = '-';

  private ArrayList<Integer> values;
  private ArrayList<Character> operators;

  public FormulaParser() {
    values = new ArrayList<>();
    operators = new ArrayList<>();
  }

  private boolean validateFormula(ArrayList<String> tokenList) {
    boolean isFirstInt;
    boolean isDigit;
    boolean isNextDigit;

    isFirstInt = Character.isDigit(tokenList.get(0).charAt(0));

    if (tokenList.size() <= 1) {
      return false;
    }

    for (int i = 1; i < tokenList.size(); i += 2) {
      if (i + 1 == tokenList.size()) {
        isDigit = Character.isDigit(tokenList.get(i).charAt(0));
        if (!isDigit) {
          return false;
        }

        break;
      }
      isDigit = Character.isDigit(tokenList.get(i).charAt(0));
      isNextDigit = Character.isDigit(tokenList.get(i + 1).charAt(0));

      if (isFirstInt) {
        if (isDigit || !isNextDigit) {
          return false;
        }
      } else {
        if (!isDigit || isNextDigit) {
          return false;
        }
      }
    }

    return true;
  }

  public ParseInfo parseFormula(String line) {
    line = line.replace(BLINK, EMPTY);
    StringTokenizer tokenizer = new StringTokenizer(line, DELIMITER, true);
    ArrayList<String> tokenList = new ArrayList<>();

    while (tokenizer.hasMoreTokens()) {
      tokenList.add(tokenizer.nextToken());
    }

    if (!validateFormula(tokenList)) {
      return null;
    }

    boolean isOdd = true;

    if (!Character.isDigit(tokenList.get(0).charAt(0))) {
      char op = tokenList.get(0).charAt(0);
      int val = Integer.parseInt(tokenList.get(1));
      if (op == NEGATIVE) {
        val = -val;
      }

      tokenList.set(0, Integer.toString(val));
      tokenList.remove(1);
    }

    for (String s : tokenList) {
      if (isOdd) {
        values.add(Integer.parseInt(s));
        isOdd = false;
      } else {
        operators.add(s.charAt(0));
        isOdd = true;
      }
    }

    ParseInfo parseInfo = new ParseInfo();

    parseInfo.setValues(values);
    parseInfo.setOperators(operators);

    return parseInfo;
  }
}
