package ru.clevertec.checkrunnerservlets.controller.servlet;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/all_products")
public class ServletAllProducts extends HttpServlet {
    private final static String INDEX = "/WEB-INF/view/all_products.jsp";
    private final Repository<Product> productRepository = new ProductImpl(dataSource);
    private List<Product> products;

    @Override
    public void init() throws ServletException {
        super.init();
        products = new CopyOnWriteArrayList<>();
        products.add(new Product(1L, "product1", 10d, false));
        products.add(new Product(2L, "product2", 20d, true));
        products.add(new Product(3L, "product3", 30d, false));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("products", productRepository.findAll());

        request.getRequestDispatcher(INDEX).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
