package ru.clevertec.checkrunnerservlets.controller.servlet.servletProduct;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryProductImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/delete_product")
public class ServletDeleteProduct extends HttpServlet {

    private final Repository<Product, Long> repositoryProduct = new RepositoryProductImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("idProduct"));

        repositoryProduct.delete(id);

        response.sendRedirect(request.getContextPath() + "/all_products");
    }
}
