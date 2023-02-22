package controller;

import java.io.*;

public class FileControl {
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    public void fileWrite(String writedInfo, String fileName){
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeBytes(writedInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileRead(String fileName){
        try {
            fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void fileDelete(String fileName){
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
