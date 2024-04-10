package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Chongfeng Ling
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // When args is empty, display help message and exit.
        if (args.length == 0) {
            Repository.gitletHelp();
            return;
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // usage: java gitlet.Main init
                validateNumArgs(args, 1);
                Repository.initCommand();
                break;
            case "add":
                // usage: java gitlet.Main add [filename]
                // in gitlet, only one file can be added at a time
                validateNumArgs(args, 2);
                Repository.addCommand(args[1]);
                break;
            case "status":
                // usage: java gitlet.Main status
                validateNumArgs(args, 1);
                Repository.statusCommand();
                break;

        }
    }

    /*
    * Checks the number of arguments versus the expected number,
    * exit if they do not match
    *
    * @param args: Argument array from command line
    * @param n: Number of expected arguments
    * */
    private static void validateNumArgs(String[] args, int n) {
        if (args.length != n) {
            Utils.message("Length of arguments doesn't match the expected value.");
            System.exit(0);
        }
    }
}
