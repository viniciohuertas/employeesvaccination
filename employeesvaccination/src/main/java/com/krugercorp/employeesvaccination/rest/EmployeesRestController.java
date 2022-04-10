package com.krugercorp.employeesvaccination.rest;

import com.krugercorp.employeesvaccination.commons.bo.CommonBO;
import com.krugercorp.employeesvaccination.commons.bo.ValidationsBO;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.exception.CustomValidationException;
import com.krugercorp.employeesvaccination.commons.request.EmployeePatchReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePostReq;
import com.krugercorp.employeesvaccination.commons.request.EmployeePutReq;
import com.krugercorp.employeesvaccination.commons.response.InfoResponse;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import com.krugercorp.employeesvaccination.entity.Employee;
import com.krugercorp.employeesvaccination.service.EmployeesService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommonsLog(topic = "employeesRestController")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class EmployeesRestController {

    private Map<String, Object> response = null;
    private InfoResponse infoResponse;
    private Employee employee;
    private List<Employee> employees;
    
    private CommonBO commonBO;
    private ValidationsBO validationsBO;
    private final EmployeesService employeesService;

    @Autowired
    public EmployeesRestController(CommonBO commonBO, ValidationsBO validationsBO, EmployeesService employeesService) {
        this.commonBO = commonBO;
        this.validationsBO = validationsBO;
        this.employeesService = employeesService;
    }

    @PostMapping("employees")
    public ResponseEntity<?> postEmployees(@Valid @RequestBody EmployeePostReq employeePostReq, BindingResult result, @RequestHeader Map<String, String> headers) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        if (result.hasErrors()) {
            this.infoResponse = this.commonBO.fillInfo(result);
            this.response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            this.validationsBO.employeePostValidation(employeePostReq.getIdentification());
            this.employee = this.employeesService.postEmployees(employeePostReq);
            response.put(Constants.Messages.EMPLOYEE, this.employee);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("employees/{id}")
    public ResponseEntity<?> putEmployees(@PathVariable Integer id, @Valid @RequestBody EmployeePutReq employeePutReq, BindingResult result, @RequestHeader Map<String, String> headers) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        if (result.hasErrors()) {
            this.infoResponse = this.commonBO.fillInfo(result);
            this.response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            this.validationsBO.employeePostValidation(employeePutReq.getIdentification());
            this.employee = this.employeesService.putEmployee(id, employeePutReq);
            response.put(Constants.Messages.EMPLOYEE, this.employee);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("employees/{id}")
    public ResponseEntity<?> patchEmployees(@PathVariable Integer id, @Valid @RequestBody EmployeePatchReq employeePatchReq, BindingResult result, @RequestHeader Map<String, String> headers) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        if (result.hasErrors()) {
            this.infoResponse = this.commonBO.fillInfo(result);
            this.response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            this.employee = this.employeesService.patchEmployee(id, employeePatchReq);
            response.put(Constants.Messages.EMPLOYEES, this.employee);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);  
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<?> getEmployeesById(@PathVariable Integer id) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        try {
            this.employee = this.employeesService.getEmployee(id);
            response.put(Constants.Messages.EMPLOYEE, this.employee);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("employees/")
    public ResponseEntity<?> getEmployees() {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        try {
            this.employees = this.employeesService.getEmployees();
            response.put(Constants.Messages.EMPLOYEES, this.employees);
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);            
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @DeleteMapping("employees/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id) {
        this.response = new HashMap<>();
        this.infoResponse = new InfoResponse();
        try {
            this.employeesService.deleteEmployee(id);
            response.put(Constants.Messages.EMPLOYEE, "Registro eliminado correctamente");
        } catch (DataAccessException e) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CustomValidationException cvex) {
            infoResponse = this.commonBO.fillInfo(cvex.getCode(), cvex.getMessage());
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            infoResponse = this.commonBO.fillInfo(EnumResponse.ERROR_DB);
            response.put(Constants.Messages.INFO_RESPONSE, infoResponse);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
