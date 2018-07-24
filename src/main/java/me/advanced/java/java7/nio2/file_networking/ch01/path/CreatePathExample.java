package me.advanced.java.java7.nio2.file_networking.ch01.path;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 */
@Service
public class CreatePathExample implements ApplicationRunner {
	
	@ChapterRunner
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
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

	@ChapterLogger
	public void normalize(){
		Path path1 = Paths.get("C:/users/taesu/desktop");
		Path path2 = Paths.get("C:/users/./taesu/desktop");
		Path path3 = Paths.get("C:/users/./taesu/../taesu/desktop");

		System.out.println(path1);
		System.out.println(path2);
		System.out.println(path3);
		System.out.println("\nNormalize 후");
		System.out.println(path1.normalize());
		System.out.println(path2.normalize());
		System.out.println(path3.normalize());
	}

	@ChapterLogger
	public void getPathFromFileSystems(){
		System.out.println("Path를 생성하는 일반적 방법");

		Path path = FileSystems.getDefault().getPath("C:/users/taesu/desktop");
		System.out.println(path);
	}

	@ChapterLogger
	public void getHomeDirectory(){
		//반환되는 홈디렉토리는 OS 따라 다르다
		System.out.println(Paths.get(System.getProperty("user.home"), "downloads"));
	}
}
