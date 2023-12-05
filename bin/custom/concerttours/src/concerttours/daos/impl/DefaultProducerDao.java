package concerttours.daos.impl;

import concerttours.daos.ProducerDAO;
import concerttours.model.ProducerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultProducerDao implements ProducerDAO {

    private final FlexibleSearchService flexibleSearchService;

    private static final String SELECT_PRODUCERS = "SELECT {p:pk} FROM {Producer AS p}";
    private static final String SELECT_PRODUCER_BY_NAME = "SELECT {p:pk} FROM {Producer AS p} WHERE {p:name} = ?name";

    public DefaultProducerDao(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public ProducerModel getProducerByName(String name) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_PRODUCER_BY_NAME);
        query.addQueryParameter("name", name);
        return flexibleSearchService.<ProducerModel>search(query).getResult().get(0);
    }

    @Override
    public List<ProducerModel> getProducers() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_PRODUCERS);
        return flexibleSearchService.<ProducerModel> search(query).getResult();
    }
}
