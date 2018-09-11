package java7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class CreateFile {

	public static void main(String[] args) throws IOException {
		createFile();
		createNonDirAndFile();
	}

	/**
	 * 在不存在的目录下创建文件，会抛出异常：
	 * Exception in thread "main" java.nio.file.NoSuchFileException: ./myfile/UTWX001.txt
	 */
	private static void createNonDirAndFile() throws IOException {
		Path path = Paths.get("./myfile/", "UTWX001.txt");// 创建没有的myfile目录
		byte[] bytes = "test".getBytes();
		Files.write(path, bytes);
		Logger.getGlobal().info("create dir and write file finished.");
	}

	/**
	 * 当前目录生成文件
	 * @throws IOException
	 */
	private static void createFile() throws IOException {
		Path path = Paths.get("./", "UTWX001.txt");// 在src同级目录生成文件
		byte[] bytes = "test".getBytes();
		Files.write(path, bytes);
		Logger.getGlobal().info("write file finished.");
	}
}
