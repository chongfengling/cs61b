package gitlet;

import java.io.Serializable;
import java.util.Date;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Chongfeng Ling
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    /** reference to the parent. */
    private String parentID;
    /** Username. */
    private String author;
    /** Datestamp. 0 for the first commit. */
    private Date dateStamp;

    // constructor 1: work for initial commit
    public Commit(String message) {
        this.message = message;
        // no parentID for the first commit
        this.parentID = null;
        this.author = System.getProperty("user.name");
        // timestamp of the first commit is time 0
        this.dateStamp = new Date(0);
    }
}
