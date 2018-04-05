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
      char[] alfabet = temp.toCharArray();
    }
  }

  public static void main(String[] args) {
    AFD sta = new AFD();
    sta.fill();
  }
}
