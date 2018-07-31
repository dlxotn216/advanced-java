package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

/**
 *
 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample#bufferedWriter()
 java.nio.file.FileAlreadyExistsException: C:\\user\\taesu\\test\\createFile.txt
 at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:81)
 at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
 at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
 at sun.nio.fs.WindowsFileSystemProvider.newByteChannel(WindowsFileSystemProvider.java:230)
 at java.nio.file.spi.FileSystemProvider.newOutputStream(FileSystemProvider.java:434)
 at java.nio.file.Files.newOutputStream(Files.java:216)
 at java.nio.file.Files.newBufferedWriter(Files.java:2860)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample.bufferedWriter(BufferStreamSample.java:32)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample$$FastClassBySpringCGLIB$$3a8464e1.invoke(<generated>)
 at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
 at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
 at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)
 at me.advanced.java.config.aop.ChapterLoggerAspect.around(ChapterLoggerAspect.java:28)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample$$EnhancerBySpringCGLIB$$9950ab0c.bufferedWriter(<generated>)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at me.advanced.java.config.aop.ChapterRunnerAspect.lambda$afterReturning$2(ChapterRunnerAspect.java:54)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
 at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
 at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
 at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
 at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
 at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
 at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
 at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
 at me.advanced.java.config.aop.ChapterRunnerAspect.afterReturning(ChapterRunnerAspect.java:52)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample$$EnhancerBySpringCGLIB$$9950ab0c.run(<generated>)
 at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:788)
 at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:778)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:335)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1255)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1243)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.Ch04Application.main(Ch04Application.java:13)
 ===========================End   of BufferStreamSample.bufferedWriter()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.BufferStreamSample#bufferedReader()
 For testing 테스트Who am i
 Who am i
 Taesu
 Why so serious
 I am a boy
 You are a girl
 Let's dance
 ===========================End   of BufferStreamSample.bufferedReader()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.CheckMethod#isHidden()
 false
 ===========================End   of CheckMethod.isHidden()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.CheckMethod#isSameFile()
 C:\\Users\\taesu\\Desktop\\advanced-java\\target\\classes\\temp\\test.txt
 C:\\Users\\taesu\\Desktop\\advanced-java\\target\\classes\\temp\\test.txt
 isSameFile ? true
 ===========================End   of CheckMethod.isSameFile()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.CheckMethod#checkAccessbility()
 isReadable true
 isWritable true
 isExecutable true

 일반 파일 => 심볼링크, 디렉터리가아닌것
 isRegularFile true
 ===========================End   of CheckMethod.checkAccessbility()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.CheckMethod#checkFileOrDirectoryExist()
 파일이 존재
 ===========================End   of CheckMethod.checkFileOrDirectoryExist()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy#delete()
 java.nio.file.DirectoryNotEmptyException: C:\\Users\\taesu\\test
 at sun.nio.fs.WindowsFileSystemProvider.implDelete(WindowsFileSystemProvider.java:266)
 at sun.nio.fs.AbstractFileSystemProvider.delete(AbstractFileSystemProvider.java:103)
 at java.nio.file.Files.delete(Files.java:1126)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy.delete(DeleteMoveCopy.java:27)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy$$FastClassBySpringCGLIB$$d1af1c68.invoke(<generated>)
 at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
 at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
 at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)
 at me.advanced.java.config.aop.ChapterLoggerAspect.around(ChapterLoggerAspect.java:28)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy$$EnhancerBySpringCGLIB$$aec03e01.delete(<generated>)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at me.advanced.java.config.aop.ChapterRunnerAspect.lambda$afterReturning$2(ChapterRunnerAspect.java:54)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
 at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
 at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
 at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
 at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
 at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
 at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
 at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
 at me.advanced.java.config.aop.ChapterRunnerAspect.afterReturning(ChapterRunnerAspect.java:52)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy$$EnhancerBySpringCGLIB$$aec03e01.run(<generated>)
 at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:788)
 at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:778)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:335)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1255)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1243)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.Ch04Application.main(Ch04Application.java:13)
 ===========================End   of DeleteMoveCopy.delete()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy#copy()
 ===========================End   of DeleteMoveCopy.copy()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.DeleteMoveCopy#move()
 ===========================End   of DeleteMoveCopy.move()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#createDirectory()
 디렉토리 생성 중 예외 발생 C:\\Users\\taesu\\test
 ===========================End   of ManageDirectory.createDirectory()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#createAllDirectories()
 ===========================End   of ManageDirectory.createAllDirectories()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#readAllRootDirectories()
 C:\\
 D:\\
 E:\\
 ===========================End   of ManageDirectory.readAllRootDirectories()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#applyGlobPattern()
 eula.1028.txt
 eula.1031.txt
 eula.1033.txt
 eula.1036.txt
 eula.1040.txt
 eula.1041.txt
 eula.1042.txt
 eula.2052.txt
 eula.3082.txt
 hiberfil.sys
 install.res.1028.dll
 install.res.1031.dll
 install.res.1033.dll
 install.res.1036.dll
 install.res.1040.dll
 install.res.1041.dll
 install.res.1042.dll
 install.res.2052.dll
 install.res.3082.dll
 pagefile.sys
 swapfile.sys
 ===========================End   of ManageDirectory.applyGlobPattern()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#searchDirectory()
 $RECYCLE.BIN
 $SysReset
 apache-tomcat-8.0.44
 app
 backup
 bootmgr
 Documents and Settings
 eula.1028.txt
 eula.1031.txt
 eula.1033.txt
 eula.1036.txt
 eula.1040.txt
 eula.1041.txt
 eula.1042.txt
 eula.2052.txt
 eula.3082.txt
 globdata.ini
 hiberfil.sys
 install.exe
 install.ini
 install.res.1028.dll
 install.res.1031.dll
 install.res.1033.dll
 install.res.1036.dll
 install.res.1040.dll
 install.res.1041.dll
 install.res.1042.dll
 install.res.2052.dll
 install.res.3082.dll
 pagefile.sys
 PerfLogs
 Program Files
 Program Files (x86)
 ProgramData
 Recovery
 Riot Games
 root
 swapfile.sys
 System Volume Information
 Temp
 user
 Users
 vcredist.bmp
 VC_RED.cab
 VC_RED.MSI
 Windows
 ===========================End   of ManageDirectory.searchDirectory()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageDirectory#applyFilter()
 Directory인 것만 골라내는 필터
 $RECYCLE.BIN
 $SysReset
 apache-tomcat-8.0.44
 app
 backup
 Documents and Settings
 PerfLogs
 Program Files
 Program Files (x86)
 ProgramData
 Recovery
 Riot Games
 root
 System Volume Information
 Temp
 user
 Users
 Windows

 100KB 보다 큰 파일/디렉토리를 필터
 bootmgr
 hiberfil.sys
 install.exe
 pagefile.sys
 swapfile.sys
 VC_RED.cab
 VC_RED.MSI
 ===========================End   of ManageDirectory.applyFilter()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile#createFile()
 java.nio.file.FileAlreadyExistsException: C:\\user\\taesu\\test\\createFile.txt
 at sun.nio.fs.WindowsException.translateToIOException(WindowsException.java:81)
 at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:97)
 at sun.nio.fs.WindowsException.rethrowAsIOException(WindowsException.java:102)
 at sun.nio.fs.WindowsFileSystemProvider.newByteChannel(WindowsFileSystemProvider.java:230)
 at java.nio.file.Files.newByteChannel(Files.java:361)
 at java.nio.file.Files.createFile(Files.java:632)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile.createFile(ManageFile.java:33)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile$$FastClassBySpringCGLIB$$7134beb8.invoke(<generated>)
 at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
 at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:746)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
 at org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint.proceed(MethodInvocationProceedingJoinPoint.java:88)
 at me.advanced.java.config.aop.ChapterLoggerAspect.around(ChapterLoggerAspect.java:28)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile$$EnhancerBySpringCGLIB$$758116f1.createFile(<generated>)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at me.advanced.java.config.aop.ChapterRunnerAspect.lambda$afterReturning$2(ChapterRunnerAspect.java:54)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
 at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
 at java.util.Spliterators$ArraySpliterator.forEachRemaining(Spliterators.java:948)
 at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
 at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
 at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
 at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
 at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
 at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
 at me.advanced.java.config.aop.ChapterRunnerAspect.afterReturning(ChapterRunnerAspect.java:52)
 at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 at java.lang.reflect.Method.invoke(Method.java:498)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethodWithGivenArgs(AbstractAspectJAdvice.java:644)
 at org.springframework.aop.aspectj.AbstractAspectJAdvice.invokeAdviceMethod(AbstractAspectJAdvice.java:633)
 at org.springframework.aop.aspectj.AspectJAroundAdvice.invoke(AspectJAroundAdvice.java:70)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:174)
 at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)
 at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
 at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile$$EnhancerBySpringCGLIB$$758116f1.run(<generated>)
 at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:788)
 at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:778)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:335)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1255)
 at org.springframework.boot.SpringApplication.run(SpringApplication.java:1243)
 at me.advanced.java.java7.nio2.file_networking.ch04.file_directory.Ch04Application.main(Ch04Application.java:13)
 ===========================End   of ManageFile.createFile()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile#readAllLines()
 For testing 테스트Who am i
 Who am i
 Taesu
 Why so serious
 I am a boy
 You are a girl
 Let's dance
 ===========================End   of ManageFile.readAllLines()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile#writeSmallFileRunnGroup()


 ===========================End   of ManageFile.writeSmallFileRunnGroup()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.ManageFile#readSmallFile()
 For testing 테스트Who am i
 Who am i
 Taesu
 Why so serious
 I am a boy
 You are a girl
 Let's dance

 ===========================End   of ManageFile.readSmallFile()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.TemporaryFileDirectory#종료_후크로_임시디렉토리_삭제()
 ===========================End   of TemporaryFileDirectory.종료_후크로_임시디렉토리_삭제()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.TemporaryFileDirectory#deleteOnClose()
 ===========================End   of TemporaryFileDirectory.deleteOnClose()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.TemporaryFileDirectory#createTempDir()
 tempDir with prefix :C:\\Users\\taesu\\AppData\\Local\\Temp\\prefix3955679399960013232
 tempDir without prefix :C:\\Users\\taesu\\AppData\\Local\\Temp\\271031834318765120
 basic temp dir is :C:\\Users\\taesu\\AppData\\Local\\Temp\\
 ===========================End   of TemporaryFileDirectory.createTempDir()==========================


 @see me.advanced.java.java7.nio2.file_networking.ch04.file_directory.TemporaryFileDirectory#tempFile()
 tempFile with prefix suffix :C:\\Users\\taesu\\AppData\\Local\\Temp\\prefix3233823821870739113suffix
 tempFile with prefix suffix :C:\\Users\\taesu\\AppData\\Local\\Temp\\3343221424147865276.tmp
 ===========================End   of TemporaryFileDirectory.tempFile()==========================


 */
public class Ch04Result {
}
