package kr.owens.unittestexample.data;

import java.util.ArrayList;

/**
 * @author owen151128@gmail.com
 * <p>
 * Created by owen151128 on 2019-11-26 17:17
 * <p>
 * Providing features related to ParseInfo class
 */
public class ParseInfo {

  private ArrayList<Integer> values;
  private ArrayList<Character> operators;

  public ArrayList<Integer> getValues() {
    return values;
  }

  public void setValues(ArrayList<Integer> values) {
    this.values = values;
  }

  public ArrayList<Character> getOperators() {
    return operators;
  }

  public void setOperators(ArrayList<Character> operators) {
    this.operators = operators;
  }
}
