package me.advanced.java.java7.nio2.file_networking.ch01.path;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 */
@Service
public class AbstractPathRunner implements ApplicationRunner {
	
	@ChapterRunner
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		/*
				this.abstractPathBasic();
				내부에서 실행하므로 AOP 동작 안함
		 */
		
		//Temporary code snippet - MD5 checksum from file
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("temp/test.txt").toURI())));
		byte[] digest = md.digest();
		String digestInHex = DatatypeConverter.printHexBinary(digest).toUpperCase();
		System.out.println(digestInHex);
	}
	
	@ChapterLogger
	public void abstractPathBasic() {
		Path path1 = Paths.get("C:/users/taesu/desktop");
		Path path2 = Paths.get("C:", "users/taesu/desktop");
		Path path3 = Paths.get("C:", "users", "taesu", "desktop");
		
		System.out.println(path1);
		System.out.println(path2);
		System.out.println(path3);
	}
}
