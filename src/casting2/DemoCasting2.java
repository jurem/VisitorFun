package casting2;

import visitable.clean.*;

/**
 * TODO: write a short description
 *
 * @author jure
 */
public class DemoCasting2 {

    public static void main(String[] args) {
        System.out.println(DemoCasting2.class.getPackage().getName());

        Node prog = DemoAST.prog;

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