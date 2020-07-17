import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RulerMeasurement{
	public static class State{
	    private int ruler_1,ruler_2;
	    
	    public State(){
	        ruler_1 = 0;
	        ruler_2 = 0;
	    }
	    public State(int r1, int r2){
	    	ruler_1 = r1;
	        ruler_2 = r2;
	    }
	    
	    public State[] genNextState(){
	        State s[] = new State[4];
	        
	        int a = Math.abs(ruler_1 - ruler_2);
	        int b = ruler_1 + ruler_2;

	        s[0] = new State(ruler_1, a);
	        s[1] = new State(ruler_2, a);
	        s[2] = new State(ruler_1, b);
	        s[3] = new State(ruler_2, b);
	        
	        //System.out.println(s[0].ruler_1+" "+s[0].ruler_2);
	        //System.out.println(s[1].ruler_1+" "+s[1].ruler_2);
	        //System.out.println(s[2].ruler_1+" "+s[2].ruler_2);
	        //System.out.println(s[3].ruler_1+" "+s[3].ruler_2);
	            
	        return s;     
	    }
	    
	    public int getRuler1(){
	        return ruler_1;
	    }
	    public int getRuler2(){
	        return ruler_2;
	    }
	    public String toString(){
	        return ""+"("+ruler_1+", "+ruler_2+")";
	    }
	}
	
	public static class BFS {
	    Queue<State> q;
	    HashSet<String> visited_states, explored_states;
	    
	    LinkedList<ArrayList<String>> path;
	    ArrayList<String> s_path;
	    
	    public BFS(){
	        q = new LinkedList<>();
	        visited_states = new HashSet<>();
	        explored_states = new HashSet<>();
	        path = new LinkedList<>();
	        s_path=new ArrayList<>();
	    }
	    
	    public void bfsTraversal(State s, int goal){
	        q.add(s);
	        State new_states[];
	        
	        ArrayList<String> new_path = new ArrayList<>();
	        new_path.add(s.toString());
	        path.add(new_path);

	        while(!q.isEmpty()){
                State s1 = q.poll();
                visited_states.add(s1.toString());
                s_path = path.poll();
                
                if(s1.getRuler1() == goal || s1.getRuler2() == goal){
                	System.out.println("\nGoal Found!!");
                    System.out.println(goal+" Inch Measured!!");
                    System.out.println("Goal State --> ("+s1.getRuler1()+", "+s1.getRuler2()+")");
                    return;
                }
                
                new_states = s1.genNextState();
                
                for(State st: new_states){
                    if(visited_states.contains(st.toString())) continue;
                    
    		        if(explored_states.contains(st.toString())) continue;
                    
                    q.add(st);
                    visited_states.add(st.toString());
                    
                    ArrayList<String> new_path1 = new ArrayList<>(s_path);
                    new_path1.add(st.toString());
                    path.add(new_path1);  
               }
           }
	    }
	    
	    public void printPath(){
	    	System.out.println();
	    	System.out.println("Showing path:");
	    	System.out.println("(m1, m2)");
	    	System.out.println("--------");
	    	
	        for (String s: s_path){
	            System.out.println(s);
	        }
	    }  
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ruler_1,ruler_2,goal;
		
		System.out.print("Size of 1st ruler: ");
		ruler_1 = sc.nextInt();
		System.out.print("Size of 2nd ruler: ");
		ruler_2 = sc.nextInt();
		System.out.print("Size to be measured: ");
		goal = sc.nextInt();
		
		State st = new State(ruler_1, ruler_2);
		
	    System.out.println("\nStart State --> "+st.toString());
	    
	    BFS measureGoal = new BFS();
	    measureGoal.bfsTraversal(st, goal);
	    measureGoal.printPath();
	}
}
