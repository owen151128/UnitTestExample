package kr.owens.unittestexample.io;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2019-11-26 11:34
 * <p>
 * Providing features related to Console class
 */
public class Console {

  private static Console instance = null;

  private static final String COMMA = ", ";
  private static final String INPUT_PREFIX = " >> ";

  private static final char NULL = '\0';

  private static final String ILLEGAL_INPUT_ERR_FORMAT = "Please input the %s! String is not support.";
  private static final String TAG = "[OWEN]";
  private static final String START_TAG = "[OWEN] ";
  private static final String NEW_LINE = "\n";
  private static final String LINE_CHAR = "=";
  private static final String INTEGER = "Integer";
  private static final String FLOAT = "Float";
  private static final String DOUBLE = "Double";
  private static final String CHAR = "Char";

  private static final int LINE_LEN = 40;

  private PrintStream standardOutput;
  private Scanner standardInput;
  private String LINES;
  private boolean isPrintTag = false;

  private Console() {
    standardInput = new Scanner(System.in);
    standardOutput = System.out;

    StringBuilder sb = new StringBuilder();
    int totalLen = LINE_LEN + 1;

    for (int i = 0; i < totalLen; i++) {
      if (i == totalLen / 2) {
        sb.append(TAG);
        continue;
      }

      sb.append(LINE_CHAR);
    }

    LINES = sb.toString().intern();
  }

  public static synchronized Console getInstance() {
    if (instance == null) {
      instance = new Console();
    }

    return instance;
  }

  public void setTagOption(boolean isPrintTag) {
    this.isPrintTag = isPrintTag;
  }

  public void startWithLine(String message) {
    standardOutput.println(LINES + NEW_LINE + message);
  }

  public void startWithLines(String format, Object... objects) {
    standardOutput.println(LINES + NEW_LINE + String.format(format, objects));
  }

  public void endWithLines(String message) {
    standardOutput.println(message + NEW_LINE + LINES);
  }

  public void endWithLines(String format, Object... objects) {
    standardOutput.println(String.format(format, objects) + NEW_LINE + LINES);
  }

  public void print(String message) {
    standardOutput.println(isPrintTag ? START_TAG + message : message);
  }

  public void print(String message, String sep) {
    standardOutput.println(isPrintTag ? START_TAG + message + sep : message + sep);
  }

  public void print(String... messages) {
    if (isPrintTag) {
      standardOutput.print(START_TAG);
    }

    for (String s : messages) {
      standardOutput.print(s + COMMA);
    }
    standardOutput.println();
  }

  public void print(String sep, String... messages) {
    if (isPrintTag) {
      standardOutput.print(START_TAG);
    }

    for (String s : messages) {
      standardOutput.print(s + sep);
    }
    standardOutput.println();
  }

  public void print(String format, Object... objects) {
    standardOutput.printf(isPrintTag ? START_TAG + format : format, objects);
  }

  public synchronized String getLine(String message) {
    standardOutput.println(message + INPUT_PREFIX);
    return standardInput.nextLine();
  }

  public int getInt(String message) {
    try {
      standardOutput.println(message + INPUT_PREFIX);

      return Integer.parseInt(standardInput.nextLine());
    } catch (NumberFormatException e) {
      standardOutput.println(String.format(ILLEGAL_INPUT_ERR_FORMAT, INTEGER));
    }

    return 0;
  }

  public float getFloat(String message) {
    try {
      standardOutput.println(message + INPUT_PREFIX);

      return Float.parseFloat(standardInput.nextLine());
    } catch (NumberFormatException e) {
      standardOutput.println(String.format(ILLEGAL_INPUT_ERR_FORMAT, FLOAT));
    }

    return 0;
  }

  public double getDouble(String message) {
    try {
      standardOutput.println(message + INPUT_PREFIX);

      return Double.parseDouble(standardInput.nextLine());
    } catch (NumberFormatException e) {
      standardOutput.println(String.format(ILLEGAL_INPUT_ERR_FORMAT, DOUBLE));
    }

    return 0;
  }

  public char getChar(String message) {
    String line;
    standardOutput.println(message + INPUT_PREFIX);
    line = standardInput.nextLine();

    if (line.length() != 1) {
      standardOutput.println(String.format(ILLEGAL_INPUT_ERR_FORMAT, CHAR));

      return NULL;
    }

    return line.charAt(0);
  }
}
