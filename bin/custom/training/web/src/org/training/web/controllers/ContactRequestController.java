package org.training.web.controllers;

import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.training.model.ContactRequestModel;
import org.training.service.ContactRequestService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contactRequest")
public class ContactRequestController {

    private final ContactRequestService contactRequestService;
    private final ModelService modelService;

    public ContactRequestController(ContactRequestService contactRequestService, ModelService modelService) {
        this.contactRequestService = contactRequestService;
        this.modelService = modelService;
    }

    @GetMapping
    public String getContactRequest(@RequestParam(required = false) String sender, Model model) {
        ContactRequestModel contactRequest = null;
        if (sender != null && !sender.isBlank()) {
            try {
                contactRequest = contactRequestService.getContactRequest(sender);
            } catch (final UnknownIdentifierException e) {
                return "404";
            }
        }

        model.addAttribute("contactRequest", contactRequest);
        return "contactRequest";
    }

    @PostMapping
    public String addContactRequest(@RequestParam String sender, @RequestParam String newSender,
                                    @RequestParam String newMessage, Model model) {
        ContactRequestModel contactRequest = null;

        if (sender != null && !sender.isBlank()) {
            contactRequest = contactRequestService.getContactRequest(sender);
        }

        if (contactRequest == null) {
            contactRequest = new ContactRequestModel();
            modelService.attach(contactRequest);
        }

        if (newSender != null) {
            contactRequest.setSender(newSender);
        }

        if (newMessage != null) {
            contactRequest.setMessage(newMessage);
        }

        modelService.save(contactRequest);
        model.addAttribute("contactRequest", contactRequest);
        return "contactRequest";
    }
}
