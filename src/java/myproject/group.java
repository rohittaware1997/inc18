package myproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author warrior-of-light
 */
public class group extends HttpServlet {
public static PrintWriter out;
ConnectDatabase cd=new ConnectDatabase();
PreparedStatement stmt;
Connection  conn = ConnectDatabase.getConnection();

@SuppressWarnings("empty-statement")
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
  out = response.getWriter();
  int pid = Integer.parseInt(request.getParameter("pid").trim());
  int pdid;
  String name1 = request.getParameter("name1");
  String name2 = request.getParameter("name2");
  String name3 = request.getParameter("name3");
  String name4 = request.getParameter("name4");
  String name5 = request.getParameter("name5");
  String title = request.getParameter("title");
   String clg = request.getParameter("clg");
   
  String[] value=request.getParameterValues("rb");
    char[] temp = value[0].toCharArray();;
    out.println(temp[2]);
    pdid  = Character.getNumericValue(temp[2]); 
  
   String q="";   
  q+= "insert into groups values(?,?,?,?,?,?,?,?,?);";
   stmt=conn.prepareStatement(q);
   stmt.setInt(1,pid);
   stmt.setInt(2,pdid);
   stmt.setString(3,name1);
   stmt.setString(4,name2);
   stmt.setString(5,name3);
   stmt.setString(6,name4);
   stmt.setString(7,name5);
   stmt.setString(8,clg);
   stmt.setString(9,title);
   stmt.execute();
   
  response.sendRedirect("index.html");
    
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(judge.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(judge.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
