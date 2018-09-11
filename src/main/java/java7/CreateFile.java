package java7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class CreateFile {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("./", "UTWX001.txt");// 在src同级目录生成文件
		byte[] bytes = "test".getBytes();
		Files.write(path, bytes);
		Logger.getGlobal().info("write file finished.");
	}
}
