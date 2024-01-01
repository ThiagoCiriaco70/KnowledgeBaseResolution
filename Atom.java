public class Atom {

    String symbol;
    boolean negated;

    public Atom(){
        symbol = "";
        negated = false;
    }

    public Atom(boolean negated, String symbol){
        this.negated = negated;
        this.symbol = symbol;
    }

    public Atom(String s){
        if(s.charAt(0) == '~'){
            negated = true;
            symbol = s.substring(1);
        } else {
            negated = false;
            symbol = s;
        }


    }

    @Override
    public String toString(){
        if(negated){
            return "~" + symbol;
        } else {
            return symbol;
        }
    }

    public void print(){
        System.out.print(this.toString());
    }

    public boolean resolvesWith(Atom a){
        return ((this.symbol.equals(a.symbol)) && (this.negated == !a.negated));
    }

    public boolean equals(Atom a){
        return ((this.symbol.equals(a.symbol)) && (this.negated == a.negated));
    }

}
