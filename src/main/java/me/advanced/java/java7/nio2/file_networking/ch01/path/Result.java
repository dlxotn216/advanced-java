package me.advanced.java.java7.nio2.file_networking.ch01.path;

/**
 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#toFile()
 C:\\users\\taesu\\desktop
 true
 true
 ===========================End   of ConvertPathSample.toFile()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#toRealPath()
 C:\\Users\\taesu\\Desktop
 ===========================End   of ConvertPathSample.toRealPath()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#toURI()
 file:///C:/users/taesu/desktop/
 ===========================End   of ConvertPathSample.toURI()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#두_위치_사이_경로()
 users\\taesu\\data.txt
 ===========================End   of ConvertPathSample.두_위치_사이_경로()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#relativePathToAbstractPath()
 relative path users\\taesu\\desktop
 abstract path C:\\Users\\taesu\\Desktop\\advanced-java\\users\\taesu\\desktop
 ===========================End   of ConvertPathSample.relativePathToAbstractPath()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#조합하기()
 C:\\users\\taesu\\data.txt
 C:\\users\\taesu\\bmp.dat
 Target file is C:\\users\\taesu\\data.txt
 동일위치의 형제 찾기 C:\\users\\taesu\\brohter.txt
 ===========================End   of ConvertPathSample.조합하기()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.ConvertPathSample#convertToString()
 C:\\users\\taesu\\desktop
 ===========================End   of ConvertPathSample.convertToString()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CoparePathSample#compareByIsSampleFile()
 C:\\users\\taesu\\test
 \\users\\taesu\\test
 파일이 존재하지 않음

 C:\\users\\taesu
 C:\\users\\taesu
 true
 startWith 'C:'? false
 endWith 'taesu'? true
 ===========================End   of CoparePathSample.compareByIsSampleFile()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CoparePathSample#comparePath()
 C:\\users\\taesu\\test
 \\users\\taesu\\test
 false
 ===========================End   of CoparePathSample.comparePath()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CreatePathExample#normalize()
 C:\\users\\taesu\\desktop
 C:\\users\\.\\taesu\\desktop
 C:\\users\\.\\taesu\\..\\taesu\\desktop

 Normalize 후
 C:\\users\\taesu\\desktop
 C:\\users\\taesu\\desktop
 C:\\users\\taesu\\desktop
 ===========================End   of CreatePathExample.normalize()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CreatePathExample#abstractPathBasic()
 C:\\users\\taesu\\desktop
 C:\\users\\taesu\\desktop
 C:\\users\\taesu\\desktop
 ===========================End   of CreatePathExample.abstractPathBasic()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CreatePathExample#getPathFromFileSystems()
 Path를 생성하는 일반적 방법
 C:\\users\\taesu\\desktop
 ===========================End   of CreatePathExample.getPathFromFileSystems()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.CreatePathExample#getHomeDirectory()
 C:\\Users\\taesu\\downloads
 ===========================End   of CreatePathExample.getHomeDirectory()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.PathInfoSample#getParent()
 C:\\users\\taesu
 ===========================End   of PathInfoSample.getParent()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.PathInfoSample#getRoot()
 C:\\
 ===========================End   of PathInfoSample.getRoot()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.PathInfoSample#getFileOrDirectoryName()
 file or directory name is desktop
 file or directory name is expcust.dmp
 ===========================End   of PathInfoSample.getFileOrDirectoryName()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch01.path.PathInfoSample#getSubPaths()
 각 요소 출력
 users
 taesu
 desktop

 subPath
 users\\taesu
 ===========================End   of PathInfoSample.getSubPaths()==========================
 */
public class Result {

}