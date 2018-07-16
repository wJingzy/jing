
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

//import tfjy.util.DBConnection;

public class Genti extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		String question_id = request.getParameter("id");
		System.out.println("id:" + question_id);
		
		try {
			////////////
			// do something
			////////////
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from qus ");
			
			String id = "";
			String question = "";
			String opt1= "";
			String opt2 = "";
			String opt3 = "";
			String opt4 = "";
			String answer = "";
			
			int count=0;
			ArrayList<Question> list=new ArrayList<Question>();
			
			while(rs.next()){
				id=rs.getString(1);
				question=rs.getString(2);
				opt1=rs.getString(3);
				opt2=rs.getString(4);
				opt3=rs.getString(5);
				opt4=rs.getString(6);
				answer=rs.getString(7);
				Question q=new Question(id,question,opt1,opt2,opt3,opt4,answer);
				list.add(q);
			}
			db.close();
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
			
			Random r = new Random();
			int j=0;
			int[] ques= {-1,-1,-1,-1};
			while(j<4) {
				int randnumber = r.nextInt(10);
				
				//if not exist 	
				int k=0;
				while(k<4){
					if(randnumber==ques[k])
						break;
					k++;
				}
				if(k==4)
				{
					ques[j]=randnumber;
					System.out.println(randnumber);
					j++;
				}
			}
		
			JSONObject obj1= new JSONObject();
			Question q1=list.get(ques[0]);
			obj1.put("id",q1.id);
			obj1.put("question", q1.question);
			obj1.put("opt1", q1.opt1);
			obj1.put("opt2",q1. opt2);
			obj1.put("opt3", q1.opt3);
			obj1.put("opt4", q1.opt4);
			obj1.put("answer", q1.answer);
			obj.put("question1", obj1);
			
			JSONObject obj2= new JSONObject();
			Question q2=list.get(ques[1]);
			obj2.put("id", q2.id);
			obj2.put("question", q2.question);
			obj2.put("opt1", q2.opt1);
			obj2.put("opt2", q2.opt2);
			obj2.put("opt3", q2.opt3);
			obj2.put("opt4", q2.opt4);
			obj2.put("answer", q2.answer);
			obj.put("question2", obj2);
			
			JSONObject obj3= new JSONObject();
			Question q3=list.get(ques[2]);
			obj3.put("id", q3.id);
			obj3.put("question", q3.question);
			obj3.put("opt1", q3.opt1);
			obj3.put("opt2", q3.opt2);
			obj3.put("opt3", q3.opt3);
			obj3.put("opt4", q3.opt4);
			obj3.put("answer", q3.answer);
			obj.put("question3", obj3);
			
			JSONObject obj4= new JSONObject();
			Question q4=list.get(ques[3]);
			obj4.put("id", q4.id);
			obj4.put("question", q4.question);
			obj4.put("opt1", q4.opt1);
			obj4.put("opt2", q4.opt2);
			obj4.put("opt3", q4.opt3);
			obj4.put("opt4", q4.opt4);
			obj4.put("answer", q4.answer);
			obj.put("question4", obj4);
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	

}
