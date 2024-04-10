package gitlet;

import java.io.File;
import java.io.Serializable;

public class Blob implements Serializable {
    /*
    * create blob in folder objects/ when it is staged
    * single file may correspond to multiple blobs
    *
    * */

    private String fileName;
    private String fileID;
    private byte[] fileContent;

    public Blob(File f) {
        this.fileName = f.getName();
        this.fileContent = Utils.readContents(f);
        this.fileID = Utils.sha1(fileContent);
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileID() {
        return this.fileID;
    }

    public byte[] getFileContent() {
        return this.fileContent;
    }

}
