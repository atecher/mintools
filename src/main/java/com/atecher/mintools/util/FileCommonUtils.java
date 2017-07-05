package com.atecher.mintools.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述：文件操作工具栏
 * @作者 韩红伟
 * @邮箱 hongwei.han@qq.com
 * @日期 2014-7-29
 * @版本 v1.0
 */
public class FileCommonUtils {
	private static final Log log = LogFactory.getLog(FileCommonUtils.class);
	/**
	 * 描述：通过url下载文件
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param urlString
	 * @param filename
	 * @param savePath
	 * @throws IOException 
	 * @throws Exception
	 */
	public static void download(String urlString, String filename,String savePath) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try{
			URL url = new URL(urlString);// 构造URL
			URLConnection con = url.openConnection();// 打开连接
			con.setConnectTimeout(5 * 1000);// 设置请求超时为5s
			is = con.getInputStream();// 输入流
			byte[] bs = new byte[1024];// 1K的数据缓冲
			int len;// 读取到的数据长度
			File sf = new File(savePath);// 输出的文件流
			if (!sf.exists()&&sf.mkdirs()) {
				log.debug("创建下载目录");
			}
			os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		}finally{
			// 完毕，关闭所有链接
			if(os!=null)
				os.close();
			if(os!=null)	
				is.close();
		}
	}

}
