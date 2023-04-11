package ru.clevertec.checkrunnerservlets.controller.servlet.servletProduct;

import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryProductImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/single_product")
public class ServletSingleProduct extends HttpServlet {

    private final Repository<Product, Long> repositoryProduct = new RepositoryProductImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("product", repositoryProduct.find(id));
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/single_product.jsp");
        dispatcher.forward(request, response);
    }
}
