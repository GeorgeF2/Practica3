import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Main {

  public static class State {
    int id;
    Map<Character, Integer> trans = new HashMap<Character, Integer>();
    boolean fi;

    State(int id, char[] a, int[] t, boolean f) {
      this.id = id;
      for (int i = 0; i < a.length; i++)
        this.trans.put(a[i], t[i]);
      this.fi = f;
    }

    int nextState(char a) {
      return this.trans.get(a);
    }

    boolean isFinal() {
      return this.fi;
    }

    void print() {
      System.out.println("State " + id + ": ");
      System.out.println(trans);
      if (fi)
        System.out.println("This state is final");
      else
        System.out.println("This state is not final");
    }
  }

  public static class Pair {
    int id;
    State st1, st2;
    boolean dist;

    Pair(int i, State a, State b) {
      this.id = i;
      this.st1 = a;
      this.st2 = b;
      if ((this.st1.isFinal() && !this.st2.isFinal()) || (!this.st1.isFinal() && this.st2.isFinal()))
        this.dist = true;
      else
        this.dist = false;
    }

    void print(){
      System.out.println("Pair " + id + ":");
      System.out.println("Includes states " + st1.id + " and " + st2.id);
      if (dist)
        System.out.println("This is a distinguishable pair");
      else
        System.out.println("This is not a distinguishable pair");
    }
  }

  public static class AFD {
    State[] states;
    Pair[] pairs;

    void fill() {
      String temp = JOptionPane.showInputDialog("How many states are there?");
      int numStates = Integer.valueOf(temp);
      states = new State[numStates];
      temp = JOptionPane.showInputDialog("whats the alphabet?");
      char[] alfabet = temp.toCharArray();
      temp = JOptionPane.showInputDialog("what are the final states?");
      char[] finalstates = temp.toCharArray();

      for (int i = 0; i < numStates; i++) {
        temp = JOptionPane.showInputDialog("For state " + i + " transitions");
        char[] transitions = temp.toCharArray();
        int[] tr = new int[temp.length()];
        for (int j = 0; j < temp.length(); j++) {
          tr[j] = Character.getNumericValue(transitions[j]);
        }
        boolean check = false;
        for (int j = 0; j < finalstates.length; j++) {
          if (Character.getNumericValue(finalstates[j]) == i) {
            check = true;
          }
        }
        states[i] = new State(i, alfabet, tr, check);
      }

      int numOfPairs = ((numStates - 1) * ((numStates - 1) + 1)) / 2;
      pairs = new Pair[numOfPairs];
      int i = 0;
      for (int j = 0; j < numStates - 1; j++) {
        for (int k = j + 1; k < numStates; k++) {
          pairs[i] = new Pair(i, states[j], states[k]);
          i++;
        }
      }
    }

    void print() {
      System.out.println("Number of states: " + states.length);
      for (int i = 0; i<states.length; i++) {
        states[i].print();
      }
      System.out.println();
      System.out.println("Number of pairs: " + pairs.length);
      for (int i = 0; i<pairs.length; i++) {
        pairs[i].print();
      }
    }

    AFD minimizeAFD(){
      AFD minimized = this;

      return minimized;
    }
  }

  public static void main(String[] args) {
    AFD sta = new AFD();
    sta.fill();
    sta.print();
  }
}
