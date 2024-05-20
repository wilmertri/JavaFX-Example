package repositories;

import entities.User;
import interfaces.IDatabaseEntity;
import persistence.FileManage;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DBUser extends DatabaseManage implements IDatabaseEntity<User> {

    private User user;

    public DBUser() {
        super("users.txt");
    }

    public DBUser(User user) {
        super("users.txt");
        this.user = user;
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
        return this.fileManage.insertDataInFile(this.getLineData(this.user));
    }

    @Override
    public boolean updateData(User newUser) {
        String lineOldUser = this.getLineData(this.user);
        String lineNewUser = this.getLineData(newUser);
        return this.fileManage.updateDataInFile(lineOldUser, lineNewUser);
    }

    @Override
    public void deleteData() {
        this.fileManage.deleteDataInFile(this.getLineData(this.user));
    }

    @Override
    public String getLineData(User user) {
        return user.getName() + ";" + user.getLastName() + ";" + user.getAge();
    }
}
