package kr.owens.unittestexample.calc;

import java.util.ArrayList;
import kr.owens.unittestexample.data.ParseInfo;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2019-11-26 13:38
 * <p>
 * Providing features related to Calculator class
 */
public class Calculator {

  private static final char ADD = '+';
  private static final char SUB = '-';
  private static final char MUL = '*';
  private static final char DIV = '/';

  public static int calculate(ParseInfo parseInfo) {
    ArrayList<Integer> values = parseInfo.getValues();
    ArrayList<Character> operators = parseInfo.getOperators();

    int lhs, rhs, result;
    char operator;

    for (int i = 0; i < operators.size(); i++) {
      operator = operators.get(i);
      lhs = values.get(i);
      rhs = values.get(i + 1);
      switch (operator) {
        case MUL:
          values.set(i, lhs * rhs);
          values.remove(i + 1);
          operators.remove(i);
          i--;
          break;
        case DIV:
          values.set(i, lhs / rhs);
          values.remove(i + 1);
          operators.remove(i);
          i--;
      }
    }

    result = values.get(0);
    for (int i = 0; i < operators.size(); i++) {
      operator = operators.get(i);
      rhs = values.get(i + 1);

      switch (operator) {
        case ADD:
          result += rhs;
          break;
        case SUB:
          result -= rhs;
      }
    }

    return result;
  }
}
