package me.advanced.java.java8.in.action.ch06.designpattern;

import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by taesu on 2018-07-11.
 */
@Service
public class TemplateMethodPattern implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        TaskUser taskUser = new TaskUser();
        taskUser.setEmail("taesu@crscube.co.kr");
        taskUser.setTaskName("작성");
        taskUser.setUserId("taesu");
        taskUser.setUserName("이태수");

        TaskUser taskUser2 = new TaskUser();
        taskUser2.setEmail("taesu2@crscube.co.kr");
        taskUser2.setTaskName("리뷰");
        taskUser2.setUserId("taesu2");
        taskUser2.setUserName("이태수2");

        MailTemplateBuilder<TaskUser> builder = new TaskAssignMailTemplateBuilder();
        builder.getMails(Arrays.asList(taskUser, taskUser2))
                .forEach(mail -> {
                    System.out.println(mail.getTitle());
                    System.out.println(mail.getContent());
                });

        System.out.println();

        new LambdaMailTemplateBuilder<TaskUser>().getMails(
                Arrays.asList(taskUser, taskUser2),
                source->{
                    Mail mail = new Mail();
                    mail.setTo(source.getEmail());
                    mail.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
                    mail.setCreatedBy(-1L);
                    return mail;
                },
                ()->MailType.TASK,
                (origin, source)-> {
                    origin = origin.replaceAll("#ID#", source.getUserId());
                    origin = origin.replaceAll("#TASKNAME#", source.getTaskName());
                    return origin;
                },
                (origin, source)-> {
                    origin = origin.replaceAll("#ID#", source.getUserId());
                    origin = origin.replaceAll("#NAME#", source.getUserName());
                    origin = origin.replaceAll("#TASKNAME#", source.getTaskName());
                    return origin;
                }
        ).forEach(mail -> {
            System.out.println(mail.getTitle());
            System.out.println(mail.getContent());
        });

    }
}

enum MailType {
    ACCOUNT,
    PASSWORD_RESET,
    TASK;
}

@Data
class Mail {
    private Long mailKey;
    private String title;
    private String content;
    private MailType mailType;
    private String from;
    private String to;
    private Long createdBy;
    private ZonedDateTime createdAt;
}

interface MessageTemplateRepository {
    default String findMessageTemplateTitleByMailType(MailType mailType){
        if(mailType == MailType.TASK){
            return "#ID#님 #TASKNAME# 태스크에 할당되었습니다";
        }
        return "title";
    }
    default String findMessageTemplateContentByMailType(MailType mailType){
        if(mailType == MailType.TASK){
            return "#ID#(#NAME#)님 #TASKNAME# 태스크에 할당되었습니다";
        }
        return "content";
    }
}

abstract class MailTemplateBuilder<T> {
    private MessageTemplateRepository repository = new MessageTemplateRepository() {};

    protected final String fromUser = "crscube@crscube.co.kr";    //may be application property

    protected String getTitleMessageTemplate() {
        return repository.findMessageTemplateTitleByMailType(getMailType());
    }

    protected String getContentMessageTemplate() {
        return repository.findMessageTemplateContentByMailType(getMailType());
    }

    protected List<Mail> getMails(List<T> sources){
        List<Mail> mails = new ArrayList<>();
        sources.forEach(source -> mails.add(getMail(source)));
        return mails;
    }

    protected abstract Mail getMail(T source);

    protected abstract MailType getMailType();

    protected abstract String getTitle(T source);

    protected abstract String getContent(T source);
}

@Data
class TaskUser {
    private String userId;
    private String userName;
    private String email;
    private String taskName;
}

class TaskAssignMailTemplateBuilder extends MailTemplateBuilder<TaskUser> {

    @Override
    protected Mail getMail(TaskUser source) {
        Mail mail = new Mail();
        mail.setTitle(getTitle(source));
        mail.setContent(getContent(source));
        mail.setTo(source.getEmail());
        mail.setFrom(fromUser);
        mail.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        mail.setCreatedBy(-1L);
        mail.setMailType(getMailType());
        //...
        return mail;
    }

    @Override
    protected MailType getMailType() {
        return MailType.TASK;
    }

    /**
     * Message template의 종류에 저장소에서 템플릿을 불러온 후
     * 맞게 값을 치환하여 반환한다
     * @param source Source
     * @return Mail title
     */
    @Override
    protected String getTitle(TaskUser source) {
        String template = super.getTitleMessageTemplate();
        template = template.replaceAll("#ID#", source.getUserId());
        template = template.replaceAll("#TASKNAME#", source.getTaskName());
        return template;
    }

    /**
     *
     * @param source Source
     * @return Mail content
     */
    @Override
    protected String getContent(TaskUser source) {
        String template = super.getContentMessageTemplate();
        template = template.replaceAll("#ID#", source.getUserId());
        template = template.replaceAll("#NAME#", source.getUserName());
        template = template.replaceAll("#TASKNAME#", source.getTaskName());
        return template;
    }
}

class LambdaMailTemplateBuilder<T> {
    protected final String fromUser = "crscube@crscube.co.kr";    //may be application property
    private MessageTemplateRepository repository = new MessageTemplateRepository() {};

    public List<Mail> getMails(List<T> sources,
                               Function<T, Mail> baseMailBuilder,
                               Supplier<MailType> mailTypeSupplier,
                               BiFunction<String, T, String> titleSupplier,
                               BiFunction<String, T, String> contentSupplier){
        List<Mail> mails = new ArrayList<>();
        sources.forEach(source -> mails.add(getMail(source, baseMailBuilder, mailTypeSupplier, titleSupplier, contentSupplier)));
        return mails;
    }

    public Mail getMail(T source,
                        Function<T, Mail> baseMailBuilder,
                        Supplier<MailType> mailTypeSupplier,
                        BiFunction<String, T, String> titleSupplier,
                        BiFunction<String, T, String> contentSupplier){
        Mail mail = baseMailBuilder.apply(source);
        mail.setMailType(mailTypeSupplier.get());
        mail.setFrom(fromUser);
        String title = titleSupplier.apply(repository.findMessageTemplateTitleByMailType(mailTypeSupplier.get()), source);
        String content = contentSupplier.apply(repository.findMessageTemplateContentByMailType(mailTypeSupplier.get()), source);
        mail.setTitle(title);
        mail.setContent(content);
        return mail;
    }
}