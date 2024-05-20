package repositories;

import persistence.FileManage;

public class DatabaseManage {

    protected FileManage fileManage;
    public DatabaseManage(String filename)
    {
        this.fileManage = new FileManage(filename);
    }

}
