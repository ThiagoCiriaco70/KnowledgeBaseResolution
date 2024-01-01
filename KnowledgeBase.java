import java.util.ArrayList;
import java.util.Scanner;

public class KnowledgeBase extends ArrayList<Clause> {

    public KnowledgeBase(){
        super();
    }
    public void addClause(String s){ //this takes in a clause as a string

        Clause newC = new Clause();
        String[] sp = s.split(" ");

        for(int i = 0; i < sp.length; i++){
            newC.add(new Atom(sp[i]));
        }

        this.add(newC);
    }

    public void addClause(String s, int ii, int jj){ //this takes in a clause as a string

        Clause newC = new Clause();
        String[] sp = s.split(" ");

        for(int i = 0; i < sp.length; i++){
            newC.add(new Atom(sp[i]));
            newC.iParent = ii;
            newC.jParent = jj;
        }

        this.add(newC);
    }

    public void addNegatedClause(String s){ //this takes in a clause as a string
        String[] sp = s.split(" ");

        for(int i = 0; i < sp.length; i++){
            Clause newC = new Clause();
            Atom a = new Atom(sp[i]);
            a.negated = !a.negated;
            newC.add(a);
            this.add(newC);
        }
    }

    @Override
    public String toString(){
        String returnMe = "";
        for(int i = 0; i < this.size(); i++){
            returnMe += (i + 1) + ". " + this.get(i).toString();
            if(i != this.size()-1){
                returnMe += "\n";
            }
        }
        return returnMe;
    }

    public void resolve(){
        int size = this.size();
        int i = 0;
        int j = 0;

        while(i < size){
            j = 0;
            while(j < i){
                Clause c = this.get(i).tryResolveAndAdd(this.get(j), i, j);
                if(c == null){
                    j++;
                    continue;
                }
                if(!containsClause(c)) {
                    this.add(c);
                    size++;
                }
                if(c.isEmpty()){
                    System.out.println(this.toString());
                    System.out.print("Valid");
                    return;
                }
                j++;
            }
            i++;
        }

        System.out.print(this.toString());

    }

    public boolean containsClause(Clause c){
        for(int i = 0; i < size(); i++){
            if(c.equals(this.get(i))){
                return true;
            }
        }
        return false;
    }
}
