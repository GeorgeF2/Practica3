import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Main {

  public static class State {
    int id;
    Map<Character, State> trans = new HashMap<Character, State>();
    char[] alfa;
    boolean fi;

    State(int id, char[] a, State[] t, boolean f) {
      this.id = id;
      for (int i = 0; i < a.length; i++)
        this.trans.put(a[i], t[i]);
      this.alfa = a;
      this.fi = f;
    }

    State nextState(char a) {
      return this.trans.get(a);
    }

    boolean isFinal() {
      return this.fi;
    }

    void print() {
      System.out.println("State " + id + ": ");

      for (int i = 0; i < trans.size(); i++) {
        System.out.println("With " + alfa[i] + " -> " + trans.get(alfa[i]).id);
      }

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

    void print() {
      System.out.println("Pair " + id + ":");
      System.out.println("Includes states " + st1.id + " and " + st2.id);
      if (dist)
        System.out.println("This is a distinguishable pair");
      else
        System.out.println("This is not a distinguishable pair");
    }

    boolean mark(Pair[] list) {
      State temp1, temp2;
      for (int i = 0; i < st1.alfa.length; i++) {
        temp1 = st1.nextState(st1.alfa[i]);
        temp2 = st2.nextState(st2.alfa[i]);
        for (int j = 0; j < list.length; j++) {
          if (((list[j].st1.id == temp1.id && list[j].st2.id == temp2.id)
              || (list[j].st1.id == temp2.id && list[j].st2.id == temp1.id)) && !this.dist) {
            if (list[j].dist) {
              this.dist = true;
              return true;
            }
          }
        }
      }
      return false;
    }

    Pair nextPair(char a, Pair[] list) {
      State temp1, temp2;
      temp1 = st1.nextState(a);
      temp2 = st2.nextState(a);
      for (int j = 0; j < list.length; j++) {
        if ((list[j].st1.id == temp1.id && list[j].st2.id == temp2.id)
            || (list[j].st1.id == temp2.id && list[j].st2.id == temp1.id)) {
          return list[j];
        }
      }
      return null;
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

      State[] tr = new State[alfabet.length];
      for (int j = 0; j < temp.length(); j++) {
        tr[j] = new State(j, alfabet, tr, false);
      }
      for (int j = 0; j < temp.length(); j++) {
        tr[j] = new State(j, alfabet, tr, false);
      }
      for (int j = 0; j < numStates; j++) {
        states[j] = new State(j, alfabet, tr, false);
      }

      for (int i = 0; i < numStates; i++) {
        temp = JOptionPane.showInputDialog("For state " + i + " transitions");
        char[] transitions = temp.toCharArray();
        for (int j = 0; j < temp.length(); j++) {
          tr[j] = states[Character.getNumericValue(transitions[j])];
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
      for (int i = 0; i < states.length; i++) {
        states[i].print();
      }
      System.out.println();
      System.out.println("Number of pairs: " + pairs.length);
      for (int i = 0; i < pairs.length; i++) {
        pairs[i].print();
      }
    }

    AFD minimize() {
      AFD result = this;
      boolean done = false;

      do {
        for (int i = 1; i < pairs.length; i++) {
          done = false;
          if (result.pairs[i].mark(result.pairs)) {
            done = true;
          }
        }
      } while (!done);

      return result;
    }
  }

  public static void main(String[] args) {
    AFD sta = new AFD();
    sta.fill();
    sta.print();
  }
}
