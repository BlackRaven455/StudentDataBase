package thedarkdnktv.studentdb.api;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface IDataModel {

    void store(DataOutput out) throws IOException;

    void read(DataInput in) throws IOException;
}
