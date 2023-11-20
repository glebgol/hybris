package org.training.web.controllers;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.service.TrainingProductService;
@Controller
public class TrainingProductController {
    private final CatalogVersionService catalogVersionService;

    private final TrainingProductService trainingProductService;

    public TrainingProductController(CatalogVersionService catalogVersionService,
                                     @Qualifier("productService") TrainingProductService trainingProductService) {
        this.catalogVersionService = catalogVersionService;
        this.trainingProductService = trainingProductService;
    }

    @GetMapping
    public String getProduct(@RequestParam String code, @RequestParam String name, Model model) {
        catalogVersionService.setSessionCatalogVersion("concertToursProductCatalog", "Online");
        ProductModel product = null;

        if (code != null && name != null) {
            product = trainingProductService.getProductForCode(code, name);
        }

        model.addAttribute("product", product);
        return "trainingProduct";
    }
}
