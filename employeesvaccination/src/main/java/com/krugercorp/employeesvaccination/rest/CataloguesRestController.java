package com.krugercorp.employeesvaccination.rest;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog(topic = "cataloguesRestController")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class CataloguesRestController {
}
