package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class MinimalServlet {
	
	private final static String responseAsString = "[\r\n" + 
			"    {\r\n" + 
			"        \"id\": 86,\r\n" + 
			"        \"descricao\": \"PRODUTO 10\"\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"        \"id\": 87,\r\n" + 
			"        \"descricao\": \"PRODUTO 11\"\r\n" + 
			"    }\r\n" + 
			"]";
	
	private final static int PORT = 9999;
	private final static String CONTEXT = "/api/produto";
		
	public static void main(String[] args) throws Exception {
		Server server = new Server(PORT);
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(HelloServlet.class, CONTEXT == null ? "/*" : CONTEXT);
		server.start();
		server.join();
	}

	public static class HelloServlet extends HttpServlet {

		private static final long serialVersionUID = 0l;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(responseAsString);
		}
	}
}
