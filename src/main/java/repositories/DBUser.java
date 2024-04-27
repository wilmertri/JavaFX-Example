package repositories;

import entities.User;
import interfaces.IDatabaseEntity;
import persistence.FileManage;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBUser implements IDatabaseEntity {

    private User user;
    private FileManage fileManage;

    public DBUser(User user) {
        this.user = user;
        this.fileManage = new FileManage("users.txt");
    }

    public DBUser() {
        this.fileManage = new FileManage("users.txt");
    }
    public ArrayList<User> getData(){
        ArrayList<User> users = null;
        FileManage fileManage = new FileManage("users.txt");
        ArrayList<String> lines = fileManage.getDataOfFile();
        if(lines != null){
            users = new ArrayList<>();
            for (String line : lines){
                StringTokenizer tokens = new StringTokenizer(line, ";");
                String name = tokens.nextToken();
                String lastName = tokens.nextToken();
                int age = Integer.parseInt(tokens.nextToken());
                users.add(new User(name, lastName, age));
            }
        }
        return users;
    }

    @Override
    public boolean addData() {
        return this.fileManage.insertDataInFile(this.getLineData());
    }

    @Override
    public boolean updateData() {
        return false;
    }

    @Override
    public String getLineData() {
        return this.user.getName() + ";" + this.user.getLastName() + ";" + this.user.getAge();
    }
}
