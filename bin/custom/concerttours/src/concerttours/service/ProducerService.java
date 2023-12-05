package concerttours.service;

import concerttours.model.ProducerModel;

import java.util.List;

public interface ProducerService {
    ProducerModel getProducerByName(String name);
    List<ProducerModel> getProducers();
}
