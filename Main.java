import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;


public class Main {

  public static class State {
    int id;
    Map<Character, Integer> trans;
    boolean fi;

    State(int id, char[] a, int[] t, boolean f) {
      this.id = id;
      for (int i = 0; i < a.length; i++){
        System.out.println("for state " + id + " character " + a[i] + " goes to " + t[i]);
        this.trans.put(a[i], t[i]);
      }
      this.fi = f;
    }

    int nextState(char a) {
      return this.trans.get(a);
    }

    boolean isFinal() {
      return this.fi;
    }
  }

  public static class Pair {
    State st1, st2;
    boolean dist;

    Pair(State a, State b) {
      this.st1 = a;
      this.st2 = b;
      if ((this.st1.isFinal() && !this.st2.isFinal()) || (!this.st1.isFinal() && this.st2.isFinal()))
        this.dist = true;
      else
        this.dist = false;
    }

  }

  public static class AFD{
    State[] states;
    Pair[] pairs;

    void fill(){
      String temp = JOptionPane.showInputDialog("How many states are there?");
      int numStates = Integer.valueOf(temp);
      temp = JOptionPane.showInputDialog("whats the alphabet?");
      char[] alfabet = temp.toCharArray();
      temp = JOptionPane.showInputDialog("what are the final states?");
      char[] finalstates = temp.toCharArray();

      for (int i = 0; i<numStates; i++) {
        temp = JOptionPane.showInputDialog("For state " + i + " transitions");
        char[] transitions = temp.toCharArray();
        System.out.println("chararray = " + transitions[0] + ", "+ transitions[1]);
        int[] tr = new int[temp.length()];
        for (int j = 0; j<temp.length(); j++) {
          tr[j] = Integer.valueOf(transitions[j]);
        }
        System.out.println("intarray = " + tr[0] + ", "+ tr[1]);
        boolean check = false;
        for (int j = 0; j<finalstates.length; j++) {
          if (Integer.valueOf(finalstates[j]) == i){
            check = true;
          }
        }
        states[i] = new State(i, alfabet, tr, check);
      }
    }
  }

  public static void main(String[] args) {
    AFD sta = new AFD();
    sta.fill();
  }
}
