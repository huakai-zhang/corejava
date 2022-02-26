package com.nio.buffer;

import java.io.*;  
import java.nio.*;  
import java.nio.channels.*;  
  
public class DirectBuffer {  
    static public void main( String args[] ) throws Exception {
        String infile = "E:/info.txt";
        FileInputStream fin = new FileInputStream( infile );  
        FileChannel fcin = fin.getChannel();  
          
        //把刚刚读取的内容写入到一个新的文件中
        String outfile = String.format("E:/infocopy.txt");
        FileOutputStream fout = new FileOutputStream( outfile );      
        FileChannel fcout = fout.getChannel();  
          
        // 使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  
          
        while (true) {  
            buffer.clear();  
              
            int r = fcin.read(buffer);  
              
            if (r==-1) {  
                break;  
            }  
              
            buffer.flip();  
              
            fcout.write(buffer);  
        }
	}  
}  
