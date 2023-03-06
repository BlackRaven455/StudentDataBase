import controller.FileControl;

import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menuControl = 0;
        while (true) {
            while(true){
                out.print("Choose database file:\n");
                for (int i = 0; i < DatabaseFiles.values().length - 1; i++) {
                    out.printf("\t%d. %s\n", i + 1, DatabaseFiles.getFileName(i).label);
                }
                try {
                    int j = scanner.nextInt() - 1;
                    if(j < 0 || j > 2){
                        out.println("File with this number doesn't exist");
                        continue;
                    }
                    Path nameFile = Paths.get(DatabaseFiles.getFileName(j).label);
                    FileControl file = new FileControl(nameFile);
                    break;
                } catch (InputMismatchException e) {
                    out.println("Invalid value, use numbers\n");
                }
            }
            while (menuControl != 4) {
                out.println("1 - Add student\n2- Delete student\n3 - List of students\n4 - Exit\n");
                menuControl = scanner.nextInt();
                switch (menuControl) {
                    case 1: {
//                        file.
                    }
                    case 2: {


                    }
                    case 3: {

                    }
                    case 4: {
                        System.exit(0);
                    }
                    default:
                        out.println("No options");

                }
            }
            out.println("Thank you for using this shity app");
        }
    }
}
