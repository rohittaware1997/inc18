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

public class marks extends HttpServlet {
public static PrintWriter out;
ConnectDatabase cd=new ConnectDatabase();
PreparedStatement stmt;
Connection  conn = ConnectDatabase.getConnection();

@SuppressWarnings("empty-statement")
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
  out = response.getWriter();
  int jid = Integer.parseInt(request.getParameter("jid").trim());
  int pid = Integer.parseInt(request.getParameter("pid").trim());
  int marks = Integer.parseInt(request.getParameter("marks").trim());
  
    int pdid=-1;
    String[] value=request.getParameterValues("rb");
    char[] temp = value[0].toCharArray();;
    pdid  = Character.getNumericValue(temp[2]); 
  
  String q="";   
  q+= "insert into marks values(?,?,?,?,?);";
   stmt=conn.prepareStatement(q);
   stmt.setInt(1,jid);
   stmt.setInt(2,pdid);
   stmt.setInt(3,pid);
   stmt.setInt(4,marks);
   stmt.setFloat(5,0);
   stmt.execute();
   
   String q1 = "update judges set max_marks = ? where max_marks < ? and jid = ?;";
   stmt = conn.prepareStatement(q1);
   stmt.setInt(1, marks);
   stmt.setInt(2, marks);
   stmt.setInt(3, jid);
   stmt.execute();
  response.sendRedirect("marks.jsp");
   
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
