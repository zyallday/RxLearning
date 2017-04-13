package org.desert.learning.rxlearning.model;

/**
 * Created by Desert on 31/03/2017.
 */

public enum Event {

  AutoEvent(0) {
    @Override public String toString() {
      return "AutoEvent";
    }
  }, TabEvent(1) {
    @Override public String toString() {
      return "TabEvent";
    }
  };

  int type;

  Event(int type) {
  }

  public int getType() {
    return type;
  }

  @Override public String toString() {
    throw new UnsupportedOperationException();
  }
}
