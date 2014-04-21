package org.red5.server.hdfs;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class HdfsConnection{
	private static String HDFS_ADDRESS = "hdfs://192.168.137.246:9000/";
	private FSDataInputStream hdfsInStream;
	private FileSystem fs;
	private FileStatus fileStatus;
	private Configuration conf;
	private long fileSize;
	private String fileName;
	
	public HdfsConnection() {
		conf = new Configuration();			
	}
	
	public FSDataInputStream getHdfsInputStream(String fileName) {
		this.fileName = fileName;
		String uri = HDFS_ADDRESS + this.fileName;	
		Path hdfsPath = new Path(uri);
		try {
			fs = FileSystem.get(URI.create(uri), conf);
			hdfsInStream = fs.open(hdfsPath);
			fileStatus = fs.getFileStatus(hdfsPath);			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		fileSize = fileStatus.getLen();
		
		return hdfsInStream;
	}
	
	public String getFullFileName() {
		if (fs != null) {
			return HDFS_ADDRESS + this.fileName;
		}
		
		return null;
	}	
	
	public long size() {
		return fileSize;
	}
	
	public long position() throws IOException{
		long pos = -1;
		try {
			pos = hdfsInStream.getPos();
		} catch (IOException e) {
			throw e;
		}
		
		return pos;
	}

	public void close() throws IOException{
		try {
			if (hdfsInStream != null) {
				hdfsInStream.close();
			}
			if (fs != null) {
				fs.close();
			}
		} catch (IOException e) {
			throw e;
		}		
	}
	
	public void position(long pos) throws IOException{
		try {
			hdfsInStream.seek(pos);
		} catch (IOException e) {
			throw e;
		}
	}
}
