package com.sh.vhr.service;

import com.sh.vhr.mapper.EmployeeMapper;
import com.sh.vhr.model.Employee;
import com.sh.vhr.model.RespPageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    /**
     * 计算合同期限
     *
     * @param employee
     */
    public void calculateContractTerm(Employee employee) {
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12
                + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));

        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
    }

    public RespPageBean getEmployeeByPage(Integer pageNum, Integer pageSize, String keyword) {
        Integer offset = null;
        if (pageNum != null && pageSize != null) {
            offset = (pageNum - 1) * pageSize;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(offset, pageSize, keyword);
        Long total = employeeMapper.getTotal(keyword);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public Integer addEmployee(Employee employee) {
        calculateContractTerm(employee);
        Integer result = employeeMapper.addEmployee(employee);
        if (result == 1) {
            Employee emp = employeeMapper.getEmployeeById(employee.getId());
            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
                if (ack) {
                    logger.info("消息已被消费！");
                } else {
                    logger.info("消息未被消费！");
                }
            });
            rabbitTemplate.convertAndSend("vhr.mail.welcome", emp);
        }
        return result;
    }

    public Integer getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }

    public Integer deleteEmployee(Integer id) {
        return employeeMapper.deleteEmployee(id);
    }

    public Integer updateEmployee(Employee employee) {
        calculateContractTerm(employee);
        return employeeMapper.updateEmployee(employee);
    }

    public int addEmployees(List<Employee> employeeList) {
        return employeeMapper.addEmployees(employeeList);
    }

    public RespPageBean getEmployeeByPageSuper(Integer pageNum, Integer pageSize, Employee employee, Date[] beginDateScope) {
        Integer offset = null;
        if (pageNum != null && pageSize != null) {
            offset = (pageNum - 1) * pageSize;
        }
        List<Employee> data = employeeMapper.getEmployeeByPageSuper(offset, pageSize, employee, beginDateScope);
        Long total = employeeMapper.getTotalSuper(employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }

    public RespPageBean getEmployeeWithSalaryByPage(Integer pageNum, Integer pageSize) {
        Integer offset = null;
        if (pageNum != null && pageSize != null) {
            offset = (pageNum - 1) * pageSize;
        }
        List<Employee> data = employeeMapper.getEmployeeWithSalaryByPage(offset, pageSize);
        Long total = employeeMapper.getTotal("");
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }
}
