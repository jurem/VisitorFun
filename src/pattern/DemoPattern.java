package pattern;

/**
 * Visitors by visitor design pattern.
 *   - additional accept() methods in vistables
 *   + no type casts
 *   + no recompilation of vistables on new operation
 *
 * @author jure
 */
public class DemoPattern {

    static Node prog = new Block(
            new Print("Hello visitor!"),
            new Block(
                    new Comment("TODO: Write a code."),
                    new PrintFancy("Goodbye visitor!")
            )
    );

    public static void main(String[] args) {
        System.out.println(DemoPattern.class.getPackage().getName());

        System.out.println("--------------");
        new Printer(prog).go();

        System.out.println("--------------");
        new Executor(prog).go();

        System.out.println("--------------");
        Sizer sizer = new Sizer(prog);
        sizer.go();
        System.out.println(sizer.size());

        System.out.println("--------------");
        new Dumper(prog).go();
        Compiler compiler = new Compiler(prog);
        compiler.go();
        new Dumper(compiler.result()).go();
    }

}
