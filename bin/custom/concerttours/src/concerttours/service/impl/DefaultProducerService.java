package concerttours.service.impl;

import concerttours.daos.ProducerDAO;
import concerttours.model.ProducerModel;
import concerttours.service.ProducerService;

import java.util.List;

public class DefaultProducerService implements ProducerService {
    private final ProducerDAO producerDAO;

    public DefaultProducerService(ProducerDAO producerDAO) {
        this.producerDAO = producerDAO;
    }

    @Override
    public ProducerModel getProducerByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }

        return producerDAO.getProducerByName(name);
    }

    @Override
    public List<ProducerModel> getProducers() {
        return producerDAO.getProducers();
    }
}
