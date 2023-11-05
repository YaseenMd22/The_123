package com.aa.easyBag.controller;

import com.aa.easyBag.myDTO.BaggageIsScannedResponse;
import com.aa.easyBag.service.BaggageService;
import com.aa.easyBag.model.Baggage;
import com.aa.easyBag.repository.BaggageRepository;
import com.aa.easyBag.service.SMSService;
import com.aa.easyBag.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EasyBagController {

    private final BaggageRepository baggageRepository;

    private final SMSService smsService;

    private final BaggageService baggageService;

    private final EmailService emailService;

    @Autowired
    public EasyBagController(BaggageRepository baggageRepository, SMSService smsService, BaggageService baggageService, EmailService emailService) {

        this.baggageRepository = baggageRepository;
        this.smsService = smsService;
        this.baggageService = baggageService;
        this.emailService = emailService;
    }

    @PostMapping("/scan")
    public ResponseEntity<?> scanBaggage(@RequestBody String barcode) throws MessagingException {
        Optional<Baggage> baggageOptional = baggageRepository.findByBarcode(barcode);

        if (baggageOptional.isPresent()) {
            Baggage baggage = baggageOptional.get();

            String htmlContent = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Baggage Claim Information</title>\n" +
                    "    <style>\n" +
                    "        body { font-family: Arial, sans-serif; line-height: 1.6; }\n" +
                    "        .container { width: 100%; max-width: 600px; margin: 0 auto; background: #f6f6f6; padding: 20px; }\n" +
                    "        .header { background: #0046ad; color: white; padding: 10px; text-align: center; }\n" +
                    "        .content { padding: 20px; }\n" +
                    "        .footer { background: #ddd; padding: 10px; text-align: center; }\n" +
                    "        a { color: #0046ad; }\n" +
                    "        .button { display: inline-block; padding: 10px 20px; margin: 10px 0; background: #0046ad; color: white; text-decoration: none; }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"header\">\n" +
                    "            American Airlines Baggage Services\n" +
                    "        </div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <p>Dear Customer,</p>\n" +
                    "            <p>We trust that your flight with us was a comfortable and enjoyable one.</p>\n" +
                    "            <p>As part of our continued commitment to providing you with a seamless travel experience, we are writing to inform you that your baggage has arrived and is ready for collection.</p>\n" +
                    "            <p>Please proceed to the designated Baggage Claim area and locate Belt 08 to retrieve your items.</p>\n" +
                    "            <p><strong>Baggage Claim Details:</strong><br>\n" +
                    "                - Belt Number: 08 <br>\n" +
                    "                - Bag Number: "+ baggage.getBagNumber() +"<br>\n" +
                    "            </p>\n" +
                    "            <p>If you require assistance or have any concerns regarding your baggage, our customer service representatives at the baggage claim area are at your service.</p>\n" +
                    "            <p>We appreciate your patronage and look forward to serving you again.</p>\n" +
                    "            <p>Best Regards,<br>\n" +
                    "            American Airlines Baggage Services Team</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"footer\">\n" +
                    "            For further assistance, please contact us:<br>\n" +
                    "            Phone: 800-433-7300<br>\n" +
                    "            Website: <a href=\"http://www.aa.com\" target=\"_blank\">www.aa.com</a><br>\n" +
                    "            <a href=\"https://apps.apple.com/us/app/american-airlines/id382698565\" target=\"_blank\">Download on the App Store</a><br>\n" +
                    "            <a href=\"https://play.google.com/store/apps/details?id=com.aa.android\" target=\"_blank\">Get it on Google Play</a><br>\n" +
                    "            <a href=\"[AA Privacy Policy Link]\" target=\"_blank\">Privacy Policy</a><br>\n" +
                    "            © American Airlines, Inc. All Rights Reserved.\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n";
            // Assuming you have a way to retrieve the customer details
            // based on the customer ID that is associated with the baggage.
            Long customerId = baggage.getCustomerId();
            baggage.setScanned(true);
            baggageRepository.save(baggage);
            String smsMessage = "Dear Customer,\n\n"
                    + "We hope you had a pleasant journey with American Airlines. We are pleased to inform you that your baggage is now available for collection at baggage claim.\n\n"
                    + "Please proceed to Belt 08 to retrieve your baggage number" +  baggage.getBagNumber()  +".\n\n"
                    + "We thank you for choosing American Airlines and look forward to serving you again soon.\n\n"
                    + "Warm regards,\n"
                    + "American Airlines Team";
            // Placeholder for actual notification logic:
            smsService.sendSMS("+19402187047", smsMessage);
            emailService.sendHtmlMessage("asmasidmd@gmail.com", "American Airlines Baggage Alert", htmlContent);

            return ResponseEntity.ok("Customer with ID " + customerId + " has been notified.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/scanned-status/{customerId}")
    public ResponseEntity<List<BaggageIsScannedResponse>> getIsScannedStatusByCustomerId(@PathVariable Long customerId) {

        List<BaggageIsScannedResponse> responseList = baggageService.getBaggageIsScannedInfoByCustomerId(customerId);

      return ResponseEntity.ok(responseList);
    }



}
