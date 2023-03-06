public enum DatabaseFiles {
    STUDENT ("Studentdb.bin"),
    SCHOOLAR ("Schooldb.bin"),
    SLAVE ("Companydb.bin"),
    NOFILE("No file with that number");


    public static DatabaseFiles getFileName(int index){
        if(index >= 0 && index < values().length)
        {
            return values()[index];
        }
        return NOFILE;
    }

    public final String label;

    DatabaseFiles(String label) {
        this.label = label;
    }
}

