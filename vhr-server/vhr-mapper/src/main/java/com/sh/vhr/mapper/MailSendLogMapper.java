package com.sh.vhr.mapper;

import com.sh.vhr.model.MailSendLog;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MailSendLogMapper{
    List<MailSendLog> getAllMailSendLogs();
}