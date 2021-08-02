/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.services;

import java.util.List;
import mcc53.com.front.models.Department;
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
public class DepartmentService {

    private RestTemplate restTemplate;

    @Value("${api.baseUrl}department")
    private String baseUrl;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Department> getAll() {
        ResponseEntity<List<Department>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Department>>() {
                });

        return res.getBody();
    }

    public Department createDepartment(Department department) {
        ResponseEntity<Department> dept = restTemplate
                .postForEntity(baseUrl, department, Department.class);
        return dept.getBody();
    }

    public String deleteDepartment(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, Department.class);
        return "done";
    }

    public Department getDepartmentById(Integer id) {
        ResponseEntity<Department> dept = restTemplate.
                getForEntity(baseUrl + "/" + id, Department.class);
        return dept.getBody();
    }

    public String updateDepartment(Integer id, Department department) {
        restTemplate.put(baseUrl + "/" + id, department, Department.class);

        return "done";
    }
}
