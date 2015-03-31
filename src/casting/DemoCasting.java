package casting;

import visitable.clean.*;

/**
 * Visitors by instanceof and type casts.
 *   + no touching of visitables
 *   - many instanceofs and type casts
 *   - instanceof is runtime operation, slow?
 *   * not object-oriented
 *
 * @author jure
 */
public class DemoCasting {

    public static void main(String[] args) {
        System.out.println(DemoCasting.class.getPackage().getName());

        Node prog = DemoAST.prog;

        System.out.println("--------------");
        Visitor.print(0, prog);

        System.out.println("--------------");
        Visitor.exec(prog);

        System.out.println("--------------");
        System.out.println(Visitor.size(prog));

        System.out.println("--------------");
        Visitor.dump(prog);
        System.out.println();
        Visitor.dump(Visitor.compile(prog));
        System.out.println();

    }

}
