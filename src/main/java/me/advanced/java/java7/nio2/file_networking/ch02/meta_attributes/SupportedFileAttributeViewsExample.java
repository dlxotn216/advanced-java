package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.attribute.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @see java.nio.file.attribute.BasicFileAttributeView
 * @see java.nio.file.attribute.DosFileAttributeView
 * @see java.nio.file.attribute.PosixFileAttributeView
 * @see java.nio.file.attribute.FileOwnerAttributeView
 * @see java.nio.file.attribute.AclFileAttributeView
 * @see java.nio.file.attribute.UserDefinedFileAttributeView
 * @since 2018-07-26
 * <p>
 * NIO2에서 지원하는 뷰
 */
@Service
public class SupportedFileAttributeViewsExample implements ApplicationRunner {
	@ChapterRunner
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		
	}
	
	@ChapterLogger
	public void supportedFileAttributeViews() {
		//현재 파일시스템에서 지원하는 뷰
		//Basic 뷰는 모든 파일시스템이 반드시 지원한다
		FileSystems.getDefault().supportedFileAttributeViews()
				.forEach(System.out::println);
	}
	
	@ChapterLogger
	public void fileStores() {
		List<Class> views = Arrays.asList(
				BasicFileAttributeView.class, DosFileAttributeView.class, PosixFileAttributeView.class,
				FileOwnerAttributeView.class, AclFileAttributeView.class, UserDefinedFileAttributeView.class
		);
		FileSystems.getDefault().getFileStores()
				.forEach(fileStore ->
						views.forEach(view -> 
								System.out.println(fileStore.name() + " ----------" + fileStore.supportsFileAttributeView(view))));
	}
}
