package concerttours.controller;

import concerttours.data.ProducerData;
import concerttours.facades.ProducerFacade;
import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producers")
public class ProducersController {

    private final CatalogVersionService catalogVersionService;
    private final ProducerFacade producerFacade;

    private static final String CATALOG_ID = "Default";
    private static final String CATALOG_VERSION_NAME = "Online";

    public ProducersController(CatalogVersionService catalogVersionService, ProducerFacade producerService) {
        this.catalogVersionService = catalogVersionService;
        this.producerFacade = producerService;
    }

    @GetMapping("/{name}")
    public String showProducer(@PathVariable String name, Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);

        ProducerData producerData = producerFacade.getProducer(name);
        model.addAttribute("producer", producerData);

        return "producer";
    }

    @GetMapping
    public String showProducers(Model model) {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);

        model.addAttribute("producers", producerFacade.getProducers());
        return "producers";
    }
}
