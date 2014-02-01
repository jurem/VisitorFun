package methods;

/**
 * Visitors by dedicated methods.
 *   - must change the implementation of visitables
 *   + fast
 *   - new operation changes visitables and causes recompilation
 *
 * @author jure
 */
public class DemoMethods {

    static Node prog = new Block(
            new Print("Hello visitor!"),
            new Block(
                    new Comment("TODO: Write a code."),
                    new PrintFancy("Goodbye visitor!")
            )
    );

    public static void main(String[] args) {
        System.out.println(DemoMethods.class.getPackage().getName());

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
