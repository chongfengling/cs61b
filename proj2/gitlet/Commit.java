package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Chongfeng Ling
 */
public class Commit implements Serializable {
    /** The message of this Commit. */
    private String message;
    /** reference to the parent. */
    private String parentID;
    /** unique identifier for this commit*/
    private String commitID;
    /** Username. */
    private String author;
    /** Datestamp. 0 for the first commit. */
    private Date dateStamp;
    /** use TreeMap to represent the tree object associated with the commit.
     *  each commit points to a tree object, records blob identifier, path names and metadata.
     * */
    private TreeMap blobRefs;

    // constructor 1: work for initial commit
    public Commit(String message) {
        this.message = message;
        // no parentID for the first commit
        this.parentID = "abc123";
        this.author = "Chongfeng";
        // timestamp of the first commit is time 0
        this.dateStamp = new Date(0);
        this.blobRefs = new TreeMap();
        generateCommitID();
    }

    // generate commitID by log message, metadata (date, author), tree reference, parentID.
    private void generateCommitID() {
        List<Object> vals = new ArrayList<>();
        vals.add(this.message);
        vals.add(this.dateStamp.toString());
        vals.add(this.author);
        vals.add(this.blobRefs.toString()); // TODO: 1. verify. 2. add blobs to the tree
        vals.add(this.parentID);
        this.commitID = Utils.sha1(vals);
    }

    // save commit object to file located in .gitlet/Objects/<sha1 code>
    // save commitID in the file HEAD and current branch
    // TODO: save commitID of current branch located in .gitlet/refs/<head>
    public void save() {
        // save commit object to file located in .gitlet/Objects/<sha1 code>
        File commitObject = commitObject_F(this.commitID);
        Utils.writeObject(commitObject, this);
        // save commitID in the file HEAD and current branch
        Utils.writeContents(Repository.HEAD_F, this.commitID);
    }

    private static File commitObject_DIR(String sha1Code) {
        return Utils.join(Repository.OBJECTS_DIR, sha1Code.substring(0, 2));
    }

    private static File commitObject_F(String sha1Code) {
        File COMMIT_DIR = commitObject_DIR(sha1Code);
        if (!COMMIT_DIR.exists()) {
            COMMIT_DIR.mkdir();
        }
        return Utils.join(COMMIT_DIR, sha1Code.substring(2, sha1Code.length()));
    }

    public static Commit fromFile(File f) {
        String headCommitID = Utils.readContentsAsString(f);
        return Utils.readObject(commitObject_F(headCommitID), Commit.class);
    }

    public String getMessage() {
        return this.message;
    }

    public String getCommitID() {
        return this.commitID;
    }

    public String getParentID() {
        return this.parentID;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDataStamp() {
        return this.dateStamp.toString();
    }

}
