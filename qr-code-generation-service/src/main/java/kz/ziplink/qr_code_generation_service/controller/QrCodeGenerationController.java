package kz.ziplink.qr_code_generation_service.controller;

import kz.ziplink.qr_code_generation_service.service.JwtService;
import kz.ziplink.qr_code_generation_service.service.QrCodeGenerationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrCodeGeneration")
public class QrCodeGenerationController {

    QrCodeGenerationService qrCodeGenerationService;

    public QrCodeGenerationController(QrCodeGenerationService qrCodeGenerationService) {
        this.qrCodeGenerationService = qrCodeGenerationService;
    }

    @GetMapping
    public ResponseEntity<byte[]> generateQrCodeFromUsername(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = JwtService.extractUsername(token);

        if (username == null || username.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            return new ResponseEntity<>(qrCodeGenerationService.generateQrCodeFromUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
