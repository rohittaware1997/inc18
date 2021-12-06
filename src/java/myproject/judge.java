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
public class judge extends HttpServlet {
public static PrintWriter out;
ConnectDatabase cd=new ConnectDatabase();
PreparedStatement stmt;
Connection  conn = ConnectDatabase.getConnection();

protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
  out = response.getWriter();
  int jid = Integer.parseInt(request.getParameter("jid").trim());
  String jname = request.getParameter("jname");
  String jemail = request.getParameter("jemail");
  String jcontact = request.getParameter("jcontact");

  String q="";   
  q+= "insert into judges values(?,?,?,?,?);";
   stmt=conn.prepareStatement(q);
   stmt.setInt(1,jid);
   stmt.setString(2,jname);
   stmt.setString(3,jemail);
   stmt.setString(4,jcontact);
   stmt.setInt(5,0);
   stmt.execute();
   /*
   
  if(request.getParameter("cb1") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,1);
     stmt.execute();
  } 
  if(request.getParameter("cb2") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,2);
     stmt.execute();
  } 
  if(request.getParameter("cb3") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,3);
     stmt.execute();
  } 
  if(request.getParameter("cb4") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,4);
     stmt.execute();
  } 
  if(request.getParameter("cb5") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,5);
     stmt.execute();
  } 
  if(request.getParameter("cb6") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,6);
     stmt.execute();
  } 
  if(request.getParameter("cb7") != null) {
     q="";
     q+= "insert into doi values(?,?);";
     stmt=conn.prepareStatement(q);
     stmt.setInt(1,jid);
     stmt.setInt(2,7);
     stmt.execute();
  } 
  */
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
