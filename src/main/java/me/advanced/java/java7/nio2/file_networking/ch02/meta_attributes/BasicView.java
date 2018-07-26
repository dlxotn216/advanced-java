package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-26
 */
@Service
public class BasicView implements ApplicationRunner {
	private Path path;
	
	public BasicView() {
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
			BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
			
			System.out.println("File size " + attributes.size());
			System.out.println("File creation time " + attributes.creationTime());
			System.out.println("File was last accessed at " + attributes.lastAccessTime());
			System.out.println("File was last modified at " + attributes.lastModifiedTime());
			System.out.println("File is directory " + attributes.isDirectory());
			System.out.println("File is regular file " + attributes.isRegularFile());
			System.out.println("File is symbolic link " + attributes.isSymbolicLink());
			System.out.println("File is other " + attributes.isOther());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ChapterLogger
	public void getAttribute() {
		//또 다른 방법으로 개별 attribute를 읽을 수 있다. view-name:attributeName 형태가 key
		try {
			System.out.println(Files.getAttribute(path, "basic:size", LinkOption.NOFOLLOW_LINKS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ChapterLogger
	public void updateAttribute() {
		try {
			FileTime lastModifiedTime = FileTime.from(Instant.now());
			FileTime lastAccessTime
					= FileTime.from(ZonedDateTime.of(2017,
					11,
					21,
					8,
					20,
					30,
					12,
					ZoneId.from(ZoneOffset.UTC)).toInstant());
			Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)
					.setTimes(lastModifiedTime, lastAccessTime, null);
			
			BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			System.out.println("creation time " + attributes.creationTime());
			System.out.println("lastAccessTime " + attributes.lastAccessTime());
			System.out.println("lastModifiedTime " + attributes.lastModifiedTime());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
