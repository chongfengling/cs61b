package gitlet;

import java.io.File;
import java.io.IOException;
import static gitlet.Utils.*;

/** Represents a gitlet repository.
 * .gitlet/ -- top level folder for all persistent data required for version control initialized by `gitlet init`
 *    - HEAD      -- HEAD of current branch
 *    - index     -- staging area
 *    - objects/  -- all "objects", including blob (files), trees (directories), commits (reference to a tree,
 *    parent commit, etc.)
 *    - refs/     -- pointers to commit objects
 *      - heads/  -- HEADs of each branch
 *
 *  @author Chongfeng Ling
 */
public class Repository {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File OBJECTS_DIR = join(GITLET_DIR, "objects");

    public static final File REFS_DIR = join(GITLET_DIR, "refs");

    public static final File HEAD_F = join(GITLET_DIR, "HEAD");

    public static final File INDEX_F = join(GITLET_DIR, "index");

    public static final File REFS_HEADS = join(REFS_DIR, "heads");


    public static void gitletHelp() {
        System.out.println("Gitlet help message.");
    }

    /*
     * TODO: single current branch: master
     * */
    public static void initCommand() {
        // when there is already a repo, abort.
        if (GITLET_DIR.exists()) {
            Utils.message("A gitlet version-control system already exists in the current directory.");
            System.exit(0);
        } else {
            initGitlet();
            Commit commit = new Commit("initial commit");
            commit.save();
        }
        return;
    }

    // TODO: identical file (changed back after it was changed and added)
    public static void addCommand(String fileName) {
        // check if to be added file exists
        File f = Utils.join(CWD, fileName);
        checkFileExistence(f);
        stagingForAddition(f);
    }
    /* Display status as follow:
     * === Branches ===
     * === Staged Files ===
     * === Removed Files ===
     * === Modifications Not Staged For Commit ===
     * === Untracked Files ===
     * */
    public static void statusCommand() {
        Stage stage = Stage.fromFile(INDEX_F);
        stage.status();
    }
    /* Help Functions */

    private static void initCommand(String message) {
        Commit commit = new Commit(message);
        commit.save();
    }

    // read and save current blob into stage area
    private static void stagingForAddition(File f) {
        Stage stage = Stage.fromFile(INDEX_F);
        Blob blob = new Blob(f);
        stage.add(blob);
        stage.save(INDEX_F);
    }

    private static void checkFileExistence(File f) {
        if (!f.exists()) {
            Utils.message("File does not exist");
            System.exit(0);
        }
    }

    // initialize the folder .gitlet
    // create several folders and files inside .gitlet
    private static void initGitlet() {
        GITLET_DIR.mkdir();
        OBJECTS_DIR.mkdir();
        REFS_DIR.mkdir();
        try {
            // TODO: write an empty String into the file rather than create it.
            HEAD_F.createNewFile();
            Utils.writeObject(INDEX_F, new Stage()); // readObject() from an empty file will fail
            REFS_HEADS.mkdir();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the files");
        }
    }

}
