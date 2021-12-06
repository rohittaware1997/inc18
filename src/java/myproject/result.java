package myproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class result extends HttpServlet {
public static PrintWriter out;
ConnectDatabase cd=new ConnectDatabase();
PreparedStatement stmt;
Connection  conn = ConnectDatabase.getConnection();

@SuppressWarnings("empty-statement")
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
  out = response.getWriter(); 
   int[] max_marks = new int[500];
 
 calculate_max_marks(max_marks);
 calculate_percentile(max_marks);
}

void calculate_max_marks(int[] max_marks) throws SQLException {
    Statement stmt1 = conn.createStatement();
  
  String qu = "select jid,max_marks from judges";
  ResultSet rs = stmt1.executeQuery(qu);
  Arrays.fill(max_marks, -1);
  while (rs.next()) {
  max_marks[rs.getInt(1)] = rs.getInt(2); 
  out.println(rs.getInt(1) + " - " + rs.getInt(2));
  }
}

public void calculate_percentile(int[] max_marks) throws SQLException {
    
    
    int total_project=0;
 
     Statement stmt1 = conn.createStatement();
    String qu = "select count(distinct pid,pdid) from marks";
    ResultSet rs = stmt1.executeQuery(qu);
    if(rs.next()) total_project = rs.getInt(1);
    
    float[][] scores = new float[total_project][4];
    for (float[] row: scores)
    Arrays.fill(row, 0);
    
    int index=0;int get_index;
    HashMap<Integer, Integer> map = new HashMap<>();
 
   
    Statement smt;
        smt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                   ResultSet.CONCUR_UPDATABLE);
       
         qu = "select * from marks";
  ResultSet uprs = smt.executeQuery(qu);
 int jid,marks,pid,pdid, unique_id;
       float percentile;
       
        while (uprs.next()) {
            jid = uprs.getInt("jid");
            pid = uprs.getInt("pid");
            pdid = uprs.getInt("pdid");
            marks = uprs.getInt("marks");
            percentile = 100* (float)marks/(float)max_marks[jid];
            unique_id = pdid + pid*10;
            
            uprs.updateFloat( "percentile", percentile);
            uprs.updateRow();
            
            if(!map.containsKey(unique_id)) {
                map.put(unique_id,index);
                scores[index][0] = percentile;
                index++;
            }
            else {
                get_index = map.get(unique_id);
                float[] a = new float[4];
                a[0] = scores[get_index][0];
                a[1] = scores[get_index][1];
                a[2] = scores[get_index][2];
                a[3] = percentile;
                
                  Arrays.sort(a);
                scores[get_index][0] = a[3];
                scores[get_index][1] = a[2];
                scores[get_index][2] = a[1];
                  
            }
            
            
         }
        print(map);
            
            int i,j;
            for(i=0;i<total_project; i++) {
                for(j=0;j<3;j++) out.println(scores[i][j] + "  ");
                out.println("\n");
            }
        
            
}

public static void print(Map<Integer, Integer> map) 
    {
        if (map.isEmpty()) 
        {
            out.println("map is empty");
        }
         
        else
        {

        Set< Map.Entry< Integer,Integer> > st = map.entrySet();   
 
       for (Map.Entry< Integer,Integer> me:st)
       {
           out.print(me.getKey()+":");
           out.println(me.getValue());
                               

       }    
        }
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
