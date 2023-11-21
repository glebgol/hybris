package org.training.web.controllers;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private final CatalogVersionService catalogVersionService;
    private final ProductService productService;

    public ProductController(CatalogVersionService catalogVersionService, ProductService productService) {
        this.catalogVersionService = catalogVersionService;
        this.productService = productService;
    }

    @GetMapping(value = "/product")
    public String handleRequest(final ModelMap model, @RequestParam(required = false) String code) throws Exception {
        ProductModel product = null;
        catalogVersionService.setSessionCatalogVersion("concertToursProductCatalog", "Online");

        try {
            product = productService.getProductForCode(code);
        } catch (UnknownIdentifierException | IllegalArgumentException e) {
            return "404";
        }

        model.put("product", product);
        return "product";
    }
}
