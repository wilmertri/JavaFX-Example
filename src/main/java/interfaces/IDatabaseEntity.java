package interfaces;

import entities.User;

import java.util.ArrayList;

public interface IDatabaseEntity<T> {
    ArrayList<T> getData();
    boolean addData();
    boolean updateData(T data);
    void deleteData();
    String getLineData(T data);

}
