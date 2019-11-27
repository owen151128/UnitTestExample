package kr.owens.unittestexample.main;

import kr.owens.unittestexample.calc.Calculator;
import kr.owens.unittestexample.data.ParseInfo;
import kr.owens.unittestexample.io.Console;
import kr.owens.unittestexample.parser.FormulaParser;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2019-11-26 11:32
 * <p>
 * Providing features related to CalculatorMain class
 */
public class CalculatorMain {

  private static final String APPLICATION_NAME = "Calculator v1.0.0";

  private static final String INPUT_MSG = "Input Formula";
  private static final String ILLEGAL_MSG = "Formula is Illegal! please check input.";
  private static final String ANSWER_PREFIX = "Answer : ";

  private static final String EMPTY = "";

  public static void main(Runtime runtime) {
    String line;

    ParseInfo parseInfo;
    Console console = Console.getInstance();
    console.setTagOption(true);

    console.startWithLine(APPLICATION_NAME);
    console.endWithLines(EMPTY);

    FormulaParser formulaParser = new FormulaParser();

    do {
      line = console.getLine(INPUT_MSG);
      parseInfo = formulaParser.parseFormula(line);

      if (parseInfo == null) {
        console.print(ILLEGAL_MSG);
      }
    } while (parseInfo == null);

    console.print(ANSWER_PREFIX + Calculator.calculate(parseInfo));
    runtime.exit(0);
  }
}
