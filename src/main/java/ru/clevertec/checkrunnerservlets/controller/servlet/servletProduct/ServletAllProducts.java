package ru.clevertec.checkrunnerservlets.controller.servlet.servletProduct;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryProductImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/all_products")
public class ServletAllProducts extends HttpServlet {

    private final Repository<Product, Long> repositoryProduct = new RepositoryProductImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", repositoryProduct.findAll());
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/all_products.jsp");
        dispatcher.forward(request, response);

    }
}
