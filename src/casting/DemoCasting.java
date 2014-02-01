package casting;

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

    static Node prog = new Block(
            new Print("Hello visitor!"),
            new Block(
                    new Comment("TODO: Write a code."),
                    new PrintFancy("Goodbye visitor!")
            )
    );

    public static void main(String[] args) {
        System.out.println(DemoCasting.class.getPackage().getName());

        Visitor visitor = new Visitor();

        System.out.println("--------------");
        visitor.print(0, prog);

        System.out.println("--------------");
        visitor.exec(prog);

        System.out.println("--------------");
        System.out.println(visitor.size(prog));

        System.out.println("--------------");
        visitor.dump(prog);
        System.out.println();
        visitor.dump(visitor.compile(prog));
        System.out.println();

    }

}
