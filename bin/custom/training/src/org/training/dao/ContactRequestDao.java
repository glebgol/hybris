package org.training.dao;

import org.training.model.ContactRequestModel;

import java.util.List;
import de.hybris.platform.servicelayer.internal.dao.Dao;

public interface ContactRequestDao extends Dao {
    public List<ContactRequestModel> findBySender(String sender);
}
