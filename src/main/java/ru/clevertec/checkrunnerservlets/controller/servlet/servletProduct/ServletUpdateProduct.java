package ru.clevertec.checkrunnerservlets.controller.servlet.servletProduct;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryProductImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_product")
public class ServletUpdateProduct extends HttpServlet {

    private final Repository<Product, Long> repositoryProduct = new RepositoryProductImpl(ConnectionManager.open());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("idProduct"));
        String name = request.getParameter("nameProduct");
        Double price = Double.valueOf(request.getParameter("priceProduct"));
        Boolean discount = Boolean.valueOf(request.getParameter("discountProduct"));
        Product product = new Product(id, name, price, discount);

        repositoryProduct.update(id, product);

        response.sendRedirect(request.getContextPath() + "/single_product?id=" + id);
    }
}
