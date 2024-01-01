import java.util.ArrayList;

public class Clause extends ArrayList<Atom>{

    int iParent;
    int jParent;
    public Clause(){
        super();
        iParent = -1;
        jParent = -1;
    }

    /*public Clause(int i, int j){
        super();
        iParent = i + 1;
        jParent = j + 1;
    }*/

    public boolean equals(Clause c){
        if(c.size() != this.size()){
            return false;
        }

        boolean contains;

        for(int i = 0; i < this.size(); i++){
            contains = false;
            for(int j = 0; j < c.size(); j++){
                if(c.get(j).equals(this.get(i))){
                    contains = true;
                }
            }
            if(contains == false){
                return false;
            }
        }
        return true;
    }

    public Clause tryResolveAndAdd(Clause c, int ii, int jj){

        ArrayList<Integer> matchedIndThis = new ArrayList<Integer>();
        ArrayList<Integer> matchedIndC = new ArrayList<Integer>();

        for(int i = 0; i < this.size(); i++){
            for(int j = 0; j < c.size(); j++){
                if(c.get(j).resolvesWith(this.get(i))) {
                    matchedIndThis.add(i);
                    matchedIndC.add(j);
                }
            }
        }

        if(matchedIndThis.isEmpty() || matchedIndThis.size() > 1){
            return null;
        }

        Clause newC = new Clause();
        for(int i = 0; i < this.size(); i++){
            if(i != matchedIndThis.get(0)){
                newC.add(this.get(i));
            }
        }
        for(int i = 0; i < c.size(); i++){
            if(i != matchedIndC.get(0)){
                newC.add(c.get(i));
            }
        }

        newC.iParent = ii + 1;
        newC.jParent = jj + 1;

        newC.simplify();
        return newC;
    }

    public void simplify(){

        boolean dupe;
        int i = 0;
        int originalSize = this.size()-1;
        while(i < originalSize){
            for(int j = i+1; j < this.size(); j++){
                if(this.get(i).equals(this.get(j))){
                    remove(j);
                    originalSize--;
                    j = i+1;
                }
            }
            i++;
        }
    }

    @Override
    public String toString(){


        String returnMe = "";

        if(this.isEmpty()){
            returnMe += "Contradiction ";
        } else
            for(int i = 0; i < this.size(); i++){
            returnMe += this.get(i).toString() + " ";
        }

        if(iParent > 0 && jParent > 0){
            returnMe += "{" + iParent + ", " + jParent + "}";
        } else {
            returnMe += "{}";
        }
        return returnMe;
    }
}
