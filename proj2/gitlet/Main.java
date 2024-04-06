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
                // in git, second args will create new folder and init it while more than 2 args will exit with error.
                // error message is init usage
                Repository.initCommand();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
        }
    }
}
