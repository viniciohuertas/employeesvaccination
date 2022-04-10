package com.krugercorp.employeesvaccination.commons.bo;

import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.response.InfoResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommonBO {

   /* @Autowired
    private JWTService jwtService;

    public String obtenerUsuarioToken(Map<String, String> headers) {
        return jwtService.getUsername(headers.get("Authorization"));
    }*/

    public InfoResponse fillInfo(EnumResponse enumResponse) {
        InfoResponse infoResponse = new InfoResponse();
        infoResponse.setCode(enumResponse.getCode());
        infoResponse.setMessage(enumResponse.getMessage());
        return infoResponse;
    }

    public InfoResponse fillInfo(String Code, String mensaje) {
        InfoResponse infoResponse = new InfoResponse();
        infoResponse.setCode(Optional.ofNullable(Code).orElse(""));
        infoResponse.setMessage(Optional.ofNullable(mensaje).orElse(""));
        return infoResponse;
    }

    public InfoResponse fillInfo(BindingResult result) {
        InfoResponse infoResponse = new InfoResponse();
        StringBuilder sb = new StringBuilder();
        sb.append(EnumResponse.VALIDACIONES_CORREGIR.getMessage());
        sb.append(" ");

        List<String> errores = result.getFieldErrors().stream()
                .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.toList());
        sb.append(errores);

        infoResponse.setCode(EnumResponse.INCORRECTO.getCode());
        infoResponse.setMessage(sb.toString());
        return infoResponse;
    }

}
