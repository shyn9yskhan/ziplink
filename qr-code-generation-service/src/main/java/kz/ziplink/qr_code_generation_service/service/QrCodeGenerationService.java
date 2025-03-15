package kz.ziplink.qr_code_generation_service.service;

public interface QrCodeGenerationService {
    byte[] generateQrCodeFromUsername(String username) throws Exception;
}
