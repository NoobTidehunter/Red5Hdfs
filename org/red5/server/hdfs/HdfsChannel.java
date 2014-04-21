package org.red5.server.hdfs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HdfsChannel implements ReadableByteChannel {

	private HdfsConnection hdfsConn;
	private ReadableByteChannel channel;
	private InputStream hdfsInStream;
	
	public HdfsChannel(String filePath) {
		hdfsConn = new HdfsConnection();
		hdfsInStream = hdfsConn.getHdfsInputStream(filePath);
		channel = Channels.newChannel(hdfsInStream);
	}
	
	
	@Override
	public boolean isOpen() {
		return channel.isOpen();
	}

	@Override
	public void close() throws IOException {
		hdfsConn.close();
		channel.close();
	}

	@Override
	public int read(ByteBuffer dst) throws IOException {
		return channel.read(dst);
	}

	public long size() {
		return hdfsConn.size();
	}
	
	public long position() throws IOException{
		return hdfsConn.position();
	}
	
	public void position(long pos) throws IOException{
		hdfsConn.position(pos);
	}
	
	public String getFullFileName() {
		return hdfsConn.getFullFileName();
	}
}
