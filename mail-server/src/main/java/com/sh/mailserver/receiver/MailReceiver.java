package com.sh.mailserver.receiver;

import com.sh.vhr.model.Employee;
import com.sh.vhr.model.Hr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailReceiver {
    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class.getName());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @RabbitListener(queues = "${mail.welcome.queue}")
    public void receiveMessage(Employee emp) {
        // 收到消息，发送邮件
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
            mmh.setSubject("入职欢迎邮件");

            // 开始配置Thymeleaf邮件模板
            Context context = new Context();
            // 准备邮件模板参数
            Map<String, Object> params = new HashMap<>();
            params.put("nickname", emp.getName());
            params.put("positionName", emp.getPosition().getName());
            params.put("jobLevelName", emp.getJobLevel().getName());
            params.put("departmentName", emp.getDepartment().getName());
            // 给模板传递参数
            context.setVariables(params);
            // 完成模板的渲染
            String content = templateEngine.process("welcome_mail.html", context);
            mmh.setText(content, true);

            mmh.setFrom(from);
            mmh.setSentDate(new Date());
            mmh.setTo(emp.getEmail());
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败：" + e.getMessage());
        }
    }
}
