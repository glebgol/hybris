package concerttours.facades.impl;

import java.lang.InterruptedException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import concerttours.data.BandData;
import concerttours.facades.BandFacade;
import de.hybris.platform.core.Registry;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.*;

@IntegrationTest
public class DefaultBandFacadeIntegrationWithPropertiesTest extends ServicelayerTransactionalTest
{
    @Resource
    private BandFacade bandFacade;

    @Before
    public void setUp() throws Exception
    {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        catch (InterruptedException exc) {}
    }

    @Test
    public void testProperties() throws Exception
    {
        createCoreData();
        importCsv("/impex/essentialdata-mediaformats.impex", "UTF-8");
        importCsv("/impex/concerttours-bands.impex", "UTF-8");
        importCsv("/impex/withlocalization/concerttours-yBandTour.impex", "UTF-8");

        List<BandData> bands = bandFacade.getBands();
        assertFalse(bands.isEmpty());
        assertEquals(DefaultBandFacade.BAND_LIST_FORMAT, "band.list.format.name");
    }

    @After
    public void teardown() {

    }
}
