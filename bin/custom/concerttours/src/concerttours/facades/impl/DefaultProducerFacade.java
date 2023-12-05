package concerttours.facades.impl;

import concerttours.data.ProducerData;
import concerttours.facades.ProducerFacade;
import concerttours.model.ProducerModel;
import concerttours.service.ProducerService;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultProducerFacade implements ProducerFacade {
    private final ProducerService producerService;

    public DefaultProducerFacade(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public List<ProducerData> getProducers() {
        return producerService.getProducers().stream()
                .map(DefaultProducerFacade::mapToProducerData)
                .collect(Collectors.toList());
    }

    @Override
    public ProducerData getProducer(String name) {
        return mapToProducerData(producerService.getProducerByName(name));
    }

    private static ProducerData mapToProducerData(ProducerModel producerModel) {
        ProducerData producerData = new ProducerData();
        producerData.setName(producerModel.getName());
        producerData.setTours(producerModel.getProducts().stream()
                .map(ProductModel::getName)
                .collect(Collectors.toSet()));
        return producerData;
    }
}
