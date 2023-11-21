package org.training.facade;

import org.training.data.ContactRequestData;

public interface ContactRequestFacade {
    ContactRequestData get(String sender);
}
