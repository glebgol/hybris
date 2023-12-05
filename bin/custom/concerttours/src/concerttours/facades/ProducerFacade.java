package concerttours.facades;

import concerttours.data.ProducerData;

import java.util.List;

public interface ProducerFacade {
    List<ProducerData> getProducers();
    ProducerData getProducer(String name);
}
