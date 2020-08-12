package exibir;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProdutoDao;
import model.Produto;

public class ExibirProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
         * resp.setContentType("text/html"); resp.setCharacterEncoding("UTF-8");
         * 
         * PrintWriter saida = resp.getWriter();
         * 
         * String nome = req.getParameter("nome");
         * 
         * saida.println("<html>"); saida.println("<body>");
         * saida.println("<p>Teste de Servlet</p>"); saida.println("</body>");
         * saida.println("</html>");
         * 
         * saida.flush(); saida.close();
         * 
         */

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();
        try {
            ProdutoDao dao = new ProdutoDao();
            List<Produto> lstProd = dao.pesquisar(Integer.parseInt(req.getParameter("id")));             

            for (Produto p : lstProd) {
                String produtoJsonStr = new Gson().toJson(p);
                saida.println(produtoJsonStr);
            }

        } catch (SQLException e) {
            saida.println("<p>Erro " + e.getMessage() + "</p>");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("<p>Erro " + e.getMessage() + "</p>");
        } catch (Exception e) {
            e.printStackTrace();
            saida.println("<p>Erro " + e.getMessage() + "</p>");
        }
        
        saida.flush();
        saida.close();
    }
    
}