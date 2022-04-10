package com.krugercorp.employeesvaccination.commons.response;

import com.krugercorp.employeesvaccination.entity.Employee;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePostRes  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Employee employee;
}
