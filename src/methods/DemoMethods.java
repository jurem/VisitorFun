package methods;

import visitable.methods.*;

/**
 * Visitors by dedicated methods.
 *   - must change the implementation of visitables
 *   + fast
 *   - new operation changes visitables and causes recompilation
 *
 * @author jure
 */
public class DemoMethods {

    public static void main(String[] args) {
        System.out.println(DemoMethods.class.getPackage().getName());

        Node prog = DemoAST.prog;

        System.out.println("--------------");
        prog.print(0);

        System.out.println("--------------");
        prog.exec();

        System.out.println("--------------");
        System.out.println(prog.size());

        System.out.println("--------------");
        prog.dump();
        System.out.println();
        prog.compile().dump();
        System.out.println();

    }

}
