package controller;

import java.io.*;

public class FileControl {
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    public void fileWrite(String writtenInfo, String fileName) throws IOException {
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(writtenInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileOutputStream.close();
    }
    public void fileRead(String fileName){
        try {
            fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            System.out.println(objectInputStream.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> void fileDelete(String fileName, Object deletedInfo){
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
