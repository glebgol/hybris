package org.training.facade.impl;

import org.training.data.ContactRequestData;
import org.training.facade.ContactRequestFacade;
import org.training.model.ContactRequestModel;
import org.training.service.ContactRequestService;

public class DefaultContactRequestFacade implements ContactRequestFacade {

    private final ContactRequestService contactRequestService;

    public DefaultContactRequestFacade(ContactRequestService contactRequestService) {
        this.contactRequestService = contactRequestService;
    }

    @Override
    public ContactRequestData get(String sender) {
        if (sender == null) {
            throw new IllegalArgumentException("Sender can't be null!");
        }

        ContactRequestModel contactRequestModel = contactRequestService.getContactRequest(sender);

        if (contactRequestModel == null) {
            return null;
        }

        ContactRequestData contactRequestData = new ContactRequestData();
        contactRequestData.setSender(contactRequestModel.getSender());
        contactRequestData.setMessage(contactRequestModel.getMessage());

        return contactRequestData;
    }
}
