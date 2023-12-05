package concerttours.daos;

import concerttours.model.ProducerModel;

import java.util.List;

public interface ProducerDAO {
    ProducerModel getProducerByName(String name);
    List<ProducerModel> getProducers();
}
