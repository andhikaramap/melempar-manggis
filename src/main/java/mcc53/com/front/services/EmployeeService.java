/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.services;

import java.util.List;
import mcc53.com.front.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Xvitas
 */
@Service
public class EmployeeService {

    private RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}employee")
    private String baseUrl;

    public List<Employee> getAll() {
        ResponseEntity<List<Employee>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>() {
                });

        return res.getBody();
    }

    public Employee createEmployee(Employee employee) {
        ResponseEntity<Employee> emp = restTemplate
                .postForEntity(baseUrl, employee, Employee.class);
        return emp.getBody();
    }

    public String deleteEmployee(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, Employee.class);
        return "done";
    }

    public Employee getEmployeeById(Integer id) {
        ResponseEntity<Employee> dept = restTemplate.
                getForEntity(baseUrl + "/" + id, Employee.class);
        return dept.getBody();
    }

    public String updateEmployee(Integer id, Employee employee) {
        restTemplate.put(baseUrl + "/" + id, employee, Employee.class);

        return "done";
    }
}
