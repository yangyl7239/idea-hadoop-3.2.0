package edu.nwpu.hdfs.mr.mysql;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyDBWritable
 */
public class MyDBWritable implements DBWritable, Writable {
    private int id;
    private String name;
    private String txt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeUTF(name);
        out.writeUTF(txt);
    }

    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        name = in.readUTF();
        txt = in.readUTF();
    }

    /**
     * 写入db
     */
    public void write(PreparedStatement ppst) throws SQLException {
        ppst.setInt(1, id);
        ppst.setString(2, name);
        ppst.setString(3, txt);
    }

    /**
     * 从db读取
     */
    public void readFields(ResultSet rs) throws SQLException {
        id = rs.getInt(1);
        name = rs.getString(2);
        txt = rs.getString(3);
    }
}
