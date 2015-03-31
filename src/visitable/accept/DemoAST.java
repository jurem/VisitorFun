package visitable.accept;

/**
 * TODO: write a short description
 *
 * @author jure
 */
public class DemoAST {

    public static Node prog = new Block(
            new Print("Hello visitor!"),
            new Block(
                    new Comment("TODO: Write a code."),
                    new PrintBold("Goodbye visitor!")
            )
    );

}
