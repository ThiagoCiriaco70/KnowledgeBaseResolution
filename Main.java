import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int MODE = 1;

    public static void main(String[] args) throws FileNotFoundException {
        KnowledgeBase kb = new KnowledgeBase();

        File KBfile;

        if(MODE == 0) {
            KBfile = new File("src/" + args[0]);
        } else {
            KBfile = new File(args[0]);
        }

        if(!KBfile.exists()){
            System.err.println("KB file could not be found");
            System.exit(-1);
        }

        Scanner kbScan = new Scanner(KBfile);

        while(kbScan.hasNextLine()){
            String nextLine = kbScan.nextLine();
            if(kbScan.hasNextLine()){
                kb.addClause(nextLine);
            } else {
                kb.addNegatedClause(nextLine);
            }
        }

        //System.out.print(kb.toString());

        kb.resolve();
    }

}