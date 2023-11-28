package concerttours.daos.impl;

import concerttours.daos.TokenDao;
import concerttours.model.TokenModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

public class DefaultTokenDao implements TokenDao {
    private final FlexibleSearchService flexibleSearchService;

    public DefaultTokenDao(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public TokenModel get() {
        final String queryString = "SELECT {p:" + TokenModel.PK + "} " + "FROM {" + TokenModel._TYPECODE + " AS p} ";
        final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
        return flexibleSearchService.searchUnique(query);
    }
}
