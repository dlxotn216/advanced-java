package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.Optional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-26
 */
@Service
public class DosView implements ApplicationRunner {
	private Path path;
	
	public DosView() {
		try {
			this.path = Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp/test.txt"))
					.orElseThrow(IllegalArgumentException::new).toURI());
		} catch (URISyntaxException e) {
			this.path = null;
		}
	}
	
	@ChapterRunner
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		
	}
	
	@ChapterLogger
	public void readAttributes() {
		try {
			DosFileAttributes attributes = Files.readAttributes(path, DosFileAttributes.class);
			
			//BasicView에서 확장되었기 때문에 DosView에서 BasicView에 직접 접근 가능
			System.out.println("File size " + attributes.size());
			System.out.println("File creation time " + attributes.creationTime());
			System.out.println("File was last accessed at " + attributes.lastAccessTime());
			System.out.println("File was last modified at " + attributes.lastModifiedTime());
			System.out.println("File is directory " + attributes.isDirectory());
			System.out.println("File is regular file " + attributes.isRegularFile());
			System.out.println("File is symbolic link " + attributes.isSymbolicLink());
			System.out.println("File is other " + attributes.isOther());
			
			System.out.println("File is hidden" + attributes.isHidden());
			System.out.println("File is archive" + attributes.isArchive());		//백업 프로그램용 전용 속성
			System.out.println("File is readonly " + attributes.isReadOnly());
			System.out.println("File is system " + attributes.isSystem());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
